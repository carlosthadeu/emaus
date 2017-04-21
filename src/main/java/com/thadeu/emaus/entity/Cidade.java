/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thadeu.emaus.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author thadeu
 */
@Entity
public class Cidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id    
    @SequenceGenerator(name="cidade_seq", allocationSize = 1, sequenceName="seq_cidade")
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="cidade_seq")
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Column(name="codigo_ibge", length=7)
    private String codigoIbge;
    
    @Column(name="nome_municipio", length=100, nullable=false, unique=true)
    private String nomeMunicipio;
    
    @OneToMany(mappedBy="cidade")
    private List<Doador> doadores;

    public String getCodigoIbge() {
        return codigoIbge;
    }

    public void setCodigoIbge(String codigoIbge) {
        this.codigoIbge = codigoIbge;
    }

    public String getNomeMunicipio() {
        return nomeMunicipio;
    }

    public void setNomeMunicipio(String nomeMunicipio) {
        this.nomeMunicipio = nomeMunicipio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    public List<Doador> getDoadores() {
        return doadores;
    }

    public void setDoadores(List<Doador> doadores) {
        this.doadores = doadores;
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
        if (!(object instanceof Cidade)) {
            return false;
        }
        Cidade other = (Cidade) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nomeMunicipio ;
    }
    
}
