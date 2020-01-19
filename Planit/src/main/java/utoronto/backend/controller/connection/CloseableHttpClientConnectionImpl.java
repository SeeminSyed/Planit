package utoronto.backend.controller.connection;

import java.io.IOException;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * This class encapsulates an HTTP Client. This client lets us send REST requests.
 */
public class CloseableHttpClientConnectionImpl implements MyConnection<CloseableHttpClient> {

    private CloseableHttpClient client;
    private static CloseableHttpClientConnectionImpl instance;

    private CloseableHttpClientConnectionImpl() {}

    public static CloseableHttpClientConnectionImpl get() {
        if (instance == null) instance = new CloseableHttpClientConnectionImpl();
        return instance;
    }

    public boolean connect() {
        if (client != null) return false;
        client = HttpClients.createDefault();
        return true;
    }


    public void close() {
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 
    public CloseableHttpClient getConnectorObject() {
        return client;
    }

}