package br.com.marvel.utils;

import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class Hooks {

    @Before
    public void before() {
       RestAssured.baseURI = "https://gateway.marvel.com";
    }
}
