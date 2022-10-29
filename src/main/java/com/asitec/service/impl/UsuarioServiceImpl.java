package com.asitec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asitec.dao.UsuarioDao;
import com.asitec.model.Usuario;
import com.asitec.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	UsuarioDao usuarioDao;

	@Override
	public List<Usuario> buscarInfo(Usuario usuario) { 
		return usuarioDao.buscarInfo(usuario);
	}

}
