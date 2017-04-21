package com.thadeu.emaus.web;

import com.thadeu.emaus.entity.TipoTelefone;
import com.thadeu.emaus.web.util.JsfUtil;
import com.thadeu.emaus.session.TipoTelefoneFacade;

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

@Named("tipoTelefoneController")
@SessionScoped
public class TipoTelefoneController implements Serializable {

    private TipoTelefone current;
    private DataModel items = null;
    @EJB
    private com.thadeu.emaus.session.TipoTelefoneFacade ejbFacade;
    private Map<Integer, Boolean> checked = new HashMap<>();
    private Map<String, String> criteriaParams = new HashMap<String, String>();
    private String paramTipoTelefone;

    public Map<String, String> getCriteriaParams() {
        return criteriaParams;
    }

    public void setCriteriaParams(Map<String, String> criteriaParams) {
        this.criteriaParams = criteriaParams;
    }
    

    public String getParamTipoTelefone() {
        return paramTipoTelefone;
    }

    public void setParamTipoTelefone(String paramTipoTelefone) {
        this.paramTipoTelefone = paramTipoTelefone;
    }
    

    public TipoTelefoneController() {
    }

    public TipoTelefone getSelected() {
        if (current == null) {
            current = new TipoTelefone();
        }
        return current;
    }

    private TipoTelefoneFacade getFacade() {
        return ejbFacade;
    }

  

    public String prepareList() {
        recreateModel();
        return "ListTipoTelefone";
    }

    public String prepareView() {
        current = (TipoTelefone) getItems().getRowData();
        return "ViewTipoTelefone";
    }

    public String prepareCreate() {
        current = new TipoTelefone();
        
        return "CreateTipoTelefone";
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
        current = (TipoTelefone) getItems().getRowData();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RegistroAtualizado"));
            return "ListTipoTelefone";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("ErroDePersistencia"));
            return null;
        }
    }

    public String destroy() {
        current = (TipoTelefone) getItems().getRowData();
        performDestroy();
        recreateModel();
        return "ListTipoTelefone";
    }
    
    public String resetSearch() {
        recreateModel();
        getItems();
        return "ListTipoTelefone";
    }
    
    public String performSearch() {
        recreateModel();
        if (paramTipoTelefone == null) {
            items = new ListDataModel(ejbFacade.findAll());
        } 
        else {
        criteriaParams.put("tipoTelefone", paramTipoTelefone); 
        items = new ListDataModel(ejbFacade.findLike(criteriaParams));
        }
        return "ListTipoTelefone";
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
        return "ListTipoTelefone";
    }

    public Map<Integer, Boolean> getChecked() {
        return checked;
    }

    public void setChecked(Map<Integer, Boolean> checked) {
        this.checked = checked;
    }

    public String destroyAndView() {
        performDestroy();
        
        return "ViewTipoTelefone";
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

    public TipoTelefone getTipoTelefone(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = TipoTelefone.class)
    public static class TipoTelefoneControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoTelefoneController controller = (TipoTelefoneController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoTelefoneController");
            return controller.getTipoTelefone(getKey(value));
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
            if (object instanceof TipoTelefone) {
                TipoTelefone o = (TipoTelefone) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TipoTelefone.class.getName());
            }
        }

    }

}
