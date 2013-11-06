package no.ciber.people.controllers;

import net.sf.ehcache.CacheManager;
import org.hibernate.Session;
import org.hibernate.stat.Statistics;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * User: Michael Johansen
 * Date: 17.10.13
 * Time: 23:00
 */
@Controller
public class HibernateStatisticsController {
    @PersistenceUnit
    private EntityManagerFactory emf;

    @ResponseBody
    @RequestMapping(value = {"statistics"}, method = {GET}, produces = {"application/json"})
    public Map<String, ?> show() throws Exception {
        Map<String, Object> model = new HashMap<>();
        model.put("statistics", getSessionStatistics());
        for (String cacheName : CacheManager.create().getCacheNames()) {
            model.put(cacheName, getStatistics(cacheName));
        }
        return model;
    }

    private Statistics getSessionStatistics() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.unwrap(Session.class).getSessionFactory().getStatistics();
        } finally {
            em.close();
        }
    }

    private net.sf.ehcache.Statistics getStatistics(String cacheName) throws NoSuchFieldException, IllegalAccessException {
        net.sf.ehcache.Statistics statistics = CacheManager.create().getCache(cacheName).getStatistics();
        Field cache = statistics.getClass().getDeclaredField("cache");
        cache.setAccessible(true);
        cache.set(statistics, null);
        return statistics;
    }
}
