package com.atos.rmg.entity;

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
@javax.persistence.Table(name="country_practicemaster")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region="com.atos.rmg.entity.CountryPractise")
public class CountryPractise {
	
	@Id
	@Column(name="CountryID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String countryID;
	
	@Column(name="GPracticeID")
	private String gPracticeID;
	
	@Column(name="GPracticeName")
	private String gPracticeName;
	
	@Column(name="GPracticeDetail")
	private String gPracticeDetail;
	
	@Column(name="SubPracticeID")
	private String subPracticeID;
	
	@Column(name="SubPracticeName")
	private String subPractiseName;
	
	@Column(name="SubPracticeDetail")
	private String subPracticeDetail;
	
	@Column(name="CreatedDate")
	private Date createdDate;
	
	@Column(name="CreatedBy")
	private String createdBy;
	
	@Column(name="UpdatedDate")
	private Date updatedDate;
	
	@Column(name="UpdatedBy")
	private String updatedBy;

	public String getCountryID() {
		return countryID;
	}

	public void setCountryID(String countryID) {
		this.countryID = countryID;
	}

	public String getgPracticeID() {
		return gPracticeID;
	}

	public void setgPracticeID(String gPracticeID) {
		this.gPracticeID = gPracticeID;
	}

	public String getgPracticeName() {
		return gPracticeName;
	}

	public void setgPracticeName(String gPracticeName) {
		this.gPracticeName = gPracticeName;
	}

	public String getgPracticeDetail() {
		return gPracticeDetail;
	}

	public void setgPracticeDetail(String gPracticeDetail) {
		this.gPracticeDetail = gPracticeDetail;
	}

	public String getSubPracticeID() {
		return subPracticeID;
	}

	public void setSubPracticeID(String subPracticeID) {
		this.subPracticeID = subPracticeID;
	}

	public String getSubPractiseName() {
		return subPractiseName;
	}

	public void setSubPractiseName(String subPractiseName) {
		this.subPractiseName = subPractiseName;
	}

	public String getSubPracticeDetail() {
		return subPracticeDetail;
	}

	public void setSubPracticeDetail(String subPracticeDetail) {
		this.subPracticeDetail = subPracticeDetail;
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
        hcb.append(this.getgPracticeName());
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
        CountryPractise  that = (CountryPractise) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(this.getgPracticeName(), that.getgPracticeName());
        return eb.isEquals();
    }
	
	
}
