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
@javax.persistence.Table(name="gbu_countrymaster")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region="com.atos.rmg.entity.Country")
public class Country {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="GBUID")
	private String gbuID;
	
	@Column(name="GBUName")	
	private String gbuName;	
	
	@Column(name="GBUDetail")
	private String gbuDetail;
	
	@Column(name="OUID")
	private String ouID;
	
	@Column(name="CountryID")
	private String countryID;
	
	@Column(name="CountryName")
	private String countryName;
	
	@Column(name="CountryDetail")
	private String countryDetail;
	
	@Column(name="LangID")
	private String langID;
	
	@Column(name="DemandStatusID")	
	private String demandStatusID;	
	
	@Column(name="CreatedDate")
	private Date createdDate;
	
	@Column(name="CreatedBy")
	private String createdBy;
	
	@Column(name="UpdatedDate")
	private Date updatedDate;
	
	@Column(name="UpdatedBy")
	private String updatedBy;

	public String getGbuID() {
		return gbuID;
	}

	public void setGbuID(String gbuID) {
		this.gbuID = gbuID;
	}

	public String getGbuName() {
		return gbuName;
	}

	public void setGbuName(String gbuName) {
		this.gbuName = gbuName;
	}

	public String getGbuDetail() {
		return gbuDetail;
	}

	public void setGbuDetail(String gbuDetail) {
		this.gbuDetail = gbuDetail;
	}

	public String getOuID() {
		return ouID;
	}

	public void setOuID(String ouID) {
		this.ouID = ouID;
	}

	public String getCountryID() {
		return countryID;
	}

	public void setCountryID(String countryID) {
		this.countryID = countryID;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryDetail() {
		return countryDetail;
	}

	public void setCountryDetail(String countryDetail) {
		this.countryDetail = countryDetail;
	}

	public String getLangID() {
		return langID;
	}

	public void setLangID(String langID) {
		this.langID = langID;
	}

	public String getDemandStatusID() {
		return demandStatusID;
	}

	public void setDemandStatusID(String demandStatusID) {
		this.demandStatusID = demandStatusID;
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
        hcb.append(this.getGbuName());
        return hcb.toHashCode();
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this.equals(obj)) {
            return true;
        }
        if (!(obj instanceof Country)) {
            return false;
        }
        Country that = (Country) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(this.getGbuName(), that.getGbuName());
        return eb.isEquals();
    }
	
}
