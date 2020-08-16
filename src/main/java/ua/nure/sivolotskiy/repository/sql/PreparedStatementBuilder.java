package ua.nure.sivolotskiy.repository.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementBuilder {

    PreparedStatement buildPrepareStatement(Connection connection) throws SQLException;

}
