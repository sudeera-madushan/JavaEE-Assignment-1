package com.sudeera.backend.service.custom.impl;

import com.sudeera.backend.FactoryConfiguration;
import com.sudeera.backend.dao.custom.ItemDAO;
import com.sudeera.backend.dao.util.DaoFactory;
import com.sudeera.backend.dao.util.DaoTypes;
import com.sudeera.backend.dto.ItemDTO;
import com.sudeera.backend.service.custom.ItemService;
import com.sudeera.backend.service.util.Convertor;
import org.hibernate.Session;

import java.util.List;
import java.util.stream.Collectors;

public class ItemServiceImpl implements ItemService {

    private final Session session;
    private final Convertor convertor;
    private ItemDAO itemDAO;

    public ItemServiceImpl() {
        session = FactoryConfiguration.getInstance().getSession();
        convertor=new Convertor();
        itemDAO= DaoFactory.getInstance().getDAO(session, DaoTypes.ITEM);
    }


    @Override
    public List<ItemDTO> getAllItems() {
        return itemDAO.getAll().stream().map(item -> convertor.fromItem(item)).collect(Collectors.toList());
    }

    @Override
    public ItemDTO findByPk(String code) {
        return convertor.fromItem(itemDAO.findByPk(code));
    }
}
