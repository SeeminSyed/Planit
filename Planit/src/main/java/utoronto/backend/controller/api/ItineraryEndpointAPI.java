package utoronto.backend.controller.api;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utoronto.backend.controller.business.itinerary.Event;
import utoronto.backend.controller.business.itinerary.Itinerary;
import utoronto.backend.controller.business.itinerarybuilder.ItineraryBuilder;
import utoronto.backend.controller.business.itinerarybuilder.ItineraryBuilderImpl;
import utoronto.backend.controller.business.utils.EventCreateHelper;
import utoronto.backend.controller.business.utils.JSONExchangeConverter;
import utoronto.backend.controller.business.validation.ValidationImpl;
import utoronto.backend.model.service.DAOImpl;
import utoronto.backend.model.service.insertdao.InsertDAO;
import utoronto.backend.model.service.retrievedao.RetrieveDAO;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ItineraryEndpointAPI implements HttpHandler {

	private InsertDAO insertDAO;
	private RetrieveDAO retrieveDAO;
	private utoronto.backend.model.service.DAO mockDAO = new DAOImpl();
	private EventCreateHelper eventCreate;

	public ItineraryEndpointAPI(InsertDAO insertDAO, RetrieveDAO retrieveDAO) {
		this.insertDAO = insertDAO;
		this.retrieveDAO = retrieveDAO;
		this.eventCreate = new EventCreateHelper(/*insertDAO, retrieveDAO, updateDAO*/);
	}

	public void handle(HttpExchange exchange) throws IOException {

		if (exchange.getRequestMethod().equals("PUT")) {
			handlePut(exchange);
		} else if (exchange.getRequestMethod().equals("GET")) {
			handleGet(exchange);
		} else if (exchange.getRequestMethod().equals("POST")) {
			handlePost(exchange);
		}
	}

	private void handleGet(HttpExchange exchange) throws IOException {
		int responseNumber = 500;
		String response = "";
		try {

			JSONObject body = JSONExchangeConverter.convertFromGetToJSON(exchange, 
				new String[] {"user_id", "itinerary_id", "all_id"});
			JSONObject json = null;

			if (body.has("user_id")) {

				String userId = body.getString("user_id");
				if (body.has("all_id") && body.getBoolean("all_id")) {
					// Retrieve all itineraries created for user. 
					json = retrieveDAO.retrievePreviousItinerariesByUserId(userId);
				} else {
					// Retrieve the most recently created itinerary for user.
					json = retrieveDAO.retrieveMostRecentlyCreatedItineraryByUserId(userId);
				}

				response = json.toString();
				responseNumber = 200;
			} else if (body.has("itinerary_id")) {
				int itineraryId = body.getInt("itinerary_id");

				// Retrieve an itinerary by its id.
				json = retrieveDAO.retrieveItineraryByItineraryId(itineraryId);
				response = json.toString();
				responseNumber = 200;
			}
			else {
				responseNumber = 400;
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseNumber = 500;
		} finally {
			exchange.sendResponseHeaders(responseNumber, response.getBytes().length);
			exchange.getResponseHeaders().set("Content-Type", "application/json");
			OutputStream os = exchange.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}
	}

	public void handlePost(HttpExchange exchange) throws IOException {
		int responseNumber = 500;
		String response = "";
		try {
			JSONObject body = JSONExchangeConverter.convert(exchange);
			if (body.has("user_id") &&
					body.has("city") &&
					body.has("country") &&
					body.has("date") &&
					body.has("startTime") &&
					body.has("endTime") &&
					body.has("duration") &&
					body.has("participants") &&
					body.has("maxBudget") &&
					body.has("transportation") &&
					body.has("maxDistance") &&
					body.has("attractions")
					) {
				// get data from request
				String user_id = body.getString("user_id");
				String city = body.getString("city");
				String country = body.getString("country");
				String date = body.getString("date").replace("//", "");
				String startTime = body.getString("startTime");
				String endTime = body.getString("endTime");
				int duration = body.getInt("duration");
				double maxDistance = body.getDouble("maxDistance");
				int participants = Integer.parseInt(body.getString("participants"));
				double maxBudget = Double.parseDouble(body.getString("maxBudget"));
				String attractions = body.getString("attractions");
				String transportation = body.getString("transportation");

				// create itinerary object
				Itinerary newItinerary = createItinerary(user_id, city, country, date, startTime, endTime, duration, maxDistance,
						participants, maxBudget, attractions, transportation);
				//				newItinerary.getEvents
				// put itinerary object in response
				if (newItinerary.getEvents().size() <= 0) {
					response = "";
					responseNumber = 404;
				} else {
					response = newItinerary.toJson();
					responseNumber = 200;
				}
			} else {
				responseNumber = 400;
			}
		} catch (JSONException e) {
			responseNumber = 400;
			e.printStackTrace();
		} catch (Exception e) {
			responseNumber = 500;
			e.printStackTrace();
		} finally {
			exchange.sendResponseHeaders(responseNumber, response.getBytes().length);
			exchange.getResponseHeaders().set("Content-Type", "application/json");
			OutputStream os = exchange.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}
	}

	private void handlePut(HttpExchange exchange) throws IOException {
		int responseNumber = 500;
		String response = "";
		try {
			JSONObject body = JSONExchangeConverter.convert(exchange);
			if (body.has("user_id") &&
					body.has("city") &&
					body.has("country") &&
					body.has("date") &&
					body.has("startTime") &&
					body.has("endTime") &&
					body.has("duration") &&
					body.has("participants") &&
					body.has("maxBudget") &&
					body.has("transportation") &&
					body.has("maxDistance") &&
					body.has("attractions") &&
					body.has("events")
					) {
				// get data from request
				String user_id = body.getString("user_id");
				String city = body.getString("city");
				String country = body.getString("country");
				String date = body.getString("date").replace("//", "");
				String startTime = body.getString("startTime");
				String endTime = body.getString("endTime");
				int duration = body.getInt("duration");
				double maxDistance = body.getDouble("maxDistance");
				int participants = Integer.parseInt(body.getString("participants"));
				double maxBudget = Double.parseDouble(body.getString("maxBudget"));
				String attractions = body.getString("attractions");
				String transportation = body.getString("transportation");

				int itinerary_id = insertDAO.insertItinerary(user_id, city, country, date, startTime, endTime, duration, maxDistance,
						participants, maxBudget, attractions, transportation);
				JSONArray events = body.getJSONArray("events");
				int i = 0;
				while (i < events.length()) {
					JSONObject itineraryEvents = events.getJSONObject(i);
					String eventStartTime = itineraryEvents.getString("eventStartTime");
					String eventEndTime = itineraryEvents.getString("eventEndTime");
					int totalTime = itineraryEvents.getInt("totalTime");

					JSONObject event = itineraryEvents.getJSONObject("event");
					String eventId = event.getString("eventId");
					String name = event.getString("name");
					String address = event.getString("address");
					double averageRating = event.getDouble("averageRating");
					String details = event.getString("details");
					String contact = event.getString("contact");
					Double cost = event.getDouble("cost");
					String category = event.getString("category");
					insertDAO.insertEvent(eventId, name, address, averageRating, details, contact, cost, category);
//					System.out.print();
					insertDAO.insertItineraryEvents(itinerary_id, eventId, eventStartTime, eventEndTime, totalTime);
					i+= 1;
				}
				responseNumber = 200;
			} else {
				responseNumber = 400;
			}
		} catch (JSONException e) {
			responseNumber = 400;
			e.printStackTrace();
		} catch (Exception e) {
			responseNumber = 500;
			e.printStackTrace();
		} finally {
			exchange.sendResponseHeaders(responseNumber, response.getBytes().length);
			exchange.getResponseHeaders().set("Content-Type", "application/json");
			OutputStream os = exchange.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}
	}


	// given the details, creates and returns an itinerary object with events
	public Itinerary createItinerary(
			String user_id, String city, String country, String date, String startTime, String endTime, int duration,
			double maxDistance, int participants, double maxBudget, String attractions, String transportation) {
		ItineraryBuilder builder = new ItineraryBuilderImpl(new ValidationImpl());
		builder.setUser_id(user_id);
		builder.setCity(city, country);
		builder.setCountry(country);
		builder.setDate(date);
		builder.setStartTime(startTime);
		builder.setEndTime(endTime);
		builder.setDuration(duration);
		builder.setMaxDistance(maxDistance);
		builder.setparticipants(String.valueOf(participants));
		builder.setMaxBudget(maxBudget);
		builder.setChosenAttractions(attractions);
		builder.setTransportationModes(transportation);

		List<Event> events = eventCreate.getItineraryEvents(city, country, Double.valueOf(maxBudget), startTime, endTime);

		builder.setEvents(events);

		return builder.build();

	}
}

