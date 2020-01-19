package utoronto.backend.controller.connection;
import utoronto.backend.Globals;

/**
 * The class responsible for opening and closing connections. It
 * was possible to just call all these connection methods at once
 * in App.main(), but this class provides a way to do everything
 * at once.
 */
public class Connector {

    public static void establishConnections() {
        Globals.client.connect();
        Globals.database.connect(); 
        Globals.server.connect();
    }

    public static void closeConnections() {
        Globals.client.close();
        Globals.database.close();
        Globals.server.close();
    }
}