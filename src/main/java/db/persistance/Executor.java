package db.persistance;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Executor<T> {

    public void execute(String query) throws SQLException{
        try (Connection connection = ConnectionManager.getInstance().getConnection();
             Statement statement = connection.createStatement()
        ) {
            statement.execute(query);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public List<T> executeSelect(String query, Extractor<T> extractor) throws SQLException{
        try (Connection connection = ConnectionManager.getInstance().getConnection();
             Statement statement = connection.createStatement()
        ) {
            return extractor.extract(statement.executeQuery(query));
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
