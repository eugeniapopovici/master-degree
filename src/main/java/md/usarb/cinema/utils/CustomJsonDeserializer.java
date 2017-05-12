//package md.usarb.cinema.utils;
//
//import flexjson.JSONDeserializer;
//import flexjson.factories.BooleanAsStringObjectFactory;
//import flexjson.transformer.DateTransformer;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//
//
///**
// * This class is meant to transform {@link flexjson.JSON} to {@link Object}
// *
// * @author Natalia Balan
// */
//public class CustomJsonDeserializer {
//
//    private static final DateTransformer DATE_TRANSFORMER = new DateTransformer("MM/dd/yyyy");
//
//    private static ShowingDao<Showing> showingDao = new ShowingDao<>();
//    private static CategoryDao<Category> categoryDao = new CategoryDao<>();
//    private static CustomerDao<Customer> customerDao = new CustomerDao<>();
//
//    /**
//     * This method transforms @link flexjson.JSON} to {@link SearchFilter}
//     *
//     * @param json the json string
//     * @return the {@link SearchFilter} object
//     */
//    public static SearchFilter jsonDeserializer(String json){
//        return new JSONDeserializer<SearchFilter>().use(Boolean.class, new BooleanAsStringObjectFactory("true", "false"))
//                .use(Date.class, DATE_TRANSFORMER).deserialize(json, SearchFilter.class);
//    }
//
//    /**
//     * This method transforms @link flexjson.JSON} to {@link Booking}
//     *
//     * @param json the json string
//     * @return the {@link Booking} object
//     */
//    public static Booking jsonBookingDeserializer(String json){
//        CustomJsonDeserializer customJsonDeserializer = new CustomJsonDeserializer();
//
//        Booking booking = new Booking();
//        HashMap<String, Object> jsonDesiarialiseMap = new JSONDeserializer<HashMap<String, Object>>().deserialize(json);
//
//        if (jsonDesiarialiseMap != null && !jsonDesiarialiseMap.isEmpty() && jsonDesiarialiseMap.size() > 2) {
//            booking = customJsonDeserializer.addShowingFromDeserialiseMap(booking, jsonDesiarialiseMap, customJsonDeserializer);
//            booking = customJsonDeserializer.addCategoryFromDeserialiseMap(booking, jsonDesiarialiseMap);
//            booking = customJsonDeserializer.addParsedDateFromDeserialiseMap(booking, jsonDesiarialiseMap);
//
//            Customer customer = new Customer();
//            customer.setId(1L);
//            booking.setCustomer(customer);
//        }
//
//        return booking;
//    }
//
//    private Booking addShowingFromDeserialiseMap(Booking booking, HashMap<String, Object> jsonDesiarialiseMap, CustomJsonDeserializer customJsonDeserializer) {
//        Long movieId = null;
//        Long cinemaId;
//        Long performanceId = null;
//
//        Showing showing = null;
//
//        String movieIdStr = (String) jsonDesiarialiseMap.get("showing.movie.id");
//        if (movieIdStr != null && !movieIdStr.isEmpty()) {
//            movieId = Long.valueOf(movieIdStr);
//        }
//
//        cinemaId = (Long) jsonDesiarialiseMap.get("showing.cinema.id");
//
//        String performanceIdStr = (String) jsonDesiarialiseMap.get("showing.performance.id");
//        if (performanceIdStr != null && !performanceIdStr.isEmpty()) {
//            performanceId = Long.valueOf(performanceIdStr);
//        }
//
//        List<Showing> showings = null;
//        if (movieId != null && cinemaId != null) {
//            showings = showingDao.loadShowingsByCinemaAndMovieIds(cinemaId, movieId);
//        }
//
//        if (showings != null && !showings.isEmpty()) {
//            showing = customJsonDeserializer.getShowingFromShowingListByPerformance(showings, performanceId);
//        }
//
//        if (showing != null) {
//            booking.setShowing(showing);
//        }
//
//        return booking;
//    }
//
//    private Booking addCategoryFromDeserialiseMap(Booking booking, HashMap<String, Object> jsonDesiarialiseMap) {
//        Long categoryId;
//
//        categoryId = (Long) jsonDesiarialiseMap.get("category.id");
//        if (categoryId != null) {
//            Category category = new Category();
//            category.setId(categoryId);
//            booking.setCategory(category);
//        }
//
//        return booking;
//    }
//
//    private Booking addParsedDateFromDeserialiseMap(Booking booking, HashMap<String, Object> jsonDesiarialiseMap) {
//        String bookingForDateStr = (String) jsonDesiarialiseMap.get("bookingForDate");
//        if (bookingForDateStr != null && !bookingForDateStr.isEmpty()) {
//            DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//            LocalDate bookingForDate = LocalDate.parse(bookingForDateStr, FORMATTER);
//
//            if (bookingForDate != null) {
//                booking.setBookingForDate(bookingForDate);
//            }
//        }
//
//        return booking;
//    }
//
//    private Showing getShowingFromShowingListByPerformance( List<Showing> showings, Long performanceId) {
//        Showing showing;
//        showing = showings.get(0);
//
//        if (performanceId != null) {
//            for (Showing showingNew: showings) {
//                if (showingNew.getPerformance()!= null && showingNew.getPerformance().getId().equals(performanceId)) {
//                    showing = showingNew;
//                }
//            }
//        }
//
//        return showing;
//    }
//
//}