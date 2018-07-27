package com.atos.rmg.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@javax.persistence.Table(name="ApprovalTrans_History")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region="com.atos.rmg.entity.SubconHistory")
public class SubconHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	/*@Column(name="History_ID")
	private Integer History_ID;*/
	
	@Column(name="SubcoDemandId")
	private int subcoDemandId;
	
	@Column(name="SubcoDAS_ID")
	private String SubcodasID;
	
	@Column(name="Approval")
	private String approval;
	
	@Column(name="ApprRej_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date apprejdate;
	
	@Column(name="ApprRej_DASID")
	private String apprrejdasid;
	
	@Column(name="ApprRej_FName")
	private String apprrejFname;
	
	@Column(name="ApprRej_LName")
	private String apprejLname;
	
	@Column(name="ApprRej_Role")
	private String apprejrole;
	
	@Column(name="ApprRej_Remark")
	private String apprejremark;
	
	@Column(name="CreatedDate")
	private Date createddate;
	
	@Column(name="CreatedBy")
	private String createdby;

	
	@Column(name="UpdatedDate")
	private Date updateddate;
	
	@Column(name="UpdatedBy")
	private String updatedby;

	
	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	public Date getApprejdate() {
		return apprejdate;
	}

	public void setApprejdate(Date apprejdate) {
		this.apprejdate = apprejdate;
	}

	public String getApprrejdasid() {
		return apprrejdasid;
	}

	public void setApprrejdasid(String apprrejdasid) {
		this.apprrejdasid = apprrejdasid;
	}

	public String getApprrejFname() {
		return apprrejFname;
	}

	public void setApprrejFname(String apprrejFname) {
		this.apprrejFname = apprrejFname;
	}

	public String getApprejLname() {
		return apprejLname;
	}

	public void setApprejLname(String apprejLname) {
		this.apprejLname = apprejLname;
	}

	public String getApprejrole() {
		return apprejrole;
	}

	public void setApprejrole(String apprejrole) {
		this.apprejrole = apprejrole;
	}

	public String getApprejremark() {
		return apprejremark;
	}

	public void setApprejremark(String apprejremark) {
		this.apprejremark = apprejremark;
	}

	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public Date getUpdateddate() {
		return updateddate;
	}

	public void setUpdateddate(Date updateddate) {
		this.updateddate = updateddate;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	
	public String getSubcodasID() {
		return SubcodasID;
	}

	public void setSubcodasID(String subcodasID) {
		SubcodasID = subcodasID;
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
        hcb.append(getSubcodasID());
        return hcb.toHashCode();
    }
 
    @Override
    public boolean equals(Object obj) {
    	if (this.equals(obj)) {
            return true;
        }
        if (!(obj instanceof SubconHistory)) {
            return false;
        }
        SubconHistory that = (SubconHistory) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(this.getSubcodasID(), that.getSubcodasID());
        return eb.isEquals();
    }

	
	
	
}
