package com.fourboy.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class CustomerAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse resp) throws AuthenticationException {
        if (req.getContentType().equals("application/json;charset=UTF-8")
                || req.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            ObjectMapper objectMapper = new ObjectMapper();
            UsernamePasswordAuthenticationToken token = null;
            try (ServletInputStream is = req.getInputStream()) {
                Map<String, String> map = objectMapper.readValue(is, Map.class);
                token = new UsernamePasswordAuthenticationToken(map.get("username"), map.get("password"));
            } catch (IOException e) {
                e.printStackTrace();
                token = new UsernamePasswordAuthenticationToken("", "");
            } finally {
                setDetails(req, token);
                return this.getAuthenticationManager().authenticate(token);
            }
        } else {
            return super.attemptAuthentication(req, resp);
        }
    }


}
