package mx.edu.utez.photoparty.controllers;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import mx.edu.utez.photoparty.models.Dao.DaoCabina;
import mx.edu.utez.photoparty.models.Objects.Cabina;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@MultipartConfig
@WebServlet(name = "cabinas", urlPatterns = {
        "/Photo_Party/cabina/cabinas", "/cabina/cabina",
        "/Photo_Party/cabina/cabina-view", "/cabina/save",
        "/Photo_Party/cabina/cabina-view-update", "/cabina/update",
        "/cabina/delete","/carrusel/list","/cabina/imagenes",
        "/cabina/select","/cabina/activar","/cabina/eliminar"
})
public class ServletCabina extends HttpServlet {
    private String action;
    private String redirect="/Photo_Party/cabina/cabinas";
    private String id, nombre, descripcion, costo, extra, nombre_old;


    private Cabina cabina;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        action=req.getServletPath();
        switch (action){
            case "/Photo_Party/cabina/cabinas":
                List<Cabina> cabinas = new DaoCabina().findAll(null);
                req.setAttribute("cabinas", cabinas);
                redirect="/views/cabina/cabinas.jsp";
                break;

            case  "/Photo_Party/cabina/cabina-view":
                 cabinas = new DaoCabina().findAll(null);
                req.setAttribute("cabinas", cabinas);
                redirect="/views/cabina/Anadircabina.jsp";
                break;

            case "/Photo_Party/cabina/cabina-view-update":
              id = req.getParameter("id");
                cabina = new DaoCabina().findOne((id != null) ? (Long.parseLong(id)) : (0), null);
                if (cabina != null) {
                    req.setAttribute("cabina", cabina);
                    List<Cabina> cabinas2 = new DaoCabina().imagenes(Long.parseLong(id));
                    if (cabinas2 != null) {
                        req.setAttribute("cabinas2", cabinas2);
                    }
                    redirect = "/views/cabina/Editcabina.jsp";
                } else {
                    redirect = "/Photo_Party/cabina/cabinas?result=false&message=" + URLEncoder.encode("¡Error! Acción no realizada correctamente", StandardCharsets.UTF_8);
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
        resp.setContentType("text/html");
        action = req.getServletPath();
        switch (action){
            case "/cabina/delete":
                id = req.getParameter("id");
                if (!new DaoCabina().delete(null, id)){
                    redirect = "/Photo_Party/cabina/cabinas?result= " + true + "&message="
                            + URLEncoder.encode("¡Éxito! Cabina desativada correctamente",
                            StandardCharsets.UTF_8);
                } else {
                    redirect = "/Photo_Party/cabina/cabinas?result= " + false + "&message="
                            + URLEncoder.encode("¡Error! Acción no realizada correctamente",
                            StandardCharsets.UTF_8);
                }
                break;

            case "/cabina/save":
                // Obtener los datos del formulario
                nombre = req.getParameter("nombre");
                costo = req.getParameter("costo");
                extra = req.getParameter("extra");
                descripcion = req.getParameter("descripcion");

                Part part = req.getPart("imagen"); // Obtener la imagen del formulario

                InputStream inputStream = part.getInputStream();
                cabina = new Cabina(0L, nombre, descripcion, Float.parseFloat(costo), Float.parseFloat(extra), true, inputStream);
                boolean result = new DaoCabina().save(cabina);

                if (!result) {
                    redirect = "/Photo_Party/cabina/cabina-view?result=" + true + "&message="
                            + URLEncoder.encode("Éxito! Cabina registrada correctamente",
                            StandardCharsets.UTF_8);
                } else {
                    redirect = "/Photo_Party/cabina/cabina-view?result=" + false + "&message="
                            + URLEncoder.encode("¡Error! Acción no realizada correctamente",
                            StandardCharsets.UTF_8);
                }
                break;

            case "/cabina/imagenes":
                nombre = req.getParameter("cabina");
                part = req.getPart("archivo"); // Obtener la imagen del formulario
                 inputStream = part.getInputStream();

                cabina = new Cabina(nombre,inputStream);
                 result = new DaoCabina().img(cabina);

                if (!result) {
                    redirect = "/Photo_Party/cabina/cabina-view?result=" + true + "&message="
                            + URLEncoder.encode("Éxito! Cabina registrada correctamente",
                            StandardCharsets.UTF_8);
                } else {
                    redirect = "/Photo_Party/cabina/cabina-view?result=" + false + "&message="
                            + URLEncoder.encode("¡Error! Acción no realizada correctamente",
                            StandardCharsets.UTF_8);
                }
                break;

            case "/cabina/update":

                nombre = req.getParameter("nombre");
                costo = req.getParameter("costo");
                extra = req.getParameter("extra");
                descripcion = req.getParameter("descripcion");
                 part = req.getPart("imagen"); // Obtener la imagen del formulario

                 inputStream = part.getInputStream();
                cabina = new Cabina(0L, nombre, descripcion, Float.parseFloat(costo), Float.parseFloat(extra), true, inputStream);
                 result = new DaoCabina().update(cabina,nombre);

                if (!result) {
                    redirect = "/Photo_Party/cabina/cabina-view?result=" + true + "&message="
                            + URLEncoder.encode("Éxito! Cabina registrada correctamente",
                            StandardCharsets.UTF_8);
                } else {
                    redirect = "/Photo_Party/cabina/cabina-view?result=" + false + "&message="
                            + URLEncoder.encode("¡Error! Acción no realizada correctamente",
                            StandardCharsets.UTF_8);
                }
                break;
            case "/cabina/activar":
                id = req.getParameter("nombre");
                if (!new DaoCabina().activar(id)){
                    redirect = "/Photo_Party/cabina/cabinas?result= " + true + "&message="
                            + URLEncoder.encode("¡Éxito! Cabina desativada correctamente",
                            StandardCharsets.UTF_8);
                } else {
                    redirect = "/Photo_Party/cabina/cabinas?result= " + false + "&message="
                            + URLEncoder.encode("¡Error! Acción no realizada correctamente",
                            StandardCharsets.UTF_8);
                }
      break;
            case "/cabina/eliminar":
                id = req.getParameter("id");
                if (!new DaoCabina().eliminar(id)){
                    redirect = "/Photo_Party/cabina/cabinas?result= " + true + "&message="
                            + URLEncoder.encode("¡Éxito! Datos eliminados correctamente",
                            StandardCharsets.UTF_8);
                } else {
                    redirect = "/Photo_Party/cabina/cabinas?result= " + false + "&message="
                            + URLEncoder.encode("¡Error! Acción no realizada correctamente",
                            StandardCharsets.UTF_8);
                }
                break;




        }
        resp.sendRedirect(req.getContextPath() + redirect);
    }

}
