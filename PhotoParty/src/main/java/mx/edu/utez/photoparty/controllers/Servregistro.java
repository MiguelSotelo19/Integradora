package mx.edu.utez.photoparty.controllers;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import mx.edu.utez.photoparty.models.Dao.*;
import mx.edu.utez.photoparty.models.Objects.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebServlet(name = "registro", urlPatterns = { "/evento/registrar","/evento/listar",
        "/evento/eliminar","/evento/drag",})
public class Servregistro extends HttpServlet {
    private String action;
    private Random rnd = new Random ();
    private String redirect= "/evento/eventos";
    private Evento evento;
    private Contrato contrato;
    private Solicitud solicitud;
    private Cliente cliente;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        action = req.getServletPath();
        switch (action) {
            case "/evento/eventos":
                //Aquí va a ir el link de los contratos para el administrador (Agenda) en el redirect
                redirect = "/views/Admin/Agenda.jsp";
                break;
            case "/evento/eventos/error/":
                redirect = "views/Admin/Agenda.jsp";
                break;
            case "/evento/listar":
                resp.setContentType("application/json");
                // Aquí realizas la lógica para obtener todos los eventos de la base de datos
                List<Evento> eventos = new DaoEvento().findAll();
                // Creas un array JSON y agregas cada objeto Evento a ese array
                JSONArray jsonArray = new JSONArray();
                for (Evento evento : eventos) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", evento.getA());
                    jsonObject.put("title", evento.getNombre());
                    jsonObject.put("segnombre", evento.getSegnombre());
                    jsonObject.put("ape_p", evento.getApe_p());
                    jsonObject.put("ape_m", evento.getApe_m());
                    jsonObject.put("direccion", evento.getDireccion());
                    jsonObject.put("telefono", evento.getTelefono());
                    jsonObject.put("correo", evento.getCorreo());
                    jsonObject.put("start", evento.getStart());
                    jsonObject.put("hora_inicio", evento.getHora_inicio());
                    jsonObject.put("hora_final", evento.getHora_final());
                    jsonObject.put("hora_extra", evento.getHora_extra());
                    jsonObject.put("cabina", evento.getCabina());
                    jsonObject.put("total", evento.getTotal());
                    jsonObject.put("color", evento.getColor());

                    jsonArray.add(jsonObject);
                }
                // Envías la respuesta al cliente (JavaScript)
                PrintWriter out = resp.getWriter();
                out.print(jsonArray.toJSONString());
                out.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        // Obtener el cuerpo de la solicitud
        BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream()));
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }

        // Procesar los datos recibidos del cuerpo de la solicitud (datos en formato JSON)
        String userData = requestBody.toString();
        // Convertir userData a un objeto JSON utilizando json-simple
        JSONParser parser = new JSONParser();
        JSONObject jsonObject;
        try {
            jsonObject = (JSONObject) parser.parse(userData);
        } catch (ParseException e) {
            // Manejo de error si la cadena userData no es un JSON válido
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        action = req.getServletPath();

        switch (action) {
            case "/evento/registrar":

                // Se extrae la información del objeto JSON
                String id = (String) jsonObject.get("id");
                String nombre = (String) jsonObject.get("title");
                String start = (String) jsonObject.get("start");
                String color = (String) jsonObject.get("color");
                String hora_inicio= (String) jsonObject.get("hora_inicio");
                String hora_final = (String) jsonObject.get("hora_final");
                String telefono= (String) jsonObject.get("telefono");
                String direccion = (String) jsonObject.get("direccion");
                String total= (String) jsonObject.get("total");
                String hora_extra = (String) jsonObject.get("hora_extra");
                String segnombre = (String) jsonObject.get("segnombre");
                String ape_p = (String) jsonObject.get("ape_p");
                String ape_m = (String) jsonObject.get("ape_m");
                String correo = (String) jsonObject.get("correo");
                String cabina = (String) jsonObject.get("cabina");

                if(id == null || id.isEmpty()){

                    id = nombre.charAt(0)+""+ape_p.charAt(0)+""+ape_m.charAt(0)+""+start.charAt(2)+""+start.charAt(3)+""+start.charAt(5)+
                            ""+start.charAt(6)+""+start.charAt(8)+""+start.charAt(9)+""+rnd.nextInt(9);

                    contrato = new Contrato(id, cabina, Double.valueOf(total),color);
                    solicitud = new Solicitud( start, hora_inicio, hora_final, direccion, id, id, Integer.parseInt(hora_extra));
                    cliente = new Cliente(id, nombre, segnombre, ape_p, ape_m, telefono, correo);
                    boolean result2 = new DaoSolicitud().save(solicitud);
                    boolean result = new DaoContrato().save(contrato);
                    boolean result3 = new DaoCliente().save(cliente);

                    if (result == true && result2 == true && result3 == true) {
                        boolean res = true;
                        // Crear una respuesta JSON para enviar al cliente
                        JSONObject jsonResponse = new JSONObject();
                        jsonResponse.put("estado", true); // Aquí asumimos que result es un booleano que indica si la operación fue exitosa o no.
                        jsonResponse.put("mensaje ", res ? "Datos recibidos y guardados correctamente.":"");

                        // Enviar la respuesta JSON al cliente
                        resp.getWriter().write(jsonResponse.toJSONString());
                    }else {
                        boolean res2 =false;
                        JSONObject jsonResponse = new JSONObject();
                        jsonResponse.put("estado", false); // Aquí asumimos que result es un booleano que indica si la operación fue exitosa o no.
                        jsonResponse.put("mensaje ", res2? "Error al guardar los datos. " : "");
                        // Enviar la respuesta JSON al cliente
                        resp.getWriter().write(jsonResponse.toJSONString());
                    }
                } else if(id != null || !id.isEmpty()){

                    solicitud = new Solicitud( start, hora_inicio, hora_final, direccion, id, Integer.parseInt(hora_extra));
                    cliente = new Cliente(id, nombre, segnombre, ape_p, ape_m, telefono, correo);
                    contrato = new Contrato(id, cabina, Double.valueOf(total),color);


                    boolean result2 = new DaoSolicitud().update(solicitud,null);
                    boolean result = new DaoContrato().update(contrato,null);
                    boolean result3 = new DaoCliente().update(cliente,null);

                    if(result ==true && result2 ==true && result3 == true) {
                        boolean res = true;
                        // Crear una respuesta JSON para enviar al cliente
                        JSONObject jsonResponse = new JSONObject();
                        jsonResponse.put("estado", res); // Aquí asumimos que result es un booleano que indica si la operación fue exitosa o no.
                        jsonResponse.put("mensaje ", res ? "Datos recibidos y guardados correctamente.":"");
                        // Enviar la respuesta JSON al cliente
                        resp.getWriter().write(jsonResponse.toJSONString());
                    }else {
                        boolean res2 =false;
                        JSONObject jsonResponse = new JSONObject();
                        jsonResponse.put("estado", res2); // Aquí asumimos que result es un booleano que indica si la operación fue exitosa o no.
                        jsonResponse.put("mensaje ", res2? "Error al guardar los datos. " : "");
                        // Enviar la respuesta JSON al cliente
                        resp.getWriter().write(jsonResponse.toJSONString());
                    }
                }
                break;

            case "/evento/eliminar":

                id = (String) jsonObject.get("id");

                if (!new DaoContrato().delete(null, id)) {
                    JSONObject responseJson = new JSONObject();

                    responseJson.put("result", true);
                    responseJson.put("message" , "¡Éxito! Cabina desactivada correctamente");

                    // Aquí debes enviar jsonResponse como respuesta HTTP.
                    // Dependiendo del framework o biblioteca que estés utilizando, esto puede variar.
                    resp.getWriter().write(responseJson.toJSONString());
                } else {
                    JSONObject responseJson = new JSONObject();
                    responseJson.put("result", false);
                    responseJson.put("message", "¡Error! Acción no realizada correctamente");
                    // Aquí debes enviar jsonResponse como respuesta HTTP.
                    // Dependiendo del framework o biblioteca que estés utilizando, esto puede variar.
                    resp.getWriter().write(responseJson.toJSONString());
                }
                break;

            case "/evento/drag":

                String s = (String) jsonObject.get("id");
                start = (String) jsonObject.get("start");
                hora_inicio= (String) jsonObject.get("hora_inicio");
                hora_final = (String) jsonObject.get("hora_final");

                solicitud = new Solicitud( start, hora_inicio, hora_final, s);

                boolean rest = new DaoSolicitud().drag(solicitud);

                // Crear una respuesta JSON para enviar al cliente
                JSONObject jsonResponse = new JSONObject();
                jsonResponse.put("estado", rest); // Aquí asumimos que result es un booleano que indica si la operación fue exitosa o no.
                jsonResponse.put("mensaje ", rest ? "Datos recibidos y guardados correctamente.":"Error datos no guardados");
                // Enviar la respuesta JSON al cliente
                resp.getWriter().write(jsonResponse.toJSONString());

                break;

        }



    }

}