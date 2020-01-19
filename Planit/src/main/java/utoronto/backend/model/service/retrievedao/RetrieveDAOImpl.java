package utoronto.backend.model.service.retrievedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Retrieves an itinerary from the database.
 */
public class RetrieveDAOImpl implements RetrieveDAO {
	// The Connection to the postgres database.
	private final Connection connection;

	public RetrieveDAOImpl(Connection connection) {
		this.connection = connection;
	}

	/**
	 *  Return the most recently created itinerary associated to this user.
	 */
	public JSONObject retrieveMostRecentlyCreatedItineraryByUserId(String userId) throws SQLException, JSONException {

		PreparedStatement statement = null;
		// Return the most recently created itinerary associated to this user.
		statement = connection.prepareStatement(
						"SELECT * "
						+ "FROM itineraries "
						+ "WHERE user_id = ? "
						+ "ORDER BY itinerary_id DESC "
						+ "LIMIT 1 ;");

		statement.setString(1, userId);
		ResultSet results = statement.executeQuery();
		return returnJSONForOneItinerary(results);
	}

	/**
	 *  Return all itineraries associated to this user.
	 */
	public JSONObject retrievePreviousItinerariesByUserId(String userId) throws SQLException, JSONException {

		PreparedStatement statement = null;
		// Return all itineraries associated to this user.
		statement = connection.prepareStatement(
						"SELECT itinerary_id, date, city, country "
						+ "FROM itineraries "
						+ "WHERE user_id = ? "
						+ "ORDER BY itinerary_id DESC ;");

		statement.setString(1, userId);
		ResultSet results = statement.executeQuery();
		
		JSONObject json = new JSONObject();
		JSONArray itinerariesArray = new JSONArray();
		while (results.next()) {
			JSONObject itinerary = new JSONObject();
			itinerary.put("id", results.getString("itinerary_id"));
			itinerary.put("country", results.getString("country"));
			itinerary.put("city", results.getString("city"));
			itinerary.put("date", results.getString("date"));
			itinerariesArray.put(itinerary);
		}
		
		json.put("itineraries", itinerariesArray);
		return json;
	}

	/**
	 * Return an itinerary by its id.
	 */
	public JSONObject retrieveItineraryByItineraryId(int itineraryId) throws SQLException, JSONException {

		PreparedStatement statement = connection.prepareStatement(
						"SELECT * "
						+ "FROM itineraries "
						+ "WHERE itinerary_id = ? ;");

		statement.setInt(1, itineraryId);
		ResultSet results = statement.executeQuery();
		JSONObject json = returnJSONForOneItinerary(results);

		return json;
	}

	@Override
	public JSONArray getAttraction() throws SQLException {
		PreparedStatement statement = connection.prepareStatement(
				"SELECT * FROM attraction;");
		ResultSet results = statement.executeQuery();
		JSONArray jsonArray = new JSONArray();

		while (results.next()) {
			jsonArray.put(results.getString("attraction_name"));
		}

		return jsonArray;
	}

	private JSONArray retrieveItineraryEventsByItineraryId(String itinerary_id) throws SQLException, JSONException {
		PreparedStatement statement = connection.prepareStatement(
						"SELECT * "
						+ "FROM itineraryEvents INNER JOIN  events on itineraryEvents.event_id = events.event_id "
						+ "WHERE itineraryEvents.itinerary_id = ? "
						+ "ORDER BY itineraryEvents.event_order ASC ;");
		statement.setInt(1, Integer.valueOf(itinerary_id));

		ResultSet results = statement.executeQuery();
		JSONArray itineraryEvents = new JSONArray();

		while (results.next()) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("eventStartTime", results.getString("eventStartTime"));
			jsonObject.put("eventEndTime", results.getString("eventEndTime"));
			jsonObject.put("totalTime", results.getInt("totalTime"));

			JSONObject events = new JSONObject();
			events.put("eventId", results.getString("event_id"));
			events.put("name", results.getString("name"));
			events.put("address", results.getString("address"));
			events.put("averageRating", results.getDouble("averageRating"));
			events.put("details", results.getString("details"));
			events.put("contact", results.getString("contact"));
			events.put("cost", results.getDouble("cost"));
			events.put("category", results.getString("category"));
			jsonObject.put("event", events);

			itineraryEvents.put(jsonObject);
		}
		return itineraryEvents;
	}

	private JSONObject returnJSONForOneItinerary(ResultSet results) throws JSONException, SQLException {
		JSONObject json = new JSONObject();
		while (results.next()) {
			// System.out.println("lala la la la la");
			json.put("id", results.getString("itinerary_id"));
			json.put("user_id", results.getString("user_id"));
			json.put("transportation", results.getString("transportation"));
			json.put("participants", results.getInt("participants"));
			json.put("maxDistance", results.getDouble("maxDistance"));
			json.put("maxBudget", results.getDouble("maxBudget"));
			json.put("country", results.getString("country"));
			json.put("city", results.getString("city"));
			json.put("date", results.getString("date"));
			json.put("startTime", results.getString("startTime"));
			json.put("endTime", results.getString("endTime"));
			json.put("duration", results.getInt("duration"));
			json.put("attractions", results.getString("attractions"));
			JSONArray itineraryResults = retrieveItineraryEventsByItineraryId(json.getString("id"));
			json.put("events", itineraryResults);
		}

		return json;
	}
}