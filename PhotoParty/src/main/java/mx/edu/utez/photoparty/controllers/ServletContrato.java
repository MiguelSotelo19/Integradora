package mx.edu.utez.photoparty.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.photoparty.models.Dao.*;
import mx.edu.utez.photoparty.models.Objects.*;
import mx.edu.utez.photoparty.utils.MySQLConnClien;
import mx.edu.utez.photoparty.utils.MySQLConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;

@WebServlet(name = "contratos", urlPatterns = {
        "/contrato/contrato",
        "/contrato/contrato-view", "/contrato/save",
        "/contrato/contrato-view-update", "/contrato/update",
        "/contrato/delete", "/Photo_Party/contrato/info",
        "/Photo_Party/contratos/historial", "/Photo_Party/contratos/eliminados"
})
public class ServletContrato extends HttpServlet {
    private String action;
    private Connection conn;
    private String redirect="/contrato/contrato-view";
    private String id, nombre, segnombre, ape_p, ape_m, telefono, correo,
        fecha_ev, hr_inicio, hr_fin, hr_extra, direccion, total2, cabina;
    private Double total;
    private Random rnd = new Random ();
    private Contrato contrato;
    private Solicitud solicitud;
    private Cliente cliente;
    private log_contratos log;
    private HttpSession session;
    private Usuario usuario;



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        action=req.getServletPath();
        switch (action){
            case "/Photo_Party/contratos/eliminados":
                id=req.getParameter("id");
                log = new Daolog().findOne(null, id);
                if(log != null){
                    req.setAttribute("log", log);
                    redirect= "/views/Admin/info_log.jsp";
                } else {
                    redirect = "/Photo_Party/usuario/usuario-view?result= "+false+"&message="
                            + URLEncoder.encode("¡Error! Acción no realizada correctamente",
                            StandardCharsets.UTF_8);
                }
                break;
            case  "/contrato/contrato-view":
                redirect="/inicio";
                break;
            case "/contrato/contrato-view-update":
                id = req.getParameter("id");
                contrato = new DaoContrato().findOne(null, (id != null) ? (id) : (null));
                if(contrato != null){
                    req.setAttribute("contrato", contrato);
                    redirect="/views/contrato/update.jsp";
                } else {
                    redirect = "/contrato/contratos?result= "+false+"&message="
                            + URLEncoder.encode("¡Error! Acción no realizada correctamente",
                            StandardCharsets.UTF_8);
                }
                break;
            case "/Photo_Party/contrato/info":
                id= req.getParameter("id");
                contrato = new DaoContrato().findOne(null, id);
                cliente = new DaoCliente().findOne(null, id);
                solicitud = new DaoSolicitud().findOne(null, id);
                List<Usuario> usuarios= new DaoUsuario().v_users(id);

                if(contrato != null || cliente != null || solicitud != null || usuarios != null){
                    req.setAttribute("contrato", contrato);
                    req.setAttribute("cliente", cliente);
                    req.setAttribute("solicitud", solicitud);
                    req.setAttribute("usuarios", usuarios);

                    redirect="/views/Admin/Info_contratos.jsp";
                } else {
                    redirect="/views/Admin/Info_contratos.jsp?result= "+false+"&message="
                            + URLEncoder.encode("¡Error! Acción no realizada correctamente",
                            StandardCharsets.UTF_8);
                }
                break;
            case "/Photo_Party/contratos/historial":
                session = req.getSession();
                usuario = (Usuario) session.getAttribute("usuario");
                if(usuario.getId()==1){
                    List<log_contratos> log =  new Daolog().findAll(null);
                    req.setAttribute("log", log);
                    redirect = "/views/Admin/eliminados.jsp";
                } else {
                    redirect = "/Photo_Party/empleados";
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
            case "/contrato/delete":
                id = req.getParameter("id");
                if (new DaoContrato().delete(null, id)){
                    redirect = "/contrato/contratos?result= " + true + "&message="
                            + URLEncoder.encode("¡Éxito! Contrato eliminado correctamente",
                            StandardCharsets.UTF_8);
                } else {
                    redirect = "/contrato/contratos?result= " + false + "&message="
                            + URLEncoder.encode("¡Error! Acción no realizada correctamente",
                            StandardCharsets.UTF_8);
                }
                break;
            case "/contrato/save":
                nombre = req.getParameter("nombre");
                segnombre = req.getParameter("segnombre");
                ape_p = req.getParameter("ape_p");
                ape_m = req.getParameter("ape_m");
                telefono = req.getParameter("telefono");
                correo = req.getParameter("correo");
                fecha_ev = req.getParameter("fecha_ev");
                hr_inicio = req.getParameter("hora_inicio");
                hr_fin = req.getParameter("hora_final");
                direccion = req.getParameter("direccion");
                cabina = req.getParameter("cabina");
                Double costo = Double.parseDouble(req.getParameter("costo"));
                Long hora_inicio = (Long.parseLong(""+hr_inicio.charAt(0)+hr_inicio.charAt(1)) * 60) + (Long.parseLong(""+hr_inicio.charAt(3)+hr_inicio.charAt(4)));
                Long hora_final= (Long.parseLong(""+hr_fin.charAt(0)+hr_fin.charAt(1)) * 60) + (Long.parseLong(""+hr_fin.charAt(3)+hr_fin.charAt(4)));
                Double min_total = (hora_final - hora_inicio)/60.0;
                total=costo*min_total;
                id = nombre.charAt(0)+""+ape_p.charAt(0)+""+ape_m.charAt(0)+""+fecha_ev.charAt(2)+""+fecha_ev.charAt(3)+""+fecha_ev.charAt(5)+
                        ""+fecha_ev.charAt(6)+""+fecha_ev.charAt(8)+""+fecha_ev.charAt(9)+""+rnd.nextInt(9);

                contrato = new Contrato(id, cabina, null, total);
                solicitud = new Solicitud(0L, fecha_ev, hr_inicio, hr_fin, direccion, id, id, 0);
                cliente = new Cliente(id, nombre, segnombre, ape_p, ape_m, telefono, correo);

                boolean result = new DaoContrato().save(contrato);
                boolean result2 = new DaoSolicitud().save(solicitud);
                boolean result3 = new DaoCliente().save(cliente);
                if (!result || !result2 || !result3) {

                    try {
                        conn = new MySQLConnection().connect();
                        List<pdf> data = new ArrayList<>();
                        ResultSet resultSet;
                        CallableStatement cstm;
                        try{
                            String query = "CALL MostrarInfoContrato(?);";
                            cstm = conn.prepareCall(query);
                            cstm.setString(1, id);
                            resultSet = cstm.executeQuery();
                            while (resultSet.next()) {
                                pdf bean = new pdf();
                                bean.setId_solicitud(resultSet.getString("id_solicitud"));
                                bean.setNombre_completo(resultSet.getString("nombre_completo"));
                                bean.setTelefono(resultSet.getString("telefono"));
                                bean.setFecha(resultSet.getString("fecha_evento"));
                                bean.setDireccion(resultSet.getString("direccion"));
                                bean.setHoras(resultSet.getString("horas_apartadas"));
                                bean.setId_contrato(resultSet.getString("id_contrato"));
                                bean.setCabina(resultSet.getString("nombre_cabina"));
                                bean.setMonto(total);
                                data.add(bean);
                            }
                            resultSet.close();
                            cstm.close();
                        }catch (SQLException e){
                            Logger.getLogger(DaoContrato.class.getName())
                                    .log(Level.SEVERE, "Error"+e.getMessage());
                        }

                        Map<String, Object> parameters = new HashMap<>();
                        File fichero = new File("src/main/webapp/Img/Logo.jpg");
                        parameters.put("logo", fichero.getAbsolutePath());

                        // Configurar la fuente de datos
                        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);

                        // Cargar el pdf desde el archivo .jasper
                        String reportPath = "/WEB-INF/report/prueba.jasper";
                        InputStream jasperStream = getServletContext().getResourceAsStream(reportPath);

                        // Llenar el informe con los datos y parámetros
                        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, null, dataSource);
                        String pdfFilePath = "/home/PDF/contrato.pdf";
                        JasperExportManager.exportReportToPdfFile(jasperPrint, pdfFilePath);
                        File pdfFile = new File(pdfFilePath);
                        FileInputStream inputStream = new FileInputStream(pdfFile);

                        // Configurar cabeceras de respuesta
                        resp.setContentType("application/pdf");
                        resp.setHeader("Content-Disposition", "attachment; filename=contrato.pdf");

                        // Enviar el archivo PDF al flujo de salida de la respuesta
                        OutputStream outputStream = resp.getOutputStream();
                        byte[] buffer = new byte[4096];
                        int bytesRead;
                        while ((bytesRead = inputStream.read(buffer))!= -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                        inputStream.close();
                        outputStream.flush();
                        outputStream.close();
                        conn.close();
                        redirect = "/inicio?result=" + true + "&message="
                                + URLEncoder.encode("Éxito! Datos regsitrados correctamente",
                                StandardCharsets.UTF_8);
                    } catch (SQLException | JRException e) {
                        e.printStackTrace();
                    }catch (RuntimeException e){
                        throw new RuntimeException(e);
                    }
                }else {
                    redirect = "/inicio?result= " + false + "&message="
                            + URLEncoder.encode("¡Error! Acción no realizada correctamente",
                            StandardCharsets.UTF_8);
                }

                break;
            case "/contrato/update":
                id = req.getParameter("id");
                nombre = req.getParameter("nombre");
                segnombre = req.getParameter("segnombre");
                ape_p = req.getParameter("ape_p");
                ape_m = req.getParameter("ape_m");
                telefono = req.getParameter("telefono");
                correo = req.getParameter("correo");
                fecha_ev = req.getParameter("fecha_ev");
                hr_inicio = req.getParameter("hr_inicio");
                hr_fin = req.getParameter("hr_fin");
                hr_extra = req.getParameter("hr_extra");
                direccion = req.getParameter("direccion");
                total2 = req.getParameter("total");
                cabina = req.getParameter("cabina");

                contrato = new Contrato(id, cabina, null, Double.parseDouble(total2));
                solicitud = new Solicitud(0L, fecha_ev, hr_inicio, hr_fin, direccion, id, id, Integer.parseInt(hr_extra));
                cliente = new Cliente(id, nombre, segnombre, ape_p, ape_m, telefono, correo);

                if (new DaoContrato().update(contrato, null) || new DaoSolicitud().update(solicitud, null) || new DaoCliente().update(cliente, null)){
                    redirect = "/contrato/contratos?result=" + true + "&message="
                            + URLEncoder.encode("Éxito! Usuario actualizado correctamente",
                            StandardCharsets.UTF_8);
                }else {
                    redirect = "/contrato/contratos?result= " + false + "&message="
                            + URLEncoder.encode("¡Error! Acción no realizada correctamente",
                            StandardCharsets.UTF_8);
                }
                break;
        }
        resp.sendRedirect(req.getContextPath() + redirect);
    }
}
