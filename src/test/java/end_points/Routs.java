package end_points;

public class Routs {
    //https://restful-booker.herokuapp.com
    public  static String baseUri="https://restful-booker.herokuapp.com";
    public static String getAllBookings=baseUri+"/booking";
    public static String getBookingByID=baseUri+"/booking/{id}";
    public static String createBooking=baseUri+"/booking";
    public static String deleteBooking=baseUri+"/booking/{id}";
    public static String updateBooking=baseUri+"/booking/{id}";
    public static String auth =baseUri+"/auth";
}
