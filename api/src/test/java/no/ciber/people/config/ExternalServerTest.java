package no.ciber.people.config;

import no.ciber.people.model.Person;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.OK;

/**
 * User: Michael Johansen
 * Date: 30.10.13
 * Time: 22:21
 */
@Ignore
public class ExternalServerTest {

    public static final String BASE_URL = "http://localhost:8080/api";
    private RestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
        restTemplate = new RestTemplate();
    }

    @Test
    public void testResourceAvailable() throws Exception {
        ResponseEntity<Resources> root = getRoot();

        assertThat(root.getStatusCode(), is(OK));
        assertThat(root.getBody().getLink("person"), is(notNullValue()));
    }

    private ResponseEntity<Resources> getRoot() {
        return restTemplate.getForEntity(BASE_URL, Resources.class);
    }

    @Test
    public void testPersonCountIs100() throws Exception {
        ResponseEntity<PagedResources<Resource<Person>>> exchange = getPersonResource(getPersonListUrl() + "?size=1000");

        assertThat(exchange.getStatusCode(), is(OK));
        Collection<Resource<Person>> content = exchange.getBody().getContent();
        assertThat(content.size(), is(100));
        for (Resource<Person> personResource : content) {
            assertThat(personResource.getContent(), is(instanceOf(Person.class)));
        }
    }

    private String getPersonListUrl() {
        return getHref(getRoot().getBody(), "person");
    }

    private String getHref(Resources resource, String rel) {
        return resource.getLink(rel).getHref();
    }

    private ResponseEntity<PagedResources<Resource<Person>>> getPersonResource(String url) {
        return get(url, new ParameterizedTypeReference<PagedResources<Resource<Person>>>() {
        });
    }

    private <T extends ParameterizedTypeReference<S>, S> ResponseEntity<S> get(String url, T type) {
        return restTemplate.exchange(url, GET, null, type);
    }
}
