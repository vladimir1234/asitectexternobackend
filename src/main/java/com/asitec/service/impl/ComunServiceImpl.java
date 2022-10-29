package com.asitec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asitec.dao.ComunDao;
import com.asitec.model.Periodo;
import com.asitec.model.Ubigeo;

@Service
public class ComunServiceImpl implements com.asitec.service.ComunService {
	
	@Autowired 
    ComunDao  comunDao;
	
	
	@Override
	public List<Periodo> listarPeridos() { 
		return comunDao.listarPeridos();
	}


	@Override
	public List<Ubigeo> listarRegion() { 
		return comunDao.listarRegion();
	}

}
