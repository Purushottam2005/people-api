package no.ciber.people.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class RestExporterWebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext rootCtx = new AnnotationConfigWebApplicationContext();
        rootCtx.register(RestConfiguration.class);
        servletContext.addListener(new ContextLoaderListener(rootCtx));

        DispatcherServlet dispatcherServlet = new DispatcherServlet(rootCtx);
        ServletRegistration.Dynamic reg = servletContext.addServlet("rest-exporter", dispatcherServlet);
        reg.setLoadOnStartup(1);
        reg.addMapping("/*");
    }

}