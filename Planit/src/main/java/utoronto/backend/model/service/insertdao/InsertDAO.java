package utoronto.backend.model.service.insertdao;

import java.sql.SQLException;

/**
 * Inserts a user into the database.
 */
public interface InsertDAO {
    
    /**
     * 
     * This function was originally used to insert locations in the database.
     * @deprecated
     * @param city The city to insert into the database.
     * @param country The country the city is in. 
     */
    @Deprecated
    public void insertLocation(String city, String country) throws SQLException;

    public void insertUser(String userId, String username, String password, String email, boolean active) throws SQLException;
    
    public void insertRating(String user_id, String event_id, int rating) throws SQLException;
    
    public int insertItinerary(String user_id, String city, String country, String date, String startTime, String endTime, int duration, double maxDistance,
            int participants, double maxBudget, String attractions, String transportation) throws SQLException;

	public void insertItineraryEvents(int itinerary_id, String eventId, String eventStartTime, String eventEndTime, int totalTime) throws SQLException;

	public void insertEvent(String eventId, String name, String address, double averageRating, String details,
			String contact, Double cost, String category) throws SQLException;

    public void insertAttraction(String id, String name) throws SQLException;
}