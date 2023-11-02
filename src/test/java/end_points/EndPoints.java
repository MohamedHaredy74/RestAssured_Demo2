package end_points;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class EndPoints {



    private static RequestSpecification requestSpec()
    {
        RequestSpecBuilder specBuilder=new RequestSpecBuilder();
        return specBuilder.addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addFilter(new AllureRestAssured())
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }

    public static Response createBooking(Object payload)
    {

        return given()
                .spec(requestSpec())
                .body(payload).log().uri()
                .when()
                .post(Routs.createBooking);
    }

    public static Response getAllBookings()
    {
        return  given()
                .when()
                .get(Routs.getAllBookings);
    }

    public static Response getBookingById(int bookingId)
    {
        return given()
                .pathParam("id", bookingId)
                .when()
                .get(Routs.getBookingByID);
    }

    public static Response updateBooking(int bookingId, Object payload , String token)
    {
        return  given().spec(requestSpec())
                .pathParam("id", bookingId)
                .header("Cookie","token="+token)
                .log().all()
                .body(payload)
                .when()
                .put(Routs.updateBooking);
    }

    public static Response updateBookingPartially(int bookingId, Object payload , String token)
    {
        return  given().spec(requestSpec())
                .pathParam("id", bookingId)
                .header("Cookie","token="+token)
                .body(payload)
                .when()
                .patch(Routs.updateBooking);
    }

    public static Response deleteBooking(int bookingId , String token)
    {
        return given().spec(requestSpec())
                .pathParam("id",bookingId)
                .header("Cookie","token="+token)
                .when()
                .delete(Routs.deleteBooking);
    }
    public static String generateToken(Object payload)
    {
        return  given()
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .post(Routs.auth)
                .body().jsonPath().get("token");
    }










}
