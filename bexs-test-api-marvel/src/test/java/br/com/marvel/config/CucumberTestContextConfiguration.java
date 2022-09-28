package br.com.marvel.config;

import io.cucumber.java.Before;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = ConfInit.class, loader = SpringBootContextLoader.class)
public class CucumberTestContextConfiguration {

    @Before
    public void SetupSpringContext() {
    }
}
