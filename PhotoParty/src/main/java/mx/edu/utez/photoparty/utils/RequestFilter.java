package mx.edu.utez.photoparty.utils;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(urlPatterns = {"/Photo_Party/*"})
public class RequestFilter implements Filter {

    List<String> whiteList = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        whiteList.add("/Photo_Party/crear_contrato");
        whiteList.add("/Photo_Party/inicio");
        whiteList.add("/Photo_Party/login");
        whiteList.add("/Photo_Party/cabinas");
        whiteList.add("/Photo_Party/contacto");
        whiteList.add("/Photo_Party/informacion");
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String action = request.getServletPath();
        if (whiteList.contains(action)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpSession session = request.getSession();
            if (session.getAttribute("usuario") != null) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                response.sendRedirect(request.getContextPath() + "/Photo_Party/inicio");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
