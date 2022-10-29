package com.asitec.service;

import com.asitec.api.request.ColegioMineduRequest;
import com.asitec.util.ApiOutResponse;

public interface ColegioMineduService {

	ApiOutResponse buscarColegioMinedu(ColegioMineduRequest colegioMineduRequest);
}
