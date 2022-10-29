package com.asitec.service;

import java.util.List;

import com.asitec.model.Periodo;
import com.asitec.model.Ubigeo;

public interface ComunService {

	List<Periodo> listarPeridos();
	List<Ubigeo> listarRegion();
	
}
