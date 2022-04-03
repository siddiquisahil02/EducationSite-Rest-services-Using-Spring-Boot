package com.camp.educationalsite.utils.Error;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAccesDenied implements AccessDeniedHandler{

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
                response.addHeader("Content-Type", "application/json");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                PrintWriter writer = response.getWriter();
                ObjectNode node = new ObjectMapper().readValue("{\"code\":\"403\", \"message\":\"You don't have the permissions to access this resource.\"}",ObjectNode.class);
                writer.print(node);
        
    }
    
}
