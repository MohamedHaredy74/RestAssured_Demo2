package end_points;

public class Routs {
    public  static String baseUri="http://localhost:3001";
    public static String getAllBookings=baseUri+"/booking";
    public static String getBookingByID=baseUri+"/booking/{id}";
    public static String createBooking=baseUri+"/booking";
    public static String deleteBooking=baseUri+"/booking/{id}";
    public static String updateBooking=baseUri+"/booking/{id}";
    public static String auth =baseUri+"/auth";
}
