package com.damdinov.server;

public class Factory {
    public static Factory factory = new Factory();
    public ModelDaoImpl modelDao = null;

    private Factory(){}

    //TODO: not safe for many threads
    public static Factory getInstance(){
        return factory;
    }

    public ModelDaoImpl getModelDao(){
        if (null == modelDao){
            modelDao = new ModelDaoImpl();
        }
        return modelDao;
    }
}
