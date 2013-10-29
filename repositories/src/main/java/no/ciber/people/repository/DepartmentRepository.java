package no.ciber.people.repository;

import no.ciber.people.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: Michael Johansen
 * Date: 28.10.13
 * Time: 23:23
 */
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
