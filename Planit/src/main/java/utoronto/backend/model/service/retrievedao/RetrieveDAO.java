package utoronto.backend.model.service.retrievedao;

import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

	/**
	 * Retrieves an itinerary from the database.
	 */
	public interface RetrieveDAO {
	    
		public JSONObject retrieveMostRecentlyCreatedItineraryByUserId(String userId) throws SQLException, JSONException;
		
		public JSONObject retrievePreviousItinerariesByUserId(String userId) throws SQLException, JSONException;
	    
		public JSONObject retrieveItineraryByItineraryId(int itineraryId) throws SQLException, JSONException;

		public JSONArray getAttraction() throws SQLException;
	}


