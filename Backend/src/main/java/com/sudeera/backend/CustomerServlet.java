package com.sudeera.backend;

import com.sudeera.backend.dao.custom.impl.CustomerDAOImpl;
import com.sudeera.backend.service.ServiceFactory;
import com.sudeera.backend.service.ServiceTypes;
import com.sudeera.backend.service.SuperService;
import com.sudeera.backend.service.custom.CustomerService;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CustomerServlet extends HttpServlet {
    Jsonb jsonb=JsonbBuilder.create();
    CustomerService customerService=ServiceFactory.getInstance().getService(ServiceTypes.CUSTOMER);
    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if (req.getContentType()==null || !req.getContentType().toLowerCase().startsWith("application/json")){
//            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
//        }
//        Jsonb jsonb = JsonbBuilder.create();
//        CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);
//        {
//            boolean b = customerService.saveCustomer(customerDTO);
//            try {
//                System.out.println(b);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }


        if (req.getContentType()==null || !req.getContentType().toLowerCase().startsWith("application/json")){
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
        CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);
        System.out.println(customerDTO);
        customerDTO = customerService.deleteCustomer(customerDTO);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("application/json");
        jsonb.toJson(customerDTO,resp.getWriter());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType()==null || !req.getContentType().toLowerCase().startsWith("application/json")){
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
        String id = req.getParameter("id");
        if (id!=null) {
            CustomerDTO customer = customerService.getCustomer(Integer.parseInt(id));
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            jsonb.toJson(customer,resp.getWriter());
        }else {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("application/json");
            jsonb.toJson(customerService.getAllCustomers(),resp.getWriter());
        }

//        List<CustomerDTO> allCustomers = customerService.getAllCustomers();

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if (req.getContentType()==null || !req.getContentType().toLowerCase().startsWith("application/json")){
//            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
//        }
//        CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);
//        customerDTO = customerService.deleteCustomer(customerDTO);
//        resp.setStatus(HttpServletResponse.SC_OK);
//        resp.setContentType("application/json");
//        jsonb.toJson(customerDTO,resp.getWriter());
        System.out.println("YEEE");;
    }

}
