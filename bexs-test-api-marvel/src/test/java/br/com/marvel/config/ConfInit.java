package br.com.marvel.config;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("br.com.marvel")
@EntityScan("br.com.marvel" )
@CucumberContextConfiguration
public class ConfInit {
}
