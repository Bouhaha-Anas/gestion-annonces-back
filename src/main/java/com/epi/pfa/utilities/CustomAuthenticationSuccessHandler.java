package com.epi.pfa.utilities;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler 
{
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication auth) throws IOException, ServletException 
    {
         
        HttpSession session = httpServletRequest.getSession();
        
        auth =  SecurityContextHolder.getContext().getAuthentication();
        session.setAttribute("username", auth.getName());
        session.setAttribute("authorities", auth.getAuthorities());
 
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        
        String beforeURL = httpServletRequest.getHeader("referer");
        Object[] role =  auth.getAuthorities().toArray();
        
        if(role[0].equals("CLIENT"))
        {
        	if( beforeURL.contains("/inscriptionConfirm") )
            {
            	httpServletResponse.sendRedirect("profilClient/mesRecommandations");
            }
        }  
        else
        {
        	httpServletResponse.sendRedirect("accueil");
        }
        
    }
}
