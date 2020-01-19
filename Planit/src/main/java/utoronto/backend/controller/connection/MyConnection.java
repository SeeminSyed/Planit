package utoronto.backend.controller.connection;


/**
 * An interface encapsulating a connection to a service.
 * This interface provides an abstract implementation to closing
 * connections so they can be dealt with easily. 
 */ 
public interface MyConnection<T> {

    /**
     * Connect to the service.
     * @return if the connection was successful.
     */
    public boolean connect();

    /**
     * Immediately close the connection to the service.
     */
    public void close();

    /**
     * @return The object instance that the implemented class encapsulates.
     */
    public T getConnectorObject();

}