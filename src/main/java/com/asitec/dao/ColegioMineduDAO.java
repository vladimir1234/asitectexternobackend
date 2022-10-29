package com.asitec.dao;

import com.asitec.api.request.ColegioMineduRequest;
import com.asitec.util.ApiOutResponse;

public interface ColegioMineduDAO {

	ApiOutResponse buscarColegioMinedu(ColegioMineduRequest colegioMineduRequest);
}


