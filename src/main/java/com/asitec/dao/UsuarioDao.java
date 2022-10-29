package com.asitec.dao;

import java.util.List;

import com.asitec.model.Usuario;

public interface UsuarioDao {
	List<Usuario> buscarInfo(Usuario usuario);
}
