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
@javax.persistence.Table(name="subcon_transaction_log")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region="com.atos.rmg.entity.TransactionLog")
public class TransactionLog {
	
	@Id
	
	
	@Column(name="SubcoDemandID", unique=true)	
	private int subcoDemandId;
	
	@Column(name="SubcoDAS_ID")
	private String subcoDasID;
	
    @Column(name="SubcoProviderID")
	private String subcoProviderID;
	@Column(name="GBUID")
	private String gBUID;
	@Column(name="CountryID")
	private String countryID;
	@Column(name="SubcoFName")
	private String subcoFName;
	@Column(name="SubcoLName")
	private String subcoLName;
	@Column(name="SubcoEmail")
	private String subcoEmail;
	@Column(name="SubcoContactNo")
	private String subcoContactNo;
	@Column(name="EmployeeID")
	private String employeeID;
	@Column(name="SubcoAgreement")
	private String subCoAgreement;
	@Column(name="SubcoPrimarySkill")
	private String subcoPrimarySkill;
	@Column(name="SubcoSecondarySkill")
	private String subcoSecondarySkill;
	@Column(name="GCMFamilyID")
	private String gcmFamilyID;
	@Column(name="GCM")
	private String gcm;
	@Column(name="SubcoCustomerAssignment")
	private String subCoCustomerAssignment;
	@Column(name="ProjectWBS_Code")
	private String projectWBSCode;
	@Column(name="ProjectDesc")
	private String projectDesc;
	@Column(name="AssignedLocation")
	private String assignedLocation;
	@Column(name="AssignedCountryID")
	private String assignedCountryID;
	@Column(name="IsLocalLangReq")
	private String isLocalLangReq;
	@Column(name="LocalLang")
	private String localLang;
	@Column(name="IsSecurityClearanceReq")
	private String isSecurityClearanceReq;
	@Column(name="SecurityClearanceNo")	
	private String securityClearanceNo;
	@Column(name="IsRemoteWorkPossibility")
	private String isRemoteWorkPossiblity;
	@Column(name="IsWorkDoneNearShore")
	private String isWorkDoneNearShore;
	@Column(name="IsReplaceable")
	private String isReplaceable;
	@Column(name="ReplacementSelectedOption")
	private String replacementSelectedOption;
	@Column(name="DemandStatus")
	private String demandStatus;
	@Column(name="AvgDailyCost" )
	private String avgDailyCost;
	@Column(name="AvgDailySalesRate")
	private String avgDailySalesRate;
	public String getAvgDailyCost() {
		return avgDailyCost;
	}
	public void setAvgDailyCost(String avgDailyCost) {
		this.avgDailyCost = avgDailyCost;
	}
	public String getAvgDailySalesRate() {
		return avgDailySalesRate;
	}
	public void setAvgDailySalesRate(String avgDailySalesRate) {
		this.avgDailySalesRate = avgDailySalesRate;
	}
	@Column(name="Margin", columnDefinition="Decimal(10,2)")
	private String margin;
	public String getMargin() {
		return margin;
	}
	public void setMargin(String margin) {
		this.margin = margin;
	}
	@Column(name="avgFTE_UsedInMonth")
	private String avgFTEUsedInMonth;
	@Column(name="SubcoSDateWithAtos")
	private Date subCoDateWithAtos;
	@Column(name="SubcoDuration")
	private String subcoDuration;
	@Column(name="SubcoEDateWithAtos")
	private Date subcoEDateWithAtos;
	@Column(name="SubcoActionPlan")
	private String subcoActionPlan;
	@Column(name="SubcoImpact_KEuro")
	private String subcoImpactKEuro;
	@Column(name="GBU_Comments")
	private String gbu_Comments;
	@Column(name="Monthly_TS_Data")
	private String monthlyTSData;	
	@Column(name="WBS_CodePM")
	private String wbs_CodePM;
	@Column(name="SubcoExtnSdate")
	private Date subcoExtnSdate;
	@Column(name="subcoExtnEdate")
	private Date subcoExtnEdate;
	@Column(name="CreatedDate")
	private Date createdDate;
	@Column(name="CreatedBy")
	private String createdBy;
	@Column(name="UpdatedDate")
	private Date updatedDate;
	@Column(name="UpdatedBy")
	private String updatedBy;
	
	@Column(name="SubconExtnRemark")
	private String subcoExtnRemark;
	
	@Column(name="GpracticeID")
	private String gPracticeID;
	@Column(name="SubpracticeID")
	private String subPracticeID;
	
	@Column(name="Requestor_Comment")
	private String requestorComment;
	@Column(name="Validation_Comment")
	private String validationComment;
	@Column(name="New_Prolog_Estimate")
	private String newPrologEstimate;
	@Column(name="Type_of_Contract")
	private String TypeOfContract;
	@Column(name="Type_of_Request")
	private String TypeOfRequest;
	@Column(name="YTD_ER")
	private String YTDExtRevenue;
	@Column(name="YTD_PM_Per")
	private String yTDProfitMargin;
	@Column(name="Billable")
	private String billable;
	@Column(name="Operational_Role")
	private String operationalRole;
	@Column(name="Reason_for_Vacancy")
	private String reasonForVacancy;
	@Column(name="Customer")
	private String customer;
	

	@Column(name="RomaID")
	private String romaId;
	@Column(name="RFPNumber")
	private String rpfNumber;
	
	public String getRequestorComment() {
		return requestorComment;
	}
	public void setRequestorComment(String requestorComment) {
		this.requestorComment = requestorComment;
	}
	public String getValidationComment() {
		return validationComment;
	}
	public void setValidationComment(String validationComment) {
		this.validationComment = validationComment;
	}
	public String getNewPrologEstimate() {
		return newPrologEstimate;
	}
	public void setNewPrologEstimate(String newPrologEstimate) {
		this.newPrologEstimate = newPrologEstimate;
	}
	public String getTypeOfContract() {
		return TypeOfContract;
	}
	public void setTypeOfContract(String typeOfContract) {
		TypeOfContract = typeOfContract;
	}
	public String getTypeOfRequest() {
		return TypeOfRequest;
	}
	public void setTypeOfRequest(String typeOfRequest) {
		TypeOfRequest = typeOfRequest;
	}
	public String getYTDExtRevenue() {
		return YTDExtRevenue;
	}
	public void setYTDExtRevenue(String yTDExtRevenue) {
		YTDExtRevenue = yTDExtRevenue;
	}
	public String getyTDProfitMargin() {
		return yTDProfitMargin;
	}
	public void setyTDProfitMargin(String yTDProfitMargin) {
		this.yTDProfitMargin = yTDProfitMargin;
	}
	public String getBillable() {
		return billable;
	}
	public void setBillable(String billable) {
		this.billable = billable;
	}
	public String getOperationalRole() {
		return operationalRole;
	}
	public void setOperationalRole(String operationalRole) {
		this.operationalRole = operationalRole;
	}
	public String getReasonForVacancy() {
		return reasonForVacancy;
	}
	public void setReasonForVacancy(String reasonForVacancy) {
		this.reasonForVacancy = reasonForVacancy;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
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
	
	
	
	
	public String getSecurityClearanceNo() {
		return securityClearanceNo;
	}
	public void setSecurityClearanceNo(String securityClearanceNo) {
		this.securityClearanceNo = securityClearanceNo;
	}
   
	
	public String getSubcoProviderID() {
		return subcoProviderID;
	}
	public void setSubcoProviderID(String subcoProviderID) {
		this.subcoProviderID = subcoProviderID;
	}
	
	public String getgBUID() {
		return gBUID;
	}
	public void setgBUID(String gBUID) {
		this.gBUID = gBUID;
	}
	public String getCountryID() {
		return countryID;
	}
	public void setCountryID(String countryID) {
		this.countryID = countryID;
	}
	public String getSubcoFName() {
		return subcoFName;
	}
	public void setSubcoFName(String subcoFName) {
		this.subcoFName = subcoFName;
	}
	public String getSubcoLName() {
		return subcoLName;
	}
	public void setSubcoLName(String subcoLName) {
		this.subcoLName = subcoLName;
	}
	public String getSubcoEmail() {
		return subcoEmail;
	}
	public void setSubcoEmail(String subcoEmail) {
		this.subcoEmail = subcoEmail;
	}
	public String getSubcoContactNo() {
		return subcoContactNo;
	}
	public void setSubcoContactNo(String subcoContactNo) {
		this.subcoContactNo = subcoContactNo;
	}
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public String getSubCoAgreement() {
		return subCoAgreement;
	}
	public void setSubCoAgreement(String subCoAgreement) {
		this.subCoAgreement = subCoAgreement;
	}
	public String getSubcoPrimarySkill() {
		return subcoPrimarySkill;
	}
	public void setSubcoPrimarySkill(String subcoPrimarySkill) {
		this.subcoPrimarySkill = subcoPrimarySkill;
	}
	public String getSubcoSecondarySkill() {
		return subcoSecondarySkill;
	}
	public void setSubcoSecondarySkill(String subcoSecondarySkill) {
		this.subcoSecondarySkill = subcoSecondarySkill;
	}
	public String getGcmFamilyID() {
		return gcmFamilyID;
	}
	public void setGcmFamilyID(String gcmFamilyID) {
		this.gcmFamilyID = gcmFamilyID;
	}
	public String getGcm() {
		return gcm;
	}
	public void setGcm(String gcm) {
		this.gcm = gcm;
	}
	public String getSubCoCustomerAssignment() {
		return subCoCustomerAssignment;
	}
	public void setSubCoCustomerAssignment(String subCoCustomerAssignment) {
		this.subCoCustomerAssignment = subCoCustomerAssignment;
	}
	public String getProjectWBSCode() {
		return projectWBSCode;
	}
	public void setProjectWBSCode(String projectWBSCode) {
		this.projectWBSCode = projectWBSCode;
	}
	public String getProjectDesc() {
		return projectDesc;
	}
	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}
	public String getAssignedLocation() {
		return assignedLocation;
	}
	public void setAssignedLocation(String assignedLocation) {
		this.assignedLocation = assignedLocation;
	}
	public String getAssignedCountryID() {
		return assignedCountryID;
	}
	public void setAssignedCountryID(String assignedCountryID) {
		this.assignedCountryID = assignedCountryID;
	}
	public String getIsLocalLangReq() {
		return isLocalLangReq;
	}
	public void setIsLocalLangReq(String isLocalLangReq) {
		this.isLocalLangReq = isLocalLangReq;
	}
	public String getLocalLang() {
		return localLang;
	}
	public void setLocalLang(String localLang) {
		this.localLang = localLang;
	}
	public String getIsSecurityClearanceReq() {
		return isSecurityClearanceReq;
	}
	public void setIsSecurityClearanceReq(String isSecurityClearanceReq) {
		this.isSecurityClearanceReq = isSecurityClearanceReq;
	}
	public String getIsRemoteWorkPossiblity() {
		return isRemoteWorkPossiblity;
	}
	public void setIsRemoteWorkPossiblity(String isRemoteWorkPossiblity) {
		this.isRemoteWorkPossiblity = isRemoteWorkPossiblity;
	}
	public String getIsWorkDoneNearShore() {
		return isWorkDoneNearShore;
	}
	public void setIsWorkDoneNearShore(String isWorkDoneNearShore) {
		this.isWorkDoneNearShore = isWorkDoneNearShore;
	}
	public String getIsReplaceable() {
		return isReplaceable;
	}
	public void setIsReplaceable(String isReplaceable) {
		this.isReplaceable = isReplaceable;
	}
	public String getReplacementSelectedOption() {
		return replacementSelectedOption;
	}
	public void setReplacementSelectedOption(String replacementSelectedOption) {
		this.replacementSelectedOption = replacementSelectedOption;
	}
	public String getDemandStatus() {
		return demandStatus;
	}
	public void setDemandStatus(String demandStatus) {
		this.demandStatus = demandStatus;
	}

	public String getAvgFTEUsedInMonth() {
		return avgFTEUsedInMonth;
	}
	public void setAvgFTEUsedInMonth(String avgFTEUsedInMonth) {
		this.avgFTEUsedInMonth = avgFTEUsedInMonth;
	}
	public Date getSubCoDateWithAtos() {
		return subCoDateWithAtos;
	}
	public void setSubCoDateWithAtos(Date subCoDateWithAtos) {
		this.subCoDateWithAtos = subCoDateWithAtos;
	}
	public String getSubcoDuration() {
		return subcoDuration;
	}
	public void setSubcoDuration(String subcoDuration) {
		this.subcoDuration = subcoDuration;
	}
	public Date getSubcoEDateWithAtos() {
		return subcoEDateWithAtos;
	}
	public void setSubcoEDateWithAtos(Date subcoEDateWithAtos) {
		this.subcoEDateWithAtos = subcoEDateWithAtos;
	}
	public String getSubcoActionPlan() {
		return subcoActionPlan;
	}
	public void setSubcoActionPlan(String subcoActionPlan) {
		this.subcoActionPlan = subcoActionPlan;
	}
	public String getSubcoImpactKEuro() {
		return subcoImpactKEuro;
	}
	public void setSubcoImpactKEuro(String subcoImpactKEuro) {
		this.subcoImpactKEuro = subcoImpactKEuro;
	}
	public String getGbu_Comments() {
		return gbu_Comments;
	}
	public void setGbu_Comments(String gbu_Comments) {
		this.gbu_Comments = gbu_Comments;
	}
	public String getMonthlyTSData() {
		return monthlyTSData;
	}
	public void setMonthlyTSData(String monthlyTSData) {
		this.monthlyTSData = monthlyTSData;
	}
	public String getWbs_CodePM() {
		return wbs_CodePM;
	}
	public void setWbs_CodePM(String wbs_CodePM) {
		this.wbs_CodePM = wbs_CodePM;
	}
	public Date getSubcoExtnSdate() {
		return subcoExtnSdate;
	}
	public void setSubcoExtnSdate(Date subcoExtnSdate) {
		this.subcoExtnSdate = subcoExtnSdate;
	}
	public Date getSubcoExtnEdate() {
		return subcoExtnEdate;
	}
	public void setSubcoExtnEdate(Date subcoExtnEdate) {
		this.subcoExtnEdate = subcoExtnEdate;
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
	public String getSubcoExtnRemark() {
		return subcoExtnRemark;
	}
	public void setSubcoExtnRemark(String subcoExtnRemark) {
		this.subcoExtnRemark = subcoExtnRemark;
	}
	public String getSubcoDasID() {
		return subcoDasID;
	}
	public void setSubcoDasID(String subcoDasID) {
		this.subcoDasID = subcoDasID;
	}
	public int getSubcoDemandId() {
		return subcoDemandId;
	}
	public void setSubcoDemandId(int subcoDemandId) {
		this.subcoDemandId = subcoDemandId;
	}
	public String getRomaId() {
		return romaId;
	}
	public void setRomaId(String romaId) {
		this.romaId = romaId;
	}
	public String getRpfNumber() {
		return rpfNumber;
	}
	public void setRpfNumber(String rpfNumber) {
		this.rpfNumber = rpfNumber;
	}
	
	@Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(getSubcoDasID());
        return hcb.toHashCode();
    }
 
    @Override
    public boolean equals(Object obj) {
    	if (this.equals(obj)) {
            return true;
        }
        if (!(obj instanceof TransactionLog)) {
            return false;
        }
        TransactionLog that = (TransactionLog) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(this.getSubcoDasID(), that.getSubcoDasID());
        return eb.isEquals();
    }
	
}
