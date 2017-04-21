/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thadeu.emaus.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author thadeu
 */
@Entity
@Table(name = "tipo_item_doacao")
@NamedQueries({
@NamedQuery(name = "TipoItemDoacao.findAll", query = "SELECT t FROM TipoItemDoacao t")})
public class TipoItemDoacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="tipo_item_doacao_seq", allocationSize = 1, sequenceName="seq_tipo_item_doacao")
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="tipo_item_doacao_seq")
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tipo_item_doacao")
    private String tipoItemDoacao;

    public TipoItemDoacao() {
    }

    public TipoItemDoacao(Integer id) {
        this.id = id;
    }

    public TipoItemDoacao(Integer id, String tipoItemDoacao) {
        this.id = id;
        this.tipoItemDoacao = tipoItemDoacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoItemDoacao() {
        return tipoItemDoacao;
    }

    public void setTipoItemDoacao(String tipoItemDoacao) {
        this.tipoItemDoacao = tipoItemDoacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoItemDoacao)) {
            return false;
        }
        TipoItemDoacao other = (TipoItemDoacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.tipoItemDoacao;
    }
    
}
