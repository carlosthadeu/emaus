/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thadeu.emaus.entity;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author thadeu
 */
public enum StatusDoacoes {
    
    OFERTADA("Ofertada","O"), EM_ROTA("Em Rota","R"), CANCELADA("Cancelada","C"), EFETIVADA("Efetivada","E");
    
    private final String codigo;

    
    private final String descricao;

	StatusDoacoes(String codigo, String descricao) {
            this.descricao = descricao;
            this.codigo = codigo;
	}
        
        
        private static Map<String, StatusDoacoes> codigoStatusMapeamento;
        
        public static StatusDoacoes getStatus(String codigo) {
        if (codigoStatusMapeamento == null) {
            iniciarMapeamento();
        }
        return codigoStatusMapeamento.get(codigo);
        }
        
        private static void iniciarMapeamento() {
        codigoStatusMapeamento = new HashMap<String, StatusDoacoes>();
        for (StatusDoacoes s : values()) {
            codigoStatusMapeamento.put(s.codigo, s);
        }
        }
        
        public String getCodigo() {
            return codigo;
        }
        
        public String getDescricao() {
            return descricao;
        }

    
}
