package utoronto.backend;
import java.sql.Connection;

import utoronto.backend.controller.connection.CloseableHttpClientConnectionImpl;
import utoronto.backend.controller.connection.DatabaseConnectionImpl;
import utoronto.backend.controller.connection.HttpServerConnectionImpl;
import utoronto.backend.controller.connection.MyConnection;

import com.sun.net.httpserver.HttpServer;
import org.apache.http.impl.client.CloseableHttpClient;

public class Globals {

    // The connection to our postgres database
    public static final MyConnection<Connection> database = DatabaseConnectionImpl.get();
    // The connection to our http request sending client
    public static MyConnection<CloseableHttpClient> client = CloseableHttpClientConnectionImpl.get();
    // The connection to the server that recieves requests from the front end
    public static MyConnection<HttpServer> server = HttpServerConnectionImpl.get();
    
    // Theres should be a json file for this but honestly the java is NOT
    // letting me open the file and this sprint is about to END
    
    // Public apis for validating location
    public static String countryValidationUrl = "http://geodb-free-service.wirefreethought.com/v1/geo/countries";
    public static final String cityValidationUrl = "http://geodb-free-service.wirefreethought.com/v1/geo/cities";
    public static final String exploreAPI = "https://api.foursquare.com/v2/venues/explore";
    public static final String detailsAPI = "https://api.foursquare.com/v2/venues/";
    public static final String categoriesAPI = "https://api.foursquare.com/v2/venues/categories";

    public static final int PORT = 8080;
    
    public static final String databaseUrl = "jdbc:postgresql://mathlab.utsc.utoronto.ca/c01f19g1";
	public static final String databaseUserName = "c01f19g1";
    public static final String databasePassword = "c01f19g1";
    
    public static final String clientId = "ZCTFI0ZES0C0AIGDJQJYY2RAZON2DZWZ5CO13HR2VCYYBH20";
    public static final String clientSecret = "N5RD3ONTA32ZLUAQFHUE5Y0NAWV05FBJXSS1WDX1XBLKEJG1";
    public static final String version = "20191212";
    
    public static final String validateLocationApi = "/v1/validateLocation";
    public static final String ratingsEndpointApi = "/v1/ratings";
    public static final String userRegistrationtApi = "/v1/register";
    public static final String deactivateUserApi = "/v1/deactivate";
    public static final String AttractionsApi = "/v1/attractions";
    public static final String itineraryEndpointApi = "/v1/Itinerary";
    public static final String TransportationApi = "/v1/Transportation";
    public static final String SearchApi = "/v1/Search";
    public static final String ChangeEventApi = "/v1/ChangeEvent";
}