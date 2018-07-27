package com.atos.rmg.entity;

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
@javax.persistence.Table(name="Personalise")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region="com.atos.rmg.entity.Personalise")
public class Personalise {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name="UserRole")
	private String userRole;
	
	@Column(name="SubCoDASID")
	private String subCoDASID;
	
	
	@Column(name="Page")
	private String page;	
	
	@Column(name="gbu")
	private String gbu;
	
	@Column(name="country")
	private String country;
	
	@Column(name="Gcm")
	private String gcm;

	@Column(name="GlobalPractice")
	private String globalPractice;

	@Column(name="SubPractice")
	private String subPractice;
	
	@Column(name="SubCoDemandID")
	private String subCoDemandID;
	
	@Column(name="SubCoProviderID")
	private String subCoProviderID;

	@Column(name="SubCoName")
	private String subCoName;
	
	@Column(name="SubCoEmployeeID")
	private String subCoEmployeeID;
	
	@Column(name="SubCoEmail")
	private String subCoEmail;

	@Column(name="SubCoContactNumber")
	private String subCoContactNumber;
	
	@Column(name="SubCoPrimarySkills")
	private String subCoPrimarySkills;
	
	@Column(name="SubCoSecondarySkills")
	private String subCoSecondarySkills;

	@Column(name="GCMFamilyID")
	private String gCMFamilyID;
	
	@Column(name="SubCoAssignedLocation")
	private String subCoAssignedLocation;
	
	@Column(name="LineManagerName")
	private String lineManagerName;

	@Column(name="LineManagerDASID")
	private String lineManagerDASID;
	
	@Column(name="LineManagerEmail")
	private String lineManagerEmail;
	
	@Column(name="LineManagerContactNumber")
	private String lineManagerContactNumber;

	@Column(name="OUManagerName")
	private String ouManagerName;
	
	@Column(name="OUManagerDASID")
	private String ouManagerDASID;
	
	@Column(name="OUManagerEmail")
	private String ouManagerEmail;

	@Column(name="OUManagerContactNumber")
	private String ouManagerContactNumber;
	
	@Column(name="ProjectManagerName")
	private String projectManagerName;
	
	@Column(name="ProjectManagerDASID")
	private String projectManagerDASID;

	@Column(name="ProjectManagerEmail")
	private String projectManagerEmail;
	
	@Column(name="ProjectManagerContactNumber")
	private String projectManagerContactNumber;
	
	@Column(name="ResourceManagerName")
	private String resourceManagerName;
	
	@Column(name="ResourceManagerDASID")
	private String resourceManagerDASID;
	
	@Column(name="ResourceManagerEmail")
	private String resourceManagerEmail;
	
	@Column(name="ResourceManagerContactNumber")
	private String resourceManagerContactNumber;
	
	@Column(name="TAndMorFPagreementwithSubCon")
	private String tandMorFPagreementwithSubCon;
	
	@Column(name="AssignmentAccountOrCustomerName")
	private String assignmentAccountOrCustomerName;
	
	@Column(name="ProjectDescription")
	private String projectDescription;
	
	@Column(name="Type")
	private String type;
	
	@Column(name="ProjectMargin")
	private String projectMargin;
	
	@Column(name="FirstStartDate")
	private String firstStartDate;
	
	@Column(name="SubcoEndDate")
	private String subcoEndDate;
	
	@Column(name="SubcoExtStartDate")
	private String subcoExtStartDate;
	
	@Column(name="SubcoExtEndDate")
	private String subcoExtEndDate;
	
	@Column(name="GBUComments")
	private String gbuComments;
	
	@Column(name="ProjectWBSCode")
	private String projectWBSCode;
	
	@Column(name="ProjectManagerWBSCode")
	private String projectManagerWBSCode;
	
	@Column(name="WorkLocation")
	private String workLocation;
	
	@Column(name="DemandStatus")
	private String demandStatus;
	
	@Column(name="AverageDailyCost")
	private String averageDailyCost;
	
	@Column(name="AverageDailySalesRate")
	private String averageDailySalesRate;
	
	@Column(name="AverageFTEinMonth")
	private String averageFTEinMonth;
	
	@Column(name="YearssincefirststartDate")
	private String yearssincefirststartDate;
	
	@Column(name="SubcoActionPlan")
	private String subcoActionPlan;
	
	@Column(name="ImpactinKEuro")
	private String impactinKEuro;
	
	@Column(name="TSHrMay")
	private String tsHrMay;
	
	@Column(name="LocalLanguageRequired")
	private String localLanguageRequired;
	
	@Column(name="LocalLanguage")
	private String localLanguage;
	
	@Column(name="SecurityClearanceRequired")
	private String securityClearanceRequired;
	
	@Column(name="ClearanceNo")
	private String clearanceNo;
	
	@Column(name="RemoteWorkingPossibility")
	private String remoteWorkingPossibility;
	
	@Column(name="WorkdoneinIndiaOrNearShore")
	private String workdoneinIndiaOrNearShore;
	
	@Column(name="ReplacementPossible")
	private String replacementPossible;
	
	@Column(name="Replacedfrom")
	private String replacedfrom;
	
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
	
	/*public String getSubcoDemandID() {
		return subCoDASID;
	}

	public void setSubcoDemandID(String subCoDASID) {
		this.subCoDASID = subCoDASID;
	}*/


	public String getSubCoDASID() {
		return subCoDASID;
	}

	public void setSubCoDASID(String subCoDASID) {
		this.subCoDASID = subCoDASID;
	}
	
	public String getPage() {
		return page;
	}


	public void setPage(String page) {
		this.page = page;
	}
	
	public String getUserRole() {
		return userRole;
	}
	
	

	public String getGbu() {
		return gbu;
	}

	public void setGbu(String gbu) {
		this.gbu = gbu;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGcm() {
		return gcm;
	}

	public void setGcm(String gcm) {
		this.gcm = gcm;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	

	public String getGlobalPractice() {
		return globalPractice;
	}

	public void setGlobalPractice(String globalPractice) {
		this.globalPractice = globalPractice;
	}

	public String getSubPractice() {
		return subPractice;
	}

	public void setSubPractice(String subPractice) {
		this.subPractice = subPractice;
	}

	public String getSubCoDemandID() {
		return subCoDemandID;
	}

	public void setSubCoDemandID(String subCoDemandID) {
		this.subCoDemandID = subCoDemandID;
	}

	public String getSubCoProviderID() {
		return subCoProviderID;
	}

	public void setSubCoProviderID(String subCoProviderID) {
		this.subCoProviderID = subCoProviderID;
	}

	public String getSubCoName() {
		return subCoName;
	}

	public void setSubCoName(String subCoName) {
		this.subCoName = subCoName;
	}

	public String getSubCoEmployeeID() {
		return subCoEmployeeID;
	}

	public void setSubCoEmployeeID(String subCoEmployeeID) {
		this.subCoEmployeeID = subCoEmployeeID;
	}

	public String getSubCoEmail() {
		return subCoEmail;
	}

	public void setSubCoEmail(String subCoEmail) {
		this.subCoEmail = subCoEmail;
	}

	public String getSubCoContactNumber() {
		return subCoContactNumber;
	}

	public void setSubCoContactNumber(String subCoContactNumber) {
		this.subCoContactNumber = subCoContactNumber;
	}

	public String getSubCoPrimarySkills() {
		return subCoPrimarySkills;
	}

	public void setSubCoPrimarySkills(String subCoPrimarySkills) {
		this.subCoPrimarySkills = subCoPrimarySkills;
	}

	public String getSubCoSecondarySkills() {
		return subCoSecondarySkills;
	}

	public void setSubCoSecondarySkills(String subCoSecondarySkills) {
		this.subCoSecondarySkills = subCoSecondarySkills;
	}

	public String getgCMFamilyID() {
		return gCMFamilyID;
	}

	public void setgCMFamilyID(String gCMFamilyID) {
		this.gCMFamilyID = gCMFamilyID;
	}

	public String getSubCoAssignedLocation() {
		return subCoAssignedLocation;
	}

	public void setSubCoAssignedLocation(String subCoAssignedLocation) {
		this.subCoAssignedLocation = subCoAssignedLocation;
	}

	public String getLineManagerName() {
		return lineManagerName;
	}

	public void setLineManagerName(String lineManagerName) {
		this.lineManagerName = lineManagerName;
	}

	public String getLineManagerDASID() {
		return lineManagerDASID;
	}

	public void setLineManagerDASID(String lineManagerDASID) {
		this.lineManagerDASID = lineManagerDASID;
	}

	public String getLineManagerEmail() {
		return lineManagerEmail;
	}

	public void setLineManagerEmail(String lineManagerEmail) {
		this.lineManagerEmail = lineManagerEmail;
	}

	public String getLineManagerContactNumber() {
		return lineManagerContactNumber;
	}

	public void setLineManagerContactNumber(String lineManagerContactNumber) {
		this.lineManagerContactNumber = lineManagerContactNumber;
	}

	public String getOuManagerName() {
		return ouManagerName;
	}

	public void setOuManagerName(String ouManagerName) {
		this.ouManagerName = ouManagerName;
	}

	public String getOuManagerDASID() {
		return ouManagerDASID;
	}

	public void setOuManagerDASID(String ouManagerDASID) {
		this.ouManagerDASID = ouManagerDASID;
	}

	public String getOuManagerEmail() {
		return ouManagerEmail;
	}

	public void setOuManagerEmail(String ouManagerEmail) {
		this.ouManagerEmail = ouManagerEmail;
	}

	public String getOuManagerContactNumber() {
		return ouManagerContactNumber;
	}

	public void setOuManagerContactNumber(String ouManagerContactNumber) {
		this.ouManagerContactNumber = ouManagerContactNumber;
	}

	public String getProjectManagerName() {
		return projectManagerName;
	}

	public void setProjectManagerName(String projectManagerName) {
		this.projectManagerName = projectManagerName;
	}

	public String getProjectManagerDASID() {
		return projectManagerDASID;
	}

	public void setProjectManagerDASID(String projectManagerDASID) {
		this.projectManagerDASID = projectManagerDASID;
	}

	public String getProjectManagerEmail() {
		return projectManagerEmail;
	}

	public void setProjectManagerEmail(String projectManagerEmail) {
		this.projectManagerEmail = projectManagerEmail;
	}

	public String getProjectManagerContactNumber() {
		return projectManagerContactNumber;
	}

	public void setProjectManagerContactNumber(String projectManagerContactNumber) {
		this.projectManagerContactNumber = projectManagerContactNumber;
	}

	public String getResourceManagerName() {
		return resourceManagerName;
	}

	public void setResourceManagerName(String resourceManagerName) {
		this.resourceManagerName = resourceManagerName;
	}

	public String getResourceManagerDASID() {
		return resourceManagerDASID;
	}

	public void setResourceManagerDASID(String resourceManagerDASID) {
		this.resourceManagerDASID = resourceManagerDASID;
	}

	public String getResourceManagerEmail() {
		return resourceManagerEmail;
	}

	public void setResourceManagerEmail(String resourceManagerEmail) {
		this.resourceManagerEmail = resourceManagerEmail;
	}

	public String getResourceManagerContactNumber() {
		return resourceManagerContactNumber;
	}

	public void setResourceManagerContactNumber(String resourceManagerContactNumber) {
		this.resourceManagerContactNumber = resourceManagerContactNumber;
	}

	public String getTandMorFPagreementwithSubCon() {
		return tandMorFPagreementwithSubCon;
	}

	public void setTandMorFPagreementwithSubCon(String tandMorFPagreementwithSubCon) {
		this.tandMorFPagreementwithSubCon = tandMorFPagreementwithSubCon;
	}

	public String getAssignmentAccountOrCustomerName() {
		return assignmentAccountOrCustomerName;
	}

	public void setAssignmentAccountOrCustomerName(
			String assignmentAccountOrCustomerName) {
		this.assignmentAccountOrCustomerName = assignmentAccountOrCustomerName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProjectMargin() {
		return projectMargin;
	}

	public void setProjectMargin(String projectMargin) {
		this.projectMargin = projectMargin;
	}

	public String getFirstStartDate() {
		return firstStartDate;
	}

	public void setFirstStartDate(String firstStartDate) {
		this.firstStartDate = firstStartDate;
	}

	public String getSubcoEndDate() {
		return subcoEndDate;
	}

	public void setSubcoEndDate(String subcoEndDate) {
		this.subcoEndDate = subcoEndDate;
	}

	public String getSubcoExtStartDate() {
		return subcoExtStartDate;
	}

	public void setSubcoExtStartDate(String subcoExtStartDate) {
		this.subcoExtStartDate = subcoExtStartDate;
	}

	public String getSubcoExtEndDate() {
		return subcoExtEndDate;
	}

	public void setSubcoExtEndDate(String subcoExtEndDate) {
		this.subcoExtEndDate = subcoExtEndDate;
	}

	public String getGbuComments() {
		return gbuComments;
	}

	public void setGbuComments(String gbuComments) {
		this.gbuComments = gbuComments;
	}

	public String getProjectWBSCode() {
		return projectWBSCode;
	}

	public void setProjectWBSCode(String projectWBSCode) {
		this.projectWBSCode = projectWBSCode;
	}

	public String getProjectManagerWBSCode() {
		return projectManagerWBSCode;
	}

	public void setProjectManagerWBSCode(String projectManagerWBSCode) {
		this.projectManagerWBSCode = projectManagerWBSCode;
	}

	public String getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}

	public String getDemandStatus() {
		return demandStatus;
	}

	public void setDemandStatus(String demandStatus) {
		this.demandStatus = demandStatus;
	}

	public String getAverageDailyCost() {
		return averageDailyCost;
	}

	public void setAverageDailyCost(String averageDailyCost) {
		this.averageDailyCost = averageDailyCost;
	}

	public String getAverageDailySalesRate() {
		return averageDailySalesRate;
	}

	public void setAverageDailySalesRate(String averageDailySalesRate) {
		this.averageDailySalesRate = averageDailySalesRate;
	}

	public String getAverageFTEinMonth() {
		return averageFTEinMonth;
	}

	public void setAverageFTEinMonth(String averageFTEinMonth) {
		this.averageFTEinMonth = averageFTEinMonth;
	}

	public String getYearssincefirststartDate() {
		return yearssincefirststartDate;
	}

	public void setYearssincefirststartDate(String yearssincefirststartDate) {
		this.yearssincefirststartDate = yearssincefirststartDate;
	}

	public String getSubcoActionPlan() {
		return subcoActionPlan;
	}

	public void setSubcoActionPlan(String subcoActionPlan) {
		this.subcoActionPlan = subcoActionPlan;
	}

	public String getImpactinKEuro() {
		return impactinKEuro;
	}

	public void setImpactinKEuro(String impactinKEuro) {
		this.impactinKEuro = impactinKEuro;
	}

	public String getTsHrMay() {
		return tsHrMay;
	}

	public void setTsHrMay(String tsHrMay) {
		this.tsHrMay = tsHrMay;
	}

	public String getLocalLanguageRequired() {
		return localLanguageRequired;
	}

	public void setLocalLanguageRequired(String localLanguageRequired) {
		this.localLanguageRequired = localLanguageRequired;
	}

	public String getLocalLanguage() {
		return localLanguage;
	}

	public void setLocalLanguage(String localLanguage) {
		this.localLanguage = localLanguage;
	}

	public String getSecurityClearanceRequired() {
		return securityClearanceRequired;
	}

	public void setSecurityClearanceRequired(String securityClearanceRequired) {
		this.securityClearanceRequired = securityClearanceRequired;
	}

	public String getClearanceNo() {
		return clearanceNo;
	}

	public void setClearanceNo(String clearanceNo) {
		this.clearanceNo = clearanceNo;
	}

	public String getRemoteWorkingPossibility() {
		return remoteWorkingPossibility;
	}

	public void setRemoteWorkingPossibility(String remoteWorkingPossibility) {
		this.remoteWorkingPossibility = remoteWorkingPossibility;
	}

	public String getWorkdoneinIndiaOrNearShore() {
		return workdoneinIndiaOrNearShore;
	}

	public void setWorkdoneinIndiaOrNearShore(String workdoneinIndiaOrNearShore) {
		this.workdoneinIndiaOrNearShore = workdoneinIndiaOrNearShore;
	}

	public String getReplacementPossible() {
		return replacementPossible;
	}

	public void setReplacementPossible(String replacementPossible) {
		this.replacementPossible = replacementPossible;
	}

	public String getReplacedfrom() {
		return replacedfrom;
	}

	public void setReplacedfrom(String replacedfrom) {
		this.replacedfrom = replacedfrom;
	}

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
	
	@Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(getUserRole());
        return hcb.toHashCode();
    }
 
    @Override
    public boolean equals(Object obj) {
    	if (this.equals(obj)) {
            return true;
        }
        if (!(obj instanceof Personalise)) {
            return false;
        }
        Personalise	 that = (Personalise) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(this.getUserRole(), that.getUserRole());
        return eb.isEquals();
    }

}
