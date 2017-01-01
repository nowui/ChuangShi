package com.shanghaichuangshi.handler;

import com.shanghaichuangshi.model.Model;
import org.apache.commons.dbutils.handlers.AbstractListHandler;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ModelListHandler extends AbstractListHandler<Model<? extends Model>> {

    private Class<? extends Model> modelClass;

    public ModelListHandler(Class<? extends Model> modelClass) {
        this.modelClass = modelClass;
    }

    @Override
    protected Model<? extends Model> handleRow(ResultSet resultSet) throws SQLException {
        Model<? extends Model> model = null;

        try {
            model = modelClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException("InstantiationException:", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("IllegalAccessException:", e);
        }

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            model.put(resultSetMetaData.getColumnLabel(i), resultSet.getObject(i));
        }

        return model;
    }
}
