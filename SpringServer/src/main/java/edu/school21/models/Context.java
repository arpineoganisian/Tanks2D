package edu.school21.models;

import edu.school21.config.ServerConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Context {
    private static ApplicationContext context;

    public static ApplicationContext getContext() {
        if (context == null) {
            context = new AnnotationConfigApplicationContext(ServerConfiguration.class);
        }
        return context;
    }
}
