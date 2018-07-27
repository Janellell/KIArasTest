package com.atos.rmg.entity;

//import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 
 * @author A180562
 *
 */
@javax.persistence.Entity
@javax.persistence.Table(name="Subco_Mgr_Master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region="com.atos.rmg.entity.Manager")
public class Manager {

	@Id
	@Column(name="SubcoDemandID", unique=true)
	private int subcoDemandID;

	@Column(name="SubcoDAS_ID")
	private String id;
	
	@Column(name="PMDAS_ID")
	private String pmDAS_ID;
	
	@Column(name="PMFName")
	private String pmFName;
	
	@Column(name="PMLName")
	private String pmLName;
	
	@Column(name="PMEmail")
	private String pmEmail;
	
	@Column(name="PMContactNo")
	private String pmContactNo;
	
	@Column(name="ResMgrDAS_ID")
	private String resMgrDASID;

	@Column(name="ResMgrFName")
	private String resMgrFName;
	
	@Column(name="ResMgrLName")
	private String resMgrLName;

	@Column(name="ResMgrEmail")
	private String resMgrEmail;
	
	@Column(name="ResMgrContatNo")
	private String resMgrContatNo;
	@Column(name="LinMgrDAS_ID")
	private String linMgrDAS_ID;
	@Column(name="LinMgrFName")
	private String linMgrFName;
	@Column(name="LinMgrLName")
	private String linMgrLName;
	@Column(name="LinMgrEmail")
	private String linMgrEmail;
	@Column(name="LinMgrContactNo")
	private String linMgrContactNo;
	@Column(name="OUMgrDAS_ID")
	private String ouMgrDAS_ID;
	@Column(name="OUMgrFName")
	private String ouMgrFName;
	@Column(name="OUMgrLName")
	private String ouMgrLName;
	@Column(name="OUMgrEmail")
	private String ouMgrEmail;
	@Column(name="OUMgrContactNo")
	private String ouMgrContactNo;
	@Column(name="DemandRaisedDate")
	private Date demandRaisedDate;
	public Date getDemandRaisedDate() {
		return demandRaisedDate;
	}
	public void setDemandRaisedDate(Date demandRaisedDate) {
		this.demandRaisedDate = demandRaisedDate;
	}
	//private LocalDateTime demandRaisedDate;
	@Column(name="CreatedDate")
	private Date createdDate;
	@Column(name="CreatedBy")
	private String createdBy;
	@Column(name="UpdatedDate")
	private Date updatedDate;
	@Column(name="UpdatedBy")
	private String updatedBy;
	
/*	public void setDemandRaisedDate(LocalDateTime demandRaisedDate) {
		this.demandRaisedDate = demandRaisedDate;
	}*/
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getSubcoDemandID() {
		return subcoDemandID;
	}
	public void setSubcoDemandID(int subcoDemandID) {
		this.subcoDemandID = subcoDemandID;
	}
	public String getPmDAS_ID() {
		return pmDAS_ID;
	}
	public void setPmDAS_ID(String pmDAS_ID) {
		this.pmDAS_ID = pmDAS_ID;
	}
	public String getPmFName() {
		return pmFName;
	}
	public void setPmFName(String pmFName) {
		this.pmFName = pmFName;
	}
	public String getPmLName() {
		return pmLName;
	}
	public void setPmLName(String pmLName) {
		this.pmLName = pmLName;
	}
	public String getPmEmail() {
		return pmEmail;
	}
	public void setPmEmail(String pmEmail) {
		this.pmEmail = pmEmail;
	}
	public String getPmContactNo() {
		return pmContactNo;
	}
	public void setPmContactNo(String pmContactNo) {
		this.pmContactNo = pmContactNo;
	}
	public String getResMgrDASID() {
		return resMgrDASID;
	}
	public void setResMgrDASID(String resMgrDASID) {
		this.resMgrDASID = resMgrDASID;
	}
	public String getResMgrFName() {
		return resMgrFName;
	}
	public void setResMgrFName(String resMgrFName) {
		this.resMgrFName = resMgrFName;
	}
	public String getResMgrLName() {
		return resMgrLName;
	}
	public void setResMgrLName(String resMgrLName) {
		this.resMgrLName = resMgrLName;
	}
	public String getResMgrEmail() {
		return resMgrEmail;
	}
	public void setResMgrEmail(String resMgrEmail) {
		this.resMgrEmail = resMgrEmail;
	}
	public String getResMgrContatNo() {
		return resMgrContatNo;
	}
	public void setResMgrContatNo(String resMgrContatNo) {
		this.resMgrContatNo = resMgrContatNo;
	}
	public String getLinMgrDAS_ID() {
		return linMgrDAS_ID;
	}
	public void setLinMgrDAS_ID(String linMgrDAS_ID) {
		this.linMgrDAS_ID = linMgrDAS_ID;
	}
	public String getLinMgrFName() {
		return linMgrFName;
	}
	public void setLinMgrFName(String linMgrFName) {
		this.linMgrFName = linMgrFName;
	}
	public String getLinMgrLName() {
		return linMgrLName;
	}
	public void setLinMgrLName(String linMgrLName) {
		this.linMgrLName = linMgrLName;
	}
	public String getLinMgrEmail() {
		return linMgrEmail;
	}
	public void setLinMgrEmail(String linMgrEmail) {
		this.linMgrEmail = linMgrEmail;
	}
	public String getLinMgrContactNo() {
		return linMgrContactNo;
	}
	public void setLinMgrContactNo(String linMgrContactNo) {
		this.linMgrContactNo = linMgrContactNo;
	}
	public String getOuMgrDAS_ID() {
		return ouMgrDAS_ID;
	}
	public void setOuMgrDAS_ID(String ouMgrDAS_ID) {
		this.ouMgrDAS_ID = ouMgrDAS_ID;
	}
	public String getOuMgrFName() {
		return ouMgrFName;
	}
	public void setOuMgrFName(String ouMgrFName) {
		this.ouMgrFName = ouMgrFName;
	}
	public String getOuMgrLName() {
		return ouMgrLName;
	}
	public void setOuMgrLName(String ouMgrLName) {
		this.ouMgrLName = ouMgrLName;
	}
	public String getOuMgrEmail() {
		return ouMgrEmail;
	}
	public void setOuMgrEmail(String ouMgrEmail) {
		this.ouMgrEmail = ouMgrEmail;
	}
	public String getOuMgrContactNo() {
		return ouMgrContactNo;
	}
	public void setOuMgrContactNo(String ouMgrContactNo) {
		this.ouMgrContactNo = ouMgrContactNo;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	

	@Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(this.getId());
        return hcb.toHashCode();
    }
 
    @Override
    public boolean equals(Object obj) {
    	if (this.equals(obj)) {
            return true;
        }
        if (!(obj instanceof Manager)) {
            return false;
        }
        Manager  that = (Manager) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(this.getId(), that.getId());
        return eb.isEquals();
    }
	
}
