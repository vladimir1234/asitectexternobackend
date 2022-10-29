package com.asitec.dao;

import com.asitec.api.request.ReniecRequest;
import com.asitec.util.ApiOutResponse;

public interface ReniecDAO {

	ApiOutResponse buscarReniec(ReniecRequest reniecRequest);
}


