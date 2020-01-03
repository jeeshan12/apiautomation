package restapi.automation.testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.SystemUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import restapi.automation.basetest.BaseTest;
import restapi.automation.helperutils.HelperUtils;
import restapi.automation.reports.ExtentReportTestManager;

/**
 * Hello world!
 *
 */
public class GetJSONRequest extends BaseTest {
	
	
	@BeforeClass
	public void setUp() {
		test=ExtentReportTestManager.createTest("Get JSON Request", "Test to create the test for GET METHOD");
	}

	@Test(dataProvider = "GetRequest")
	public void testGetRequest(Map<String, String> map) {
		test.log(Status.INFO, "Get JSON request for :"+System.getProperty("jsonBaseURI"));
		Response response = RestAssured.given().headers("Content-Type", "application/json").body("").when()
				.get(map.get("path")).then().log().all().extract().response();
		
		test.log(Status.INFO, "Response  is  :"+response.asString());
		
		System.out.println("Response  is :" + response.asString());
		
		test.log(Status.INFO, "Status  code is  :"+response.statusCode());

		System.out.println("Status code is :" + response.statusCode());
		
		test.log(Status.INFO, "Status  Line is  :"+response.statusLine());

		System.out.println("Status Line is :" + response.statusLine());

		int expectedTotalPages = JsonPath.parse(response.asString()).read("$.total_pages");
		
		test.log(Status.INFO, "Comparing actual and expected total pages");

		assertThat("Comparing actual and expected total pages", expectedTotalPages,
				equalTo(Integer.parseInt(map.get("totalPage"))));

		test.log(Status.INFO, "Comparing actual and expected id count");
		
		List<String> ecpectedIDCount = JsonPath.parse(response.asString()).read("$.data[*].id");

		assertThat("Comparing actual and expected id count", ecpectedIDCount.size(),
				equalTo(Integer.parseInt(map.get("totalID"))));

	}

	@DataProvider(name = "GetRequest")
	public Object[][] getData() {
		return HelperUtils.getDataFromJSON(
				SystemUtils.getUserDir() + File.separator + "src" + File.separator + "test" + File.separator
						+ "resources" + File.separator + "data" + File.separator + "GetRequestTestData.json",
				"getRequest");
	}

}
