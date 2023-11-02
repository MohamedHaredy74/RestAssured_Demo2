package utils.testData;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingDates {
    String checkin;
    String checkout;
}
