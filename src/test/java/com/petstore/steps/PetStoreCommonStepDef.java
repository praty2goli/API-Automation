/**
 * 
 * Common step definitions class
 */

package com.petstore.steps;

import java.util.List;

import org.testng.Assert;

import com.petstore.base.TestBase;
import com.petstore.utility.PropertyConfiguration;
import com.petstore.utility.Utility;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class PetStoreCommonStepDef extends TestBase {

	@Given("^user have the endpoint as \"([^\"]*)\"$")
	public void createEndpoint(String endpoint) throws Throwable {
		setProperty("URL", Utility.createEndPoint(PropertyConfiguration.getInstance().valueFromConfig(endpoint.split("/")[0]),
				PropertyConfiguration.getInstance().valueFromConfig(endpoint.split("/")[1])));
	}

	@When("^user send the \"([^\"]*)\" request to \"([^\"]*)\" using$")
	public void sendReq(String methodType, String endpoint, DataTable table) {
		response = Utility.buildRequest(table, methodType);
	}

	@When("^user send the \"([^\"]*)\" request using request body as \"([^\"]*)\"$")
	public void sendPost(String methodType, String jsonBody, DataTable table) throws Throwable {
		setProperty("body", Utility.readJson(jsonBody));
		response = Utility.buildRequest(table, methodType);
	}

	@When("^user assert all pets status as \"([^\"]*)\"$")
	public void assertCondition(String value) {
		List<String> status = response.jsonPath().getList("status");
		for (String sta : status) {
			Assert.assertTrue(sta.equals(value));
		}
	}

	@When("^user assert value as \"([^\"]*)\" from response path \"([^\"]*)\"$")
	public void assertAddedPet(String value, String path) {
		Assert.assertTrue(
				response.jsonPath().getString(path).equals(getProperty(value) == null ? value : getProperty(value)));
	}

	@When("^user store the \"([^\"]*)\" of Pet$")
	public void storePetId(String path) {
		setProperty(path, response.jsonPath().getString(path));
	}
}
