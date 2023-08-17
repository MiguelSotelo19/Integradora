package mx.edu.utez.photoparty.controllers;

import mx.edu.utez.photoparty.models.Dao.DaoUsuario;
import mx.edu.utez.photoparty.models.Objects.Usuario;

public class AuthService {
    DaoUsuario usuario = new DaoUsuario();

    public Usuario login (String user, String pass){return usuario.validate(user, pass);}
}
