/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thadeu.emaus.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author thadeu
 */
@Entity
public class Doacoes implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id    
    @SequenceGenerator(name="doacoes_seq", allocationSize = 1, sequenceName="seq_doacoes")
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="doacoes_seq")
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "data_ligacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataLigacao;
    
    @Column(name = "data_buscaPrevisao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataBuscaPrevisao;
    
    @Column(name = "status_doacao")
    private StatusDoacoes statusDoacao;
    
    @Column(name="observacao", length = 2000)
    private String observacao;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Doacoes other = (Doacoes) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
