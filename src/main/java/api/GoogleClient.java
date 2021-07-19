package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

public class GoogleClient extends api.clients.BasedAPIClient {

    public static Response getGoogle() {
        return basedAPIClient
                .get(new RequestSpecBuilder()
                        .build());
    }
}
