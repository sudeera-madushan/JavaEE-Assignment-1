package com.sudeera.backend.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Old
@WebFilter(filterName = "CORSFilter",urlPatterns = "/*")
public class CORSFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Allow requests from any origin
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");

        // Allow specific HTTP methods (e.g., GET, POST, OPTIONS, etc.)
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, OPTIONS");

        // Allow specific HTTP headers
        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        // Enable CORS credentials (if required, set to "true")
        httpResponse.setHeader("Access-Control-Allow-Credentials", "false");

        // Set the max age for preflight requests (cache the options response)
        httpResponse.setHeader("Access-Control-Max-Age", "3600");
        httpResponse.setHeader("Access-Control-Expose-Headers", "Content-Type");
        // Continue the filter chain
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

//    @Override
//    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
//        try {
//            String origin = req.getHeader("Origin");
//            if (origin.contains("http://localhost:63342")) {
//                res.setHeader("Access-Control-Allow-Origin", origin);
//                res.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,HEADER");
//                res.setHeader("Access-Control-Allow-Headers", "Content-Type");
//                res.setHeader("Access-Control-Expose-Headers", "Content-Type");
//            }
//            chain.doFilter(req, res);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
}
