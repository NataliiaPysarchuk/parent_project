package api.clients;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static java.lang.Boolean.parseBoolean;
import static util.readers.PropertiesReader.getProperty;

@Getter
public class BasedAPIClient {

    protected static BasedAPIClient basedAPIClient;
    protected static String baseUrl;
    public static String internalApiPassword;

    protected static RequestSpecification requestSpecification;

    static {
        basedAPIClient = new BasedAPIClient();
        baseUrl = getProperty("api.base.url");

        requestSpecification = new RequestSpecBuilder()
                .setContentType(JSON)
                .setRelaxedHTTPSValidation()
                .build();

        baseURI = baseUrl;

        filters(new ResponseLoggingFilter(), new RequestLoggingFilter());

        if (parseBoolean(getProperty("api.logs.print"))) {
            filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        }
    }

    public final Response get(final RequestSpecification requestSpecification) {
        return given()
                .spec(requestSpecification)
                .get();
    }

    public final Response post(final RequestSpecification requestSpecification) {
        return given()
                .spec(requestSpecification)
                .post();
    }

    public final Response put(final RequestSpecification requestSpecification) {
        return given()
                .spec(requestSpecification)
                .put();
    }

    public final Response delete(final RequestSpecification requestSpecification) {
        return given()
                .spec(requestSpecification)
                .delete();
    }
}