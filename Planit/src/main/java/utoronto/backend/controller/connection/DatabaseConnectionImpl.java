package utoronto.backend.controller.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import utoronto.backend.Globals;

/**
 * The connection to the postgresql database.
 */
public class DatabaseConnectionImpl implements MyConnection<Connection> {

    private Connection connection;
    private static DatabaseConnectionImpl instance;

    private DatabaseConnectionImpl() {}

    public static DatabaseConnectionImpl get() {
        if (instance == null) instance = new DatabaseConnectionImpl();
        return instance;
    }


    public boolean connect() {
        if (connection != null) return false;
        try { 
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(Globals.databaseUrl,
            Globals.databaseUserName,
            Globals.databasePassword);
            return true;
        } 
        catch(ClassNotFoundException e1){
            return false;
        }
        catch(SQLException e2){
            return false;
        }
    }

    
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Connection getConnectorObject() {
        return connection;
    }
}