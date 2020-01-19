package utoronto.backend.model.service.updatedao;

import java.sql.SQLException;

/**
 * The interface for updating values in the database.
 */
public interface UpdateDAO {
    
    public void deactivateUser(String user_id) throws SQLException;

}