package com.asitec.service;

import com.asitec.api.request.PeriodoRequest;
import com.asitec.util.ApiOutResponse;

public interface PeriodoService {

	ApiOutResponse buscarPeriodo(PeriodoRequest periodo);

	ApiOutResponse registrarPeriodo(PeriodoRequest periodo);

	ApiOutResponse listarPeriodo();

}
