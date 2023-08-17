package mx.edu.utez.photoparty.controllers;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.photoparty.models.Dao.DaoContrato;
import mx.edu.utez.photoparty.models.Objects.Horas;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ServletJson", description = "Servlet retorna JSON", urlPatterns = {
        "/horas"
})
public class ServletJson extends HttpServlet {
    private String action;
    private String redirect="/horas";
    private Horas hora = new Horas();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        action=req.getServletPath();
        switch (action){
            case "/horas":
                List<Horas> horas = new DaoContrato().horas();

                Gson gson = new Gson();
                String json = gson.toJson(horas);

                PrintWriter print = resp.getWriter();
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                print.write(json);
                print.close();
                break;
        }
    }


}
