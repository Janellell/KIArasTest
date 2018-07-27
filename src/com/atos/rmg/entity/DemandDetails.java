package com.atos.rmg.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;

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
@javax.persistence.Table(name="DemandStatus_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region="com.atos.rmg.entity.DemandDetails")
public class DemandDetails {
		
	@Id
	@Column(name="DemandStatusID", unique=true)
	private String demandStatusID;
	
    @Column(name="DemandStatus")
	private String demandStatus;
	@Column(name="StatusRemark")
	private String statusRemark;
	@Column(name="CreatedDate")
	private Date createdDate;
	@Column(name="CreatedBy")
	private String createdBy;
	@Column(name="UpdatedDate")
	private Date updatedDate;
	@Column(name="UpdatedBy")
	private String updatedBy;
	
	
	public String getDemandStatusID() {
		return demandStatusID;
	}
	public void setDemandStatusID(String demandStatusID) {
		this.demandStatusID = demandStatusID;
	}
	public String getDemandStatus() {
		return demandStatus;
	}
	public void setDemandStatus(String demandStatus) {
		this.demandStatus = demandStatus;
	}
	public String getStatusRemark() {
		return statusRemark;
	}
	public void setStatusRemark(String statusRemark) {
		this.statusRemark = statusRemark;
	}
	public Date getCreatedDate() {
		return createdDate;
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
        hcb.append(this.getDemandStatus());
        return hcb.toHashCode();
    }
 
    @Override
    public boolean equals(Object obj) {
    	   if (this.equals(obj)) {
            return true;
        }
        if (!(obj instanceof CountryPractise)) {
            return false;
        }
        DemandDetails  that = (DemandDetails) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(this.getDemandStatus(), that.getDemandStatus());
        return eb.isEquals();
    }
	
	
}
