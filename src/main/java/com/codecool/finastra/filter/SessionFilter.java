package com.codecool.finastra.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.codecool.finastra.Filter;

@WebFilter(urlPatterns = {"/accounthistory","/bankaccount","/logout",
		"/transactionpage","/transfer","/transaction", "/transition",
		"/transaction.html","/accounthistory.html","/bankaccount.html"})
public class SessionFilter extends Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain cha)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) req;
		HttpServletResponse httpServletResponse = (HttpServletResponse) resp;
		HttpSession session = httpServletRequest.getSession(false);
		if(session == null){
			httpServletResponse.sendRedirect("index.html");
		} else {
			cha.doFilter(req, resp);
		}
	}
	

}
