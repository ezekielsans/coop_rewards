/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.CoopReServices;

/**
 *
 * @author mis12
 */
@ManagedBean
@SessionScoped
public class CoopRewardsServicesModel implements Serializable {

    /**
     * Creates a new instance of CoopAssuranceClientTypes
     */
    public CoopRewardsServicesModel() {
    }
    /**
     * @declare Non-Primitives
     *
     */
    @ManagedProperty(value = "#{customEntityManagerFactory}")
    private CustomEntityManagerFactory customEntityManagerFactory;
    @ManagedProperty(value = "#{coopReServices}")
    private List<Object> coopReServices;


    /*
     * @declare Primitives
     *
     */
    private String serviceVal;
    private Integer serviceId;

    /**
     * @declare List
     *
     */
    /**
     * @declare: Getters & Setters -List USE: singleton pattern
     *
     */
    public CustomEntityManagerFactory getCustomEntityManagerFactory() {
        return customEntityManagerFactory == null ? customEntityManagerFactory = new CustomEntityManagerFactory() : customEntityManagerFactory;
    }

    public void setCustomEntityManagerFactory(CustomEntityManagerFactory customEntityManagerFactory) {
        this.customEntityManagerFactory = customEntityManagerFactory;
    }

    public List<Object> getCoopReServices() {
        return coopReServices == null ? coopReServices = new ArrayList<>() : coopReServices;
    }

    public void setCoopReServices(List<Object> coopReServices) {
        this.coopReServices = coopReServices;
    }

    public String getServiceVal() {
        return serviceVal;
    }

    public void setServiceVal(String serviceVal) {
        this.serviceVal = serviceVal;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    /*
     * @declare Methods - init / beanclear
     *
     */
    public void init() {

        if (FacesContext.getCurrentInstance().isPostback() == false) {

            setCoopReServices(getCustomEntityManagerFactory().getLportalMemOrgEntityManagerFactory().createEntityManager().createNativeQuery(""
                    + "SELECT * FROM coop_re_services s").getResultList());
        }
    }

}
