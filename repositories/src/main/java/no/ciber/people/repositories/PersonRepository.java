package no.ciber.people.repositories;

import no.ciber.people.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.math.BigInteger;
import java.util.List;

/**
 * User: Michael Johansen
 * Date: 30.10.13
 * Time: 16:07
 */
@RestResource(rel = "person", path = "person")
public interface PersonRepository extends JpaRepository<Person, BigInteger> {
    List<Person> findByFirstNameContainsIgnoreCase(@Param("name") String name);
    Page<Person> findByIdIn(@Param("ids") List<BigInteger> ids, Pageable page);
    Page<Person> findDistinctByFriendsFriendsId(@Param("id") BigInteger id, Pageable page);
}
