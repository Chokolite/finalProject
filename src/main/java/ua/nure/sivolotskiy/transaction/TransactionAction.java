package ua.nure.sivolotskiy.transaction;

import java.sql.Connection;
import java.sql.SQLException;

public interface TransactionAction<U> {

    U action(Connection connection) throws SQLException;

}