/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mis
 */
@Entity
@Table(name = "coop_re_services")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopReServices.findAll", query = "SELECT c FROM CoopReServices c"),
    @NamedQuery(name = "CoopReServices.findByServicesId", query = "SELECT c FROM CoopReServices c WHERE c.servicesId = :servicesId"),
    @NamedQuery(name = "CoopReServices.findByServicesName", query = "SELECT c FROM CoopReServices c WHERE c.servicesName = :servicesName")})
public class CoopReServices implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "services_id")
    private Integer servicesId;
    @Size(max = 2147483647)
    @Column(name = "services_name")
    private String servicesName;

    public CoopReServices() {
    }

    public CoopReServices(Integer servicesId) {
        this.servicesId = servicesId;
    }

    public Integer getServicesId() {
        return servicesId;
    }

    public void setServicesId(Integer servicesId) {
        this.servicesId = servicesId;
    }

    public String getServicesName() {
        return servicesName;
    }

    public void setServicesName(String servicesName) {
        this.servicesName = servicesName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (servicesId != null ? servicesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoopReServices)) {
            return false;
        }
        CoopReServices other = (CoopReServices) object;
        if ((this.servicesId == null && other.servicesId != null) || (this.servicesId != null && !this.servicesId.equals(other.servicesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.CoopReServices[ servicesId=" + servicesId + " ]";
    }
    
}
