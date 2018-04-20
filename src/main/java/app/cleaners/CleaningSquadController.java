package app.cleaners;

import app.exception.ErrorResponseException;
import app.util.CleaningSquadScheduler;
import app.util.JsonUtil;
import app.util.RequestUtil;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Date;

public class CleaningSquadController {
    
    public Route getCleaningSchedule = (Request request, Response response) -> {
        validateRequest(request);
        Date date = RequestUtil.getDate(request.queryMap("date").value());
        return JsonUtil.dataToJson(CleaningSquadScheduler.instance().getCleaningSquadShifts(date));
    };
    
    private void validateRequest(Request request) {
        if(request.queryMap("date") == null || !request.queryMap("date").hasValue()) {
            throw new ErrorResponseException("Expected query parameter 'date' with dateFormat 'YYYY-MM-dd'");
        }
    }
}
