/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thadeu.emaus.session;

import com.thadeu.emaus.entity.TipoItemDoacao;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author thadeu
 */
@Stateless
public class TipoItemDoacaoFacade extends AbstractFacade<TipoItemDoacao> {

    @PersistenceContext(unitName = "emausPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoItemDoacaoFacade() {
        super(TipoItemDoacao.class);
    }
    
}
