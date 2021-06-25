package edu.school21.config;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("edu.school21")
@PropertySource("classpath:application.properties")
public class ServerConfiguration {
}
