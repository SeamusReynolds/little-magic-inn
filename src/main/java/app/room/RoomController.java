package app.room;

import app.exception.ErrorResponseException;
import app.requests.AvailabilityInquiry;
import app.util.JsonUtil;
import app.util.ReservationLedger;
import com.google.common.collect.ImmutableList;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class RoomController {
    
    private static Set<String> requiredParameters = new LinkedHashSet<>(ImmutableList.of("date",
                                                                                         "numberOfGuests",
                                                                                         "amountOfLuggage"));
    
    public static Route getAvailableRooms = (Request request, Response response) -> {
        validateRequest(request);
        AvailabilityInquiry availabilityInquiry = JsonUtil.jsonToObject(request.body(), AvailabilityInquiry.class);
        return JsonUtil.dataToJson(ReservationLedger.instance().getAvailableRooms(availabilityInquiry));
    };
    
    private static void validateRequest(Request request) {
        if(request.contentType() == null || !request.contentType().contains("json")) {
            throw new ErrorResponseException("Expected Content-Type: application/json");
        }
        
        if(request.body() == null || request.body().isEmpty()) {
            throw new ErrorResponseException("Expecting body for POST request");
        }
        
        //Check that we have all required parameters
        Set<String> missingParams = new LinkedHashSet<>();
        Map<String, Object> body = JsonUtil.jsonToMap(request.body());
        for(String requiredParam : requiredParameters) {
            if(!body.containsKey(requiredParam) || body.get(requiredParam) == null) {
                missingParams.add(requiredParam);
            }
        }
        //If missing any parameters, throw error message that states parameters that are missing
        if(missingParams.size() > 0) {
            throw new ErrorResponseException("Missing expected query parameters: " +
                                             Arrays.toString(requiredParameters.toArray()));
        }
    }
}
