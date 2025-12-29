package APITestsPlayright;


import Pogo.LoginTestRequestPogo;
import Pogo.LoginTestResponcePogo;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.qameta.allure.internal.shadowed.jackson.databind.JsonNode;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;


public class APITestsPlayrightNative {

Playwright playwright;
APIRequestContext request;

@BeforeMethod
public void SetUP(){
    playwright = Playwright.create();
    request = playwright.request().newContext(
            new APIRequest.NewContextOptions()
                    .setBaseURL("https://serverest.dev")
    );
    RestAssured.requestSpecification =
            RequestSpecs.jsonRequestSpecLogin();
}
    @Test
    public void APILogin() throws JsonProcessingException {
        Map<String, Object> body = new HashMap<>();
        body.put("email", "fulano@qa.com");
        body.put("password", "teste");

        APIResponse response = request.post("/login",
                RequestOptions.create()
                        .setData(body)
        );
        String bodyResponce = response.text();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(bodyResponce);
        System.out.println(bodyResponce);
        Assert.assertEquals("Login realizado com sucesso",json.at("/data/message").asText());

    }

    @Test
    public void LoginWithPogoPlayright() throws JsonProcessingException {
        LoginTestRequestPogo requestBody =
                new LoginTestRequestPogo();
        requestBody.setEmail("fulano@qa.com");
        requestBody.setPassword("teste");
        APIResponse response = request.post("/login",
                RequestOptions.create().setHeader("Content-Type","application/json")
                        .setData(requestBody));

        String bodyResponce = response.text();
        System.out.println(bodyResponce);
        ObjectMapper mapper = new ObjectMapper();
        LoginTestResponcePogo responcePogo =
                mapper.readValue(bodyResponce, LoginTestResponcePogo.class);
        System.out.println(responcePogo.getMessage());
        assertThat(responcePogo.getMessage()).isEqualTo("Login realizado com sucesso");

    }

    @Test
    public void LoginWithRestAssured(){
        LoginTestRequestPogo requestBody =
                new LoginTestRequestPogo();
        requestBody.setEmail("fulano@qa.com");
        requestBody.setPassword("teste");

        LoginTestResponcePogo loginResponce =
             given().contentType(ContentType.JSON)
                     .body(requestBody)
                     .when().post("https://serverest.dev/login")
                     .then()
                     .statusCode(200)
                     .contentType(ContentType.JSON)
                     .log().body()
                     .extract().as(LoginTestResponcePogo.class);
        assertThat(loginResponce)
                .extracting(LoginTestResponcePogo::getMessage)
                .isEqualTo("Login realizado com sucesso");

        TokenStorage.bearerToken = loginResponce.getAuthorization();
    }

    @Test
    public void LoginRestAssuredWithRequestScecafication(){
        LoginTestRequestPogo requestBody =
                new LoginTestRequestPogo();
        requestBody.setEmail("fulano@qa.com");
        requestBody.setPassword("teste");

        LoginTestResponcePogo loginResponce =
                given()
                        .body(requestBody)
                        .when().post("https://serverest.dev/login")
                        .then()
                        .statusCode(200)
                        .extract().as(LoginTestResponcePogo.class);
        assertThat(loginResponce)
                .extracting(LoginTestResponcePogo::getMessage)
                .isEqualTo("Login realizado com sucesso");

        TokenStorage.bearerToken = loginResponce.getAuthorization();
    }
}
