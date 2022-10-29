package com.asitec.dao;

import java.util.List;
import com.asitec.model.Periodo;
import com.asitec.model.Ubigeo;

public interface ComunDao {
	List<Periodo> listarPeridos();
	List<Ubigeo> listarRegion();
}
