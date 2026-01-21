package com.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * TestNG Cucumber Test Runner
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.automation.stepdefinitions"},
        plugin = {
                "pretty",
                "html:cucumber-reports/cucumber.html",
                "json:cucumber-reports/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true,
        dryRun = false,
        tags = ""
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
