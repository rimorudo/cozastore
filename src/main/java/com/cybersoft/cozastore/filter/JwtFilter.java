package com.cybersoft.cozastore.filter;

import com.cybersoft.cozastore.Util.JWTHelperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtFilter extends OncePerRequestFilter {

    //Buoc 1: Lay token
    //Buoc 2: Giai ma token
    //Buoc 3: Token hop le tao chung thuc cho Sercutiry
    @Autowired
    JWTHelperUtils jwtHelperUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Lay gia tri tren header
        String header = request.getHeader("Authorization");

        //Giai ma token
        try{
            String token = header.substring(7);

            String data = jwtHelperUtils.validToken(token);
            // Neu token khac ong tuc la hop le thi tao chung thuc
            if(!data.isEmpty()){
                //Tao chung thuc co Security
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("","", new ArrayList<>());
                SecurityContext securityContext = SecurityContextHolder.getContext();
                securityContext.setAuthentication(authenticationToken);
            }
            System.out.println("Kiem tra " + data);
        }catch (Exception e){
                System.out.println("Token khong hop le");
        }


        filterChain.doFilter(request,response);
    }
}
