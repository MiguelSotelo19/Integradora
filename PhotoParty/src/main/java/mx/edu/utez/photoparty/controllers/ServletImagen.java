package mx.edu.utez.photoparty.controllers;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.photoparty.models.Dao.DaoCabina;
import mx.edu.utez.photoparty.models.Objects.Cabina;


import java.io.IOException;

@WebServlet(name = "imagen",urlPatterns = { "/imagen","/imagen2"
})

public class ServletImagen extends HttpServlet {
    private String action;
    private String redirect="/cabina/cabinas";
    private Cabina cabina;
    DaoCabina dao = new DaoCabina();
    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        action=req.getServletPath();
        switch (action) {
            case  "/imagen":
                long id = Integer.parseInt(req.getParameter("id"));
                dao.listarImg((int) id, response);
            break;

            case  "/imagen2":
                long id2 = Integer.parseInt(req.getParameter("id2"));
                dao.imageneslist((int) id2, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
