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
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.persistence.Table;

/**
 *
 * @author thadeu
 */
@Entity
@Table(name="doador")
public class Doador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="doador_seq", allocationSize = 1, sequenceName="seq_doador")
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="doador_seq")
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Column(name="nome_doador", nullable=false, length=100)
    private String nomeDoador;
    
    @Column(length=8)
    private String cep;
    
    @ManyToOne
    @JoinColumn(name="idCidade", nullable=true, foreignKey=@ForeignKey(name="fk_cidade_doador"))
    private Cidade cidade;
    
    @Column(length=100)
    private String bairro;
    
    @Column(length=200)
    private String logradouro;
    
    @Column(length=200)
    private String referencia;

    
    
    @Column(length=5)
    private String numero;
    
    @Column(nullable=false, length=3)
    private String ddd1;

       
    @Column(nullable=false, length=9)
    private String telefone1;
    
    @Column(length=3)
    private String ddd2;
    
    @Column(length=9)
    private String telefone2;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeDoador() {
        return nomeDoador;
    }

    public void setNomeDoador(String nomeDoador) {
        this.nomeDoador = nomeDoador;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
    

    
    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
    
    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    public String getDdd1() {
        return ddd1;
    }

    public void setDdd1(String ddd1) {
        this.ddd1 = ddd1;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getDdd2() {
        return ddd2;
    }

    public void setDdd2(String ddd2) {
        this.ddd2 = ddd2;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }
    
    public String getMaskDdd1(){
    return "("+this.ddd1+")";
    }
    
    public String getMaskTelefone1(){
    return this.telefone1.substring(0, 5)+" - "+ this.telefone1.substring(5,9);
    }
    
    public String getMaskDdd2(){
    return "("+this.ddd2+")";
    }
    
    public String getMaskTelefone2(){
        if (this.telefone2 != null){
            return this.telefone2.substring(0, 5)+" - "+ this.telefone2.substring(5,9);
        }
        else{
            return "";
        }
            
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
        if (!(object instanceof Doador)) {
            return false;
        }
        Doador other = (Doador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.thadeu.emaus.entity.Doador[ id=" + id + " ]";
    }
    
}
