package ua.nure.sivolotskiy.repository.sql;

public interface LimitBuilder extends PreparedStatementBuilder {

    PreparedStatementBuilder limit(int offset, int size);

}