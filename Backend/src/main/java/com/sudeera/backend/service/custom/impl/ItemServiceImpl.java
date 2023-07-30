package com.sudeera.backend.service.custom.impl;

import com.sudeera.backend.CustomerDTO;
import com.sudeera.backend.FactoryConfiguration;
import com.sudeera.backend.dao.custom.ItemDAO;
import com.sudeera.backend.dao.util.DaoFactory;
import com.sudeera.backend.dao.util.DaoTypes;
import com.sudeera.backend.dto.ItemDTO;
import com.sudeera.backend.entity.Item;
import com.sudeera.backend.service.custom.ItemService;
import com.sudeera.backend.service.util.Convertor;
import org.hibernate.Session;

import java.util.List;
import java.util.stream.Collectors;

public class ItemServiceImpl implements ItemService {

    private final ItemDAO itemDAO;
    private final Convertor convertor;
    private final Session session;

    public ItemServiceImpl() {
        session = FactoryConfiguration.getInstance().getSession();
        convertor=new Convertor();
        itemDAO = DaoFactory.getInstance().getDAO(session, DaoTypes.ITEM);
    }
    @Override
    public boolean saveItem(ItemDTO itemDTO) {
        System.out.println(itemDTO);
        Item item = itemDAO.save(convertor.toItem(itemDTO));
        if (item==null){
            return false;
        }
        return true;
    }
    @Override
    public ItemDTO deleteItem(ItemDTO item) {
        Item cu = itemDAO.delete(new Item(item.getId()));
        if (cu==null){
            return convertor.fromItem(cu);
        }
        return null;
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return itemDAO.getAll().stream().map(item -> convertor.fromItem(item)).collect(Collectors.toList());
    }

    @Override
    public ItemDTO getItem(Integer code) {
        Item item = itemDAO.findByPk(code);
        if (item==null){
            return null;
        }
        return convertor.fromItem(item);
    }

}
