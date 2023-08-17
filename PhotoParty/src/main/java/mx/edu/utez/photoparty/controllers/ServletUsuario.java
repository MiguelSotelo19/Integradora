package mx.edu.utez.photoparty.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.photoparty.models.Dao.*;
import mx.edu.utez.photoparty.models.Objects.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet(name="usuarios", urlPatterns = {
        "/Photo_Party/usuario/usuarios", "/usuario/cabina",
        "/Photo_Party/usuario/usuario-view", "/usuario/save",
        "/Photo_Party/usuario/usuario-view-update", "/usuario/update",
        "/usuario/delete", "/Photo_Party/empleados/info_contrato"
})

public class ServletUsuario extends HttpServlet {
    private String action;
    private String redirect="/Photo_Party/usuario/usuarios";
    private String id, nombre, segnombre, ape_p, ape_m, telefono, user, contra;
    private Usuario usuario;
    HttpSession session;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        action=req.getServletPath();
        switch (action){
            case "/Photo_Party/usuario/usuarios":
                session = req.getSession();
                usuario = (Usuario) session.getAttribute("usuario");
                if(usuario.getId()==1){
                    List<Usuario> usuarios = new DaoUsuario().findAll(null);
                    req.setAttribute("usuarios", usuarios);
                    redirect="/views/Admin/modEmp.jsp";
                } else {
                    redirect = "/Photo_Party/empleados";
                }
                break;
            case  "/Photo_Party/usuario/usuario-view":
                session = req.getSession();
                usuario = (Usuario) session.getAttribute("usuario");
                if(usuario.getId()==1){
                    redirect="/views/Admin/RegEmple.jsp";
                } else {
                    redirect = "/Photo_Party/empleados";
                }
                break;
            case "/Photo_Party/usuario/usuario-view-update":
                session = req.getSession();
                usuario = (Usuario) session.getAttribute("usuario");
                if(usuario.getId()==1){
                    id = req.getParameter("id");
                    usuario = new DaoUsuario().findOne((id != null) ? (Long.parseLong(id)) : (0), null);
                    if(usuario != null){
                        req.setAttribute("usuario", usuario);
                        redirect="/views/Admin/updateEmp.jsp";
                    } else {
                        redirect = "/Photo_Party/usuario/usuario-view?result= "+false+"&message="
                                + URLEncoder.encode("¡Error! Acción no realizada correctamente",
                                StandardCharsets.UTF_8);
                    }
                } else {
                    redirect = "/Photo_Party/empleados";
                }

                break;
            case "/Photo_Party/empleados/info_contrato":
                id= req.getParameter("id");
                Contrato cont = new DaoContrato().findOne(null, id);
                Cliente cliente = new DaoCliente().findOne(null, id);
                Solicitud solicitud = new DaoSolicitud().findOne(null, id);


                if(cont != null && cliente != null && solicitud != null) {
                    req.setAttribute("contrato", cont);
                    req.setAttribute("cliente", cliente);
                    req.setAttribute("solicitud", solicitud);

                    redirect = "/views/Empleados/info_event.jsp";
                } else {
                    redirect="/views/Empleados/info_event.jsp?result= "+false+"&message="
                            + URLEncoder.encode("¡Error! Acción no realizada correctamente",
                            StandardCharsets.UTF_8);
                }
                break;
            default:
                System.out.println(action);
        }
        req.getRequestDispatcher(redirect).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        action = req.getServletPath();
        switch (action){
            case "/usuario/delete":
                id = req.getParameter("id");
                if (!new DaoUsuario().delete(null, id)){
                    redirect = "/Photo_Party/usuario/usuarios?result= " + true + "&message="
                            + URLEncoder.encode("¡Éxito! Usuario eliminada correctamente",
                            StandardCharsets.UTF_8);
                } else {
                    redirect = "/Photo_Party/usuario/usuarios?result= " + false + "&message="
                            + URLEncoder.encode("¡Error! Acción no realizada correctamente",
                            StandardCharsets.UTF_8);
                }
                break;
            case "/usuario/save":
                nombre = req.getParameter("nombre");
                segnombre = req.getParameter("segnombre");
                ape_p = req.getParameter("ape_p");
                ape_m = req.getParameter("ape_m");
                telefono = req.getParameter("telefono");
                user = req.getParameter("user");
                contra = req.getParameter("contra");
                usuario = new Usuario(0L, nombre, segnombre, ape_p, ape_m, telefono, user, contra);
                boolean result = new DaoUsuario().save(usuario);

                if (!result) {
                    redirect = "/Photo_Party/usuario/usuario-view?result=" + true + "&message="
                            + URLEncoder.encode("Éxito! Usuario regsitrado correctamente",
                            StandardCharsets.UTF_8);
                } else {
                    redirect = "/Photo_Party/usuario/usuario-view?result= " + false + "&message="
                            + URLEncoder.encode("¡Error! Acción no realizada correctamente",
                            StandardCharsets.UTF_8);
                }
                break;
            case "/usuario/update":
                id = req.getParameter("id");
                nombre = req.getParameter("nombre");
                segnombre = req.getParameter("segnombre");
                ape_p = req.getParameter("ape_p");
                ape_m = req.getParameter("ape_m");
                telefono = req.getParameter("telefono");
                user = req.getParameter("user");
                contra = req.getParameter("contra");
                usuario = new Usuario(Long.parseLong(id), nombre, segnombre, ape_p, ape_m, telefono, user, contra);
                if (!new DaoUsuario().update(usuario, null)){
                    redirect = "/Photo_Party/usuario/usuarios?result=" + true + "&message="
                            + URLEncoder.encode("Éxito! Usuario actualizado correctamente",
                            StandardCharsets.UTF_8);
                }else {
                    redirect = "/Photo_Party/usuario/usuarios?result= " + false + "&message="
                            + URLEncoder.encode("¡Error! Acción no realizada correctamente",
                            StandardCharsets.UTF_8);
                }
                break;
        }
        resp.sendRedirect(req.getContextPath() + redirect);
    }
}
