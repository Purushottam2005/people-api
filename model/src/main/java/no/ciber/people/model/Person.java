package no.ciber.people.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.math.BigInteger;
import java.util.Collection;

/**
 * User: Michael Johansen
 * Date: 30.10.13
 * Time: 15:47
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Person extends AbstractPersistable<BigInteger> {
    private String firstName;
    private String lastName;
    @ManyToMany
    private Collection<Person> friends;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Collection getFriends() {
        return friends;
    }

    public void setFriends(Collection friends) {
        this.friends = friends;
    }
}
