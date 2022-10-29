package com.asitec.dao;

import com.asitec.api.request.PeriodoRequest;
import com.asitec.util.ApiOutResponse;

public interface PeriodoDAO {

	ApiOutResponse buscarPeriodo(PeriodoRequest periodo);

	ApiOutResponse registrarPeriodo(PeriodoRequest periodo);

	ApiOutResponse listarPeriodo();
}
