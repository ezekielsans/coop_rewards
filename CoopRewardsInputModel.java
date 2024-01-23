/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import bean.CustomEntityManagerFactory;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author mis
 */
@ManagedBean
@SessionScoped
public class CoopRewardsInputModel implements serializable {

    public CoopRewardsInputModel() {
    }

    private String accountNo;
    private BigDecimal amount;
    private CoopRewardsView coopRewardsView; // single
    @ManagedProperty(value = "#{customEntityManagerFactory}")
    private CustomEntityManagerFactory customEntityManagerFactory;
    @ManagedProperty(value = "#{coopReServices}")
    private List<CoopReServices> coopReServices;

    public CustomEntityManagerFactory getCustomEntityManagerFactory() {
        return customEntityManagerFactory == null ? customEntityManagerFactory = new CustomEntityManagerFactory() : customEntityManagerFactory;
    }

    public void setCustomEntityManagerFactory(CustomEntityManagerFactory customEntityManagerFactory) {
        this.customEntityManagerFactory = customEntityManagerFactory;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public CoopRewardsView getCoopRewardsView() {
        return coopRewardsView == null ? coopRewardsView = new CoopRewardsView() : coopRewardsView;
    }

    public void setCoopRewardsView(CoopRewardsView coopRewardsView) {
        this.coopRewardsView = coopRewardsView;
    }

    public List<CoopReServices> getCoopReServices() {
        return coopReServices == null ? coopReServices = new ArrayList<>() : coopReServices;
    }

    public void setCoopReServices(List<CoopReServices> coopReServices) {
        this.coopReServices = coopReServices;
    }

    public void init() {
        if (FacesContext.getCurrentInstance().isPostback() == false) {
            setCoopReServices(getCustomEntityManagerFactory().getLportalMemOrgEntityManagerFactory().createEntityManager().createQuery(""
                    + "SELECT s FROM CoopReServices s ").getResultList());

            clear();

        }

    }

    public void clear() {
        setAccountNo(null);
        setAmount(null);

    }

}
