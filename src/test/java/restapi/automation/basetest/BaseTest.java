package restapi.automation.basetest;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentTest;

import io.restassured.RestAssured;


@Listeners(restapi.automation.listeners.TestListener.class)
public class BaseTest {
	
	private static final String ENVIRONMENT_CONTEXT = "envContext.xml";
	
	protected ExtentTest test;

	@SuppressWarnings("resource")
	@BeforeTest(alwaysRun = true)
	public void setUpTestConfigs() {
		new ClassPathXmlApplicationContext(ENVIRONMENT_CONTEXT);
		
		if("xml".equalsIgnoreCase(System.getProperty("content-type"))) {
			RestAssured.baseURI=System.getProperty("xmlBaseURI");
		}else if("json".equalsIgnoreCase(System.getProperty("content-type"))) {
			RestAssured.baseURI=System.getProperty("jsonBaseURI");
		}else {
			System.out.println("Please define valid content type");
		}
		
		
	}

}
