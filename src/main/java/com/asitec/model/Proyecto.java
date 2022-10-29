package com.asitec.model;

import lombok.Data;

@Data
public class Proyecto {
	private long   IdeProyecto;
	private String CodSnip;
	private String CodCui;
    private String TxtNombre;
    private double ImpMonto;
    private int    EstActivo; 

}
