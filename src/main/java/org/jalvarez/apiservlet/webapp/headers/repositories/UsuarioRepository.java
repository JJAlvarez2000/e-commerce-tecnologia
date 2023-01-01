package org.jalvarez.apiservlet.webapp.headers.repositories;

import org.jalvarez.apiservlet.webapp.headers.models.Usuario;

import java.sql.SQLException;
import java.util.Optional;

public interface UsuarioRepository extends Repository<Usuario> {
    Usuario porUsername(String username) throws SQLException;



}
