package utils.testData;

import net.datafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class DataBuilder {


    public static  BookingData getBookingData() {
        Faker faker = new Faker();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return BookingData.builder().firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .totalprice(faker.number().numberBetween(60,600))
                .depositpaid(true)
                .bookingdates(BookingDates.builder().checkin(simpleDateFormat.format(faker.date().past(5,TimeUnit.DAYS)))
                                                   .checkout(simpleDateFormat.format(faker.date().future(5,TimeUnit.DAYS))).build())
                .additionalneeds(faker.food().dish()).build();

    }



    public static AuthData getAuthData()
    {
        return AuthData.builder()
                .username("admin")
                .password("password123")
                .build();
    }

    public static PartialBookingData getPartialBookingData()
    {
        Faker faker=new Faker();
        return PartialBookingData
                .builder()
                .additionalneeds(faker.food().dish())
                .totalprice(faker.number().numberBetween(60,600))
                .build();

    }





}
