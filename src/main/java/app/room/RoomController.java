package app.room;

import app.requests.AvailabilityInquiry;
import app.util.BasePostController;
import app.util.JsonUtil;
import app.util.ReservationLedger;
import com.google.common.collect.ImmutableList;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.LinkedHashSet;
import java.util.Set;

public class RoomController extends BasePostController {
    
    @Override
    public Set<String> getRequiredParameters() {
        return new LinkedHashSet<>(ImmutableList.of("date", "numberOfGuests", "amountOfLuggage"));
    }
    
    public final Route getAvailableRooms = (Request request, Response response) -> {
        validateRequest(request);
        AvailabilityInquiry availabilityInquiry = JsonUtil.jsonToObject(request.body(), AvailabilityInquiry.class);
        return JsonUtil.dataToJson(ReservationLedger.instance().getAvailableRooms(availabilityInquiry));
    };
}
