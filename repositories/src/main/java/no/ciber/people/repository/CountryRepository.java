package no.ciber.people.repository;

import no.ciber.people.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: Michael Johansen
 * Date: 28.10.13
 * Time: 23:20
 */
public interface CountryRepository extends JpaRepository<Country, String> {
}
