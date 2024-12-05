package com.ubik.formation.library2.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
        	AppConfig.class,
    		LiquibaseConfig.class,
    		Log4j2Config.class,
    		WebConfig.class
		};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {
		};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
