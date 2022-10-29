package com.asitec.model;

import java.math.BigDecimal;

/**
 * 
 * @author hrr
 */
public class AstUsuExterno
{
	private int _IdeUsuExterno;
	public int getIdeUsuExterno()
	{
		return this._IdeUsuExterno;
	}
	public void setIdeUsuExterno(int value)
	{
		this._IdeUsuExterno = value;
	}

	private BigDecimal _IdeUsuario;
	public BigDecimal getIdeUsuario()
	{
		return this._IdeUsuario;
	}
	public void setIdeUsuario(BigDecimal value)
	{
		this._IdeUsuario = value;
	}

	private BigDecimal _IdeUei;
	public BigDecimal getIdeUei()
	{
		return this._IdeUei;
	}
	public void setIdeUei(BigDecimal value)
	{
		this._IdeUei = value;
	}

	private BigDecimal _EstAutorizado;
	public BigDecimal getEstAutorizado()
	{
		return this._EstAutorizado;
	}
	public void setEstAutorizado(BigDecimal value)
	{
		this._EstAutorizado = value;
	}

	private BigDecimal _EstActivo;
	public BigDecimal getEstActivo()
	{
		return this._EstActivo;
	}
	public void setEstActivo(BigDecimal value)
	{
		this._EstActivo = value;
	}


	public AstUsuExterno(int IdeUsuExterno_,BigDecimal IdeUsuario_,BigDecimal IdeUei_,BigDecimal EstAutorizado_,BigDecimal EstActivo_)
	{
		this._IdeUsuExterno = IdeUsuExterno_;
		this._IdeUsuario = IdeUsuario_;
		this._IdeUei = IdeUei_;
		this._EstAutorizado = EstAutorizado_;
		this._EstActivo = EstActivo_;
	}
}

