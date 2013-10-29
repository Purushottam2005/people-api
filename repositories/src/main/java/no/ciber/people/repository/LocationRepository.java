package no.ciber.people.repository;

import no.ciber.people.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: Michael Johansen
 * Date: 28.10.13
 * Time: 23:23
 */
public interface LocationRepository extends JpaRepository<Location, Integer> {
}
