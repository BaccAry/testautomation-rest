package com.company.steps.rest;

import com.company.rest.UserEndpoint;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class UserSteps {

    private UserEndpoint userEndpoint;

    public UserSteps() {
        userEndpoint = new UserEndpoint();
    }

    @Given("Create a new user")
    public void create_a_new_user() {
        userEndpoint.addTestUser();
    }

    @Then("Check if created user exists")
    public void check_if_created_user_exists() {
        userEndpoint.getTestUser();
    }

    @Given("Modify a user")
    public void modify_user() {
        userEndpoint.addTestUser();
        userEndpoint.modifyTestUser();
    }

    @Then("Check if user was modified")
    public void check_if_user_was_modified() {
        userEndpoint.getEditedTestUser();
    }
}
