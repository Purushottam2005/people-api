package no.ciber.people.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

/**
 * User: Michael Johansen
 * Date: 17.10.13
 * Time: 21:56
 */
@Configuration
@EnableWebMvc
@ComponentScan("no.ciber.people.controllers")
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    public void configureObjectMapper(RequestMappingHandlerAdapter requestMappingHandlerAdapter) {
        for (HttpMessageConverter<?> httpMessageConverter : requestMappingHandlerAdapter.getMessageConverters()) {
            if (httpMessageConverter instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter converter = (MappingJackson2HttpMessageConverter) httpMessageConverter;
                converter.getObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true);
                converter.getObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            }
        }
    }
}
