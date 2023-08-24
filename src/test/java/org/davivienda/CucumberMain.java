package org.davivienda;

import io.quarkiverse.cucumber.CucumberOptions;
import io.quarkiverse.cucumber.CucumberQuarkusTest;

@CucumberOptions(
        features = "src/main/resources/cucumber",
        glue = "org.davivienda.adapters.test"
)
public class CucumberMain extends CucumberQuarkusTest {
    public static void main(String[] args) {
        runMain(CucumberMain.class, args);
    }
}