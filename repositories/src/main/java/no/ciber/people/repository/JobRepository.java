package no.ciber.people.repository;

import no.ciber.people.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: Michael Johansen
 * Date: 28.10.13
 * Time: 23:22
 */
public interface JobRepository extends JpaRepository<Job, String> {
}
