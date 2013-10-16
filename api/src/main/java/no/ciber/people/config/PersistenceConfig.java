package no.ciber.people.config;

import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * User: Michael Johansen
 * Date: 11.10.13
 * Time: 23:31
 */
@Configuration
@EnableJpaRepositories(basePackages = {"no.ciber.people.repository"})
public class PersistenceConfig {

    private String dbUrl = "jdbc:h2:~/test;AUTO_SERVER=TRUE";
    private String dbUsername = "sa";
    private String dbPassword = "";
    private String dbDriverClassName = "org.h2.Driver";

    @Bean
    @Autowired
    public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emfBean = new LocalContainerEntityManagerFactoryBean();
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        emfBean.setJpaVendorAdapter(jpaVendorAdapter);
        emfBean.setPackagesToScan("no.ciber.people.model");
        emfBean.getJpaPropertyMap().put(AvailableSettings.HBM2DDL_AUTO, "create");
        emfBean.getJpaPropertyMap().put(AvailableSettings.FORMAT_SQL, true);
        emfBean.setDataSource(dataSource);
        emfBean.afterPropertiesSet();

        return emfBean.getObject();
    }

    @Bean
    public DataSource dataSource() throws SQLException {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(dbUrl);
        driverManagerDataSource.setUsername(dbUsername);
        driverManagerDataSource.setPassword(dbPassword);
        driverManagerDataSource.setDriverClassName(dbDriverClassName);
        return driverManagerDataSource;
    }

    @Bean
    @Autowired
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public PersistenceExceptionTranslator persistenceExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }
}
