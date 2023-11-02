package utils.testData;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PartialBookingData {
    String additionalneeds ;
    int totalprice;
}
