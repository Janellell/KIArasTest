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
import org.hibernate.service.internal.ProvidedService;

/**
 * 
 * @author A180562
 *
 */
@javax.persistence.Entity
@javax.persistence.Table(name="subco_provider_master")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region="com.atos.rmg.entity.Provider")
public class Provider {

	@Id
	@Column(name="SubcoProviderID")
	private String subcoProviderID;
	@Column(name="SubcoProviderName")
	private String subcoProviderName;
	@Column(name="SubcoProviderEmail")
	private String subcoProviderEmail;
	@Column(name="SubcoProviderContactN")
	private String subcoProviderContactN;
	@Column(name="SubcoProviderStatus")
	private String subcoProviderStatus;
	
	public String getSubcoProviderID() {
		return subcoProviderID;
	}
	public void setSubcoProviderID(String subcoProviderID) {
		this.subcoProviderID = subcoProviderID;
	}
	public String getSubcoProviderName() {
		return subcoProviderName;
	}
	public void setSubcoProviderName(String subcoProviderName) {
		this.subcoProviderName = subcoProviderName;
	}
	public String getSubcoProviderEmail() {
		return subcoProviderEmail;
	}
	public void setSubcoProviderEmail(String subcoProviderEmail) {
		this.subcoProviderEmail = subcoProviderEmail;
	}
	public String getSubcoProviderContactN() {
		return subcoProviderContactN;
	}
	public void setSubcoProviderContactN(String subcoProviderContactN) {
		this.subcoProviderContactN = subcoProviderContactN;
	}
	public String getSubcoProviderStatus() {
		return subcoProviderStatus;
	}
	public void setSubcoProviderStatus(String subcoProviderStatus) {
		this.subcoProviderStatus = subcoProviderStatus;
	}
	
	@Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(getSubcoProviderName());
        return hcb.toHashCode();
    }
 
    @Override
    public boolean equals(Object obj) {
    	if (this.equals(obj)) {
            return true;
        }
        if (!(obj instanceof Provider)) {
            return false;
        }
        Provider that = (Provider) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(this.getSubcoProviderName(), that.getSubcoProviderName());
        return eb.isEquals();
    }
}
