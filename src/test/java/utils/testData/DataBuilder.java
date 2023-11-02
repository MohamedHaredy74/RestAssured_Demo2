package utils.testData;

import net.datafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class DataBuilder {


    public static  BookingData getBookingData()
    {
        Faker faker=new Faker();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        BookingDates bookingDates =new BookingDates(simpleDateFormat.format(faker.date().past(5, TimeUnit.DAYS)),
                                                   simpleDateFormat.format(faker.date().future(5,TimeUnit.DAYS)));
       return new BookingData(faker.name().firstName(),faker.name().lastName(),
               faker.number().numberBetween(50,600),true, bookingDates,faker.food().fruit());
    }



    public static AuthData getAuthData()
    {
        return new AuthData("admin","password123");
    }

    public static PartialBookingData getPartialBookingData()
    {
        Faker faker=new Faker();
        return new PartialBookingData(faker.food().dish(),faker.number().numberBetween(50,600));
    }













}
