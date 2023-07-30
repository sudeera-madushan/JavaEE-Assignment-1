package com.sudeera.backend.controller;

import com.sudeera.backend.CustomerDTO;
import com.sudeera.backend.dto.ItemDTO;
import com.sudeera.backend.service.ServiceFactory;
import com.sudeera.backend.service.ServiceTypes;
import com.sudeera.backend.service.custom.CustomerService;
import com.sudeera.backend.service.custom.ItemService;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ItemController extends HttpServlet {
    Jsonb jsonb= JsonbBuilder.create();
    ItemService itemService= ServiceFactory.getInstance().getService(ServiceTypes.ITEM);
    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType()==null || !req.getContentType().toLowerCase().startsWith("application/json")){
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
        Jsonb jsonb = JsonbBuilder.create();
        ItemDTO itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);
        System.out.println(itemDTO);
        {
            boolean b = itemService.saveItem(itemDTO);
            try {
                System.out.println(b);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType()==null || !req.getContentType().toLowerCase().startsWith("application/json")){
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
        String id = req.getParameter("id");
        if (id!=null) {
            ItemDTO item = itemService.getItem(Integer.parseInt(id));
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            jsonb.toJson(item,resp.getWriter());
        }else {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            jsonb.toJson(itemService.getAllItems(),resp.getWriter());
        }

//        List<CustomerDTO> allCustomers = customerService.getAllCustomers();

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType()==null || !req.getContentType().toLowerCase().startsWith("application/json")){
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
        ItemDTO itemDTO = jsonb.fromJson(req.getReader(), ItemDTO.class);
        itemDTO = itemService.deleteItem(itemDTO);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("application/json");
        jsonb.toJson(itemDTO,resp.getWriter());
    }

}
