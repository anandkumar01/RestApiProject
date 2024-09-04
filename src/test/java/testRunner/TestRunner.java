package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { ".\\src\\test\\resources\\FeatureFiles" }, glue = { "stepDefinition" }, plugin = {
		"pretty", "html:reports/myreport.html" }, dryRun = false, monochrome = true, publish = true)

public class TestRunner {

}