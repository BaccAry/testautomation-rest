package com.company.rest;

import com.company.rest.dtos.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserEndpoint {

    final String baseUrl = "https://petstore.swagger.io/v2";
    final String usersPath = "/user";
    User dummyUser;

    public UserEndpoint() {
        RestAssured.baseURI = baseUrl;
    }

    @Before
    public void setup() {
        RestAssured.baseURI = baseUrl;
    }

    public void addTestUser() {
        dummyUser = new User();
        dummyUser.setId(1);
        dummyUser.setUsername("jkowalski");
        dummyUser.setFirstName("Jan");
        dummyUser.setLastName("Kowalski");
        dummyUser.setEmail("jkowalski@kowalski.pl");
        dummyUser.setPassword("111111");
        dummyUser.setPhone("123456789");
        dummyUser.setPassword("secret");
        dummyUser.setUserStatus(1);
        this.addUser(dummyUser);
    }

    public void getTestUser() {
        // sprawdz czy user jest poprawnie dodany
        User testUser = this.getUserByUsername("jkowalski");
        assertEquals(dummyUser, testUser);
        //System.out.println(testUser.toString());
    }

    public void getEditedTestUser() {
        // sprawdz czy user zostało zmodyfikowane
        User testUser = this.getUserByUsername("loko");
        assertEquals(dummyUser, testUser);
        //System.out.println(testUser.toString());
    }

    public void addUser(User user) {
        RequestSpecification httpRequest = RestAssured.given().
                contentType("application/json").accept("application/json").
                body(user);

        Response response = httpRequest.post(usersPath);

        assertEquals(response.statusCode(), 200);
    }

    public void modifyTestUser() {
        dummyUser.setUsername(("loko"));
        RequestSpecification httpRequest = RestAssured.given().
                contentType("application/json").accept("application/json").
                body(dummyUser);

        Response response = httpRequest.put(usersPath + "/" + dummyUser.getUsername());

        assertEquals(response.statusCode(), 200);
    }

    public User getUserByUsername(String username) {
        Response getResponse = RestAssured.given().get(usersPath + "/" + username);
        User returnedUser = getResponse.as(User.class);

        assertEquals(200, getResponse.statusCode());
        getResponse.getBody().prettyPrint();
        return returnedUser;
    }

}
