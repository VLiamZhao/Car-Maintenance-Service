package org.mynode.filter;



import org.mynode.model.Customer;
import org.mynode.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Claims;

import org.mynode.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@WebFilter(filterName = "securityFilter", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST})
public class SecurityFilter implements Filter {
    @Autowired
    JWTService jwtService;
    @Autowired
    CustomerService customerService;
    private Logger logger = LoggerFactory.getLogger(getClass());
    private static final Set<String> IGNORED_PATHS = new HashSet<>(Arrays.asList("/auth"));


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        int statusCode = authorization(req);
        if (statusCode == HttpServletResponse.SC_ACCEPTED) filterChain.doFilter(request, response);
        else ((HttpServletResponse)response).sendError(statusCode);
    }


    private int authorization(HttpServletRequest req) {
        int statusCode = HttpServletResponse.SC_UNAUTHORIZED;
        String uri = req.getRequestURI();
        String verb = req.getMethod();
        if (IGNORED_PATHS.contains(uri)) return HttpServletResponse.SC_ACCEPTED;

        try{
            String token = req.getHeader("Authorization").replaceAll("^(.*?) ", "");
            if (token == null || token.isEmpty()) return statusCode;

            Claims claims = jwtService.decodeJwtToken(token);
            String allowedResources = "/";
            switch(verb) {
              case "GET"    : allowedResources = (String)claims.get("allowedReadResources");   break;
              case "POST"   : allowedResources = (String)claims.get("allowedCreateResources"); break;
              case "PUT"    : allowedResources = (String)claims.get("allowedUpdateResources"); break;
              case "DELETE" : allowedResources = (String)claims.get("allowedDeleteResources"); break;
            }

            for (String s : allowedResources.split(",")) {
              if (uri.trim().toLowerCase().startsWith(s.trim().toLowerCase())) {
                  statusCode = HttpServletResponse.SC_ACCEPTED;
                  break;
              }
            }

            logger.debug(String.format("Verb: %s, allowed resources: %s", verb, allowedResources));

            if (claims.getId() != null) {
                Customer c = customerService.getCustomerById(Long.valueOf(claims.getId()));
                if (c != null) statusCode = HttpServletResponse.SC_ACCEPTED;
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return statusCode;
    }
}