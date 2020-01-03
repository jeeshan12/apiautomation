package restapi.automation.testcases;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.util.Map;

import org.apache.commons.lang3.SystemUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import restapi.automation.basetest.BaseTest;
import restapi.automation.helperutils.HelperUtils;
import restapi.automation.reports.ExtentReportTestManager;

public class GetXMLRequest extends BaseTest {
	@BeforeClass
	public void setUp() {
		test = ExtentReportTestManager.createTest("Get XML Request", "Test to create the test for XML GET METHOD");
	}

	@Test(dataProvider = "GetXMLRequest")
	public void testGetRequest(Map<String, String> map) {
		test.log(Status.INFO, "Get XML request for :" + System.getProperty("xmlBaseURI"));
		Response response = RestAssured.given().headers("Content-Type", "application/xml").body("").when()
				.get(map.get("path")).then().log().all().extract().response();

		test.log(Status.INFO, "Response  is  :" + response.asString());

		System.out.println("Response  is :" + response.asString());

		test.log(Status.INFO, "Status  code is  :" + response.statusCode());

		System.out.println("Status code is :" + response.statusCode());

		test.log(Status.INFO, "Status  Line is  :" + response.statusLine());

		System.out.println("Status Line is :" + response.statusLine());

		String xmlString = response.asString();

		String name = XmlPath.with(xmlString).get("videoGames.videoGame[0].name");

		test.log(Status.INFO, "Name of first game is   :" + name);

		System.out.println("Name of first game is : " + name);

		test.log(Status.INFO, "Comparing 4th game");

		assertThat("Comparing 4th game name", XmlPath.with(xmlString).get("videoGames.videoGame[3].name"),
				equalTo(map.get("fourthGameName")));

	}

	@DataProvider(name = "GetXMLRequest")
	public Object[][] getData() {
		return HelperUtils.getDataFromJSON(
				SystemUtils.getUserDir() + File.separator + "src" + File.separator + "test" + File.separator
						+ "resources" + File.separator + "data" + File.separator + "GetXMLRequestTestData.json",
				"getxmlRequest");
	}

}
