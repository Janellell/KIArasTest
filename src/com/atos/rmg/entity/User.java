package com.atos.rmg.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import org.hibernate.annotations.Cascade;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

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
@javax.persistence.Table(name="UserMaster")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region="com.atos.rmg.entity.User")
public class User  implements Serializable{

	@Id
	@Column(name="UserID", unique=false)
	private String userID;
	@Column(name="UserPwd")
	private String userPwd;
	//@Id
	@Column(name="UserWorkflowRole")
	private String userWorkflowRole;
	@Column(name="GBUID")
	private String gbuId;
	@Column(name="CountryID")
	private String countryID;
	@Column(name="OU_ID")
	private String ouId;
	@Column(name="GPracticeID")
	private String gPracticeID;
	@Column(name="SubPracticeID")
	private String subPracticeID;
	@Column(name="EmailId")
	private String emailID;
	@Column(name="CreatedDate")
	private Date createdDate;
	@Column(name="CreatedBy")
	private String createdBy;
	@Column(name="UpdatedDate")
	private Date updatedDate;
	@Column(name="UpdatedBy")
	private String updatedBy;
	@Column(name="UserFName")
	private String userFName;
	@Column(name="UserLName")
	private String userLName;
	@Column(name="UserActiveFlag")
	private String userActFlag;
	public String getUserActFlag() {
		return userActFlag;
	}
	public void setUserActFlag(String userActFlag) {
		this.userActFlag = userActFlag;
	}
	public String getUserFName() {
		return userFName;
	}
	public void setUserFName(String userFName) {
		this.userFName = userFName;
	}
	public String getUserLName() {
		return userLName;
	}
	public void setUserLName(String userLName) {
		this.userLName = userLName;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserWorkflowRole() {
		return userWorkflowRole;
	}
	public void setUserWorkflowRole(String userWorkflowRole) {
		this.userWorkflowRole = userWorkflowRole;
	}
	public String getGbuId() {
		return gbuId;
	}
	public void setGbuId(String gbuId) {
		this.gbuId = gbuId;
	}
	public String getCountryID() {
		return countryID;
	}
	public void setCountryID(String countryID) {
		this.countryID = countryID;
	}
	public String getOuId() {
		return ouId;
	}
	public void setOuId(String ouId) {
		this.ouId = ouId;
	}
	public String getgPracticeID() {
		return gPracticeID;
	}
	public void setgPracticeID(String gPracticeID) {
		this.gPracticeID = gPracticeID;
	}
	public String getSubPracticeID() {
		return subPracticeID;
	}
	public void setSubPracticeID(String subPracticeID) {
		this.subPracticeID = subPracticeID;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmaildID(String emailID) {
		this.emailID = emailID;
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
        hcb.append(this.getUserWorkflowRole());
        return hcb.toHashCode();
    }
 
    @Override
    public boolean equals(Object obj) {
    	if (this.equals(obj)) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User that = (User) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(this.getUserWorkflowRole(), that.getUserWorkflowRole());
        return eb.isEquals();
    }
	
	
}
