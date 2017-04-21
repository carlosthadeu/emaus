package com.thadeu.emaus.web;

import com.thadeu.emaus.entity.Cidade;
import com.thadeu.emaus.web.util.JsfUtil;
import com.thadeu.emaus.session.CidadeFacade;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("cidadeController")
@SessionScoped
public class CidadeController implements Serializable {

    private Cidade current;
    private DataModel items = null;
    @EJB
    private com.thadeu.emaus.session.CidadeFacade ejbFacade;
    private Map<Integer, Boolean> checked = new HashMap<Integer, Boolean>();
    private Map<String, String> criteriaParams;
    private String paramCidade;
    private String paramIbge;
    private DataModel cidades = null;

    public String getParamIbge() {
        return paramIbge;
    }
    
    public DataModel getCidades() {
        if (cidades == null) {
            cidades = new ListDataModel(ejbFacade.findAll());
        }
        return cidades;
    }

    public void setParamIbge(String paramIbge) {
        this.paramIbge = paramIbge;
    }

    public Map<String, String> getCriteriaParams() {
        return criteriaParams;
    }

    public void setCriteriaParams(Map<String, String> criteriaParams) {
        this.criteriaParams = criteriaParams;
    }
    

    public String getParamCidade() {
        return paramCidade;
    }

    public void setParamCidade(String paramCidade) {
        this.paramCidade = paramCidade;
    }
    

    public CidadeController() {
    }

    public Cidade getSelected() {
        if (current == null) {
            current = new Cidade();
        }
        return current;
    }

    private CidadeFacade getFacade() {
        return ejbFacade;
    }

  

    public String prepareList() {
        recreateModel();
        return "ListCidade";
    }

    public String prepareView() {
        current = (Cidade) getItems().getRowData();
        return "ViewCidade";
    }

    public String prepareCreate() {
        current = new Cidade();
        
        return "CreateCidade";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RegistroCriado"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("ErroDePersistencia"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Cidade) getItems().getRowData();
        return "EditCidade";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RegistroAtualizado"));
            return "ListCidade";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("ErroDePersistencia"));
            return null;
        }
    }

    public String destroy() {
        current = (Cidade) getItems().getRowData();
        performDestroy();
        recreateModel();
        return "ListCidade";
    }
    
    public String resetSearch() {
        recreateModel();
        getItems();
        return "ListCidade";
    }
    
    public String performSearch() {
        recreateModel();
        criteriaParams = new HashMap<String, String>();
        if (paramCidade == null & paramIbge == null) {
            items = new ListDataModel(ejbFacade.findAll());
        } 
        else {
            if (paramCidade != null){
                criteriaParams.put("nomeMunicipio", paramCidade); 
            }
            if (paramIbge != null){
                criteriaParams.put("codigoIbge", paramIbge); 
            }
        items = new ListDataModel(ejbFacade.findLike(criteriaParams));
        }
        paramCidade = "";
        paramIbge = "";
        return "ListCidade";
    }
    
    public String destroyList(){
        Integer key = 0;
        Iterator it = checked.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry<Integer, Boolean> item = (Map.Entry<Integer, Boolean>) it.next();
            if (item.getValue()) {
                key = item.getKey();
                current = ejbFacade.find(key);
                performDestroy();
                it.remove();
            }
        }
        recreateModel();
        JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RegistroExcluido"));
        return "ListCidade";
    }

    public Map<Integer, Boolean> getChecked() {
        return checked;
    }

    public void setChecked(Map<Integer, Boolean> checked) {
        this.checked = checked;
    }

    public String destroyAndView() {
        performDestroy();
        
        return "ViewCidade";
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("ErroDePersistencia"));
        }
    }

    

    public DataModel getItems() {
        if (items == null) {
            items = new ListDataModel(ejbFacade.findAll());
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

   

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Cidade getCidade(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Cidade.class)
    public static class CidadeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CidadeController controller = (CidadeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cidadeController");
            return controller.getCidade(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Cidade) {
                Cidade o = (Cidade) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Cidade.class.getName());
            }
        }

    }

}
