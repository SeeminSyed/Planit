package utoronto.backend.model.service.insertdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A basic implementation of the InsertDAO.
 * Does some validation before inserting into the database,
 * as some tables have constraints on them.
 * Breaking these constraints will raise an SQLException.
 */
public class InsertDAOImpl implements InsertDAO {

    // The Connection to the postgres database.
    private final Connection connection;

    public InsertDAOImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * @see {@link InsertDAO#insertLocation(String, String)}
     */
    public void insertLocation(String city, String country) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO Location VALUES (?, ?)");
        statement.setString(1, city);
        statement.setString(2, country);
        statement.execute();
    }

    public void insertRating(String user_id, String attraction_id, int rating) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO Ratings VALUES (?, ?, ?)");
        statement.setString(1, user_id);
        statement.setString(2, attraction_id);
        statement.setInt(3, rating);
        statement.execute();
    }

    public void insertUser(String userId, String username, String password, String email, boolean active) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO users VALUES (?, ?, ?, ?, ?)");
        statement.setString(1, userId);
        statement.setString(2, username);
        statement.setString(3, password);
        statement.setString(4, email);
        statement.setBoolean(5, active);
        statement.execute();
    }

    public int insertItinerary(String user_id, String city, String country, String date, String startTime, String endTime, int duration, double maxDistance,
                               int participants, double maxBudget, String attractions, String transportation) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO itineraries "
                        + "(user_id, city, country, date, startTime, endTime, duration, maxDistance, participants, maxBudget, attractions, transportation)"
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING itinerary_id");
        statement.setString(1, user_id);
        statement.setString(2, city);
        statement.setString(3, country);
        statement.setString(4, date);
        statement.setString(5, startTime);
        statement.setString(6, endTime);
        statement.setInt(7, duration);
        statement.setDouble(8, maxDistance);
        statement.setInt(9, participants);
        statement.setDouble(10, maxBudget);
        statement.setString(11, attractions);
        statement.setString(12, transportation);

        ResultSet rset = statement.executeQuery();

        int result = 0;
        while (rset.next()) {
            result = rset.getInt("itinerary_id");
        }

        return result;
    }

    @Override
    public void insertItineraryEvents(int itinerary_id, String eventId, String eventStartTime, String eventEndTime, int totalTime) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO itineraryEvents VALUES (?, ?, ?, ?, ?)");
        statement.setInt(1, itinerary_id);
        statement.setString(2, eventId);
        statement.setString(3, eventStartTime);
        statement.setString(4, eventEndTime);
        statement.setInt(5, totalTime);
        statement.execute();
        System.out.println("INSERT INTO itineraryEvents VALUES (?, ?, ?, ?, ?)" +
                eventId + ", " + itinerary_id + ", " + eventId + ", " + eventStartTime + ", " + eventEndTime + ", " +  totalTime);
    }

    @Override
    public void insertEvent(String eventId, String name, String address, double averageRating, String details,
                            String contact, Double cost, String category) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO events VALUES (?, ?, ?, ?, ?, ?, ?, ?) ON CONFLICT DO NOTHING");
        statement.setString(1, eventId);
        statement.setString(2, name);
        statement.setString(3, address);
        statement.setDouble(4, averageRating);
        statement.setString(5, details);
        statement.setString(6, contact);
        statement.setDouble(7, cost);
        statement.setString(8, category);
        statement.execute();
//        System.out.println("INSERT INTO events VALUES (?, ?, ?, ?, ?, ?, ?, ?) ON CONFLICT DO NOTHING" +
//                eventId + ", " + name + ", " + address + ", " + averageRating + ", " + details + ", " +  contact + ", " +  cost + ", " +  category);
    }

    @Override
    public void insertAttraction(String id, String name) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO attraction VALUES (?, ?) ON CONFLICT DO NOTHING");
        statement.setString(1, id);
        statement.setString(2, name);
        statement.execute();
    }
}