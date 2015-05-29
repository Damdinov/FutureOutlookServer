package com.damdinov.server;

import java.sql.SQLException;
import java.util.List;


public interface ModelDao {

    public void addModel(ModelsEntity model) throws SQLException;
    public void deleteModel(ModelsEntity model) throws SQLException;
    public List getModel(double latitude, double longitude) throws SQLException;
    public List getModels() throws SQLException;
}
