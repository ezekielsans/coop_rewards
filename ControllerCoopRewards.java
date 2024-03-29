/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;

import java.sql.SQLException;
import java.sql.Types;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import model.CoopRewardsInputModel;
import model.CoopRewardsView;

/**
 *
 * @author misteam
 */
@ManagedBean
@RequestScoped
public class ControllerCoopRewards implements Serializable {

    /**
     * Creates a new instance of AssuranceReportController
     */
    public ControllerCoopRewards() {
    }

    /**
     * Properties
     */
    @ManagedProperty(value = "#{messages}")
    private Messages messages;
    @ManagedProperty(value = "#{customEntityManagerFactory}")
    private CustomEntityManagerFactory customEntityManagerFactory;
    @ManagedProperty(value = "#{dbConnection}")
    private DbConnection dbConnection;
    @ManagedProperty(value = "#{portalData}")
    private PortalData portalData;
    @ManagedProperty(value = "#{customDate}")
    private CustomDate customDate;
    @ManagedProperty(value = "#{coopRewardsInputModel}")
    private CoopRewardsInputModel coopRewardsInputModel;

    /**
     * Getter & Setter
     *
     * @return
     */
    public Messages getMessages() {
        return messages;
    }

    public void setMessages(Messages messages) {
        this.messages = messages;
    }

    public CustomEntityManagerFactory getCustomEntityManagerFactory() {
        return customEntityManagerFactory == null ? customEntityManagerFactory = new CustomEntityManagerFactory() : customEntityManagerFactory;
    }

    public void setCustomEntityManagerFactory(CustomEntityManagerFactory customEntityManagerFactory) {
        this.customEntityManagerFactory = customEntityManagerFactory;
    }

    public DbConnection getDbConnection() {
        return dbConnection == null ? dbConnection = new DbConnection() : dbConnection;
    }

    public void setDbConnection(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public PortalData getPortalData() {
        return portalData == null ? portalData = new PortalData() : portalData;
    }

    public void setPortalData(PortalData portalData) {
        this.portalData = portalData;
    }

    public CustomDate getCustomDate() {
        return customDate == null ? customDate = new CustomDate() : customDate;
    }

    public void setCustomDate(CustomDate customDate) {
        this.customDate = customDate;
    }

    public CoopRewardsInputModel getCoopRewardsInputModel() {
        return coopRewardsInputModel == null ? coopRewardsInputModel = new CoopRewardsInputModel() : coopRewardsInputModel;
    }

    public void setCoopRewardsInputModel(CoopRewardsInputModel coopRewardsInputModel) {
        this.coopRewardsInputModel = coopRewardsInputModel;
    }

    /**
     * Methods
     *
     * @return
     */
    public void checkAcctno() throws SQLException {
        String query;
        String acctno = getCoopRewardsInputModel().getAccountNo();
        try {
            if (acctno != null && !acctno.isEmpty()) {

                query = "SELECT c "
                        + "FROM CoopRewardsView c "
                        + " WHERE c.scAcctno = :acctno ";
                getCoopRewardsInputModel().setCoopRewardsView((CoopRewardsView) getCustomEntityManagerFactory().getLportalMemOrgEntityManagerFactory().createEntityManager().createQuery(query).setParameter("acctno", acctno).getSingleResult());
                System.out.println("COOP REWARDS VIEW" + getCoopRewardsInputModel().getCoopRewardsView().getName());
            }
        } catch (Exception e) {
            System.out.println("Error -Checkacctno " + e.getMessage());
            System.out.println("Ito yung name " + getCoopRewardsInputModel().getCoopRewardsView().getName());
            getCoopRewardsInputModel().setCoopRewardsView(new CoopRewardsView());
            getCoopRewardsInputModel().getCoopRewardsView().setName("Non-Member");
        }

    }

    public void submit() throws SQLException {
        getDbConnection().setDbUserName(String.valueOf(getPortalData().getLiferayFacesContext().getUser().getUserId()));
        getDbConnection().lportalMemOrgConnection = getDbConnection().connectToLportalMemOrg();

        if (getDbConnection().lportalMemOrgConnection != null) {
            try {
                System.out.println("papasok sa callable");
                getDbConnection().callableStatement = getDbConnection().lportalMemOrgConnection.prepareCall("{ ? = call submit_coop_rewards(?,?,?)}");
                getDbConnection().callableStatement.registerOutParameter(1, Types.VARCHAR);
                getDbConnection().callableStatement.setString(2, ((String) getCoopRewardsInputModel().getAccountNo())); //acctno
                getDbConnection().callableStatement.setBigDecimal(3, (getCoopRewardsInputModel().getAmount())); //amount
                getDbConnection().callableStatement.setInt(4, getCoopRewardsInputModel().getCoopReServices().get(0).getServicesId()); //service id
                getDbConnection().callableStatement.execute();
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notice", "Data Added Successfully.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                clear();

            } catch (Exception e) {
                System.out.println("ControllerCoopRewards " + e);
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Database Function Error.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } finally {

                getDbConnection().callableStatement.close();
                getDbConnection().lportalMemOrgConnection.close();

            }
        }
    }

    public void init() {
        if (FacesContext.getCurrentInstance().isPostback() == false) {
            System.out.println("acctno" + getCoopRewardsInputModel().getAccountNo());
            clear();

        }

    }

    public void clear() {
        getCoopRewardsInputModel().setAccountNo(null);
        getCoopRewardsInputModel().setAmount(null);
        getCoopRewardsInputModel().setServiceName(null);
        getCoopRewardsInputModel().getCoopRewardsView().setName(null);

    }

}
