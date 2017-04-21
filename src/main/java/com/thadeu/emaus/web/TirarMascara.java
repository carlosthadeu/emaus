/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thadeu.emaus.web;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author thadeu
 */


@FacesConverter(value="tirarMascara")
public class TirarMascara implements Converter {
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String valor) {
        if(valor != null || valor != "") {
			valor = valor.toString().replaceAll("\\(", "");
			valor = valor.toString().replaceAll("\\)", "");
                        valor = valor.toString().replaceAll("-", "");
		}
		return valor;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object valor) {
        return valor.toString();
    }
}
