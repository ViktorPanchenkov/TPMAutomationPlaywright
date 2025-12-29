package APITestsPlayright;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecs {
    public static RequestSpecification jsonRequestSpecLogin() {
        return new RequestSpecBuilder()
                .setBaseUri("https://YOUR_API_URL")   // ← замени
                .setContentType(ContentType.JSON)
                .addHeader("Accept", "application/json")
                .log(io.restassured.filter.log.LogDetail.ALL)
                .build();
    }
}
