package com.codecool.finastra.filter;

//Filter to control users accessing resources
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

//Filter everything by default
@WebFilter(urlPatterns = {"/*"})
public class SessionFilter implements Filter {

    //Filter exclude array
    final static private List<String> filterExclude = Arrays.asList("login", "index", "css");

    //If the session is valid, or the requested resource is accessible without authentication
    //accept it and set header to no-store cache
    //Else redirect to index.html
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest servletRequest = (HttpServletRequest) req;
        HttpServletResponse servletResponse = (HttpServletResponse) resp;

        if (servletRequest.getSession(false) != null ||
                filterExclude.stream().anyMatch(x -> servletRequest.getRequestURI().contains(x))) {

            servletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            servletResponse.setHeader("Pragma", "no-cache");
            servletResponse.setDateHeader("Expires", 0);
            chain.doFilter(req, resp);
        } else {
            servletResponse.sendRedirect("index.html");
        }
    }

}
