package utoronto.backend.controller.connection;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import utoronto.backend.Globals;
import utoronto.backend.controller.api.AttractionsAPI;
import utoronto.backend.controller.api.ChangeEventAPI;
import utoronto.backend.controller.api.DeactivateUserApi;
import utoronto.backend.controller.api.ItineraryEndpointAPI;
import utoronto.backend.controller.api.RatingsEndpointApi;
import utoronto.backend.controller.api.SearchAPI;
import utoronto.backend.controller.api.TransportationAPI;
import utoronto.backend.controller.api.UserRegistrationApi;
import utoronto.backend.controller.api.ValidateLocationApi;
import utoronto.backend.model.service.insertdao.InsertDAO;
import utoronto.backend.model.service.insertdao.InsertDAOImpl;
import utoronto.backend.model.service.retrievedao.RetrieveDAO;
import utoronto.backend.model.service.retrievedao.RetrieveDAOImpl;
import utoronto.backend.model.service.updatedao.UpdateDAO;
import utoronto.backend.model.service.updatedao.UpdateDAOImpl;

/**
 * The connection to the HTTP Server.
 */
public class HttpServerConnectionImpl implements MyConnection<HttpServer> {

    private HttpServer server;
    private static HttpServerConnectionImpl instance;

    private HttpServerConnectionImpl() {
    }

    public static HttpServerConnectionImpl get() {
        if (instance == null)
            instance = new HttpServerConnectionImpl();
        return instance;
    }

    public boolean connect() {
        if (server != null)
            return false;
        try {
            server = HttpServer.create(new InetSocketAddress("0.0.0.0", Globals.PORT), 0);
            server.createContext(Globals.validateLocationApi, new ValidateLocationApi());
            // using dependency injection to add the registration api

            InsertDAO insertDAO = new InsertDAOImpl(Globals.database.getConnectorObject());
            UpdateDAO updateDAO = new UpdateDAOImpl(Globals.database.getConnectorObject());
            RetrieveDAO retrieveDAO = new RetrieveDAOImpl(Globals.database.getConnectorObject());

            server.createContext(Globals.validateLocationApi, new ValidateLocationApi());
            server.createContext(Globals.userRegistrationtApi, new UserRegistrationApi(insertDAO));
            server.createContext(Globals.ratingsEndpointApi, new RatingsEndpointApi(insertDAO));
            server.createContext(Globals.deactivateUserApi, new DeactivateUserApi(updateDAO));
            server.createContext(Globals.itineraryEndpointApi, new ItineraryEndpointAPI(insertDAO, retrieveDAO));
            server.createContext(Globals.TransportationApi, new TransportationAPI());
            server.createContext(Globals.AttractionsApi, new AttractionsAPI(retrieveDAO, insertDAO));
            server.createContext(Globals.SearchApi, new SearchAPI());
            server.createContext(Globals.ChangeEventApi, new ChangeEventAPI());
            server.start();
            System.out.println("Server successfully started on port " + Globals.PORT);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void close() {
        server.stop(0);
    }

    public HttpServer getConnectorObject() {
        return server;
    }
}