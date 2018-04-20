package app.util;

import app.exception.ErrorResponseException;
import spark.Request;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public abstract class BasePostController {
    public abstract Set<String> getRequiredParameters();
    
    public void validateRequest(Request request) {
        if(request.contentType() == null || !request.contentType().contains("json")) {
            throw new ErrorResponseException("Expected Content-Type: application/json");
        }
        
        if(request.body() == null || request.body().isEmpty()) {
            throw new ErrorResponseException("Expecting body for POST request");
        }
        
        //Check that we have all required parameters
        Set<String> missingParams = new LinkedHashSet<>();
        Map<String, Object> body = JsonUtil.jsonToMap(request.body());
        for(String requiredParam : getRequiredParameters()) {
            if(!body.containsKey(requiredParam) || body.get(requiredParam) == null) {
                missingParams.add(requiredParam);
            }
        }
        //If missing any parameters, throw error message that states parameters that are missing
        if(missingParams.size() > 0) {
            throw new ErrorResponseException("Missing expected query parameters: " +
                                             Arrays.toString(getRequiredParameters().toArray()));
        }
    }
}
