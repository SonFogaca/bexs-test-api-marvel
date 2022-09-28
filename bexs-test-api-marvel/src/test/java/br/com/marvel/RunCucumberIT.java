package br.com.marvel;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@Slf4j
@RunWith(Cucumber.class)
@CucumberOptions(

        tags = "@all",
        publish = true,
        stepNotifications = true,
        plugin =
                {
                        "pretty",
                        "html:target/reports/cucumber-reports-html"
                },
        features = "src/test/resources",
        glue =
                {
                        "br.com.marvel",
                        "br.com.marvel.config"
                }
)
public class RunCucumberIT {

    @BeforeClass
    public static void begin() {
        log.info( "PROJETO MARVEL API." );
    }
}
