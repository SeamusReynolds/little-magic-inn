package app.requests;

import java.util.List;

public class BookingRequest extends AvailabilityInquiry {
    protected List<String> guestNames;
    protected String paymentInformation;
    
    public List<String> getGuestNames() {
        return guestNames;
    }
    
    public void setGuestNames(List<String> guestNames) {
        this.guestNames = guestNames;
    }
    
    public String getPaymentInformation() {
        return paymentInformation;
    }
    
    public void setPaymentInformation(String paymentInformation) {
        this.paymentInformation = paymentInformation;
    }
}
