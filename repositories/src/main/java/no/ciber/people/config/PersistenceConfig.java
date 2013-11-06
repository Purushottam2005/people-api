package no.ciber.people.config;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.ehcache.CacheManager;
import no.ciber.people.model.Person;
import no.ciber.people.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static org.hibernate.cfg.AvailableSettings.*;

/**
 * User: Michael Johansen
 * Date: 11.10.13
 * Time: 23:31
 */
@Configuration
@EnableJpaRepositories(basePackages = {"no.ciber.people.repositories"})
@PropertySource("classpath:persistence.properties")
@EnableCaching
@ComponentScan("no.ciber.people.events")
public class PersistenceConfig {
    @Autowired
    private Environment env;

    @Bean
    @Autowired
    public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emfBean = new LocalContainerEntityManagerFactoryBean();
        emfBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emfBean.setPackagesToScan("no.ciber.people.model");
        emfBean.getJpaPropertyMap().put(HBM2DDL_AUTO, "create");
        emfBean.getJpaPropertyMap().put(FORMAT_SQL, true);
        emfBean.getJpaPropertyMap().put(GENERATE_STATISTICS, true);
        emfBean.getJpaPropertyMap().put(USE_SECOND_LEVEL_CACHE, true);
        emfBean.getJpaPropertyMap().put(USE_QUERY_CACHE, true);
        emfBean.getJpaPropertyMap().put(CACHE_REGION_FACTORY, "org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory");

        emfBean.setDataSource(dataSource);
        emfBean.afterPropertiesSet();

        return emfBean.getObject();
    }

    @Bean
    public DataSource dataSource() throws SQLException {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(env.getProperty("db.url"));
        driverManagerDataSource.setUsername(env.getProperty("db.user"));
        driverManagerDataSource.setPassword(env.getProperty("db.pass"));
        driverManagerDataSource.setDriverClassName(env.getProperty("db.driver"));
        return new LazyConnectionDataSourceProxy(driverManagerDataSource);
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

    @Bean
    public EhCacheCacheManager ehCacheCacheManager() {
        return new EhCacheCacheManager(CacheManager.create()) {
            @Override
            public Cache getCache(String name) {
                Cache cache = super.getCache(name);
                if (cache == null) {
                    getCacheManager().addCache(name);
                    return super.getCache(name);
                }
                return cache;
            }
        };
    }

    @Autowired
    public void initData(PersonRepository personRepository) throws IOException {
        Random random = new Random(42);
        Person[] persons = new ObjectMapper().readValue(new ClassPathResource("data.json").getInputStream(), Person[].class);
        personRepository.save(Arrays.asList(persons));
        for (Person person : persons) {
            for (Person otherPerson : persons) {
                if(!person.equals(otherPerson) && random.nextFloat() < 0.05){
                    if(person.getFriends() == null) person.setFriends(new ArrayList());
                    person.getFriends().add(otherPerson);
                }
            }
        }
        personRepository.save(Arrays.asList(persons));
    }
}
