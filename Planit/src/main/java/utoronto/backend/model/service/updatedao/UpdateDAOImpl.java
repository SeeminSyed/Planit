package utoronto.backend.model.service.updatedao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateDAOImpl implements UpdateDAO {

    private final Connection connection;

    public UpdateDAOImpl(Connection connection) {
        this.connection = connection;
    }

    // update the active attribute to false for a given user id
	public void deactivateUser(String user_id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
            "UPDATE users SET active = false WHERE users.user_id = ?");
            statement.setString(1, user_id);
        statement.execute();
		
    }
}