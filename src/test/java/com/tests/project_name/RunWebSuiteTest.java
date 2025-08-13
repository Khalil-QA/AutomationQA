package com.tests.project_name;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;

/**
 * Entry point for running Cucumber test suites.
 * This class is configured with {@code @CucumberOptions} to specify the following:
 *   Path to the feature files: {@code src/spec/features}.
 *   Plugins for reporting:
 *         {@code pretty}: Readable console output.
 *         {@code summary}: Summary of executed tests.
 *         {@code html}: Generates an HTML report at {@code target/report/cucumber-report.html}.
 *         {@code json}: Outputs results in JSON format at {@code target/report/cucumber.json}.
 * {@code tags}: Runs scenarios tagged with {@code @login}.
 * {@code monochrome}: Simplifies console output for readability.
 * {@code snippets}: Generates step definitions in camel case.
 * This configuration ensures proper execution and detailed reporting of web-based test scenarios.
 *
 * @author Zied HANNACHI
 */
@RunWith(Cucumber.class)
@CucumberOptions(
		features = { "src/spec/features" },
		plugin = {
				"pretty",
				"summary",
				"html:target/report/cucumber-report.html",
				"json:target/report/cucumber.json",
		},
        //glue = {"step_definitions"},
		tags = "@loginOrange",
		monochrome = true,
		snippets = CAMELCASE
)
public class RunWebSuiteTest {

}
