package com.asitec.util;

public class ApiOutResponse {

	private Integer codResultado;
	private String msgResultado;
	private Integer total;
	private Object response;
	
	public Integer getCodResultado() {
		return codResultado;
	}
	public void setCodResultado(Integer codResultado) {
		this.codResultado = codResultado;
	}
	public String getMsgResultado() {
		return msgResultado;
	}
	public void setMsgResultado(String msgResultado) {
		this.msgResultado = msgResultado;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Object getResponse() {
		return response;
	}
	public void setResponse(Object response) {
		this.response = response;
	}

	
}
