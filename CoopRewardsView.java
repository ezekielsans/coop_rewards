/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "coop_rewards_view")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoopRewardsView.findAll", query = "SELECT c FROM CoopRewardsView c"),
    @NamedQuery(name = "CoopRewardsView.findByScAcctno", query = "SELECT c FROM CoopRewardsView c WHERE c.scAcctno = :scAcctno"),
    @NamedQuery(name = "CoopRewardsView.findByName", query = "SELECT c FROM CoopRewardsView c WHERE c.name = :name")})
public class CoopRewardsView implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 2147483647)
    @Id
    @Column(name = "sc_acctno")
    private String scAcctno;
    @Size(max = 2147483647)
    @Column(name = "name")
    private String name;

    public CoopRewardsView() {
    }

    public String getScAcctno() {
        return scAcctno;
    }

    public void setScAcctno(String scAcctno) {
        this.scAcctno = scAcctno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
