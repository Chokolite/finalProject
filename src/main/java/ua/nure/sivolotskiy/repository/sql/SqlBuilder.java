package ua.nure.sivolotskiy.repository.sql;

import ua.nure.sivolotskiy.repository.sql.impl.SelectBuilderImpl;

public class SqlBuilder {

    public static SelectBuilder select(String selectQuery){
        return new SelectBuilderImpl(selectQuery);
    }

}