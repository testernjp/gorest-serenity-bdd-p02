package com.gorest.steps;

import com.gorest.userinfo.UserSteps;
import com.gorest.util.TestUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.Assert;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.collection.IsMapContaining.hasValue;

@RunWith(CucumberWithSerenity.class)
public class MyStepdefs {

    static ValidatableResponse response;
    static String name = null;
    static String email = null;
    static String gender =  null;
    static String status = null;
    static String updatedName = null;
    static String updatedEmail=null;
    static int id;

    @Steps
    UserSteps steps;

    @Given("Gorest application is running")
    public void gorestApplicationIsRunning() {
    }

    @When("I create a new user providing name {string} email {string} gender {string} status {string}")
    public void iCreateANewUserProvidingNameEmailGenderStatus(String _name, String _email, String gender, String status) {
        name = TestUtils.getRandomValue()+ _name;
        email = TestUtils.getRandomValue()+ _email;
        response = steps.createUser(name, email,gender,status);
        response.statusCode(201);
    }

    @Then("I verify that the user is created")
    public void iVerifyThatTheUserIsCreated() {
        HashMap<String,Object> userMap = steps.getUserInfoByName(name);
        Assert.assertThat(userMap, hasValue(name));
        id = (int)userMap.get("id");
        System.out.println("id " +id);
    }

    @And("I update the user with name {string} email {string} gender {string} status {string}")
    public void iUpdateTheUserWithNameEmailGenderStatus(String _updatedName, String _updatedEmail, String gender, String status) {
        updatedName = TestUtils.getRandomValue()+ _updatedName;
        updatedEmail = TestUtils.getRandomValue() + _updatedEmail;
        ValidatableResponse response = steps.updateUser(id, updatedName, updatedEmail, gender, status);
        response.statusCode(200);
    }

    @Then("I verify that the user with updated name {string} is updated successfully")
    public void iVerifyThatTheUserWithUpdatedNameIsUpdatedSuccessfully(String _updatedName) {

        HashMap<String,Object> userMap = steps.getUserInfoByName(updatedName);
        Assert.assertThat(userMap, hasValue(updatedName));
        id = (int)userMap.get("id");
        System.out.println("name" +name);
    }

    @When("I delete the user with name {string}")
    public void iDeleteTheUserWithName(String name) {
        steps.deleteUser(id).statusCode(204);
    }

    @Then("I verify that the user with name {string} is deleted successfully")
    public void iVerifyThatTheUserWithNameIsDeletedSuccessfully(String name) {
        steps.getUserById(id).statusCode(404);
    }
}
