package ua.nure.sivolotskiy.repository.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SelectBuilder extends WhereBuilder {

    FirstWhereBuilder where();

    PreparedStatement buildPrepareStatement(Connection connection) throws SQLException;

}