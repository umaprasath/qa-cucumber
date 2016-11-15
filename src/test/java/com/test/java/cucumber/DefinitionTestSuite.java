package com.test.java.cucumber;

import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features="src/test/resources/com/website/test/cuke/feature/", tags ={"~@ignore"})


public class DefinitionTestSuite {}
