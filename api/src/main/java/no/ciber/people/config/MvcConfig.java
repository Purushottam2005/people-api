package no.ciber.people.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * User: Michael Johansen
 * Date: 17.10.13
 * Time: 21:56
 */
@Configuration
@EnableWebMvc
@ComponentScan("no.ciber.people.controllers")
public class MvcConfig extends WebMvcConfigurerAdapter {
}
