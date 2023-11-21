package com.automation.bemol;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Login {

    //Realiza o login para obter o Token
    @Test
    public void Login() {

        //Recebe a URL Base
        RestAssured.baseURI = "https://serverest.dev";

        // Corpo da requisição POST para fazer login
        String requestBody = "{\n" +
                "  \"email\": \"fulano@qa.com\",\n" +
                "  \"password\": \"teste\"\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/login");

        // Verifica o código de status da resposta
        Assertions.assertEquals(200, response.getStatusCode());

        // Imprime o corpo da resposta
        System.out.println("Resposta da API:\n" + response.getBody().asString());
    }
}
