package app.util;

import app.exception.ErrorResponseException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RequestUtil {
    
    public static Date getDate(String dateString) {
        String pattern = "MM-dd-YYYY";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Date ret = null;
        
        if(dateString == null || dateString.isEmpty()) {
            throw new ErrorResponseException("Date string was empty or null");
        }
        
        try {
            return formatter.parse(dateString);
        } catch(ParseException e) {
            throw new ErrorResponseException("Invalid date format. Dates should be in format " + pattern);
        }
    }

}
