package no.ciber.people.repository;

import no.ciber.people.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.math.BigInteger;

/**
 * User: Michael Johansen
 * Date: 12.10.13
 * Time: 14:58
 */
@RestResource(path = "person")
public interface PersonRepository extends JpaRepository<Person,BigInteger>{
}
