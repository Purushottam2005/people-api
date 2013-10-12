package no.ciber.people.model;

import no.ciber.people.config.PersistenceConfig;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * User: Michael Johansen
 * Date: 12.10.13
 * Time: 00:38
 */
@ContextConfiguration(classes = {PersistenceConfig.class})
public class PersonTest extends AbstractTransactionalJUnit4SpringContextTests {
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testCreatePerson() throws Exception {
        Person saved = new Person();
        saved.setFirstName("Malcolm");
        saved.setLastName("Reynolds");

        entityManager.persist(saved);
        entityManager.clear();

        Person loaded = entityManager.find(Person.class, saved.getId());
        assertThat(loaded, is(not(sameInstance(saved))));
        assertThat(loaded.getFirstName(), is(saved.getFirstName()));
        assertThat(loaded.getLastName(), is(saved.getLastName()));
    }
}
