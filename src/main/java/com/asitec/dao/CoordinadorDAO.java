package com.asitec.dao;

import com.asitec.api.request.CoordinadorRequest;
import com.asitec.util.ApiOutResponse;

public interface CoordinadorDAO {


	ApiOutResponse registrar(CoordinadorRequest coordinador);
}
