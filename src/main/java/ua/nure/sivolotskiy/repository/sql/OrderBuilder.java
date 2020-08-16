package ua.nure.sivolotskiy.repository.sql;

public interface OrderBuilder extends LimitBuilder, PreparedStatementBuilder {

    LimitBuilder order(String fieldName, boolean ascending);

}
