package com.sudeera.backend.dao.util;

import com.sudeera.backend.dao.custom.impl.CustomerDAOImpl;
import com.sudeera.backend.dao.custom.impl.ItemDAOImpl;
import org.hibernate.Session;

public class DaoFactory {

    private static DaoFactory daoFactory ;
    private DaoFactory() {
    }

    public static DaoFactory getInstance(){
        return daoFactory==null?(daoFactory=new DaoFactory()):daoFactory;
    }

    public <T extends SuperDAO> T getDAO(Session session, DaoTypes daoType) {
        switch (daoType){
            case ITEM:
                return (T)new ItemDAOImpl(session);

            case CUSTOMER:
                return (T)new CustomerDAOImpl(session);
//            case PLACEORDER:
//                return (T)new PlaceOrderDAOImpl(session);

            default:
                return null;

        }

    }
}
