package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.module.jsv.JsonSchemaValidator;
import end_points.EndPoints;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.testData.AuthData;
import utils.testData.BookingData;
import utils.testData.PartialBookingData;

import java.io.InputStream;


import static org.hamcrest.Matchers.*;
import static utils.testData.DataBuilder.*;

public class Tests {


    int bookingId=8;
    BookingData creatBookingData;
    BookingData updateBookingData;
    PartialBookingData partialBookingData;
    AuthData authData;
    String Token;


    @BeforeTest
    public void setUp() {

        creatBookingData=getBookingData();
        updateBookingData=getBookingData();
        partialBookingData=getPartialBookingData();
        authData=getAuthData();
        Token=EndPoints.generateToken(authData);
    }


    @Description("Test1 : Create  Booking")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 1)
    public void createBooking()  {

        String additionalNeeds=creatBookingData.additionalneeds();
        InputStream createBookingJsonSchema=getClass().getClassLoader().getResourceAsStream("createBookingSchema.json");
        Response response=
                EndPoints.createBooking(creatBookingData);
        bookingId=response.body().jsonPath().get("bookingid").hashCode();
        response.prettyPrint();
        response
                .then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(createBookingJsonSchema))
                .assertThat()
                .body("booking.additionalneeds",is(equalTo(additionalNeeds)))
                .assertThat()
                .statusCode(200);

    }

    @Description("Test2: Get All Created Bookings")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 2)
    public void getAllBookings()
    {
        Response response=
                EndPoints.getAllBookings();
        response.prettyPrint();
        response.
                then()
                .assertThat().body("bookingis",not(empty())).
                statusCode(200);
       // List<Object>bookingID= response.body().path("bookingid");

    }


    @Description("Test3 : Get Single Booking ")
    @Severity(SeverityLevel.MINOR)
    @Test(priority = 3)
    public void getBookingById()
    {
        InputStream getBookingJsonSchema=getClass().getClassLoader().getResourceAsStream("getBookingSchema.json");
        Response response=
                EndPoints.getBookingById(bookingId);
        response.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(getBookingJsonSchema))
                .statusCode(200)
                .log().all();
    }


    @Description("Test4 : Update Full Booking Data ")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 4)
    public void updateCreatedBooking()
    {
        String firstname= creatBookingData.firstname();
        Response response=
        EndPoints.updateBooking(bookingId, creatBookingData,Token);
        response.then()
                .assertThat().body("firstname",is(equalTo(firstname)))
                .assertThat().statusCode(200)
                .log().all();
    }


    @Description("Test5 : Update AdditionalNeeds and TotalPrice Properties Of Booking ")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 5)
    public void updateAdditionalneedsAndtotalprice()
    {
        String additionalNeeds=partialBookingData.additionalneeds();
        int price= partialBookingData.totalprice();
        Response response=
        EndPoints.updateBookingPartially(bookingId,partialBookingData,Token);
        response.prettyPrint();
        response
                .then()
                .assertThat().body("additionalneeds",is(equalTo(additionalNeeds)))
                .assertThat().body("totalprice",equalTo(price))
                .statusCode(200);
    }



    @Description("Test6 : Delete Created Booking ")
    @Test(priority = 6)
    public void deleteBooking()
    {
        Response response=
                EndPoints.deleteBooking(bookingId,Token);
        response.then().assertThat().statusCode(201)
                        .log().all();
        EndPoints.getBookingById(bookingId)
                .then().assertThat().statusCode(404);

    }

}
