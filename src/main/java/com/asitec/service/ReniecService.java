package com.asitec.service;

import com.asitec.api.request.ReniecRequest;
import com.asitec.util.ApiOutResponse;

public interface ReniecService {

	ApiOutResponse buscarReniec(ReniecRequest reniecRequest);
}
