package no.ciber.people.repository;

import no.ciber.people.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: Michael Johansen
 * Date: 28.10.13
 * Time: 23:21
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
