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
@Table(name = "tipo_telefone")
@NamedQueries({
@NamedQuery(name = "TipoTelefone.findAll", query = "SELECT t FROM TipoTelefone t")})
public class TipoTelefone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="tipo_telefone_seq", allocationSize = 1, sequenceName="seq_tipo_telefone")
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="tipo_telefone_seq")
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tipo_telefone")
    private String tipoTelefone;

    public TipoTelefone() {
    }

    public TipoTelefone(Integer id) {
        this.id = id;
    }

    public TipoTelefone(Integer id, String tipoTelefone) {
        this.id = id;
        this.tipoTelefone = tipoTelefone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(String tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
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
        if (!(object instanceof TipoTelefone)) {
            return false;
        }
        TipoTelefone other = (TipoTelefone) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.thadeu.emaus.entity.TipoTelefone[ id=" + id + " ]";
    }
    
}
