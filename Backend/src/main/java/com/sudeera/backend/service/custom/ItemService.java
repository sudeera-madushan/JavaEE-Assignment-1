package com.sudeera.backend.service.custom;

import com.sudeera.backend.dto.ItemDTO;
import com.sudeera.backend.service.SuperService;

import java.util.List;

public interface ItemService extends SuperService {
    List<ItemDTO> getAllItems();

    ItemDTO findByPk(String code);
}
