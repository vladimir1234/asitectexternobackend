package com.asitec.service;

import com.asitec.api.request.CoordinadorRequest;
import com.asitec.util.ApiOutResponse;

public interface CoordinadorService {

	ApiOutResponse registrar(CoordinadorRequest coordinador);

}
