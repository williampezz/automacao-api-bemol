package com.automation.bemol;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;

public class CadProduct {

    @Test
    public void testCadProduct() {
        // Configuração da URL base da API
        RestAssured.baseURI = "https://serverest.dev";

        // Recebe o token após o login

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImJlbHRyYW5vQHFhLmNvbS5iciIsInBhc3N3b3JkIjoidGVzdGUiLCJpYXQiOjE3MDA1NzU4MTQsImV4cCI6MTcwMDU3NjQxNH0.e-gJiCFvaBhYuvjd5LA9jjzOyeqbl6uzHl5vBAAfV9k";

        // Corpo da requisição POST para cadastrar um produto
        String requestBody = "{  \"nome\": \"Logitech ABC Vertical\",\n" +
                "  \"preco\": 470,\n" +
                "  \"descricao\": \"Mouse\",\n" +
                "  \"quantidade\": 381}";

        // Faz a solicitação POST para criar o produto
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/produtos");

        // Verifica o código de status da resposta
        Assertions.assertEquals(201, response.getStatusCode());

        // Imprime o corpo da resposta
        System.out.println("Resposta da API:\n" + response.getBody().asString());

        // Extrai o ID do produto recém-criado
        String productId = response.jsonPath().getString("_id");

        // Chama o método de verificação passando o ID do produto
        testVerifyProductCreation(productId);

    }

    public void testVerifyProductCreation(String productId) {
        // Faz a solicitação GET para obter os detalhes do produto pelo ID
        Response response = given()
                .pathParam("productId", productId)
                .when()
                .get("/produtos/{productId}");

        // Verifica o código de status da resposta
        Assertions.assertEquals(200, response.getStatusCode());

        // Imprime o corpo da resposta
        System.out.println("Resposta da API:\n" + response.getBody().asString());

    }


}
