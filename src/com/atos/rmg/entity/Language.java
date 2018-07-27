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
@javax.persistence.Table(name="LanguageMaster")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region="com.atos.rmg.entity.Language")
public class Language {

	@Id
	@Column(name="LangID")
	private String langID;
	@Column(name="LangName")
	private String langName;
	@Column(name="LangDetail")
	private String langDetail;
	public String getLangID() {
		return langID;
	}
	public void setLangID(String langID) {
		this.langID = langID;
	}
	public String getLangName() {
		return langName;
	}
	public void setLangName(String langName) {
		this.langName = langName;
	}
	public String getLangDetail() {
		return langDetail;
	}
	public void setLangDetail(String langDetail) {
		this.langDetail = langDetail;
	}
	@Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(this.getLangName());
        return hcb.toHashCode();
    }
 
    @Override
    public boolean equals(Object obj) {
    	   if (this.equals(obj)) {
            return true;
        }
        if (!(obj instanceof Language)) {
            return false;
        }
        Language  that = (Language) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(this.getLangName(), that.getLangName());
        return eb.isEquals();
    }
}
