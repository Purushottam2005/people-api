package no.ciber.people.controllers;

import org.hibernate.Session;
import org.hibernate.stat.Statistics;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * User: Michael Johansen
 * Date: 17.10.13
 * Time: 23:00
 */
@Controller
public class HibernateStatisticsController {
    @PersistenceUnit
    private EntityManagerFactory emf;

    @RequestMapping("admin/stat")
    public String getStatistics(Model model) {
        EntityManager em = emf.createEntityManager();
        Statistics stats = em.unwrap(Session.class).getSessionFactory().getStatistics();
        em.close();

        model.addAttribute("statistics",stats);
        return "/stats.jsp";
    }
}
