package no.ciber.people.repository;

import no.ciber.people.model.Location;
import no.ciber.people.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

/**
 * User: Michael Johansen
 * Date: 28.10.13
 * Time: 23:23
 */
public interface RegionRepository extends JpaRepository<Region, BigDecimal> {
}
