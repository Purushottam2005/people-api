package no.ciber.people.repository;

import no.ciber.people.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.math.BigInteger;

/**
 * User: Michael Johansen
 * Date: 12.10.13
 * Time: 14:58
 */
@RestResource(path = "people",rel = "people")
public interface PersonRepository extends JpaRepository<Person, BigInteger> {
    Page<Person> findByFirstNameIgnoreCaseContains(@Param("firstName") String firstName, Pageable pageable);

    Page<Person> findByLastNameIgnoreCaseContains(@Param("lastName") String firstName, Pageable pageable);

    @Query("FROM Person p WHERE UPPER(CONCAT(p.firstName, ' ', p.lastName )) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<Person> nameContains(@Param("name") String name, Pageable pageable);

    @Query("FROM Person p WHERE LENGTH(CONCAT(p.firstName, ' ', p.lastName )) < :nameLength")
    Page<Person> nameShorterThan(@Param("nameLength") Integer nameLength, Pageable pageable);
}
