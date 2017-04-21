package com.thadeu.emaus.web;

import com.thadeu.emaus.entity.Doador;
import com.thadeu.emaus.web.util.JsfUtil;
import com.thadeu.emaus.session.DoadorFacade;

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

@Named("doadorController")
@SessionScoped
public class DoadorController implements Serializable {

    

    private Doador current;
    private DataModel items = null;
    @EJB
    private com.thadeu.emaus.session.DoadorFacade ejbFacade;
    private Map<Integer, Boolean> checked = new HashMap<>();
    private Map<String, String> criteriaParams = new HashMap<String, String>();
    private String paramDoador;
    private String paramDdd;
    private String paramTelefone;
    

    

    public Map<String, String> getCriteriaParams() {
        return criteriaParams;
    }

    public void setCriteriaParams(Map<String, String> criteriaParams) {
        this.criteriaParams = criteriaParams;
    }
    

    public String getParamDoador() {
        return paramDoador;
    }

    public void setParamDoador(String paramDoador) {
        this.paramDoador = paramDoador;
    }
    
    public String getParamDdd() {
        return paramDdd;
    }

    public void setParamDdd(String paramDdd) {
        this.paramDdd = paramDdd;
    }

    public String getParamTelefone() {
        return paramTelefone;
    }

    public void setParamTelefone(String paramTelefone) {
        this.paramTelefone = paramTelefone;
    }

    
    

    public DoadorController() {
    }

    public Doador getSelected() {
        if (current == null) {
            current = new Doador();
        }
        return current;
    }

    private DoadorFacade getFacade() {
        return ejbFacade;
    }

  

    public String prepareList() {
        recreateModel();
        return "ListDoador";
    }

    public String prepareView() {
        current = (Doador) getItems().getRowData();
        return "ViewDoador";
    }

    public String prepareCreate() {
        current = new Doador();
        
        return "CreateDoador";
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
        current = (Doador) getItems().getRowData();
        return "EditDoador";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RegistroAtualizado"));
            return "ListDoador";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("ErroDePersistencia"));
            return null;
        }
    }

    public String destroy() {
        current = (Doador) getItems().getRowData();
        performDestroy();
        recreateModel();
        return "ListDoador";
    }
    
    public String resetSearch() {
        recreateModel();
        getItems();
        return "ListDoador";
    }
    
    public String performSearch() {
        recreateModel();
        if (paramDoador == null & paramDdd == null & paramTelefone == null) {
            items = new ListDataModel(ejbFacade.findAll());
        } 
        else {
            if (paramDoador != null) {
                criteriaParams.put("nomeDoador", paramDoador); 
            }
            if (paramDdd != null) {
                criteriaParams.put("ddd1", paramDdd); 
            }
            if (paramTelefone != null) {
                criteriaParams.put("telefone1", paramTelefone); 
            }
            items = new ListDataModel(ejbFacade.findLike(criteriaParams));
        }
        return "ListDoador";
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
        return "ListDoador";
    }

    public Map<Integer, Boolean> getChecked() {
        return checked;
    }

    public void setChecked(Map<Integer, Boolean> checked) {
        this.checked = checked;
    }

    public String destroyAndView() {
        performDestroy();
        
        return "ViewDoador";
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

    public Doador getDoador(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Doador.class)
    public static class DoadorControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DoadorController controller = (DoadorController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "doadorController");
            return controller.getDoador(getKey(value));
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
            if (object instanceof Doador) {
                Doador o = (Doador) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Doador.class.getName());
            }
        }

    }

}
