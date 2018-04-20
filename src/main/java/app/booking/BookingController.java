package app.booking;

import app.exception.ErrorResponseException;
import app.requests.BookingRequest;
import app.util.BasePostController;
import app.util.JsonUtil;
import app.util.ReservationLedger;
import com.google.common.collect.ImmutableList;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.LinkedHashSet;
import java.util.Set;

public class BookingController extends BasePostController {
    
    @Override
    public Set<String> getRequiredParameters() {
        return new LinkedHashSet<>(ImmutableList.of("date", "numberOfGuests", "amountOfLuggage"));
    }
    
    public Route makeReservation = (Request request, Response response) -> {
        validateRequest(request);
        
        BookingRequest bookingRequest = JsonUtil.jsonToObject(request.body(), BookingRequest.class);
        Booking finalBooking = ReservationLedger.instance().makeReservation(bookingRequest);
        if(finalBooking == null) {
            throw new ErrorResponseException("Could not find any available rooms that fit the booking criteria.");
        }
        
        return JsonUtil.dataToJson(finalBooking);
    };
}
