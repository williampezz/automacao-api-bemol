package com.automation.bemol;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class CadUser {


    @Test
    public void testCadUser() {
        // Configuração da URL base da API
        RestAssured.baseURI = "https://serverest.dev";

        // Corpo da requisição POST para criar um usuário
        String requestBody = "{\n" +
                "  \"nome\": \"Fulano da Silva\",\n" +
                "  \"email\": \"williamtest16@qa.com.br\",\n" +
                "  \"password\": \"teste\",\n" +
                "  \"administrador\": \"true\"\n" +
                "}";

        // Faz a solicitação POST para criar um usuário
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/usuarios");

        // Verifica o código de status da resposta
        Assertions.assertEquals(201, response.getStatusCode());

        // Imprime o corpo da resposta
        System.out.println("Resposta da API:\n" + response.getBody().asString());

        // Extrai o ID do usuário recém-criado
        String userId = response.jsonPath().getString("_id");

        // Chama o método de verificação passando o ID do usuário
        testVerifyUserCreation(userId);
    }


    public void testVerifyUserCreation(String userId) {
        // Faz a solicitação GET para obter os detalhes do usuário pelo ID
        Response response = given()
                .pathParam("userId", userId)
                .when()
                .get("/usuarios/{userId}");
        // Verifica o código de status da resposta
        Assertions.assertEquals(200, response.getStatusCode());

        // Imprime o corpo da resposta
        System.out.println("Resposta da API:\n" + response.getBody().asString());

    }


}
