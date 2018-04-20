package app.util;

import spark.Filter;
import spark.Request;
import spark.Response;

public class Filters {

    // Enable JSON for all responses
    public static Filter addJsonHeaders = (Request request, Response response) -> {
        response.header("Content-Encoding", "json");
        response.header("Content-Type", "application/json");
    };

}
