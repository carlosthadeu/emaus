package com.thadeu.emaus.web;

import com.thadeu.emaus.entity.TipoItemDoacao;
import com.thadeu.emaus.web.util.JsfUtil;
import com.thadeu.emaus.session.TipoItemDoacaoFacade;

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

@Named("tipoItemDoacaoController")
@SessionScoped
public class TipoItemDoacaoController implements Serializable {

    private TipoItemDoacao current;
    private DataModel items = null;
    @EJB
    private com.thadeu.emaus.session.TipoItemDoacaoFacade ejbFacade;
    private Map<Integer, Boolean> checked = new HashMap<>();
    private Map<String, String> criteriaParams = new HashMap<String, String>();
    private String paramTipoItemDoacao;

    public Map<String, String> getCriteriaParams() {
        return criteriaParams;
    }

    public void setCriteriaParams(Map<String, String> criteriaParams) {
        this.criteriaParams = criteriaParams;
    }
    

    public String getParamTipoItemDoacao() {
        return paramTipoItemDoacao;
    }

    public void setParamTipoItemDoacao(String paramTipoItemDoacao) {
        this.paramTipoItemDoacao = paramTipoItemDoacao;
    }
    

    public TipoItemDoacaoController() {
    }

    public TipoItemDoacao getSelected() {
        if (current == null) {
            current = new TipoItemDoacao();
        }
        return current;
    }

    private TipoItemDoacaoFacade getFacade() {
        return ejbFacade;
    }

  

    public String prepareList() {
        recreateModel();
        return "ListTipoItemDoacao";
    }

    public String prepareView() {
        current = (TipoItemDoacao) getItems().getRowData();
        return "ViewTipoItemDoacao";
    }

    public String prepareCreate() {
        current = new TipoItemDoacao();
        
        return "CreateTipoItemDoacao";
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
        current = (TipoItemDoacao) getItems().getRowData();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RegistroAtualizado"));
            return "ListTipoItemDoacao";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("ErroDePersistencia"));
            return null;
        }
    }

    public String destroy() {
        current = (TipoItemDoacao) getItems().getRowData();
        performDestroy();
        recreateModel();
        return "ListTipoItemDoacao";
    }
    
    public String resetSearch() {
        recreateModel();
        getItems();
        return "ListTipoItemDoacao";
    }
    
    public String performSearch() {
        recreateModel();
        if (paramTipoItemDoacao == null) {
            items = new ListDataModel(ejbFacade.findAll());
        } 
        else {
        criteriaParams.put("tipoItemDoacao", paramTipoItemDoacao); 
        items = new ListDataModel(ejbFacade.findLike(criteriaParams));
        }
        return "ListTipoItemDoacao";
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
        return "ListTipoItemDoacao";
    }

    public Map<Integer, Boolean> getChecked() {
        return checked;
    }

    public void setChecked(Map<Integer, Boolean> checked) {
        this.checked = checked;
    }

    public String destroyAndView() {
        performDestroy();
        
        return "ViewTipoItemDoacao";
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

    public TipoItemDoacao getTipoItemDoacao(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = TipoItemDoacao.class)
    public static class TipoItemDoacaoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoItemDoacaoController controller = (TipoItemDoacaoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoItemDoacaoController");
            return controller.getTipoItemDoacao(getKey(value));
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
            if (object instanceof TipoItemDoacao) {
                TipoItemDoacao o = (TipoItemDoacao) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TipoItemDoacao.class.getName());
            }
        }

    }

}
