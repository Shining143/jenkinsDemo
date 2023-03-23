package tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {"src\\test\\resources\\features"},
		glue = {"glueCode"}, 
		monochrome = true,
		plugin = {"pretty","html:target/res","rerun:target/failedscenarios.txt"})
public class Runner extends AbstractTestNGCucumberTests
{
	// this class will be empty bydefault
} 

