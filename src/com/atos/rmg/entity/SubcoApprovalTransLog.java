package com.atos.rmg.entity;

//import java.sql.Date;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@javax.persistence.Table(name="SubcoApprovalTransLog")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region="com.atos.rmg.entity.SubcoApprovalTransLog")
public class SubcoApprovalTransLog {
	
	
	@Id
	
	@Column(name="SubcoDemandId", unique=true)
	private int subcoDemandId;
	
	@Column(name="SubcoDAS_ID")
	private String SubcoDAS_ID;
	
	
	
	@Column(name="Approval")
	private String Approval;
	
	@Column(name="ApprRej_date")
	private Date ApprRej_date;
	
	@Column(name="ApprRej_DASID")
	private String ApprRej_DASID;
	
	@Column(name="ApprRej_FName")
	private String ApprRej_FName;
	
	@Column(name="ApprRej_LName")
	private String ApprRej_LName;
	
	@Column(name="ApprRej_Role")
	private String ApprRej_Role;
	
	@Column(name="ApprRej_Remark")
	private String ApprRej_Remark;
	
	@Column(name="CreatedDate")
	private Date CreatedDate;
	
	@Column(name="CreatedBy")
	private String CreatedBy;
	
	@Column(name="UpdatedDate")
	private Date UpdatedDate;
	
	@Column(name="UpdatedBy")
	private String UpdatedBy;

	

	public String getSubcoDAS_ID() {
		return SubcoDAS_ID;
	}

	public void setSubcoDAS_ID(String subcoDAS_ID) {
		SubcoDAS_ID = subcoDAS_ID;
	}

	public String getApproval() {
		return Approval;
	}

	public void setApproval(String approval) {
		Approval = approval;
	}

	public Date getApprRej_date() {
		return ApprRej_date;
	}

	public void setApprRej_date(Date apprRej_date) {
		ApprRej_date = apprRej_date;
	}

	public String getApprRej_DASID() {
		return ApprRej_DASID;
	}

	public void setApprRej_DASID(String apprRej_DASID) {
		ApprRej_DASID = apprRej_DASID;
	}

	public String getApprRej_FName() {
		return ApprRej_FName;
	}

	public void setApprRej_FName(String apprRej_FName) {
		ApprRej_FName = apprRej_FName;
	}

	public String getApprRej_LName() {
		return ApprRej_LName;
	}

	public void setApprRej_LName(String apprRej_LName) {
		ApprRej_LName = apprRej_LName;
	}

	public String getApprRej_Role() {
		return ApprRej_Role;
	}

	public void setApprRej_Role(String apprRej_Role) {
		ApprRej_Role = apprRej_Role;
	}

	public String getApprRej_Remark() {
		return ApprRej_Remark;
	}

	public void setApprRej_Remark(String apprRej_Remark) {
		ApprRej_Remark = apprRej_Remark;
	}

	public Date getCreatedDate() {
		return CreatedDate;
	}

	public void setCreatedDate(Date createdDate) {
		CreatedDate = createdDate;
	}

	public String getCreatedBy() {
		return CreatedBy;
	}

	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}

	public Date getUpdatedDate() {
		return UpdatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		UpdatedDate = updatedDate;
	}

	public String getUpdatedBy() {
		return UpdatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		UpdatedBy = updatedBy;
	}

	public int getSubcoDemandId() {
		return subcoDemandId;
	}

	public void setSubcoDemandId(int subcoDemandId) {
		this.subcoDemandId = subcoDemandId;
	}

	
	
	@Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(getSubcoDAS_ID());
        return hcb.toHashCode();
    }
 
    @Override
    public boolean equals(Object obj) {
    	if (this.equals(obj)) {
            return true;
        }
        if (!(obj instanceof SubcoApprovalTransLog)) {
            return false;
        }
        SubcoApprovalTransLog that = (SubcoApprovalTransLog) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(this.getSubcoDAS_ID(), that.getSubcoDAS_ID());
        return eb.isEquals();
    }
	

}
