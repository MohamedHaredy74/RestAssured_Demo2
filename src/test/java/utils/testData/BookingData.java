package utils.testData;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingData {
    String firstname ;
    String lastname ;
    int totalprice ;
    boolean depositpaid;
    BookingDates bookingdates;
    String additionalneeds;
}
