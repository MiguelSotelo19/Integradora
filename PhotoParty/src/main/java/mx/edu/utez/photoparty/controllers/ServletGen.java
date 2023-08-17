package mx.edu.utez.photoparty.controllers;


import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.photoparty.models.Dao.*;
import mx.edu.utez.photoparty.models.Objects.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "inicio", urlPatterns = {
        "/Photo_Party/inicio", "/Photo_Party/login", "/Photo_Party/contacto",
        "/Photo_Party/empleados", "/Photo_Party/empleados/eventos", "/Photo_Party/seguimiento",
        "/Photo_Party/crear_contrato", "/sigin", "/empleados_aceptar",
        "/Photo_Party/empleados/pendientes", "/empleados/cancelar",
        "/Photo_Party/agenda", "/Photo_Party/informacion","/Photo_Party/cabinas"
})
public class ServletGen extends HttpServlet {
    private String action;
    private String redirect="/Photo_Party/inicio";
    private Usuario user;
    AuthService userService = new AuthService();
    HttpSession session;
    private Usuario usuario;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        action=req.getServletPath();
        switch (action){
            case "/Photo_Party/inicio":
                List<Cabina> carrusel = new DaoCabina().findAll(null);
                req.setAttribute("cabinas", carrusel);
                redirect="/views/Inicio/index.jsp";
                break;

            case "/Photo_Party/cabinas":
                String id = req.getParameter("id");
                Cabina cabina = new DaoCabina().findOne((id != null) ? (Long.parseLong(id)) : (0), null);
                if (cabina != null) {
                    req.setAttribute("cabina", cabina);
                    List<Cabina> cabinas2 = new DaoCabina().imagenes(Long.parseLong(id));
                    if (cabinas2 != null) {
                        req.setAttribute("cabinas2", cabinas2);
                    }

                    redirect="/views/Inicio/paquetes.jsp";
                } else {
                    redirect = "/inicio?result= "+false+"&message="
                            + URLEncoder.encode("¡Error! Acción no realizada correctamente",
                            StandardCharsets.UTF_8);
                }
                break;
            case "/Photo_Party/contacto":
                redirect="/views/Inicio/contacto.jsp";
                break;
            case "/Photo_Party/login":
                redirect="/views/Inicio/empleado.jsp";
                break;
            case "/Photo_Party/informacion":
                redirect="/views/Inicio/pie.jsp";
                break;
            case "/Photo_Party/empleados":
                session = req.getSession();
                Usuario user_id = (Usuario) session.getAttribute("usuario");
                if(user_id.getId()!=1){
                    List<Confirmados> confs = new DaoConfirmados().findAll(user_id.getId());
                    req.setAttribute("confs", confs);
                    redirect="/views/Empleados/acept_events.jsp";
                } else {
                    redirect = "/Photo_Party/usuario/usuario-view";
                }
                session = req.getSession();
                break;
            case "/Photo_Party/empleados/eventos":
                session = req.getSession();
                user = (Usuario) session.getAttribute("usuario");
                if(user.getId()!=1){
                    List<Hist> hists = new DaoHist().findAll(user.getId());
                    req.setAttribute("hists", hists);
                    redirect="/views/Empleados/conf_events.jsp";
                } else {
                    redirect = "/Photo_Party/usuario/usuario-view";
                }
                break;
            case "/Photo_Party/seguimiento":
                session = req.getSession();
                usuario = (Usuario) session.getAttribute("usuario");
                if(usuario.getId()==1){
                    List<Cts_users> rels = new DaoCts_users().findAll(null);
                    req.setAttribute("rels", rels);
                    redirect="/views/Admin/v_contratos.jsp";
                } else {
                    redirect = "/Photo_Party/empleados";
                }
                break;

            case "/Photo_Party/empleados/pendientes":
                session = req.getSession();
                user = (Usuario) session.getAttribute("usuario");
                if(user.getId()!=1){
                    List<Hist> events = new DaoHist().pendientes(user.getId());
                    req.setAttribute("events", events);
                    redirect="/views/Empleados/pend_events.jsp";
                } else {
                    redirect = "/Photo_Party/usuario/usuario-view";
                }
                break;
            case "/Photo_Party/agenda":
                session = req.getSession();
                usuario = (Usuario) session.getAttribute("usuario");
                if(usuario.getId()==1){
                    List<Cabina> cabinas = new DaoCabina().findAll(null);
                    req.setAttribute("cabinas", cabinas);
                    redirect="/views/Admin/Agenda.jsp";
                } else {
                    redirect = "/Photo_Party/empleados";
                }
                break;
            case "/Photo_Party/crear_contrato":
                String id_cabina = req.getParameter("id");
                Cabina cabina1 = new DaoCabina().findOne(Long.parseLong(id_cabina), null);
                req.setAttribute("cabina", cabina1);
                redirect="/views/Inicio/contrato.jsp";
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
            case "/sigin":
                String username = req.getParameter("user");
                String password = req.getParameter("pass");
                Usuario usuario = userService.login(username, password);
                if(usuario != null){
                    session= req.getSession();
                    session.setAttribute("usuario", usuario);

                    if(usuario.getId() == 1){
                        redirect = "/Photo_Party/usuario/usuario-view";
                    } else {
                        redirect= "/Photo_Party/empleados";
                    }
                } else {
                    redirect= "/Photo_Party/login?message=" + URLEncoder.encode("Usuario y/o contraseña incorrectos", StandardCharsets.UTF_8.name());
                }
                break;


            case "/empleados_aceptar":
                String id = req.getParameter("id");
                session = req.getSession();
                Usuario user = (Usuario) session.getAttribute("usuario");
                Contrato_usuario ctus = new Contrato_usuario(user.getId(), id);

                if (!new DaoContrato_usuario().save(ctus)){
                    redirect = "/Photo_Party/empleados?result= " + true + "&message="
                            + URLEncoder.encode("¡Éxito! Se ha confirmado la asistencia correctamente",
                            StandardCharsets.UTF_8);
                } else {
                    redirect = "/Photo_Party/empleados?result= " + false + "&message="
                            + URLEncoder.encode("¡Error! Acción no realizada correctamente",
                            StandardCharsets.UTF_8);
                }
                break;

            case "/empleados/cancelar":
                session = req.getSession();
                user = (Usuario) session.getAttribute("usuario");
                String id_contrato = req.getParameter("id");

                if (!new DaoContrato_usuario().delete(user.getId(), id_contrato)){
                    redirect = "/Photo_Party/empleados/pendientes?result= " + true + "&message="
                            + URLEncoder.encode("¡Éxito! Se ha cancelado la asistencia correctamente",
                            StandardCharsets.UTF_8);
                } else {
                    redirect = "/Photo_Party/empleados/pendientes?result= " + false + "&message="
                            + URLEncoder.encode("¡Error! Acción no realizada correctamente",
                            StandardCharsets.UTF_8);
                }
                break;

        }
        resp.sendRedirect(req.getContextPath() + redirect);
    }

}
