package no.ciber.people.controllers;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.hibernate.Session;
import org.hibernate.stat.Statistics;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Michael Johansen
 * Date: 17.10.13
 * Time: 23:00
 */
@Controller
public class HibernateStatisticsController {
    @PersistenceUnit
    private EntityManagerFactory emf;

    @RequestMapping("statistics")
    public String getStatistics(Model model) {
        EntityManager em = emf.createEntityManager();
        Statistics stats = em.unwrap(Session.class).getSessionFactory().getStatistics();
        em.close();

        CacheManager cacheManager = CacheManager.create();

        List<Cache> cacheList = new ArrayList<>();
        for (String cacheName : cacheManager.getCacheNames()){
            cacheList.add(cacheManager.getCache(cacheName));
        }
        model.addAttribute("cacheList", cacheList);
        model.addAttribute("statistics",stats);

        return "/stats.jsp";
    }
}
