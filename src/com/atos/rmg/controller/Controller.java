package com.atos.rmg.controller;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atos.rmg.beans.CountryBean;
import com.atos.rmg.beans.CountryPractiseBean;
import com.atos.rmg.beans.LanguageBean;
import com.atos.rmg.beans.ManagerBean;
import com.atos.rmg.beans.PersonaliseBean;
import com.atos.rmg.beans.PrimaryDataSearchResultBean;
import com.atos.rmg.beans.ResourceBean;
import com.atos.rmg.beans.SaveApprovalDataBean;
import com.atos.rmg.beans.SubconApprovalBean;
import com.atos.rmg.beans.SubconDetails;
import com.atos.rmg.beans.SubconHistoryBean;
import com.atos.rmg.beans.Summary;
import com.atos.rmg.beans.TransactionLogBean;
import com.atos.rmg.beans.UserBean;
import com.atos.rmg.entity.Country;
import com.atos.rmg.entity.CountryPractise;
import com.atos.rmg.entity.Personalise;
import com.atos.rmg.entity.SubcoApprovalTransLog;
import com.atos.rmg.entity.User;
import com.atos.rmg.services.RmgOperationServices;
import com.atos.rmg.util.EncryptableUtilities;

/**
 * 
 * @author A180562
 *
 */
@SessionAttributes("formBean")
@Scope("session")
@org.springframework.stereotype.Controller
public class Controller {

	
	@Autowired
	public RmgOperationServices rmgOperationServices;

	private static final Random RANDOM = new SecureRandom();
	 
	public static final int PASSWORD_LENGTH = 8;
	
	final static Logger log = Logger.getLogger(Controller.class.getName());
	
	/*Controller(){
		String log4jConfigFile = "log4j.properties";
		 PropertyConfigurator.configure(log4jConfigFile);
	}*/
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	@RequestMapping("/")
	public ModelAndView home() {
		UserBean user = new UserBean();
		log.info("HOme");
		ModelAndView modelAndView = new ModelAndView();
		List<String> userRole = new ArrayList<String>();
		SubconDetails subconDetails = new SubconDetails();
		userRole.add("PM");
		userRole.add("AM");
		modelAndView.addObject("userRole", userRole);
		modelAndView.addObject("user", user);
		modelAndView.addObject("subconDetails", subconDetails);
		modelAndView.setViewName("login");
		return modelAndView;
	}
	/**
	 * 
	 * @param countryvalue
	 * @return
	 */
	/*-------*/
	@RequestMapping(value = "/rmg/getGPracticesForCountry/{countryvalue}", method = RequestMethod.GET)
	public @ResponseBody List<String> getGPracticesForCountry(
			@PathVariable(value="countryvalue") String countryvalue) {
		log.debug("Entering into getGPracticesForCountry() :" +countryvalue);
		List<String> countryGPractices = new ArrayList<String>();
		countryGPractices = rmgOperationServices
				.getGPracticesForCountry(countryvalue);
		return countryGPractices;
	}
	
	@RequestMapping(value = "/rmg/getGPracticesForCountryForPRM")
	public @ResponseBody List<String> getGPracticesForCountryForPRM(HttpServletRequest request,
			@RequestParam(value="country", required = false) String countryvalue) {
		
		log.debug("Entering into getGPracticesForCountry() :" +countryvalue);
		List<String> countryGPractices = new ArrayList<String>();
		countryGPractices = rmgOperationServices
				.getGPracticesForCountry(countryvalue);
		return countryGPractices;
	}
	
	
	/**
	 * 
	 * @param gbuvalue
	 * @return
	 */
	@RequestMapping(value = "/rmg/getCountriesForGBU/{gbuvalue}", method = RequestMethod.GET)
	public @ResponseBody List<String> getCountriesForGBU(
			@PathVariable(value = "gbuvalue") String gbuvalue) {
		log.debug("Entering into getCountriesForGBU() :" +gbuvalue);
		List<String> countries = new ArrayList<String>();
		countries = rmgOperationServices.getCountriesForGBU(gbuvalue);
		return countries;
	}
	/**
	 * 
	 * @param gPractisevalue
	 * @return
	 */
	
	@RequestMapping(value = "/rmg/getSubconSubPractice/{gPractisevalue}", method = RequestMethod.GET)
    public @ResponseBody List<String> getSubconSubPractice(@PathVariable(value = "gPractisevalue") 
    String gPractisevalue) {
		
		log.debug("Entering into getSubconSubPractice() :" +gPractisevalue);
            String gPractiseName = null;
            String countryName = null;
            String gbuname = null;
            String countryGbuGpracticeValueList[] = null;
            if(gPractisevalue!=null) {
            	gPractisevalue = gPractisevalue.replace("string:", " ");
            	countryGbuGpracticeValueList = gPractisevalue.split(" ");
            }
           
            log.debug("countryGbuGpracticeValueList :" +countryGbuGpracticeValueList);

            if(countryGbuGpracticeValueList!=null && countryGbuGpracticeValueList.length >= 1){
            String countryGbuvalueList[] = countryGbuGpracticeValueList[1].split(":");
             gPractiseName = countryGbuGpracticeValueList[0];
             if(countryGbuvalueList != null && countryGbuvalueList.length >= 1) {
            	 countryName = countryGbuvalueList[0];
            	 gbuname = countryGbuvalueList[1];
             }
             log.debug("Parameter Passed countryName is:" + countryName);
             log.debug("Parameter Passed gPractiseName is:" + gPractiseName);
             log.debug("Parameter Passed gbuname is:" + gbuname);

            }

            List<String> countrySubPractices = new ArrayList<String>();
            countrySubPractices = rmgOperationServices.getCountrySubPractices(gPractiseName,countryName,gbuname);
            return countrySubPractices;
    }

	/**
	 * 
	 * @param gPractisevalue
	 * @return
	 */

	@RequestMapping(value = "/rmg/getSubconSubPractice1/{gPractisevalue}", method = RequestMethod.GET)
	public @ResponseBody List<String> getSubconSubPractice1(@PathVariable(value = "gPractisevalue") String gPractisevalue) {
		log.debug("Parameter Passed gPractisevalue is:" + gPractisevalue);
		String gPractiseName = null;
		String countryName = null;
		String gbuname = null;
		String countryGbuGpracticeValueList[] = null;
		if(gPractisevalue!=null) {
			countryGbuGpracticeValueList = gPractisevalue.split(":");
		}
		log.info("countryGbuGpracticeValueList = "+countryGbuGpracticeValueList);
		if(countryGbuGpracticeValueList!=null && countryGbuGpracticeValueList.length >= 3 ) {
			gPractiseName = countryGbuGpracticeValueList[0]; // 004
			countryName = countryGbuGpracticeValueList[1];
			gbuname = countryGbuGpracticeValueList[2];
		}
		log.info("Parameter Passed countryName is:" + countryName +"\n gPractiseName:"
		+gPractiseName+"\n gbuname:"+gbuname);
				
		List<String> countrySubPractices = new ArrayList<String>();
		countrySubPractices = rmgOperationServices
				.getCountrySubPractices(gPractiseName,countryName,gbuname);
		return countrySubPractices;
		
	}
	
	/**
	 * 
	 * @param gPractiseName
	 * @return
	 */
	@RequestMapping(value = "/rmg/getSubconSubPractice2/{gPractiseName}", method = RequestMethod.GET)
	public @ResponseBody List<String> getSubconSubPractice2(@PathVariable(value = "gPractiseName") String gPractiseName) {
		log.info("Parameter Passed is:" + gPractiseName);
		List<String> countrySubPractices = new ArrayList<String>();
		countrySubPractices = rmgOperationServices.getCountrySubPractices(gPractiseName);
		return countrySubPractices;
	}
	
	/**
	 * 
	 * @param request
	 * @param subcoId
	 * @return
	 */
	@RequestMapping("/rmg/inputSubconDetailsForSelectedRecord")
	public ModelAndView getInputSubconDetails(HttpServletRequest request,
			@RequestParam(value="subcoId", required = false) int subcoId) {
		log.info("Entering getInputSubconDetails");
		String agreementTypes = null;
		String gcmValues = null;
		String assisgmentTypes = null;
		String projectMargins = null;
		String averageCosts = null;		
		String newPrologEstimate = null;
		String typeOfRequest = null;
		String typeOfContract = null;
		String billable = null;
		String reasonForVacancy = null;
		
		try {
			Properties prop = new Properties();
			String filterValues = "filterData.properties";

			InputStream inputStream = getClass().getClassLoader()
					.getResourceAsStream(filterValues);
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '"
						+ filterValues + "' not found in the classpath");
			}

			agreementTypes = prop.getProperty("agreementType");
			gcmValues = prop.getProperty("gcm");
			assisgmentTypes = prop.getProperty("assisgmentType");
			projectMargins = prop.getProperty("projectmargin");
			averageCosts = prop.getProperty("averageCost");
			
			newPrologEstimate = prop.getProperty("newPrologEstimate");
			typeOfRequest = prop.getProperty("typeOfRequest");
			typeOfContract = prop.getProperty("typeOfContract");
			billable = prop.getProperty("billable");
			reasonForVacancy = prop.getProperty("reasonForVacancy");
			
		} catch (Exception e) {
			log.error("Exception: " + e.getMessage());
		}

		log.debug("------Session variable user is---------"	+ request.getSession().getAttribute("user"));
		UserBean userBean = (UserBean) request.getSession().getAttribute("user");
		ModelAndView modelAndView = new ModelAndView();
		SubconDetails subconDetails = new SubconDetails();
		subconDetails = rmgOperationServices.getTransactionLogForRecord(subcoId);
		if (subconDetails.getTransactionLog().getSubcoSDateWithAtos() == null 
				|| subconDetails.getTransactionLog().getSubcoSDateWithAtos()
				.toString().isEmpty()) {
			subconDetails.getTransactionLog().setSubcoSDateWithAtos(new Date());
		}
		if (subconDetails.getTransactionLog().getSubcoEDateWithAtos() == null  
				|| subconDetails.getTransactionLog().getSubcoEDateWithAtos()
				.toString().isEmpty()) {
			subconDetails.getTransactionLog().setSubcoEDateWithAtos(new Date());
		}
		List<CountryBean> countries = new ArrayList<CountryBean>();
		List<CountryPractiseBean> countryPractiseBeans = new ArrayList<CountryPractiseBean>();
		countries = rmgOperationServices.getCountries();
		
		countryPractiseBeans = rmgOperationServices.getCountriesPractiseforPRM(userBean);
		List<LanguageBean> listOfLanguageBeans = rmgOperationServices
				.getLanguages();
		modelAndView.addObject("languages", listOfLanguageBeans);
		modelAndView.addObject("subconDetails", subconDetails);
		modelAndView.addObject("countries", countries);
		modelAndView.addObject("countryPractiseBeans", countryPractiseBeans);
		modelAndView.addObject("selectedGBU", subconDetails.getCountry().getGbu());
		modelAndView.addObject("selectedCountry", subconDetails.getCountry()
				.getCountry());
		modelAndView.addObject("selectedGlobalPractise", subconDetails
				.getCountryPractise().getGlobalPractise());
		modelAndView.addObject("selectedGlobalSubPractise", subconDetails
				.getCountryPractise().getSubPractise());
		modelAndView.addObject("agreementType", agreementTypes);
		modelAndView.addObject("gcm", gcmValues);
		modelAndView.addObject("assisgmentType", assisgmentTypes);
		modelAndView.addObject("projectMargin", projectMargins);
		modelAndView.addObject("averageCost", averageCosts);		
		modelAndView.addObject("newPrologEstimate", newPrologEstimate);
		modelAndView.addObject("typeOfRequest", typeOfRequest);
		modelAndView.addObject("typeOfContract", typeOfContract);
		modelAndView.addObject("billable", billable);
		modelAndView.addObject("reasonForVacancy", reasonForVacancy);
		modelAndView.addObject("selectedStartDate", subconDetails.getTransactionLog().getSubcoSDateWithAtos());
        modelAndView.addObject("selectedEndDate", subconDetails.getTransactionLog().getSubcoEDateWithAtos());
        modelAndView.addObject("selectedSubcoDuration", subconDetails.getTransactionLog().getSubcoDuration());
       // String userRole = getUserRole(userBean);
      //modelAndView.addObject("userBean", userBean);
        modelAndView.addObject("userId", userBean.getUserID());
		modelAndView.addObject("usrRole", userBean.getUserWorkflowRole().trim());
		modelAndView.setViewName("inputSubconDetails");
		return modelAndView;

	}

	/**
	 * @param userBean
	 * @return
	 */
	private String getUserRole(UserBean userBean) {
		List<Object[]> listuserrole = rmgOperationServices.getUsrRole(userBean.getUserID());
		String userRole = "";
		if(listuserrole!=null) {
		for (Object result : listuserrole) {
				userRole = (String) result;
			}
		}
		return userRole;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/rmg/inputSubconDetails")
	public ModelAndView testing(HttpServletRequest request) {
		log.info("/rmg/inputSubconDetails");
		ModelAndView modelAndView = new ModelAndView();
		if(request.getSession().getAttribute("user") != null){
		String agreementTypes = null;
		String gcmValues = null;
		String assisgmentTypes = null;
		String projectMargins = null;
		String averageCosts = null;
		String newPrologEstimate = null;
		String typeOfRequest = null;
		String typeOfContract = null;
		String billable = null;
		String reasonForVacancy = null;

		try {
			Properties prop = new Properties();
			String filterValues = "filterData.properties";

			InputStream inputStream = getClass().getClassLoader()
					.getResourceAsStream(filterValues);
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '"
						+ filterValues + "' not found in the classpath");
			}

			agreementTypes = prop.getProperty("agreementType");
			gcmValues = prop.getProperty("gcm");
			assisgmentTypes = prop.getProperty("assisgmentType");
			projectMargins = prop.getProperty("projectmargin");
			averageCosts = prop.getProperty("averageCost");
			newPrologEstimate = prop.getProperty("newPrologEstimate");
			typeOfRequest = prop.getProperty("typeOfRequest");
			typeOfContract = prop.getProperty("typeOfContract");
			billable = prop.getProperty("billable");
			reasonForVacancy = prop.getProperty("reasonForVacancy");
			
		} catch (Exception e) {
			log.error("Exception: " + e.getMessage());
		}
		log.info("------Session variable user is---------"	+ request.getSession().getAttribute("user"));
		UserBean userBean = (UserBean) request.getSession().getAttribute("user");
		log.info("------------The userid of User logged in is:----------------------"+ userBean.getUserID());
		
		//String userRole = getUserRole(userBean);
	
		modelAndView.addObject("userId", userBean.getUserID());
		modelAndView.addObject("usrRole", userBean.getUserWorkflowRole().trim());
		
		List<Object[]> userDetails = rmgOperationServices.getUsrData(userBean.getUserID());
		if(userDetails !=null && !userDetails.isEmpty()) {
			for (Object[] result : userDetails) {
				userBean.setUserFName(String.valueOf(result[0]).trim());;
				userBean.setUserLName(String.valueOf(result[1]).trim());
				userBean.setEmailID(String.valueOf(result[4]).trim());
			}
		}
		ManagerBean managerBean = new ManagerBean();
		managerBean.setPmFName(userBean.getUserFName());
		managerBean.setPmLName(userBean.getUserLName());
		managerBean.setPmDAS_ID(userBean.getUserID().trim());
		managerBean.setPmEmail(userBean.getEmailID());
		
		SubconDetails subconDetails = new SubconDetails();
		subconDetails.setTransactionLog(new TransactionLogBean());
		subconDetails.setManagerBean(managerBean);
		List<CountryBean> countries = new ArrayList<CountryBean>();
		List<CountryPractiseBean> countryPractiseBeans = new ArrayList<CountryPractiseBean>();
		//countries = rmgOperationServices.getCountries();
		countries = rmgOperationServices.getCountries(userBean);
		
		countryPractiseBeans = rmgOperationServices.getCountriesPractise(userBean);
		List<LanguageBean> listOfLanguageBeans = rmgOperationServices
				.getLanguages();
		modelAndView.addObject("languages", listOfLanguageBeans);
		modelAndView.addObject("subconDetails", subconDetails);
		modelAndView.addObject("countries", countries);
		/*if (countries!=null && !countries.isEmpty()) {
            modelAndView.addObject("selectedGbu", countries.get(0).getGbu());
		}*/
		try {
			if (countries!=null && !countries.isEmpty()) {
				modelAndView.addObject("selectedGbu", countries.get(0).getGbu());
				modelAndView.addObject("selectedCountry", countries.get(0)
						.getCountry());
			}
			if (countryPractiseBeans!=null && !countryPractiseBeans.isEmpty()) {
				modelAndView.addObject("selectedglobalPractice",
						countryPractiseBeans.get(0).getGlobalPractise());
			}
		} catch (Exception e) {
			log.error("Exception: " + e.getMessage());
		}
		modelAndView.addObject("countryPractiseBeans", countryPractiseBeans);
		modelAndView.addObject("agreementType", agreementTypes);
		modelAndView.addObject("gcm", gcmValues);
		modelAndView.addObject("assisgmentType", assisgmentTypes);
		modelAndView.addObject("projectMargin", projectMargins);
		modelAndView.addObject("averageCost", averageCosts);
		modelAndView.addObject("newPrologEstimate", newPrologEstimate);
		modelAndView.addObject("typeOfRequest", typeOfRequest);
		modelAndView.addObject("typeOfContract", typeOfContract);
		modelAndView.addObject("billable", billable);
		modelAndView.addObject("reasonForVacancy", reasonForVacancy);
		modelAndView.setViewName("inputSubconDetails");
		} else {
			UserBean user = new UserBean();
			modelAndView.addObject("user", user);
			modelAndView.setViewName("login");
		}
		return modelAndView;

	}

	/**
	 * 
	 * @param request
	 * @param subconDetails
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/rmg/showSubconDetails" }, method = { 
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView testing(HttpServletRequest request,
			@ModelAttribute SubconDetails subconDetails) throws Exception {
		log.info("Entering showSubconDetails :/rmg/showSubconDetails");
		UserBean userBean = (UserBean) request.getSession()
				.getAttribute("user");
		SubconDetails readSuconDetails = null;
		String demandID = "";
		if(subconDetails.getTransactionLog().getSubcoDemandID() != 0) {
			readSuconDetails = rmgOperationServices.getTransactionLogForRecord
					(subconDetails.getTransactionLog().getSubcoDemandID());
			subconDetails = addDataFromForm(subconDetails, readSuconDetails);
		}
		
		demandID = rmgOperationServices.addDetails(subconDetails,userBean);
		log.info("demandID:"+demandID);
		ModelAndView modelAndView = new ModelAndView();
		if(demandID == "insertFailed"){
			modelAndView.setViewName("homepage");
			modelAndView.addObject("msgDemandID","Demand creation failed");
		}
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		String userRole = user.getUserWorkflowRole();
	//	String userRole = getUserRole(user);
		
		User tempUser = new User();
		tempUser.setUserID(user.getUserID());
		modelAndView.addObject("tempUser", tempUser);

		String agreementTypes = null;
		String gcmValues = null;
		String assisgmentTypes = null;
		String statusType = null;
		String projectMargins = null;
		String averageCosts = null;
		
		// in testing method
		List<CountryBean> countriesListBean = new ArrayList<CountryBean>();
		 
		List<CountryBean> countries = new ArrayList<CountryBean>();
		List<CountryPractiseBean> countryPractiseBeans = new ArrayList<CountryPractiseBean>();
		countries = rmgOperationServices.getCountries(userBean);
		countryPractiseBeans = rmgOperationServices
				.getCountriesPractise(userBean);
		List<PrimaryDataSearchResultBean> listOfPrimaryData = rmgOperationServices
				.getPrimaryDetails(userBean);
		List<CountryBean> countryBean = rmgOperationServices
				.getFiltersInitialData(userBean);
		
		manipulateDisplayData(userRole, listOfPrimaryData);
		
		CountryBean initialCountryData = countryBean.get(0);
		modelAndView.addObject("initialCountryData", initialCountryData);
		modelAndView.addObject("countries", countries);
		modelAndView.addObject("countryPractiseBeans", countryPractiseBeans);
		modelAndView.addObject("primaryDataBean", listOfPrimaryData);
		

		try {
			Properties prop = new Properties();
			String filterValues = "filterData.properties";

			InputStream inputStream = getClass().getClassLoader()
					.getResourceAsStream(filterValues);
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '"
						+ filterValues + "' not found in the classpath");
			}
			agreementTypes = prop.getProperty("agreementType");
			gcmValues = prop.getProperty("gcm");
			statusType = prop.getProperty("statusType");
			projectMargins = prop.getProperty("projectmargin");
			averageCosts = prop.getProperty("averageCost");
		} catch (Exception e) {
			log.error("Exception: " + e.getMessage());
		}
		try {
			if(!userRole.equalsIgnoreCase("GPRM"))
			{
				if(!userRole.equalsIgnoreCase("OH")){
					if (countries.get(0).getGbu() != null) {
						modelAndView.addObject("selectedGbu", countries.get(0).getGbu());
					}
				}
			}
			if (countries != null && userRole.equalsIgnoreCase("PRM")) {
				countriesListBean = rmgOperationServices.getCountriesForGBUGPRM(countries.get(0).getGbu());
				modelAndView.addObject("countriesListBean", countriesListBean);
			}
			/*if (countries.get(0).getCountry() != null) {
				modelAndView.addObject("selectedCountry", countries.get(0)
						.getCountry());
			}*/
			if (countryPractiseBeans.get(0).getGlobalPractise() != null) {
					modelAndView.addObject("selectedglobalPractice",
							countryPractiseBeans.get(0).getGlobalPractise());
			}
		} catch (Exception e) {
			log.error("Exception: " + e.getMessage());
		}
		
		List<Personalise> personlise = rmgOperationServices.getUserRole1(userRole);
		PersonaliseBean perBean = new PersonaliseBean();
		perBean = fillData(perBean, personlise);
		modelAndView.addObject("perBean", perBean);

		Properties prop = new Properties();
		String dbConnection = "Connection.properties";

		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream(dbConnection);
		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			log.error("property file '"
					+ dbConnection + "' not found in the classpath");
		}
		String dburl = prop.getProperty("dburl");
		List<Summary> summaryListObj = rmgOperationServices.getSummaryDetails(dburl);
		modelAndView.addObject("summaryListObj", summaryListObj);
		modelAndView.addObject("agreementType", agreementTypes);
		modelAndView.addObject("gcm", gcmValues);
		modelAndView.addObject("assisgmentType", statusType);
		modelAndView.addObject("projectMargin", projectMargins);
		modelAndView.addObject("averageCost", averageCosts);
		if(demandID!=null && !demandID.isEmpty() && demandID.contains("N")) {
			modelAndView.addObject("msgDemandID","New demand created successfully :"+demandID.replace("N", ""));
		} 
		if(demandID!=null && !demandID.isEmpty() && demandID.contains("U")) {
			modelAndView.addObject("msgDemandID","Demand updated successfully :"+demandID.replace("U", ""));
			//modelAndView.setViewName("homepage");
		}
		// modelAndView.setViewName("landingUpdated");
		modelAndView.setViewName("homepage");
		modelAndView.addObject("userId", user.getUserID());
		modelAndView.addObject("usrRole", userRole.trim());

		List<CountryBean> countriesadmin = new ArrayList<CountryBean>();
		countriesadmin = rmgOperationServices.getCountries();
		
		if(userRole!=null && userRole.trim().equals("GPRM")){		
			modelAndView.addObject("countriesGprm", countriesadmin);	
		} 		
		if(userRole!=null && userRole.trim().equals("OH")){			
			modelAndView.addObject("countriesGprm", countriesadmin);
		}
		
		return modelAndView;

	}

	/**
	 * 
	 * @param subconDetails
	 * @param readSuconDetails
	 * @return
	 */
	private SubconDetails addDataFromForm(SubconDetails subconDetails,
			SubconDetails readSuconDetails) {
		subconDetails.getTransactionLog().setDemandStatus(readSuconDetails.getTransactionLog().getDemandStatus());
		subconDetails.getTransactionLog().setCreatedBy(readSuconDetails.getTransactionLog().getCreatedBy());
		subconDetails.getTransactionLog().setCreatedDate(readSuconDetails.getTransactionLog().getCreatedDate());
		subconDetails.getManagerBean().setCreatedBy(readSuconDetails.getManagerBean().getCreatedBy());
		subconDetails.getManagerBean().setCreatedDate(readSuconDetails.getManagerBean().getCreatedDate());
		return subconDetails;
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@RequestMapping(value = "/rmg/home", method = RequestMethod.GET)
	public ModelAndView showHomePage(HttpServletRequest request) throws ClassNotFoundException, SQLException {
		ModelAndView modelAndView = new ModelAndView();
		afterUserIsValid(request, null, modelAndView);
		return modelAndView;
	}

	/**
	 * 
	 * @param request
	 * @param userBean
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	//@RequestMapping("/rmg/home")
	@RequestMapping(value = "/rmg/home", method =  {RequestMethod.POST})
	public ModelAndView validateUser(HttpServletRequest request,
			@ModelAttribute UserBean userBean) throws ClassNotFoundException, SQLException {
		
		request.getSession().setAttribute("flag", "no");
		boolean isValidUser = false;
		boolean userAlreadyExist = false;
		HttpSession httpSession = null;
		String userActFlag = "";
		UserBean user = null;
		String listuserrole = null;
		
		try {
			log.info("/rmg/home");
			
			 user = (UserBean) request.getSession().getAttribute("user");
			 
			 System.out.println("Temp Dir of Cache:" + System.getProperty("java.io.tmpdir"));
			 
			 if (user == null) {
				httpSession = request.getSession(true);
				if(userBean.getUserPwd().equalsIgnoreCase("null") || userBean.getUserPwd().equals("") )
				{
					String serverName;

					if(request.getServerName().equalsIgnoreCase("localhost"))
					{
						serverName = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

					}
					else{
						serverName = "https://" + request.getServerName();
					}
					return new ModelAndView("redirect:"+serverName+"/RmgApplication/rmg/UserDetails");
					//return new ModelAndView("redirect:/rmg/UserDetails");
				}
				listuserrole = rmgOperationServices.getUsrRoleforLogin(userBean.getUserID(),
						userBean.getUserWorkflowRole());
				isValidUser = rmgOperationServices.validateUser(userBean,	httpSession);
				/*if( !listuserrole.trim().equalsIgnoreCase("GA") 
						&&  !listuserrole.trim().equalsIgnoreCase("GBA") && isValidUser 
						&& userBean.getUserPwd()!= null && userBean.getUserID()!= null 
						&& listuserrole!=null)	{
					return new ModelAndView("redirect:/rmg/hiring");
				}*/
				if(isValidUser) {
					log.info(".......is valid user......");
					userActFlag = (String) httpSession.getAttribute("userObjActiveFlag");
				}
								
				if (userActFlag != null && userActFlag.equals("1")) {
					log.info(".......user already exist......");
					userAlreadyExist = true;
				}
				
			} else {
				httpSession = request.getSession();
				isValidUser = rmgOperationServices.validateUser(userBean, httpSession);
				if(isValidUser) {
					userActFlag = (String) httpSession.getAttribute("userObjActiveFlag");
				}
								
				if (userActFlag != null && userActFlag.equals("1")) {
					userAlreadyExist = true;
					userBean = user;
				}
				httpSession.setAttribute("sessionContinues", "true");
			}
		} catch (Exception e) {
			log.error("Error:"+e.getMessage());
		}
		ModelAndView modelAndView = new ModelAndView();
		if (isValidUser) {
			log.info(".......after user is valid......");
			if (userAlreadyExist) {
				if(httpSession.getAttribute("sessionContinues")!=null && httpSession.
						getAttribute("sessionContinues").equals("true")) {
					//httpSession.setAttribute("userObjActiveFlag", "1");
					afterUserIsValid(request, userBean, modelAndView);
				} else {
					request.getSession().setAttribute("user",null);
					modelAndView.addObject("user", new UserBean());
					modelAndView.addObject("message", "This user already logged in");
					modelAndView.setViewName("login");
				}
			} else {
				//httpSession.setAttribute("userObjActiveFlag", "1");
				afterUserIsValid(request, userBean, modelAndView);
			}	

		} else {
			user = new UserBean();
			List<String> userRole = new ArrayList<String>();
			userRole.add("PM");
			userRole.add("AM");
			modelAndView.addObject("userRole", userRole);
			modelAndView.addObject("user", user);
			if (userAlreadyExist) {
				modelAndView.addObject("message", "This user already logged in");
			} else if(listuserrole==null)
			{
				modelAndView.addObject("message", "Sorry. "
						+ " Invalid Role selection for UserID :"+userBean.getUserID());
			}else {
				modelAndView.addObject("message", "Sorry. "
						+ "Please Enter valid Login ID and Password");
			}
			modelAndView.setViewName("login");
		}
		
		return modelAndView;
	}

	
	/**
	 * 
	 * @param request
	 * @param userBean
	 * @param modelAndView
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	protected void afterUserIsValid(HttpServletRequest request,
			UserBean userBean, ModelAndView modelAndView) throws ClassNotFoundException, SQLException {
		log.info(".......afterUserIsValid......"); 
		ServletContext ctx=null;
		UserBean userObj = userBean;
		String agreementTypes = null;
		String gcmValues = null;
		String assisgmentTypes = null;
		String projectMargins = null;
		String averageCosts = null;
		String role1 = null;
		
		if (userObj == null) {
			userObj = (UserBean) request.getSession().getAttribute("user");
		} 
		request.getSession().setAttribute("user", userObj);
		ctx=request.getSession().getServletContext();			
		ctx.setAttribute("user", userObj);
		
		List<CountryBean> countries = new ArrayList<CountryBean>();
		
		//define countriesListBean
		List<CountryBean> countriesListBean = new ArrayList<CountryBean>();
		
		List<CountryPractiseBean> countryPractiseBeans = new ArrayList<CountryPractiseBean>();
		
		List<CountryBean> countriesadmin = new ArrayList<CountryBean>();
		countriesadmin = rmgOperationServices.getCountries();
		
		countries = rmgOperationServices.getCountries(userObj);
		countryPractiseBeans = rmgOperationServices.getCountriesPractise(userObj);
		
		//UserBean user = (UserBean) request.getSession().getAttribute("user");
		role1 = rmgOperationServices.getUsrRoleforLogin(userObj.getUserID(),userObj.getUserWorkflowRole());
		//String userRole = getUserRole(user);
		String userRole = userObj.getUserWorkflowRole();
		//userObj.setUserWorkflowRole(userRole);
		
		List<PrimaryDataSearchResultBean> listOfPrimaryData = rmgOperationServices.getPrimaryDetails(userObj);
		
		manipulateDisplayData(userRole.trim(), listOfPrimaryData);
		
		User tempUser = new User();
		tempUser.setUserID(userObj.getUserID());
		rmgOperationServices.updateActFlag(tempUser);
		modelAndView.addObject("countries", countries);
		modelAndView.addObject("tempUser", tempUser);
		modelAndView.addObject("countryPractiseBeans", countryPractiseBeans);
		modelAndView.addObject("primaryDataBean", listOfPrimaryData);

		try {
			Properties prop = new Properties();
			String filterValues = "filterData.properties";

			InputStream inputStream = getClass().getClassLoader()
					.getResourceAsStream(filterValues);
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '"
						+ filterValues + "' not found in the classpath");
			}

			agreementTypes = prop.getProperty("agreementType");
			gcmValues = prop.getProperty("gcm");
			assisgmentTypes = prop.getProperty("statusType");
			projectMargins = prop.getProperty("projectmargin");
			averageCosts = prop.getProperty("averageCost");
		} catch (Exception e) {
			log.error("Exception: " + e.getMessage());
		}

		try {
			if(userRole!=null && userRole.trim().equals("PM")){
				if (countries.get(0).getGbu() != null) {
					modelAndView
							.addObject("selectedGbu", countries.get(0).getGbu());
				}
				if (countries.get(0).getCountry() != null) {
					modelAndView.addObject("selectedCountry", countries.get(0)
							.getCountry());
				}
				if (countryPractiseBeans.get(0).getGlobalPractise() != null) {
					modelAndView.addObject("selectedglobalPractice",
							countryPractiseBeans.get(0).getGlobalPractise());
				}
			} 
			if(userRole!=null && userRole.trim().equals("PRM")){
				String gbuName = countries.get(0).getGbu();
				if (countries.get(0).getGbu() != null) {
					modelAndView.addObject("selectedGbu", countries.get(0).getGbu());
				}
				/*if (countries.get(0).getCountry() != null) {
					modelAndView.addObject("selectedCountry", countries.get(0)
							.getCountry());
				}*/
				if (countries != null) {
					countriesListBean = rmgOperationServices.getCountriesForGBUGPRM(gbuName);
					modelAndView.addObject("countriesListBean", countriesListBean);
				}
				if (countryPractiseBeans.get(0).getGlobalPractise() != null) {
					modelAndView.addObject("selectedglobalPractice",
							countryPractiseBeans.get(0).getGlobalPractise());
				}
				
			} 
			if(userRole!=null && userRole.trim().equals("GPRM")){
								
				//String gbuName = countries.get(0).getGbu();
				
				/*if (countries.get(0).getGbu() != null) {
					modelAndView.addObject("selectedGbu", countries.get(0).getGbu());
				}*/
				modelAndView.addObject("countriesGprm", countriesadmin);
				
				/*if (countries != null) {
					countriesListBean = rmgOperationServices.getCountriesForGBUGPRM(gbuName);
					modelAndView.addObject("countriesListBean", countriesListBean);
				}*/
				/*if (countryPractiseBeans != null) {
					modelAndView.addObject("selectedglobalPractice", countryPractiseBeans);
				}*/
				if (countryPractiseBeans.get(0).getGlobalPractise() != null) {
					modelAndView.addObject("selectedglobalPractice",
							countryPractiseBeans.get(0).getGlobalPractise());
				}
			} 
			if(userRole!=null && userRole.trim().equals("OH")){
				
				modelAndView.addObject("countriesGprm", countriesadmin);
			}
		} catch (Exception e) {
			log.error("Exception: " + e.getMessage());
		}

		Properties prop = new Properties();
		String dbConnection = "Connection.properties";

		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream(dbConnection);
		if (inputStream != null) {
			try {
				prop.load(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			log.error("property file '"
					+ dbConnection + "' not found in the classpath");
		}
		String dburl = prop.getProperty("dburl");
		List<Summary> summaryListObj = rmgOperationServices.getSummaryDetails(dburl);
		modelAndView.addObject("summaryListObj", summaryListObj);
		modelAndView.addObject("agreementType", agreementTypes);
		modelAndView.addObject("gcm", gcmValues);
		modelAndView.addObject("assisgmentType", assisgmentTypes);
		modelAndView.addObject("projectMargin", projectMargins);
		modelAndView.addObject("averageCost", averageCosts);
		List<CountryBean> countryBean = rmgOperationServices
				.getFiltersInitialData(userObj);
		
		CountryBean initialCountryData = countryBean.get(0);
		modelAndView.addObject("initialCountryData", initialCountryData);
		
		List<User> listOfUserBean = null;
		if(role1.trim().equalsIgnoreCase("GA"))
		{
			userObj = (UserBean) request.getSession().getAttribute("user");
			
			userBean = userObj;
			listOfUserBean =	rmgOperationServices.getUserMasterDetails(userBean.getUserID());
			modelAndView.addObject("countries", countriesadmin);
			modelAndView.addObject("adminuserBean", listOfUserBean);
			modelAndView.addObject("userId", userBean.getUserID());
			modelAndView.addObject("usrRole", role1.trim());
			modelAndView.addObject("userBean", new UserBean()); 
			modelAndView.setViewName("globalAdminDetails");
			
		} else if(role1.trim().equalsIgnoreCase("GBA"))	{
			userObj = (UserBean) request.getSession().getAttribute("user");
			
			userBean = userObj;
			listOfUserBean = rmgOperationServices.getUserMasterDetails(userBean.getUserID());			
			userBean.setGbuadmin("y");
			modelAndView.addObject("countries", countriesadmin);
			modelAndView.addObject("adminuserBean", listOfUserBean);
			modelAndView.addObject("userId", userBean.getUserID());
			modelAndView.addObject("usrRole", role1.trim());
			modelAndView.addObject("userBean", new UserBean()); 
			modelAndView.setViewName("globalAdminDetails");
			
		} else{
			

			List<Personalise> personlise = rmgOperationServices.getUserRole1(role1.trim());
			PersonaliseBean perBean = new PersonaliseBean();
			perBean = fillData(perBean, personlise);
			modelAndView.addObject("perBean", perBean);
			modelAndView.setViewName("homepage");
		}
	}




/**
 * 
 * @param userBean
 * @param request
 * @param response
 * @return
 * @throws IOException
 */
	
	@RequestMapping("/rmg/UserDetails")
	public ModelAndView UserDetails(@ModelAttribute UserBean userBean,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		log.info(".........../rmg/UserDetails............." );
		response.setContentType("text/html");
		ResourceBean resource = new ResourceBean();
		boolean isValidUser = false;
		String usrEmailID = null ;
		List<ResourceBean> listOfResourceBean = new ArrayList<ResourceBean>();
		listOfResourceBean.add(resource);

		if(userBean!=null && request.getParameter("inputDas")!=null 
				&& !request.getParameter("inputDas").isEmpty()) {
			userBean.setUserID(request.getParameter("inputDas"));
		}
		try {
			User user = (User) request.getSession().getAttribute("userObj");
			HttpSession httpSession = null;

			if (user == null) {
				httpSession = request.getSession(true);
			} else {
				httpSession = request.getSession();
			}

			isValidUser = rmgOperationServices.UserDetails(userBean, httpSession);
		} catch (Exception e) {
			log.error("Error:"+e.getMessage());;
		}

		ModelAndView modelAndView = new ModelAndView();
		if (isValidUser) {

			User user = new User();
			modelAndView.addObject("user", user);
			String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ0123456789+@";

			String pw = "";
			for (int i = 0; i < PASSWORD_LENGTH; i++) {
				int index = (int) (RANDOM.nextDouble() * letters.length());
				pw += letters.substring(index, index + 1);
			}

			user.setUserPwd(pw);
			usrEmailID = rmgOperationServices.getUserEmailId(userBean.getUserID());
			rmgOperationServices.updatePswdforforgot(userBean.getUserID(), user);
			String from = "ARAS@atos.net";
			 
			// Setup mail server
			
			
			String emailConf = "emailConfiguration.properties";
			Properties propFile = new Properties();

			try {
				InputStream inputStream = getClass().getClassLoader()
						.getResourceAsStream(emailConf);
				if (inputStream != null) {
					propFile.load(inputStream);
				} else {
					throw new FileNotFoundException("property file '"
							+ emailConf + "' not found in the classpath");
				}

				String host = propFile.getProperty("smtp_host");
				String socket_port = propFile.getProperty("smtp_socketFactory_port");
				String smtp_port = propFile.getProperty("smtp_port");
				
				Properties props = new Properties();
				props.put("mail.smtp.host", host);
				props.put("mail.smtp.socketFactory.port", socket_port);
				props.put("mail.smtp.port", smtp_port);
				
				// Get the default Session object.
				javax.mail.Session session = javax.mail.Session.getDefaultInstance(props);
				// Create a default MimeMessage object.
				MimeMessage message = new MimeMessage(session);

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));

				// Set To: header field of the header.
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(usrEmailID.trim()));

				// Set Subject: header field
				message.setSubject("You have received mail from Subcon Team");

				// Send message
				String Uri;

                if(request.getServerName().equalsIgnoreCase("localhost"))
                {
                 Uri = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
                }
                else{
                 Uri = "https://" + request.getServerName();
                    }

                String encryptedValue = EncryptableUtilities.getInstance().getEncryptedValue(userBean.getUserID() + ";" + pw);
                log.info("encryptedValue = " + encryptedValue);
                encryptedValue = EncryptableUtilities.getInstance().encode(encryptedValue);
                log.info("encryptedValue encoded = " + encryptedValue);
                
				// Send the actual HTML message, as big as you like

				message.setContent("<table cellspacing=10>" + "<tr><td style='font-family: Verdana, "
						+ "Geneva, sans-serif' 'color:#176ba7' 'font-size: 16px'>"
						+ "<b>Welcome to Atos Resource Approval System</b>" + "</td></tr>" + "</table>"
						+ "<table style='border:1px solid #176ba7' cellspacing=15>"
						+ "<tr><td style='font-family: Verdana, Geneva, sans-serif' 'font-size: 12px'>"
						+ "<b>User ID : </b>" + userBean.getUserID()
						+ "<br><br>" + "Click below link to reset password " + "<br>" + "<a href=" + Uri
						+ "/RmgApplication/rmg/resetPswd?id=" + encryptedValue + ">Reset Password</a>"
						+ "</td></tr>" + "</table>", "text/html");

				Transport.send(message);

				 modelAndView.addObject("message", "New password has been sent to your registered email ID!");
				 modelAndView.setViewName("login");
				//return new ModelAndView("redirect:/");
				// return new ModelAndView("redirect:/admin/adminUsers.html");
			} catch (MessagingException mex) {
				mex.printStackTrace();
			}
		} else {
			UserBean user = new UserBean();
			modelAndView.addObject("user", user);
			modelAndView.addObject("message", "User Id doesn't exist");
			modelAndView.setViewName("login");
		}
		return modelAndView;

	}

	@RequestMapping("/rmg/forgetPswd")
	public ModelAndView forgetPswd(@ModelAttribute UserBean userBean,
			HttpServletRequest request) throws IOException {
		boolean isValidUser = false;

		userBean.setUserID(request.getParameter("dasID"));
		userBean.setUserPwd(request.getParameter("confirmpassword"));

		try {
			User user = (User) request.getSession().getAttribute("userObj");
			HttpSession httpSession = null;

			if (user == null) {
				httpSession = request.getSession(true);
			} else {
				httpSession = request.getSession();
			}

			isValidUser = rmgOperationServices.forgetPswd(userBean);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView modelAndView = new ModelAndView();
		if (isValidUser) {
			UserBean user = new UserBean();
			
			modelAndView.addObject("user", user);
			modelAndView.addObject("mess",
					"Password changed successfully Enter your Credentials");
			modelAndView.setViewName("login");
			return modelAndView;
		}
		// modelAndView.addObject("message", "Password changed successfully. ");
		// modelAndView.setViewName("login");
		return modelAndView;
	}

	/**
	 * 
	 * @param selectedGbuValue
	 * @param selectedCountryValue
	 * @param selectedGlobalPracticeValue
	 * @param selectedAgreementTypeValue
	 * @param selectedGCMValue
	 * @param selectedAssisgmentTypeValue
	 * @param selectedProjectMarginValue
	 * @param selectedAverageCostValue
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "/rmg/displayfilteredData", method = RequestMethod.POST)
	public @ResponseBody List<PrimaryDataSearchResultBean> getFilteredData(
			@RequestParam(value = "selectedGbuValue", required = false) String selectedGbuValue,
			@RequestParam(value = "selectedCountryValue", required = false) String selectedCountryValue,
			@RequestParam(value = "selectedGlobalPracticeValue", required = false) String selectedGlobalPracticeValue,
			@RequestParam(value = "selectedAgreementTypeValue", required = false) String selectedAgreementTypeValue,
			@RequestParam(value = "selectedGCMValue", required = false) String selectedGCMValue,
			@RequestParam(value = "selectedAssisgmentTypeValue", required = false) String selectedAssisgmentTypeValue,
			@RequestParam(value = "selectedProjectMarginValue", required = false) String selectedProjectMarginValue,
			@RequestParam(value = "selectedAverageCostValue", required = false) String selectedAverageCostValue, 
			HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView();
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		//String userRole = getUserRole(user);
		String userRole = user.getUserWorkflowRole();
		
		//String gPracticeList = rmgOperationServices.getCountryIdCN(selectedCountryValue);
		
		PrimaryDataSearchResultBean primaryDataBean = new PrimaryDataSearchResultBean();
		List<PrimaryDataSearchResultBean> listOfPrimaryDataonGbu = rmgOperationServices
				.getPrimaryDetailsOnFilters(selectedGbuValue,
						selectedCountryValue.trim(), selectedGlobalPracticeValue,
						selectedAgreementTypeValue, selectedGCMValue,
						selectedAssisgmentTypeValue,
						selectedProjectMarginValue, selectedAverageCostValue,userRole,user);
		
		manipulateDisplayData(userRole, listOfPrimaryDataonGbu);
		primaryDataBean.setListOfPrimaryData(listOfPrimaryDataonGbu);
		modelAndView.addObject("primaryDataBean", primaryDataBean);
		modelAndView.setViewName("landingUpdated");

		return listOfPrimaryDataonGbu;
	}
/**
 * 
 * @param userRole
 * @param listOfPrimaryDataonGbu
 */
	protected void manipulateDisplayData(String userRole,
			List<PrimaryDataSearchResultBean> listOfPrimaryDataonGbu) {
		for(PrimaryDataSearchResultBean bean:listOfPrimaryDataonGbu) {
			bean.setIndicator("F");
			bean.setExtButton("F");
			bean.setSubconButton("F");
			if(userRole!= null && !userRole.trim().equals("PM")){
				if(bean.getDemandStatus()!=null && bean.getDemandStatus().trim().contains("Ext"))
					bean.setExtButton("T");
			} 
			if(userRole!=null && !userRole.trim().equals("PM") ){
				if(bean.getDemandStatus()!=null && !bean.getDemandStatus().trim().contains("Ext"))
					bean.setSubconButton("T");
			} 			
			
			if(userRole!= null && !userRole.equals("")) {
				if(userRole.trim().equals("PM") && bean.getDemandStatus() !=null) {
					if(!bean.getDemandStatus().trim().contains("Approved by OH") 
							|| bean.getDemandStatus().trim().contains("Rejected")) {
						bean.setIndicator("T");
					}					
					if(bean.getDemandStatus().trim().contains("On Hold by PRM")
							||bean.getDemandStatus().trim().contains("Extension On Hold by PRM")) {
						bean.setIndicator("F");
					}					
					if(bean.getDemandStatus()!=null && bean.getDemandStatus().trim().contains("On Hold by PRM"))
						bean.setSubconButton("T");
				}
				if(userRole.trim().equals("PRM") && bean.getDemandStatus() !=null) {
					if(bean.getDemandStatus().trim().equals("Approved by PRM")
							|| bean.getDemandStatus().trim().contains("Rejected")
							|| bean.getDemandStatus().trim().contains("Approved by OH") 
							|| bean.getDemandStatus().trim().contains("Extension Approved by PRM")
							|| bean.getDemandStatus().trim().contains("Extension Approved by OH")
							|| bean.getDemandStatus().trim().contains("Approved by GPRM")
							|| bean.getDemandStatus().trim().contains("Extension Approved by GPRM")) {
						bean.setIndicator("T");
					}
					if(bean.getDemandStatus().trim().contains("Extension On Hold by GPRM")) {
						bean.setIndicator("F");
					}
				}
				if(userRole.trim().equals("GPRM") && bean.getDemandStatus() !=null ) {
					if(bean.getDemandStatus().trim().equals("Approved by GPRM") 
							|| bean.getDemandStatus().trim().equals("New") 
							|| bean.getDemandStatus().trim().contains("Rejected") 
							|| bean.getDemandStatus().trim().contains("Approved by OH")
							|| bean.getDemandStatus().trim().contains("Extension Approved by OH")
							|| bean.getDemandStatus().trim().contains("Extension Approved by GPRM")) {
						bean.setIndicator("T");
					}
					if(bean.getDemandStatus().trim().contains("Extension On Hold by OH")) {
						bean.setIndicator("F");
					}
				}
				if(userRole.trim().equals("OH") && bean.getDemandStatus() !=null) {
					if(bean.getDemandStatus().trim().equals("Approved by OH") 
							|| bean.getDemandStatus().trim().equals("New")  
							|| bean.getDemandStatus().trim().equals("Extension")  
							|| bean.getDemandStatus().trim().contains("Rejected") 
							|| bean.getDemandStatus().trim().contains("Approved by PRM")
							|| bean.getDemandStatus().trim().contains("Extension Approved by OH")
							|| bean.getDemandStatus().trim().contains("Extension Approved by PRM")) {
						bean.setIndicator("T");
					}
					if(bean.getDemandStatus().trim().contains("Extension On Hold by OH")) {
						bean.setIndicator("F");
					}
				}
			}
		}
	}

	
	/**
	 * 
	 * @param managerBean
	 * @return
	 */
	@RequestMapping(value = { "/rmg/subconApproval" }, method =
		{ org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView subconApproval(@ModelAttribute ManagerBean managerBean) {
		log.info(".........../rmg/subconApproval............." );
		ModelAndView modelAndView = null;
		try {
			List<ManagerBean> listOfManagerBean = rmgOperationServices
					.findAll();
			listOfManagerBean.add(managerBean);
			modelAndView = new ModelAndView();
			modelAndView.addObject("managerList", listOfManagerBean);
			modelAndView.setViewName("subconApproval");

		} catch (Exception e) {
			log.error("Error:"+e.getMessage());
		}
		return modelAndView;
	}

	/**
	 * 
	 * @param managerBean
	 * @return
	 */
	@RequestMapping(value = { "/rmg/saveResource" }, method =
		{ org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView saveResource(@ModelAttribute ManagerBean managerBean) {
		ModelAndView modelAndView = null;
		try {
			log.info(".........../rmg/saveResource............." );
			rmgOperationServices.save(managerBean);
			ManagerBean searchmanagerBean = new ManagerBean();
			List<ManagerBean> listOfManagerBean = rmgOperationServices
					.serach("");
			searchmanagerBean.setListOfManagerBean(listOfManagerBean);
			modelAndView = new ModelAndView();
			modelAndView.addObject("manager", searchmanagerBean);
			modelAndView.setViewName("search");

		} catch (Exception e) {
			log.error("Error:"+e.getMessage());
		}
		return modelAndView;
	}
	/**
	 * 
	 * @param employeeId
	 * @return
	 */
	@RequestMapping(value = { "/rmg/search" }, method = 
		{ org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView search(@RequestParam String employeeId) {

		ModelAndView modelAndView = null;
		try {

			List<ManagerBean> listOfManagerBean = rmgOperationServices
					.serach(employeeId);
			modelAndView = new ModelAndView();
			modelAndView.addObject("managerList", listOfManagerBean);
			modelAndView.setViewName("searchResult");
		} catch (Exception e) {
			log.error("Error:"+e.getMessage());
		}
		return modelAndView;

	}

	/**
	 * 
	 * @param transactionBean
	 * @param request
	 * @param session
	 * @return
	 */
	
	@RequestMapping(value = { "/rmg/subconApprovalOrRejection" }, 
			method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView subconApproval(
			@ModelAttribute TransactionLogBean transactionBean,
			HttpServletRequest request, HttpSession session) {
		log.info(".........../rmg/subconApprovalOrRejection............." );
		ModelAndView modelAndView = null;
		try {
			modelAndView = new ModelAndView();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	        // Get the date today using Calendar object.
	        Date today = Calendar.getInstance().getTime();        
	        // Using DateFormat format method we can create a string 
	        // representation of a date with the defined format.
	        String reportDate = df.format(today);
	        String chkBoxIDs = request.getParameter("chkBoxIDs");

			List<String> listsubCoid = Arrays.asList(chkBoxIDs.split(","));
			List<Integer> listsubCoid1 = new ArrayList<Integer>();
			
			for(String s : listsubCoid){
				listsubCoid1.add(Integer.valueOf(s));
			}
			List<SubconApprovalBean> listsubcoApprovalBean = new ArrayList<SubconApprovalBean>();

			List<Object[]> listTransactionLogBean = rmgOperationServices.findAll_ApprovalData(listsubCoid1);
			UserBean user = (UserBean) request.getSession().getAttribute("user");
			//String userRole = getUserRole(user);
			String userRole = user.getUserWorkflowRole();
			SubconApprovalBean sabean = null;
			String sdate = null;
			String exnMark = "";
			String edate = null;
			String subcodatewithAtos = "";
			for (Object[] result : listTransactionLogBean) {
				
				sabean = new SubconApprovalBean();
				sabean.setSubcoDemandId(Integer.valueOf((Integer) result[0]));
				sabean.setGbu(String.valueOf(result[1]));
				sabean.setCountry(String.valueOf(result[2]));
				sabean.setGpractice(String.valueOf(result[3]));
				sabean.setGcm(String.valueOf(result[4]));
				sabean.setMargin(String.valueOf(result[5]));
				sabean.setAvgCost(String.valueOf(result[6]));

				sdate = String.valueOf(result[7]);
				if (sdate.equalsIgnoreCase("null")) {
					sdate = "";
					sabean.setSubcoEDateWithAtos(reportDate);
				} else {
					sabean.setSubcoEDateWithAtos(String.valueOf(result[7]).substring(0, 10));
				}

				exnMark = String.valueOf(result[8]);
				if (exnMark.equalsIgnoreCase("null")) {
					exnMark = "";
					sabean.setSubconExtnRemark(exnMark);
				} else {
					sabean.setSubconExtnRemark(String.valueOf(result[8]));
				}
				edate = String.valueOf(result[9]);
				if (edate.equalsIgnoreCase("null")) {
					edate = "";
					sabean.setSubcoExtnEdate(edate);
				} else {
					sabean.setSubcoExtnEdate(String.valueOf(result[9]).substring(0, 10));
				}
				
				subcodatewithAtos = String.valueOf(result[10]);
				if (subcodatewithAtos.equalsIgnoreCase("null")) {
					subcodatewithAtos = "";
					sabean.setSubcoSdateWithAtos(subcodatewithAtos);
				} else {
					sabean.setSubcoSdateWithAtos(String.valueOf(result[10]).substring(0, 10));
				}
				
				modelAndView.addObject("userId", user.getUserID());
				modelAndView.addObject("usrRole", userRole.trim());
				sabean.setUsrRole(userRole.trim());
				
				//modelAndView.addObject("selectedRole", sabean.getUsrRole());
				modelAndView.addObject("sabean", sabean);
				listsubcoApprovalBean.add(sabean);

			}

			SaveApprovalDataBean approvalDataBean = new SaveApprovalDataBean();
			approvalDataBean.setListsubcoApprovalBean(listsubcoApprovalBean);
			modelAndView.addObject("formBean", approvalDataBean);
			
			String action = request.getParameter("action");
			if ("Subco Approval".equalsIgnoreCase(action)) {
				// Invoke FirstServlet's job here.
				modelAndView.setViewName("subconApproval");
			}else if ("Subcon Onhold".equalsIgnoreCase(action)) {
				// Invoke SecondServlet's job here.
				modelAndView.setViewName("subconOnhold");
			}else if ("Subco Rejected".equalsIgnoreCase(action)) {
				// Invoke SecondServlet's job here.
				modelAndView.setViewName("subconRejection");
			} else if ("Subcon Extension".equalsIgnoreCase(action)) {
				// Invoke SecondServlet's job here.
				modelAndView.setViewName("subconExtension");
			}
			else if ("Approv Onhold".equalsIgnoreCase(action)) {
				// Invoke SecondServlet's job here.
				modelAndView.setViewName("approveOnhold");
			}
			
		} catch (Exception e) {

			log.error("Error:"+e.getMessage());
		}
		return modelAndView;
	}

	/**
	 * 
	 * @param formBean
	 * @param redirectAttribute
	 * @param request
	 * @param model
	 * @param bindingResult
	 * @param userBean
	 * @return
	 */
	@RequestMapping(value = { "/rmg/subconSave" },
			method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView subconSave(
			@ModelAttribute("formBean") SaveApprovalDataBean formBean,
			final RedirectAttributes redirectAttribute,
			HttpServletRequest request, ModelMap model,
			BindingResult bindingResult, @ModelAttribute UserBean userBean) {
		//Session session = null;
		ModelAndView modelAndView = null;
		try {
			modelAndView = new ModelAndView();

			String action = request.getParameter("action");
			String txtBoxIDs = request.getParameter("txtBoxIDs");
			List<String> listofinputtext = Arrays.asList(txtBoxIDs.split(","));
			String demandStatusId="";
			SubcoApprovalTransLog subconApprovaltranlog = null;
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			
			UserBean user = (UserBean) request.getSession().getAttribute("user");
			//String role1 = getUserRole(user);
			String role1 = user.getUserWorkflowRole();
			if(role1!=null && "PRM".equalsIgnoreCase(role1.trim())) {
				demandStatusId = "102";
			}
			if(role1!=null && "GPRM".equalsIgnoreCase(role1.trim()))	{
				demandStatusId = "103";
			}
			if(role1!=null && "OH".equalsIgnoreCase(role1.trim()))	{
				demandStatusId = "104";
			}
			///////////////// user bean////////////////////////
			userBean = (UserBean) request.getSession().getAttribute("user");
			if(userBean!=null) {
			List<Object[]> listTransactionLogBean = rmgOperationServices.getUsrData(userBean.getUserID());
				for (Object[] result : listTransactionLogBean) {
					
					userBean.setUserFName(String.valueOf(result[0]));;
					userBean.setUserLName(String.valueOf(result[1]));
					//userBean.setUserWorkflowRole(String.valueOf(result[2]));
					userBean.setUpdatedBy(String.valueOf(result[3]));
	
				}
			}
			
			if ("Approve Subcon".equalsIgnoreCase(action)) {
				if (formBean != null && formBean.getListsubcoApprovalBean() != null) {
					int dasIDOrderNo = 0;
					String value1 = "";
					int remarkOrderNo = 0;
					String subConDasId = "";
					//session = sessionFactory1.openSession();
					for (SubconApprovalBean be : formBean.getListsubcoApprovalBean()) {
						subconApprovaltranlog = new SubcoApprovalTransLog();
						value1 = "";
						remarkOrderNo = 0;
						for (String value : listofinputtext) {
							if (dasIDOrderNo == remarkOrderNo) {
								
								subConDasId = "";
								try {
									/*SubcoApprovalTransLog log1 =(SubcoApprovalTransLog) 
											session.get(SubcoApprovalTransLog.class , be.getSubcoDemandId());
									subConDasId= String.valueOf(log1.getSubcoDemandId());*/
									subConDasId= rmgOperationServices.getSubcoDemandID(be.getSubcoDemandId());

								} catch (Exception e) {
									log.error("Error:"+e.getMessage());
								}
								if ( !subConDasId.isEmpty() ) {
									// Skip Strings starting with letter b.
									value1 = value.toString();
	
									if(demandStatusId!=null) {
										subconApprovaltranlog.setApproval("Approved");
										subconApprovaltranlog.setApprRej_date(new Date(dateFormat.format(date)));
										subconApprovaltranlog.setApprRej_DASID(userBean.getUserID());
										subconApprovaltranlog.setApprRej_FName(userBean.getUserFName());
										subconApprovaltranlog.setApprRej_LName(userBean.getUserLName());
										subconApprovaltranlog.setApprRej_Role(userBean.getUserWorkflowRole());
										rmgOperationServices.updateapprRemark(be.getSubcoDemandId(),
												value1,demandStatusId,userBean.getUserID(),subconApprovaltranlog);
									} else	{
										rmgOperationServices.updateapprRemark(be.getSubcoDemandId(), value1);
									}
								}
								else{
									subconApprovaltranlog.setSubcoDemandId(be.getSubcoDemandId());
									subconApprovaltranlog.setSubcoDAS_ID("");
									subconApprovaltranlog.setApproval("Approved");
									subconApprovaltranlog.setApprRej_date(new Date(dateFormat.format(date)));
									subconApprovaltranlog.setApprRej_DASID(userBean.getUserID());
									subconApprovaltranlog.setApprRej_FName(userBean.getUserFName());
									subconApprovaltranlog.setApprRej_LName(userBean.getUserLName());
									subconApprovaltranlog.setApprRej_Remark(value);
									subconApprovaltranlog.setApprRej_Role(userBean.getUserWorkflowRole());
									subconApprovaltranlog.setCreatedBy(userBean.getUserID());
									subconApprovaltranlog.setCreatedDate(new Date(dateFormat.format(date)));
									if(demandStatusId!=null) {
										rmgOperationServices.saveApprovalOrRejection(subconApprovaltranlog,
												demandStatusId,userBean.getUserID());
									} else{
										rmgOperationServices.saveApprovalOrRejection(subconApprovaltranlog);
									}
								}
							}
							remarkOrderNo++;
						}
						dasIDOrderNo++;
					}
				} else {
					log.warn("No data Saved...");
				}
				String serverName;

				if(request.getServerName().equalsIgnoreCase("localhost"))
				{
					serverName = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

				}
				else{
					serverName = "https://" + request.getServerName();
				}
				return new ModelAndView("redirect:"+serverName+"/RmgApplication/rmg/home");
				//return new ModelAndView("redirect:/rmg/home");
			}
			else if ("Close".equalsIgnoreCase(action)) {
				//return new ModelAndView("redirect:/rmg/home");
				String serverName;

				if(request.getServerName().equalsIgnoreCase("localhost"))
				{
					serverName = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

				}
				else{
					serverName = "https://" + request.getServerName();
				}
				return new ModelAndView("redirect:"+serverName+"/RmgApplication/rmg/home");
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
		} 
		/*finally{
			if(session!=null && session.isOpen()) {
				session.close();
			}
		}*/
		return modelAndView;
	}
	
	/**
	 * 
	 * @param formBean
	 * @param redirectAttribute
	 * @param request
	 * @param model
	 * @param bindingResult
	 * @param userBean
	 * @return
	 */
	@RequestMapping(value = { "/rmg/approveOnHold" },
			method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView approveOnHold(
			@ModelAttribute("formBean") SaveApprovalDataBean formBean,
			final RedirectAttributes redirectAttribute,
			HttpServletRequest request, ModelMap model,
			BindingResult bindingResult, @ModelAttribute UserBean userBean) {
		//Session session = null;
		ModelAndView modelAndView = null;
		try {
			modelAndView = new ModelAndView();

			String action = request.getParameter("action");
			String txtBoxIDs = request.getParameter("txtBoxIDs");
			List<String> listofinputtext = Arrays.asList(txtBoxIDs.split(","));
			String demandStatusId="";
			SubcoApprovalTransLog subconApprovaltranlog = null;
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			
			UserBean user = (UserBean) request.getSession().getAttribute("user");
			//String role1 = getUserRole(user);
			String role1 = user.getUserWorkflowRole();
			if(role1!=null && "PM".equalsIgnoreCase(role1.trim())) {
				demandStatusId = "101";
			}
			/*if(role1!=null && "GPRM".equalsIgnoreCase(role1.trim()))	{
				demandStatusId = "103";
			}
			if(role1!=null && "OH".equalsIgnoreCase(role1.trim()))	{
				demandStatusId = "104";
			}*/
			///////////////// user bean////////////////////////
			userBean = (UserBean) request.getSession().getAttribute("user");
			if(userBean!=null) {
			List<Object[]> listTransactionLogBean = rmgOperationServices.getUsrData(userBean.getUserID());
				for (Object[] result : listTransactionLogBean) {
					
					userBean.setUserFName(String.valueOf(result[0]));;
					userBean.setUserLName(String.valueOf(result[1]));
					//userBean.setUserWorkflowRole(String.valueOf(result[2]));
					userBean.setUpdatedBy(String.valueOf(result[3]));
	
				}
			}
			
			if ("Approve OnHold".equalsIgnoreCase(action)) {
				if (formBean != null && formBean.getListsubcoApprovalBean() != null) {
					int dasIDOrderNo = 0;
					String value1 = "";
					int remarkOrderNo = 0;
					String subConDasId = "";
					//session = sessionFactory1.openSession();
					for (SubconApprovalBean be : formBean.getListsubcoApprovalBean()) {
						subconApprovaltranlog = new SubcoApprovalTransLog();
						value1 = "";
						remarkOrderNo = 0;
						for (String value : listofinputtext) {
							if (dasIDOrderNo == remarkOrderNo) {
								
								subConDasId = "";
								try {
									/*SubcoApprovalTransLog log1 =(SubcoApprovalTransLog) 
											session.get(SubcoApprovalTransLog.class , be.getSubcoDemandId());
									subConDasId= String.valueOf(log1.getSubcoDemandId());*/
									subConDasId= rmgOperationServices.getSubcoDemandID(be.getSubcoDemandId());

								} catch (Exception e) {
									log.error("Error:"+e.getMessage());
								}
								if ( !subConDasId.isEmpty() ) {
									// Skip Strings starting with letter b.
									value1 = value.toString();
	
									if(demandStatusId!=null) {
										subconApprovaltranlog.setApproval("New");
										subconApprovaltranlog.setApprRej_date(new Date(dateFormat.format(date)));
										subconApprovaltranlog.setApprRej_DASID(userBean.getUserID());
										subconApprovaltranlog.setApprRej_FName(userBean.getUserFName());
										subconApprovaltranlog.setApprRej_LName(userBean.getUserLName());
										subconApprovaltranlog.setApprRej_Role(userBean.getUserWorkflowRole());
										rmgOperationServices.updateapprRemark(be.getSubcoDemandId(),
												value1,demandStatusId,userBean.getUserID(),subconApprovaltranlog);
									} else	{
										rmgOperationServices.updateapprRemark(be.getSubcoDemandId(), value1);
									}
								}
								else{
									subconApprovaltranlog.setSubcoDemandId(be.getSubcoDemandId());
									subconApprovaltranlog.setSubcoDAS_ID("");
									subconApprovaltranlog.setApproval("New");
									subconApprovaltranlog.setApprRej_date(new Date(dateFormat.format(date)));
									subconApprovaltranlog.setApprRej_DASID(userBean.getUserID());
									subconApprovaltranlog.setApprRej_FName(userBean.getUserFName());
									subconApprovaltranlog.setApprRej_LName(userBean.getUserLName());
									subconApprovaltranlog.setApprRej_Remark(value);
									subconApprovaltranlog.setApprRej_Role(userBean.getUserWorkflowRole());
									subconApprovaltranlog.setCreatedBy(userBean.getUserID());
									subconApprovaltranlog.setCreatedDate(new Date(dateFormat.format(date)));
									if(demandStatusId!=null) {
										rmgOperationServices.saveApprovalOrRejection(subconApprovaltranlog,
												demandStatusId,userBean.getUserID());
									} else{
										rmgOperationServices.saveApprovalOrRejection(subconApprovaltranlog);
									}
								}
							}
							remarkOrderNo++;
						}
						dasIDOrderNo++;
					}
				} else {
					log.warn("No data Saved...");
				}
				String serverName;

				if(request.getServerName().equalsIgnoreCase("localhost"))
				{
					serverName = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

				}
				else{
					serverName = "https://" + request.getServerName();
				}
				return new ModelAndView("redirect:"+serverName+"/RmgApplication/rmg/home");
				//return new ModelAndView("redirect:/rmg/home");
			}
			else if ("Close".equalsIgnoreCase(action)) {
				//return new ModelAndView("redirect:/rmg/home");
				String serverName;

				if(request.getServerName().equalsIgnoreCase("localhost"))
				{
					serverName = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

				}
				else{
					serverName = "https://" + request.getServerName();
				}
				return new ModelAndView("redirect:"+serverName+"/RmgApplication/rmg/home");
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
		} 
		/*finally{
			if(session!=null && session.isOpen()) {
				session.close();
			}
		}*/
		return modelAndView;
	}	

	/**
	 * 
	 * @param formBean
	 * @param redirectAttribute
	 * @param request
	 * @param userBean
	 * @return
	 */
	@RequestMapping(value = { "/rmg/subconOnhold" },
			method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView subconOnhold(
			@ModelAttribute("formBean") SaveApprovalDataBean formBean,
			final RedirectAttributes redirectAttribute,
			HttpServletRequest request, ModelMap model,
			BindingResult bindingResult, @ModelAttribute UserBean userBean) {
		//Session session = null;
		
		//=============================================
	
		/*SubconDetails readSuconDetails = null;
		String demandID = "";*/
		
		//=====================================
		
		ModelAndView modelAndView = null;
		try {
			modelAndView = new ModelAndView();

			String action = request.getParameter("action");
			String txtBoxIDs = request.getParameter("txtBoxIDs");
			List<String> listofinputtext = Arrays.asList(txtBoxIDs.split(","));
			String demandStatusId="";
			SubcoApprovalTransLog subconApprovaltranlog = null;
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			
			UserBean user = (UserBean) request.getSession().getAttribute("user");
			//String role1 = getUserRole(user);
			String role1 = user.getUserWorkflowRole();
			if(role1!=null && "PRM".equalsIgnoreCase(role1.trim())) {
				demandStatusId = "115";
			}
			if(role1!=null && "GPRM".equalsIgnoreCase(role1.trim()))	{
				demandStatusId = "116";
			}
			if(role1!=null && "OH".equalsIgnoreCase(role1.trim()))	{
				demandStatusId = "117";
			}
			///////////////// user bean////////////////////////
			userBean = (UserBean) request.getSession().getAttribute("user");
			if(userBean!=null) {
			List<Object[]> listTransactionLogBean = rmgOperationServices.getUsrData(userBean.getUserID());
				for (Object[] result : listTransactionLogBean) {
					
					userBean.setUserFName(String.valueOf(result[0]));;
					userBean.setUserLName(String.valueOf(result[1]));
					//userBean.setUserWorkflowRole(String.valueOf(result[2]));
					userBean.setUpdatedBy(String.valueOf(result[3]));
	
				}
			}
			
			if ("Onhold Subcon".equalsIgnoreCase(action)) {
				
				//======================
				
				
				//======================
				
				
				if (formBean != null && formBean.getListsubcoApprovalBean() != null) {
					int dasIDOrderNo = 0;
					String value1 = "";
					int remarkOrderNo = 0;
					String subConDasId = "";
					//session = sessionFactory1.openSession();
					for (SubconApprovalBean be : formBean.getListsubcoApprovalBean()) {
						subconApprovaltranlog = new SubcoApprovalTransLog();
						value1 = "";
						remarkOrderNo = 0;
						for (String value : listofinputtext) {
							if (dasIDOrderNo == remarkOrderNo) {
								
								subConDasId = "";
								try {
									/*SubcoApprovalTransLog log1 =(SubcoApprovalTransLog) 
											session.get(SubcoApprovalTransLog.class , be.getSubcoDemandId());
									subConDasId= String.valueOf(log1.getSubcoDemandId());*/
									subConDasId= rmgOperationServices.getSubcoDemandID(be.getSubcoDemandId());

								} catch (Exception e) {
									log.error("Error:"+e.getMessage());
								}
								if ( !subConDasId.isEmpty() ) {
									// Skip Strings starting with letter b.
									value1 = value.toString();
	
									if(demandStatusId!=null) {
										subconApprovaltranlog.setApproval("On Hold");
										subconApprovaltranlog.setApprRej_date(new Date(dateFormat.format(date)));
										subconApprovaltranlog.setApprRej_DASID(userBean.getUserID());
										subconApprovaltranlog.setApprRej_FName(userBean.getUserFName());
										subconApprovaltranlog.setApprRej_LName(userBean.getUserLName());
										subconApprovaltranlog.setApprRej_Role(userBean.getUserWorkflowRole());
										rmgOperationServices.updateapprRemark(be.getSubcoDemandId(),
												value1,demandStatusId,userBean.getUserID(),subconApprovaltranlog);
									} else	{
										rmgOperationServices.updateapprRemark(be.getSubcoDemandId(), value1);
									}
								}
								else{
									subconApprovaltranlog.setSubcoDemandId(be.getSubcoDemandId());
									subconApprovaltranlog.setSubcoDAS_ID("");
									subconApprovaltranlog.setApproval("On Hold");
									subconApprovaltranlog.setApprRej_date(new Date(dateFormat.format(date)));
									subconApprovaltranlog.setApprRej_DASID(userBean.getUserID());
									subconApprovaltranlog.setApprRej_FName(userBean.getUserFName());
									subconApprovaltranlog.setApprRej_LName(userBean.getUserLName());
									subconApprovaltranlog.setApprRej_Remark(value);
									subconApprovaltranlog.setApprRej_Role(userBean.getUserWorkflowRole());
									subconApprovaltranlog.setCreatedBy(userBean.getUserID());
									subconApprovaltranlog.setCreatedDate(new Date(dateFormat.format(date)));
									if(demandStatusId!=null) {
										rmgOperationServices.saveApprovalOrRejection(subconApprovaltranlog,
												demandStatusId,userBean.getUserID());
									} else{
										rmgOperationServices.saveApprovalOrRejection(subconApprovaltranlog);
									}
								}
							}
							remarkOrderNo++;
						}
						dasIDOrderNo++;
					}
				} else {
					log.warn("No data Saved...");
				}
				String serverName;

				if(request.getServerName().equalsIgnoreCase("localhost"))
				{
					serverName = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

				}
				else{
					serverName = "https://" + request.getServerName();
				}
				return new ModelAndView("redirect:"+serverName+"/RmgApplication/rmg/home");
				//return new ModelAndView("redirect:/rmg/home");
			}
			else if ("Close".equalsIgnoreCase(action)) {
				//return new ModelAndView("redirect:/rmg/home");
				String serverName;

				if(request.getServerName().equalsIgnoreCase("localhost"))
				{
					serverName = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

				}
				else{
					serverName = "https://" + request.getServerName();
				}
				return new ModelAndView("redirect:"+serverName+"/RmgApplication/rmg/home");
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
		} 
		/*finally{
			if(session!=null && session.isOpen()) {
				session.close();
			}
		}*/
		return modelAndView;
	}

	
	
	/**
	 * 
	 * @param formBean
	 * @param redirectAttribute
	 * @param request
	 * @param userBean
	 * @return
	 */
	@RequestMapping(value = { "/rmg/subconReject" }, 
			method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView subconReject(
			@ModelAttribute("formBean") SaveApprovalDataBean formBean,final RedirectAttributes redirectAttribute,
			HttpServletRequest request, @ModelAttribute UserBean userBean) {
		//Session session =null;
		ModelAndView modelAndView = null;
		try {
			// Set Manger Bean
			modelAndView = new ModelAndView();
			String action = request.getParameter("action");
			String txtBoxIDs = request.getParameter("txtBoxIDs");
			List<String> listofinputtext = Arrays.asList(txtBoxIDs.split(","));

			String demandStatusId="";
			SubcoApprovalTransLog subconApprovaltranlog =null;
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			
			SubcoApprovalTransLog log1=null;
			
			userBean = (UserBean) request.getSession().getAttribute("user");
			List<Object[]> listTransactionLogBean = rmgOperationServices
					.getUsrData(userBean.getUserID());
			for (Object[] result : listTransactionLogBean) {
				userBean.setUserFName(String.valueOf(result[0]));
				userBean.setUserLName(String.valueOf(result[1]));
				//userBean.setUserWorkflowRole(String.valueOf(result[2]));
				userBean.setUpdatedBy(String.valueOf(result[3]));
			}
			UserBean user = (UserBean) request.getSession().getAttribute("user");
			//String role1 = getUserRole(user);
			String role1 = user.getUserWorkflowRole();
			if(role1!=null && "PRM".equalsIgnoreCase(role1.trim())){
				demandStatusId = "105";
			}
			if(role1!=null && "GPRM".equalsIgnoreCase(role1.trim())){
				demandStatusId = "106";
			}
			if(role1!=null && "OH".equalsIgnoreCase(role1.trim()))	{
				demandStatusId = "107";
			}
			if ("Reject Subcon".equalsIgnoreCase(action)) {
				// Invoke FirstServlet's job here.
				if (formBean != null && formBean.getListsubcoApprovalBean() != null) {

					int dasIDOrderNo = 0;
					String value1 = "";
					int remartOrderNo = 0;
					String subConDasId = "";
					for (SubconApprovalBean be : formBean.getListsubcoApprovalBean()) {
						subconApprovaltranlog = new SubcoApprovalTransLog();
						value1 = "";
						remartOrderNo = 0;
						for (String value : listofinputtext) {
							if (dasIDOrderNo == remartOrderNo) {
								subConDasId = "";
								try {
									/*log1 =(SubcoApprovalTransLog) session.get(SubcoApprovalTransLog.class , 
											be.getSubcoDemandId());
									subConDasId= String.valueOf(log1.getSubcoDemandId());*/
									subConDasId= rmgOperationServices.getSubcoDemandID(be.getSubcoDemandId());

								} catch (Exception e) {
									log.error("SubconApproval Transaction Log error "+ e.getMessage());
								}
								if ( !subConDasId.isEmpty() ) {
									value1 = value.toString();
									// remarks
									if(demandStatusId!=null){
										subconApprovaltranlog.setApproval("Rejected");
										subconApprovaltranlog.setApprRej_date(new Date(dateFormat.format(date)));
										subconApprovaltranlog.setApprRej_DASID(userBean.getUserID());
										subconApprovaltranlog.setApprRej_FName(userBean.getUserFName());
										subconApprovaltranlog.setApprRej_LName(userBean.getUserLName());
										subconApprovaltranlog.setApprRej_Role(userBean.getUserWorkflowRole());
										rmgOperationServices.updateapprRemark(be.getSubcoDemandId(), 
												value1,demandStatusId,userBean.getUserID(),subconApprovaltranlog);
									}
									else {
										rmgOperationServices.updateapprRemark(be.getSubcoDemandId(), value1);
									}
								}
								else{
									log.debug("currentSubConReject sub co das id else = "
											+ be.getSubcoDemandId()
											+ " listofinputtext = " + value);
									
									subconApprovaltranlog.setSubcoDemandId(be.getSubcoDemandId());
									subconApprovaltranlog.setSubcoDAS_ID("");
		
									subconApprovaltranlog.setApproval("Rejected");
									subconApprovaltranlog.setApprRej_date(new Date(dateFormat.format(date)));
									subconApprovaltranlog.setApprRej_DASID(userBean.getUserID());
									subconApprovaltranlog.setApprRej_FName(userBean.getUserFName());
									subconApprovaltranlog.setApprRej_LName(userBean.getUserLName());
									subconApprovaltranlog.setApprRej_Remark(value);
									subconApprovaltranlog.setApprRej_Role(userBean.getUserWorkflowRole());
									subconApprovaltranlog.setCreatedBy(userBean.getUserID());
									subconApprovaltranlog.setCreatedDate(new Date(dateFormat.format(date)));
									
									if(demandStatusId!=null){
										rmgOperationServices.saveApprovalOrRejection(subconApprovaltranlog,
												demandStatusId,userBean.getUserID());
									}
									else{
										rmgOperationServices.saveApprovalOrRejection(subconApprovaltranlog);
									}
								}
							}
							remartOrderNo++;
						}
						dasIDOrderNo++;
					}
				} else {
					log.info("No data Saved...");
				}
				String serverName;

				if(request.getServerName().equalsIgnoreCase("localhost"))
				{
					serverName = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

				}
				else{
					serverName = "https://" + request.getServerName();
				}
				return new ModelAndView("redirect:"+serverName+"/RmgApplication/rmg/home");
				//return new ModelAndView("redirect:/rmg/home");
			} else if ("Close".equalsIgnoreCase(action)) {
				// Invoke SecondServlet's job here.
				//return new ModelAndView("redirect:/rmg/home");
				String serverName;

				if(request.getServerName().equalsIgnoreCase("localhost"))
				{
					serverName = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

				}
				else{
					serverName = "https://" + request.getServerName();
				}
				return new ModelAndView("redirect:"+serverName+"/RmgApplication/rmg/home");
			}

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		/*finally{
			if(session!=null && session.isOpen()) {
				session.close();
			}
		}*/
		return modelAndView;
	}

	/**
	 * 
	 * @param formBean
	 * @param redirectAttribute
	 * @param request
	 * @param userBean
	 * @return
	 */
	@RequestMapping(value = { "/rmg/subconExtension" }, 
			method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView subconExtension(
			@ModelAttribute("formBean") SaveApprovalDataBean formBean,
			final RedirectAttributes redirectAttribute,
			HttpServletRequest request, @ModelAttribute UserBean userBean) {

		ModelAndView modelAndView = null;
		//Session session = sessionFactory1.openSession();
		try {
			modelAndView = new ModelAndView();

			String action = request.getParameter("action");

			String txtBoxIDs = request.getParameter("txtBoxIDs");
			List<String> listofinputtext = Arrays.asList(txtBoxIDs.split(","));
			String dateEnd = request.getParameter("endDates");
			List<String> listofinputtext1 = Arrays.asList(dateEnd.split(","));
			String demandStatusId=null;
			SubcoApprovalTransLog subconApprovaltranlog=null;
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			
			
			userBean = (UserBean) request.getSession().getAttribute("user");
			List<Object[]> listTransactionLogBean = rmgOperationServices
					.getUsrData(userBean.getUserID());

			for (Object[] result : listTransactionLogBean) {
				userBean.setUserFName(String.valueOf(result[0]));
				userBean.setUserLName(String.valueOf(result[1]));
				//userBean.setUserWorkflowRole(String.valueOf(result[2]));
				userBean.setUpdatedBy(String.valueOf(result[3]));
			}
			
			UserBean user = (UserBean) request.getSession().getAttribute("user");
			//String role1 = getUserRole(user);
			String role1 = user.getUserWorkflowRole();
			if ("Extend Subcon".equalsIgnoreCase(action)) {
				if (formBean != null
						&& formBean.getListsubcoApprovalBean() != null) {
					if(role1!=null && "PRM".equalsIgnoreCase(role1.trim())){
						demandStatusId = "109";
					}
					if(role1!=null && "GPRM".equalsIgnoreCase(role1.trim())){
						demandStatusId = "110";
					}
					if(role1!=null && "OH".equalsIgnoreCase(role1.trim()))	{
						demandStatusId = "111";
					}
					if(role1!=null && "PM".equalsIgnoreCase(role1.trim()))  {                        
		                   demandStatusId = "108";
		             }
					int dasIDOrderNo = 0;
					String subConDasId = "";
					String value1 = "";
					int remartOrderNo = 0;
					for (SubconApprovalBean be : formBean.getListsubcoApprovalBean()) {
						subconApprovaltranlog = new SubcoApprovalTransLog();
						value1 = "";
						remartOrderNo = 0;
						for (String value : listofinputtext) {
							if (dasIDOrderNo == remartOrderNo) {
								
								subConDasId = "";
								try {
									/*SubcoApprovalTransLog log1 =(SubcoApprovalTransLog) 
											session.get(SubcoApprovalTransLog.class , be.getSubcoDemandId());
									subConDasId= String.valueOf(log1.getSubcoDemandId());*/
									subConDasId= rmgOperationServices.getSubcoDemandID(be.getSubcoDemandId());

								} catch (Exception e) {
									log.error("SubconApproval Transaction Log error "+ e.getMessage());
								}
								if ( !subConDasId.isEmpty() ) {
									value1 = value.toString();
	
									if(demandStatusId!=null)
									{
										subconApprovaltranlog.setApproval("Extension");
										subconApprovaltranlog.setApprRej_date(new Date(dateFormat.format(date)));
										subconApprovaltranlog.setApprRej_DASID(userBean.getUserID());
										subconApprovaltranlog.setApprRej_FName(userBean.getUserFName());
										subconApprovaltranlog.setApprRej_LName(userBean.getUserLName());
										subconApprovaltranlog.setApprRej_Role(userBean.getUserWorkflowRole());
										rmgOperationServices.updateextnRemark(be.getSubcoDemandId(), value1,
												demandStatusId,userBean.getUserID(),subconApprovaltranlog);
									} else {
										rmgOperationServices.updateextnRemark(be.getSubcoDemandId(), value1);
									}
								} else {
									    subconApprovaltranlog.setSubcoDemandId(be.getSubcoDemandId());
									    subconApprovaltranlog.setSubcoDAS_ID("");
										subconApprovaltranlog.setApproval("Extension");
										subconApprovaltranlog.setApprRej_date(new Date(dateFormat.format(date)));
										subconApprovaltranlog.setApprRej_DASID(userBean.getUserID());
										subconApprovaltranlog.setApprRej_FName(userBean.getUserFName());
										subconApprovaltranlog.setApprRej_LName(userBean.getUserLName());
										subconApprovaltranlog.setApprRej_Remark(value);
										subconApprovaltranlog.setApprRej_Role(userBean.getUserWorkflowRole());
										subconApprovaltranlog.setCreatedBy(userBean.getUserID());
										subconApprovaltranlog.setCreatedDate(new Date(dateFormat.format(date)));
									
										if(demandStatusId!=null)	{
											rmgOperationServices.saveApprovalOrRejection(subconApprovaltranlog,
													demandStatusId,userBean.getUserID());
										} else {
											rmgOperationServices.saveApprovalOrRejection(subconApprovaltranlog);
										}
									}
								}
							remartOrderNo++;
						}

						remartOrderNo = 0;
						Date date1=null; 
						Date staretdate1 = null;
						Date originalStartDate = null;
						String diffInDaysinString = null;
						long extensionEndDateinMiliSecond=0, originalStartDateinMiliSecond=0,
								diffInMiliSeconds=0,diffInDays=0;
						for (String dateEndvalue : listofinputtext1) {
							if (dasIDOrderNo == remartOrderNo) {
								date1=new SimpleDateFormat("yyyy-MM-dd").parse(dateEndvalue); 
								staretdate1=new SimpleDateFormat("yyyy-MM-dd").parse(be.getSubcoEDateWithAtos());
								originalStartDate=new SimpleDateFormat("yyyy-MM-dd").parse(
										be.getSubcoSdateWithAtos()); 
								extensionEndDateinMiliSecond = date1.getTime();
								originalStartDateinMiliSecond = originalStartDate.getTime();
								diffInMiliSeconds = extensionEndDateinMiliSecond - originalStartDateinMiliSecond;
								diffInDays = diffInMiliSeconds / (24 * 60 * 60 * 1000);
								diffInDaysinString = String.valueOf(diffInDays);
							
								if("OH".equalsIgnoreCase(role1.trim()))	{
									rmgOperationServices.updateExtnDate(be.getSubcoDemandId(), date1,staretdate1,
											role1,diffInDaysinString);
								}
								else{
									rmgOperationServices.updateExtnDate(be.getSubcoDemandId(), date1,staretdate1,
											diffInDaysinString);
								}
							}
							remartOrderNo++;
						}
						dasIDOrderNo++;
					}
				} else {
					log.error("No data Saved...");
				}

				String serverName;

				if(request.getServerName().equalsIgnoreCase("localhost"))
				{
					serverName = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

				}
				else{
					serverName = "https://" + request.getServerName();
				}
				return new ModelAndView("redirect:"+serverName+"/RmgApplication/rmg/home");
				//return new ModelAndView("redirect:/rmg/home");
			} 
			
			else if ("Extend Approve".equalsIgnoreCase(action)) {
				
				if (formBean != null && formBean.getListsubcoApprovalBean() != null) {

					int dasIDOrderNo = 0;
					
					if("PRM".equalsIgnoreCase(role1.trim())){
						demandStatusId = "109";
					}
					if("GPRM".equalsIgnoreCase(role1.trim())){
						demandStatusId = "110";
					}
					if("OH".equalsIgnoreCase(role1.trim()))	{
						demandStatusId = "111";
					}
					if("PM".equalsIgnoreCase(role1.trim()))  {                        
		                   demandStatusId = "108";
		             }
					for (SubconApprovalBean be : formBean
							.getListsubcoApprovalBean()) {

						subconApprovaltranlog = new SubcoApprovalTransLog();
						String value1 = "";
						int remartOrderNo = 0;
						for (String value : listofinputtext) {
							if (dasIDOrderNo == remartOrderNo) {
								//session = sessionFactory1.openSession();
								String subConDasId = "";
								try {
									subConDasId= rmgOperationServices.getSubcoDemandID(be.getSubcoDemandId());

								} catch (Exception e) {
									log.error("SubconApproval Transaction Log error "+ e.getMessage());
								}
								if ( !subConDasId.isEmpty() ) {
									// Skip Strings starting with letter b.
									value1 = value.toString();
	
									if(demandStatusId!=null)
									{
										subconApprovaltranlog.setApproval("ExApproved");
										subconApprovaltranlog.setApprRej_date(new Date(dateFormat.format(date)));
										subconApprovaltranlog.setApprRej_DASID(userBean.getUserID());
										subconApprovaltranlog.setApprRej_FName(userBean.getUserFName());
										subconApprovaltranlog.setApprRej_LName(userBean.getUserLName());
										subconApprovaltranlog.setApprRej_Role(userBean.getUserWorkflowRole());
										rmgOperationServices.updateextnRemark(be.getSubcoDemandId(), value1, 
												demandStatusId,userBean.getUserID(),subconApprovaltranlog);
									} else {
										rmgOperationServices.updateextnRemark(be.getSubcoDemandId(), value1);
									}
								}
								else{
								    subconApprovaltranlog.setSubcoDemandId(be.getSubcoDemandId());
								    subconApprovaltranlog.setSubcoDAS_ID("");
									subconApprovaltranlog.setApproval("ExApproved");
									subconApprovaltranlog.setApprRej_date(new Date(	dateFormat.format(date)));
									subconApprovaltranlog.setApprRej_DASID(userBean.getUserID());
									subconApprovaltranlog.setApprRej_FName(userBean.getUserFName());
									subconApprovaltranlog.setApprRej_LName(userBean.getUserLName());
									subconApprovaltranlog.setApprRej_Remark(value);
									subconApprovaltranlog.setApprRej_Role(userBean.getUserWorkflowRole());
									subconApprovaltranlog.setCreatedBy(userBean.getUserID());
									subconApprovaltranlog.setCreatedDate(new Date(dateFormat.format(date)));
									
									if(demandStatusId!=null){
										rmgOperationServices.saveApprovalOrRejection(subconApprovaltranlog,
												demandStatusId,userBean.getUserID());
									}else{
										rmgOperationServices.saveApprovalOrRejection(subconApprovaltranlog);
									}
								}
							}
							remartOrderNo++;
						}

						remartOrderNo = 0;
						String dateEnd1 = "";
						Date date1=null; Date staretdate1=null; Date originalStartDate=null;
						for (String dateEndvalue : listofinputtext1) {
							if (dasIDOrderNo == remartOrderNo) {
								
								date1=new SimpleDateFormat("yyyy-MM-dd").parse(dateEndvalue); 
								staretdate1=new SimpleDateFormat("yyyy-MM-dd").
										parse(be.getSubcoEDateWithAtos()); 
								originalStartDate=new SimpleDateFormat("yyyy-MM-dd").
										parse(be.getSubcoSdateWithAtos()); 
								long extensionEndDateinMiliSecond = date1.getTime();
								long originalStartDateinMiliSecond = originalStartDate.getTime();
								long diffInMiliSeconds = extensionEndDateinMiliSecond - originalStartDateinMiliSecond;
								long diffInDays = diffInMiliSeconds / (24 * 60 * 60 * 1000);
								String diffInDaysinString = String.valueOf(diffInDays);
								if("OH".equalsIgnoreCase(role1.trim()))	{
									rmgOperationServices.updateExtnDate(be.getSubcoDemandId(), 
											date1,staretdate1,role1,diffInDaysinString);
								}
								else{
									rmgOperationServices.updateExtnDate(be.getSubcoDemandId(), 
											date1,staretdate1,diffInDaysinString);
								}
							}
							remartOrderNo++;
						}
						dasIDOrderNo++;
					}
				} else {
					log.error("No data Saved...");
				}
				String serverName;

				if(request.getServerName().equalsIgnoreCase("localhost"))
				{
					serverName = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

				}
				else{
					serverName = "https://" + request.getServerName();
				}
				return new ModelAndView("redirect:"+serverName+"/RmgApplication/rmg/home");
			//	return new ModelAndView("redirect:/rmg/home");
			} 
			
			else if ("Extend Reject".equalsIgnoreCase(action)) {
				if (formBean != null
						&& formBean.getListsubcoApprovalBean() != null) {

					int dasIDOrderNo = 0;
					
					if("PRM".equalsIgnoreCase(role1.trim())){
						demandStatusId = "112";
					}
					if("GPRM".equalsIgnoreCase(role1.trim())){
						demandStatusId = "113";
					}
					if("OH".equalsIgnoreCase(role1.trim()))	{
						demandStatusId = "114";
					}
					for (SubconApprovalBean be : formBean.getListsubcoApprovalBean()) {

						subconApprovaltranlog = new SubcoApprovalTransLog();
						String value1 = "";
						int remartOrderNo = 0;
						for (String value : listofinputtext) {
							if (dasIDOrderNo == remartOrderNo) {
							//	session = sessionFactory1.openSession();
								String subConDasId = "";
								try {
									subConDasId= rmgOperationServices.getSubcoDemandID(be.getSubcoDemandId());

								} catch (Exception e) {
									log.error("SubconApproval Transaction Log error "+ e.getMessage());
								}
								if ( !subConDasId.isEmpty() ) {
									value1 = value.toString();
	
									if(demandStatusId!=null)
									{
										subconApprovaltranlog.setApproval("ExRejected");
										subconApprovaltranlog.setApprRej_date(new Date(dateFormat.format(date)));
										subconApprovaltranlog.setApprRej_DASID(userBean.getUserID());
										subconApprovaltranlog.setApprRej_FName(userBean.getUserFName());
										subconApprovaltranlog.setApprRej_LName(userBean.getUserLName());
										subconApprovaltranlog.setApprRej_Role(userBean.getUserWorkflowRole());
										rmgOperationServices.updateextnRemark(be.getSubcoDemandId(), value1, 
												demandStatusId,userBean.getUserID(),subconApprovaltranlog);
									} else {
										rmgOperationServices.updateextnRemark(be.getSubcoDemandId(), value1);
									}
								}
								else{
								    subconApprovaltranlog.setSubcoDemandId(be.getSubcoDemandId());
								    subconApprovaltranlog.setSubcoDAS_ID("");
		
									subconApprovaltranlog.setApproval("ExRejected");
									subconApprovaltranlog.setApprRej_date(new Date(dateFormat.format(date)));
									subconApprovaltranlog.setApprRej_DASID(userBean.getUserID());
									subconApprovaltranlog.setApprRej_FName(userBean.getUserFName());
									subconApprovaltranlog.setApprRej_LName(userBean.getUserLName());
									subconApprovaltranlog.setApprRej_Remark(value);
									subconApprovaltranlog.setApprRej_Role(userBean.getUserWorkflowRole());
									subconApprovaltranlog.setCreatedBy(userBean.getUserID());
									subconApprovaltranlog.setCreatedDate(new Date(
											dateFormat.format(date)));
									
									if(demandStatusId!=null){
										rmgOperationServices.saveApprovalOrRejection(subconApprovaltranlog,
												demandStatusId,userBean.getUserID());
									}else{
										rmgOperationServices.saveApprovalOrRejection(subconApprovaltranlog);
									}
								}
							}
							remartOrderNo++;
						}

						remartOrderNo = 0;
						String dateEnd1 = "";
						Date date1=null;
						for (String dateEndvalue : listofinputtext1) {
							if (dasIDOrderNo == remartOrderNo) {
								date1=new SimpleDateFormat("yyyy-MM-dd").parse(dateEndvalue); 
								Date staretdate1=new SimpleDateFormat("yyyy-MM-dd").
										parse(be.getSubcoEDateWithAtos());  
								Date originalStartDate=new SimpleDateFormat("yyyy-MM-dd").
										parse(be.getSubcoSdateWithAtos()); 
								long extensionEndDateinMiliSecond = date1.getTime();
								long originalStartDateinMiliSecond = originalStartDate.getTime();
								long diffInMiliSeconds = extensionEndDateinMiliSecond - originalStartDateinMiliSecond;
								long diffInDays = diffInMiliSeconds / (24 * 60 * 60 * 1000);
								String diffInDaysinString = String.valueOf(diffInDays);
								System.out.println("subcon duration -- " + diffInDaysinString);
								if("OH".equalsIgnoreCase(role1.trim()))	{
									rmgOperationServices.updateExtnDate(
											be.getSubcoDemandId(), date1,staretdate1,role1,diffInDaysinString);
								}else{
									rmgOperationServices.updateExtnDate(
											be.getSubcoDemandId(), date1,staretdate1,diffInDaysinString);
								}
							}
							remartOrderNo++;
						}
						dasIDOrderNo++;
					}
				} else {
					log.error("No data Saved...");
				}
				String serverName;

				if(request.getServerName().equalsIgnoreCase("localhost"))
				{
					serverName = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

				}
				else{
					serverName = "https://" + request.getServerName();
				}
				return new ModelAndView("redirect:"+serverName+"/RmgApplication/rmg/home");
				//return new ModelAndView("redirect:/rmg/home");
			} 
			
			else if ("Close".equalsIgnoreCase(action)) {
				// Invoke SecondServlet's job here.
				String serverName;

				if(request.getServerName().equalsIgnoreCase("localhost"))
				{
					serverName = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

				}
				else{
					serverName = "https://" + request.getServerName();
				}
				return new ModelAndView("redirect:"+serverName+"/RmgApplication/rmg/home");
				//return new ModelAndView("redirect:/rmg/home");
			}
			
			else if ("Extend OnHold".equalsIgnoreCase(action)) {
				if (formBean != null
						&& formBean.getListsubcoApprovalBean() != null) {

					int dasIDOrderNo = 0;
					
					if("PRM".equalsIgnoreCase(role1.trim())){
						demandStatusId = "118";
					}
					if("GPRM".equalsIgnoreCase(role1.trim())){
						demandStatusId = "119";
					}
					if("OH".equalsIgnoreCase(role1.trim()))	{
						demandStatusId = "120";
					}
					for (SubconApprovalBean be : formBean.getListsubcoApprovalBean()) {

						subconApprovaltranlog = new SubcoApprovalTransLog();
						String value1 = "";
						int remartOrderNo = 0;
						for (String value : listofinputtext) {
							if (dasIDOrderNo == remartOrderNo) {
							//	session = sessionFactory1.openSession();
								String subConDasId = "";
								try {
									subConDasId= rmgOperationServices.getSubcoDemandID(be.getSubcoDemandId());

								} catch (Exception e) {
									log.error("SubconApproval Transaction Log error "+ e.getMessage());
								}
								if ( !subConDasId.isEmpty() ) {
									value1 = value.toString();
	
									if(demandStatusId!=null)
									{
										subconApprovaltranlog.setApproval("ExOnhold");
										subconApprovaltranlog.setApprRej_date(new Date(dateFormat.format(date)));
										subconApprovaltranlog.setApprRej_DASID(userBean.getUserID());
										subconApprovaltranlog.setApprRej_FName(userBean.getUserFName());
										subconApprovaltranlog.setApprRej_LName(userBean.getUserLName());
										subconApprovaltranlog.setApprRej_Role(userBean.getUserWorkflowRole());
										rmgOperationServices.updateextnRemark(be.getSubcoDemandId(), value1, 
												demandStatusId,userBean.getUserID(),subconApprovaltranlog);
									} else {
										rmgOperationServices.updateextnRemark(be.getSubcoDemandId(), value1);
									}
								}
								else{
								    subconApprovaltranlog.setSubcoDemandId(be.getSubcoDemandId());
								    subconApprovaltranlog.setSubcoDAS_ID("");
		
									subconApprovaltranlog.setApproval("ExOnhold");
									subconApprovaltranlog.setApprRej_date(new Date(dateFormat.format(date)));
									subconApprovaltranlog.setApprRej_DASID(userBean.getUserID());
									subconApprovaltranlog.setApprRej_FName(userBean.getUserFName());
									subconApprovaltranlog.setApprRej_LName(userBean.getUserLName());
									subconApprovaltranlog.setApprRej_Remark(value);
									subconApprovaltranlog.setApprRej_Role(userBean.getUserWorkflowRole());
									subconApprovaltranlog.setCreatedBy(userBean.getUserID());
									subconApprovaltranlog.setCreatedDate(new Date(
											dateFormat.format(date)));
									
									if(demandStatusId!=null){
										rmgOperationServices.saveApprovalOrRejection(subconApprovaltranlog,
												demandStatusId,userBean.getUserID());
									}else{
										rmgOperationServices.saveApprovalOrRejection(subconApprovaltranlog);
									}
								}
							}
							remartOrderNo++;
						}

						remartOrderNo = 0;
						String dateEnd1 = "";
						Date date1=null;
						for (String dateEndvalue : listofinputtext1) {
							if (dasIDOrderNo == remartOrderNo) {
								date1=new SimpleDateFormat("yyyy-MM-dd").parse(dateEndvalue); 
								Date staretdate1=new SimpleDateFormat("yyyy-MM-dd").
										parse(be.getSubcoEDateWithAtos());  
								Date originalStartDate=new SimpleDateFormat("yyyy-MM-dd").
										parse(be.getSubcoSdateWithAtos()); 
								long extensionEndDateinMiliSecond = date1.getTime();
								long originalStartDateinMiliSecond = originalStartDate.getTime();
								long diffInMiliSeconds = extensionEndDateinMiliSecond - originalStartDateinMiliSecond;
								long diffInDays = diffInMiliSeconds / (24 * 60 * 60 * 1000);
								String diffInDaysinString = String.valueOf(diffInDays);
								System.out.println("subcon duration -- " + diffInDaysinString);
								if("OH".equalsIgnoreCase(role1.trim()))	{
									rmgOperationServices.updateExtnDate(
											be.getSubcoDemandId(), date1,staretdate1,role1,diffInDaysinString);
								}else{
									rmgOperationServices.updateExtnDate(
											be.getSubcoDemandId(), date1,staretdate1,diffInDaysinString);
								}
							}
							remartOrderNo++;
						}
						dasIDOrderNo++;
					}
				} else {
					log.error("No data Saved...");
				}
				String serverName;

				if(request.getServerName().equalsIgnoreCase("localhost"))
				{
					serverName = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

				}
				else{
					serverName = "https://" + request.getServerName();
				}
				return new ModelAndView("redirect:"+serverName+"/RmgApplication/rmg/home");
				//return new ModelAndView("redirect:/rmg/home");
			} 
			
			else if ("Close".equalsIgnoreCase(action)) {
				// Invoke SecondServlet's job here.
				String serverName;

				if(request.getServerName().equalsIgnoreCase("localhost"))
				{
					serverName = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

				}
				else{
					serverName = "https://" + request.getServerName();
				}
				return new ModelAndView("redirect:"+serverName+"/RmgApplication/rmg/home");
				//return new ModelAndView("redirect:/rmg/home");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		} /*finally{
			if(session!=null && session.isOpen()) {
				session.close();
			}
		}*/
		return modelAndView;
	}

	/**
	 * 
	 * @param subcoId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/rmg/subconHistory" },
			method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView subconHistory(@RequestParam(value="subcoId", required = false) List<Integer> subcoId,
			HttpServletRequest request) {
		SubconHistoryBean sabean = null;
		ModelAndView modelAndView = null;
		try {
			DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			List<Object[]> listTransactionLogBean = rmgOperationServices.getHistoryData(subcoId);
			List<SubconHistoryBean> listsubcoHistorylBean = new ArrayList<SubconHistoryBean>();
			String exnMark = "";
			for (Object[] result : listTransactionLogBean) {
				sabean = new SubconHistoryBean();
				sabean.setSubcoDemandId(Integer.valueOf((Integer) result[0]));
				sabean.setSubfname(String.valueOf(result[1]));
				sabean.setCountry(String.valueOf(result[2]));
				sabean.setPractice(String.valueOf(result[3]));
				sabean.setStatus(String.valueOf(result[4]));
				sabean.setApprrejdate(sdf.format((Date) result[5]));
				sabean.setGcm(String.valueOf(result[6]));
				sabean.setMargin(String.valueOf(result[7]));
				sabean.setAvgcost(String.valueOf(result[8]));
				exnMark = String.valueOf(result[9]);
				if (exnMark.equalsIgnoreCase("null")) {
					exnMark = "";
					sabean.setApprrejremark(exnMark);
				} else {
					sabean.setApprrejremark(String.valueOf(result[9]));
				}
				sabean.setApprrejdasid(String.valueOf(result[10]));
				sabean.setApprrejrole(String.valueOf(result[11]));
				sabean.setApprrejFname(String.valueOf(result[12]));
				sabean.setApprrejLname(String.valueOf(result[13]));
				listsubcoHistorylBean.add(sabean);
			}
			modelAndView = new ModelAndView();
			modelAndView.addObject("listsubcoHistorylBean",	listsubcoHistorylBean);
			UserBean user = (UserBean) request.getSession().getAttribute("user");
			//String role1 = getUserRole(user);
			String role1 = user.getUserWorkflowRole();
	        modelAndView.addObject("userId", user.getUserID());
			modelAndView.addObject("usrRole", role1.trim());
			modelAndView.setViewName("subconHistory");
			
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return modelAndView;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */

	@RequestMapping("/rmg/logout")
	public ModelAndView logout(HttpServletRequest request) {

		ModelAndView modelAndView = null;
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		if (user != null && user.getUserID() != null) {
			rmgOperationServices.updateActFlaginlogout(user.getUserID());
		}
		HttpSession newsession = request.getSession(false);
		if (newsession != null) {
			newsession.invalidate();
		}
		modelAndView = new ModelAndView();
		UserBean user1 = new UserBean();
		modelAndView.addObject("user1", user1);
		modelAndView.addObject("user", new User());
		modelAndView.setViewName("login");
		return modelAndView;
	} 
	
	/**
	 * 
	 * @param userBean
	 * @param result
	 * @param model
	 * @param request
	 * @param searchStr
	 * @return
	 */
	@RequestMapping(value = { "/rmg/saveAdminUserDetails" }, 
			method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView saveAdminUserDetails(@Valid @ModelAttribute("userBean")
	UserBean userBean,BindingResult result,Model model,HttpServletRequest request,
			@RequestParam("searchStr") String searchStr) {
		ModelAndView modelAndView = null;
		try {
			boolean isUserExist = false;
			modelAndView = new ModelAndView();
			if (result != null && result.hasErrors()) {
				log.error("saveAdminUserDetails ------------- result has errors ----------- "
						+ result.getFieldError());
				UserBean userBeanlogin = (UserBean) request.getSession()
						.getAttribute("user");
				log.info("saveAdminUserDetails user enter login = "
						+ userBeanlogin.getUserID());
				/*List<User> listOfremveBean = rmgOperationServices
						.getEditMasterDetails(userBeanlogin.getUserID());
				UserBean saveUserBean = new UserBean();
				for (User resulthas : listOfremveBean) {
					saveUserBean.setGbuId(resulthas.getGbuId());
				}*/
				modelAndView = new ModelAndView();
				List<CountryBean> countriesadmin = new ArrayList<CountryBean>();
				countriesadmin = rmgOperationServices.getCountries();
				modelAndView.addObject("countries", countriesadmin);
				modelAndView.setViewName("globalAdminDetails");
			} else {
				DateFormat dateFormat = new SimpleDateFormat(
						"yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				User user = new User();
				user.setUserID(userBean.getUserID());
				String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ0123456789+@";

				String pw = "";
				for (int i = 0; i < PASSWORD_LENGTH; i++) {
					int index = (int) (RANDOM.nextDouble() * letters.length());
					pw += letters.substring(index, index + 1);
				}

				log.info("password random = " + pw);

				user.setUserPwd(pw);
				// user.setUserPwd(userBean.getUserPwd());
				user.setUserWorkflowRole(userBean.getUserWorkflowRole());
				modelAndView.addObject("user", user);
				List<Object[]> listTransactionLogBean = rmgOperationServices
						.getGbuUsrData(userBean.getGbuId(),
								userBean.getCountryID());

				for (Object[] adminresult : listTransactionLogBean) {
					user.setGbuId(String.valueOf(adminresult[0]));
					user.setCountryID(String.valueOf(adminresult[1]));
				}
				System.out.println("sub practice "+userBean.getSubPracticeID());
				user.setgPracticeID(userBean.getgPracticeID());
				List<Object[]> counpracid = rmgOperationServices
						.getCountryPraDetails(user.getCountryID(),
								user.getgPracticeID(),userBean.getSubPracticeID());

				for (Object[] adminresult : counpracid) {
					user.setgPracticeID(String.valueOf(adminresult[0]));
					user.setSubPracticeID(String.valueOf(adminresult[1]));
				}

				String ouid = request.getParameter("searchStr");
				String ouName = rmgOperationServices.getOuName(ouid);
				user.setOuId(ouName);
				String from = "ARAS@atos.net";
				user.setEmaildID(userBean.getEmailID());
				UserBean userBeanlogin = (UserBean) request.getSession()
						.getAttribute("user");
				isUserExist = rmgOperationServices
						.checkUserExistWthRole(userBean);
				if (isUserExist) {
					model.addAttribute("msg", "User " + user.getUserID()
							+ " with this role is already exists in DB!");
				} else {
					
					
					String emailConf = "emailConfiguration.properties";
					Properties propFile = new Properties();

					try {
						InputStream inputStream = getClass().getClassLoader()
								.getResourceAsStream(emailConf);
						if (inputStream != null) {
							propFile.load(inputStream);
						} else {
							throw new FileNotFoundException("property file '"
									+ emailConf
									+ "' not found in the classpath");
						}

						String host = propFile.getProperty("smtp_host");
						String socket_port = propFile
								.getProperty("smtp_socketFactory_port");
						String smtp_port = propFile.getProperty("smtp_port");

						Properties props = new Properties();
						props.put("mail.smtp.host", host);
						props.put("mail.smtp.socketFactory.port", socket_port);
						props.put("mail.smtp.port", smtp_port);

						// Get the default Session object.
						javax.mail.Session session = javax.mail.Session
								.getDefaultInstance(props);

						// Create a default MimeMessage object.
						MimeMessage message = new MimeMessage(session);

						// Set From: header field of the header.
						message.setFrom(new InternetAddress(from));

						// Set To: header field of the header.
						message.addRecipient(Message.RecipientType.TO,
								new InternetAddress(user.getEmailID()));

						// Set Subject: header field
						message.setSubject("You have received mail from Subcon Team");
						// Send message
						String Uri;

		                if(request.getServerName().equalsIgnoreCase("localhost"))
		                {
		                 Uri = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		                }
		                else{
		                 Uri = "https://" + request.getServerName();
		                    }
						// Send the actual HTML message, as big as you like
		                
		                String encryptedValue = EncryptableUtilities.getInstance().getEncryptedValue(userBean.getUserID() + ";" + pw);
		                log.info("encryptedValue = " + encryptedValue);
		                encryptedValue = EncryptableUtilities.getInstance().encode(encryptedValue);
		                log.info("encryptedValue encoded = " + encryptedValue);
		                
						message.setContent(
								"<table cellspacing=10>"
										+ "<tr><td style='font-family: "
										+ "Verdana, Geneva, sans-serif' 'color:#176ba7' 'font-size: 16px'>"
										+ "<b>Welcome to Atos Resource Approval System</b>"
										+ "</td></tr>"
										+ "</table>"
										+ "<table style='border:1px solid #176ba7' cellspacing=15>"
										+ "<tr><td style='font-family: Verdana, Geneva, sans-serif' 'font-size: 12px'>"
										+ "<b>User ID : </b>"
										+ userBean.getUserID()
										+ "<br>"
										+ "<b>User Name : </b>"
										+ userBean.getUserFName()
										+ " "
										+ userBean.getUserLName()
										+ "<br>"
										
										+ "<b>Role : </b>"
										+ userBean.getUserWorkflowRole()
										+ "<br><br>"
										+ "Click below link to set password "
										+ "<br>"
										+ "<a href="
										+ Uri
										+ "/RmgApplication/rmg/resetPswd?id="
										+ encryptedValue
										+ ">Set Password</a>" + "</td></tr>"
										+ "</table>", "text/html");

						Transport.send(message);
						log.info("Sent message successfully....");
					} catch (MessagingException mex) {
						mex.printStackTrace();
					}
					user.setCreatedDate(new Date(dateFormat.format(date)));
					user.setCreatedBy(userBeanlogin.getUserID());
					user.setUserFName(userBean.getUserFName());
					user.setUserLName(userBean.getUserLName());
					user.setUserActFlag("0");
					

					rmgOperationServices.save(user);
					model.addAttribute("msg", "User added successfully!");
				}

				List<CountryBean> countriesadmin = new ArrayList<CountryBean>();
				countriesadmin = rmgOperationServices.getCountries();

				modelAndView.addObject("countries", countriesadmin);
				modelAndView.addObject("userBean", userBeanlogin);
				UserBean user1 = (UserBean) request.getSession().getAttribute(
						"user");
				// String role1 = getUserRole(user1);
				String role1 = user.getUserWorkflowRole();
				if (role1.equalsIgnoreCase("GBA")) {
					userBean.setGbuadmin("y");
				}
				modelAndView.addObject("userId", user1.getUserID());
				modelAndView.addObject("usrRole", user1.getUserWorkflowRole()
						.trim());
				getAllUserDetails(user, request, modelAndView);

			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return modelAndView;
	}
	
	/**
	 * 
	 * @param user
	 * @param request
	 * @param modelAndView
	 */
	private void getAllUserDetails(User user, HttpServletRequest request, ModelAndView modelAndView) {
		
		UserBean userBeanlogin = (UserBean) request.getSession().getAttribute("user");
		List<User> listOfUserBean = null ;
		listOfUserBean =	rmgOperationServices.getUserMasterDetails(userBeanlogin.getUserID());
		modelAndView.addObject("adminuserBean", listOfUserBean);
		modelAndView.addObject("userBeanlogin", userBeanlogin);
		modelAndView.setViewName("globalAdminDetails");
	}

	/**
	 * 
	 * @param userBean
	 * @param subcoId
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/rmg/edit" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView editAdminUserDetails(@ModelAttribute("userBean") 
	UserBean userBean,
	@RequestParam(value="subcoId", required = false) String subcoId,
	@RequestParam(value="role", required = false) String role,
	Model model,HttpServletRequest request) {
		ModelAndView modelAndView = null;
		try {
			List<User> listOfUserBean = rmgOperationServices.getEditMasterDetails(subcoId,role);
			modelAndView = new ModelAndView();
			List<CountryBean> countriesadmin = new ArrayList<CountryBean>();
			countriesadmin = rmgOperationServices.getCountries();
			modelAndView.addObject("countries", countriesadmin);
			UserBean editUserBean = new UserBean();
			List<CountryPractise> counpracid = null;
			String ouName = "";
			List<Country> counprac = null;
			for (User result : listOfUserBean) 	{
				editUserBean.setUserID(result.getUserID());
				editUserBean.setUserPwd(result.getUserPwd());
				editUserBean.setUserWorkflowRole(result.getUserWorkflowRole());
				counprac = rmgOperationServices.getGbuDetails(result.getGbuId(),
						result.getCountryID());
				if(counprac !=null) {
					for (Country counpracres : counprac) {
						editUserBean.setGbuId(counpracres.getGbuName());
						editUserBean.setCountryID(counpracres.getCountryName());
					}
				}
				ouName = rmgOperationServices.getOuNameforID(result.getOuId());
				editUserBean.setOuId(ouName);
				counpracid = rmgOperationServices.getCountryPraDetailsEdit(
						result.getCountryID(),result.getSubPracticeID(),result.getgPracticeID());
				
				for (CountryPractise counpracresid : counpracid) {
					editUserBean.setgPracticeID(counpracresid.getgPracticeName());					
					editUserBean.setSubPracticeID(counpracresid.getSubPractiseName());
				}
				modelAndView.addObject("selectedGBU", editUserBean.getGbuId());
				modelAndView.addObject("selectedCountry", editUserBean.getCountryID());
				modelAndView.addObject("selectedGlobalPractise", editUserBean.getgPracticeID());
				modelAndView.addObject("selectedGlobalSubPractise", editUserBean.getSubPracticeID());
				modelAndView.addObject("selectedOuName", editUserBean.getOuId());
				
				editUserBean.setEmailID(result.getEmailID());
				UserBean adminCreatedBy = (UserBean) request.getSession().getAttribute("user");
				String createdByAdmin = rmgOperationServices.getUsrCreatedBy(adminCreatedBy.getUserID());
				editUserBean.setUpdatedBy(createdByAdmin);
				editUserBean.setUserFName(result.getUserFName());
				editUserBean.setUserLName(result.getUserLName());
				modelAndView.addObject("editUserBean", editUserBean);
			}
			
			UserBean user = (UserBean) request.getSession().getAttribute("user");
		//	String role1 = getUserRole(user);
			String role1 = user.getUserWorkflowRole();
			if(role1.equalsIgnoreCase("GBA")){
				userBean.setGbuadmin("y");
			}
			
			UserBean userBeanlogin = (UserBean) request.getSession().getAttribute("user");
			List<User> listOfEditUserBean = rmgOperationServices.getUserMasterDetails(
					userBeanlogin.getUserID());
			
			 modelAndView.addObject("userId", userBeanlogin.getUserID());
			modelAndView.addObject("usrRole", role1.trim());
		
			modelAndView.addObject("adminuserBean", listOfEditUserBean);
			modelAndView.setViewName("globalAdminDetails");

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return modelAndView;
	}
	
	/**
	 * 
	 * @param userBean
	 * @param result
	 * @param model
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value = { "/rmg/updateAdminUserDetails" }, 
			method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView updateAdminUserDetails(@Valid @ModelAttribute("userBean") 
	UserBean userBean,BindingResult result,Model model,HttpServletRequest request) {
		ModelAndView modelAndView = null;
		UserBean addUserBean = new UserBean();
		try {

			modelAndView = new ModelAndView();
			if (result.hasErrors()) {
				List<CountryBean> countriesadmin = new ArrayList<CountryBean>();
				countriesadmin = rmgOperationServices.getCountries();
				modelAndView.addObject("countries", countriesadmin);
				modelAndView.setViewName("globalAdminDetails");
			} else {
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				User user = new User();
				user.setUserID(userBean.getUserID());
				user.setUserPwd(userBean.getUserPwd());
				user.setUserWorkflowRole(userBean.getUserWorkflowRole());
				
				List<Object[]> listTransactionLogBean = rmgOperationServices.getGbuUsrData(
						userBean.getGbuId(),userBean.getCountryID());
	
				for (Object[] adminresult : listTransactionLogBean) {
					user.setGbuId(String.valueOf(adminresult[0]));
					user.setCountryID(String.valueOf(adminresult[1]));
				}
				System.out.println(userBean.getSubPracticeID());
				user.setgPracticeID(userBean.getgPracticeID());
				List<Object[]> counpracid = rmgOperationServices.getCountryPraDetails(
						user.getCountryID(),user.getgPracticeID(),userBean.getSubPracticeID());
	
				for (Object[] adminresult : counpracid) {
					user.setgPracticeID(String.valueOf(adminresult[0]));
					user.setSubPracticeID(String.valueOf(adminresult[1]));
				}
				
				String ouid = request.getParameter("searchStr");
				String ouName = rmgOperationServices.getOuName(ouid);
				user.setOuId(ouName);
				user.setEmaildID(userBean.getEmailID());
				user.setCreatedDate(new Date(dateFormat.format(date)));
				
				UserBean adminCreatedBy = (UserBean) request.getSession().getAttribute("user");
				String createdByAdmin = rmgOperationServices.getUsrCreatedBy(adminCreatedBy.getUserID());
				UserBean userBeanlogin = (UserBean) request.getSession().getAttribute("user");
				user.setCreatedBy(userBeanlogin.getUserID());
				user.setUpdatedDate(new Date(dateFormat.format(date)));
				user.setUpdatedBy(userBeanlogin.getUserID());
				user.setUserFName(userBean.getUserFName());
				user.setUserLName(userBean.getUserLName());
				user.setUserActFlag("0");
				//rmgOperationServices.save(user);
				rmgOperationServices.edit(user);
				model.addAttribute("msg", "User updated successfully!");
				List<CountryBean> countriesadmin = new ArrayList<CountryBean>();
				countriesadmin = rmgOperationServices.getCountries();
				modelAndView.addObject("countries", countriesadmin);
				//UserBean userrole = (UserBean) request.getSession().getAttribute("user");
				//String role1 = getUserRole(userrole);
				String role1 = userBean.getUserWorkflowRole();
				if(role1.equalsIgnoreCase("GBA")){
					userBean.setGbuadmin("y");
				}
				modelAndView.addObject("userId", userBean.getUserID());
				modelAndView.addObject("usrRole", role1.trim());
				getAllUserDetails(user,request,modelAndView);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return modelAndView;
	}
	
	/**
	 * 
	 * @param userBean
	 * @param id
	 * @param redirectAttributes
	 * @param model
	 * @param request
	 * @return
	 */
	
	
	@RequestMapping(value = { "/rmg/remove" },
			method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView removeAdminUserDetails(@ModelAttribute("userBean") 
	UserBean userBean,
	@RequestParam(value="subcoId", required = false) String subcoId,
	@RequestParam(value="role", required = false) String role,
	final RedirectAttributes redirectAttributes,
	Model model,HttpServletRequest request) {
		ModelAndView modelAndView = null;
		try {
			
			modelAndView = new ModelAndView();
			rmgOperationServices.getremoveMasterDetails(subcoId, role);
			model.addAttribute("msg", "User deleted successfully!");
			
			List<CountryBean> countriesadmin = new ArrayList<CountryBean>();
			countriesadmin = rmgOperationServices.getCountries();
			modelAndView.addObject("countries", countriesadmin);
			UserBean user = (UserBean) request.getSession().getAttribute("user");
			String role1 = user.getUserWorkflowRole();
			if(role1.equalsIgnoreCase("GBA")){
				userBean.setGbuadmin("y");
			}
			
			userBean = (UserBean) request.getSession().getAttribute("user");
			
			UserBean userBeanlogin = (UserBean) request.getSession().getAttribute("user");
			List<User> listOfUserBean = rmgOperationServices.getUserMasterDetails(userBeanlogin.getUserID());
			
			modelAndView.addObject("adminuserBean", listOfUserBean);
			modelAndView.addObject("userId", userBeanlogin.getUserID());
			modelAndView.addObject("usrRole", role1.trim());
			modelAndView.setViewName("globalAdminDetails");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return modelAndView;
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/rmg/personalizeDisplay")
	public ModelAndView personalizeDisplay(HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView();
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		//String role1 = getUserRole(userrole);
		String role1 = user.getUserWorkflowRole();
		// get personalize data
		List<Personalise> personlise = rmgOperationServices.getUserRole1(role1);
		PersonaliseBean personaliseBean = new PersonaliseBean();
		personaliseBean = fillData(personaliseBean,personlise);
		modelAndView.addObject("userId", user.getUserID());
        modelAndView.addObject("usrRole", role1.trim());
		modelAndView.addObject("personaliseBean", personaliseBean);
		modelAndView.setViewName("personalise");
		return modelAndView;
	}
	
	/**
	 * 
	 * @param personaliseBean
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value = { "/rmg/savePersonalizeDetails" }, 
			method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView savePersonalizeDetails( @ModelAttribute("personaliseBean") PersonaliseBean personaliseBean,
			HttpServletRequest request) {
		try {
			PersonaliseBean personaliseBeanSave = new PersonaliseBean();
			
			// get original data from DB
			if(personaliseBean!=null) {
			List<Personalise> personlise = rmgOperationServices.getUserRole1(personaliseBean.getUserRole());
			personaliseBeanSave = fillData(personaliseBeanSave,personlise);
			}
			// get updated data from FORM
			personaliseBeanSave.setUserRole(personaliseBean.getUserRole());
			personaliseBeanSave.setAssignmentAccountOrCustomerName(
					personaliseBean.getAssignmentAccountOrCustomerName());
			personaliseBeanSave.setAverageDailyCost(personaliseBean.getAverageDailyCost());
			personaliseBeanSave.setAverageDailySalesRate(personaliseBean.getAverageDailySalesRate());
			personaliseBeanSave.setCountry(personaliseBean.getCountry());
			personaliseBeanSave.setFirstStartDate(personaliseBean.getFirstStartDate());
			personaliseBeanSave.setGbuComments(personaliseBean.getGbuComments());
			personaliseBeanSave.setProjectDescription(personaliseBean.getProjectDescription());
			personaliseBeanSave.setProjectManagerName(personaliseBean.getProjectManagerName());
			personaliseBeanSave.setProjectMargin(personaliseBean.getProjectMargin());
			personaliseBeanSave.setProjectWBSCode(personaliseBean.getProjectWBSCode());
			personaliseBeanSave.setSecurityClearanceRequired(personaliseBean.getSecurityClearanceRequired());
			personaliseBeanSave.setSubcoActionPlan(personaliseBean.getSubcoActionPlan());
			personaliseBeanSave.setSubcoEndDate(personaliseBean.getSubcoEndDate());
			personaliseBeanSave.setSubcoExtEndDate(personaliseBean.getSubcoExtEndDate());
			personaliseBeanSave.setSubcoExtStartDate(personaliseBean.getSubcoExtStartDate());
			personaliseBeanSave.setSubCoName(personaliseBean.getSubCoName());
			personaliseBeanSave.setSubCoPrimarySkills(personaliseBean.getSubCoPrimarySkills());
			personaliseBeanSave.setSubCoSecondarySkills(personaliseBean.getSubCoSecondarySkills());
			personaliseBeanSave.settAndMorFPagreementwithSubCon(personaliseBean.gettAndMorFPagreementwithSubCon());
			personaliseBeanSave.setType(personaliseBean.getType());
			personaliseBeanSave.setWorkdoneinIndiaOrNearShore(personaliseBean.getWorkdoneinIndiaOrNearShore());
			personaliseBeanSave.setWorkLocation(personaliseBean.getWorkLocation());
			personaliseBeanSave.setYearssincefirststartDate(personaliseBean.getYearssincefirststartDate());
						
			// send Bean to service layer
			rmgOperationServices.updatePersonalize(personaliseBeanSave);
			
		} catch (Exception e) {
			log.error("Error "+e.getMessage());
		}
		
		String serverName;

		if(request.getServerName().equalsIgnoreCase("localhost"))
		{
			serverName = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

		}
		else{
			serverName = "https://" + request.getServerName();
		}
		
		//return new ModelAndView("redirect:/rmg/home"); 
		//String serverName = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort();
		return new ModelAndView("redirect:"+serverName+"/RmgApplication/rmg/home");
	}

	/**
	 * 
	 * @param personaliseBean
	 * @param personlise
	 * @return
	 */
	private PersonaliseBean fillData(PersonaliseBean personaliseBean,
			List<Personalise> personlise) {
		for (Personalise result : personlise) {
			personaliseBean.setUserRole(result.getUserRole());
			personaliseBean.setPage(result.getPage());
			personaliseBean.setGbu(result.getGbu());
			personaliseBean.setCountry(result.getCountry());
			personaliseBean.setGcm(result.getGcm());
			personaliseBean.setSubCoDemandID(result.getSubCoDemandID());
			personaliseBean.setGlobalPractice(result.getGlobalPractice());
			personaliseBean.setSubPractice(result.getSubPractice());
			personaliseBean.setSubCoDASID(result.getSubCoDASID());
			personaliseBean.setSubCoProviderID(result.getSubCoProviderID());
			personaliseBean.setSubCoName(result.getSubCoName());
			personaliseBean.setSubCoEmployeeID(result.getSubCoEmployeeID());
			personaliseBean.setSubCoEmail(result.getSubCoEmail());
			personaliseBean.setSubCoContactNumber(result.getSubCoContactNumber());
			personaliseBean.setSubCoPrimarySkills(result.getSubCoPrimarySkills());
			personaliseBean.setSubCoSecondarySkills(result.getSubCoSecondarySkills());
			personaliseBean.setgCMFamilyID(result.getgCMFamilyID());
			personaliseBean.setSubCoAssignedLocation(result.getSubCoAssignedLocation());
			personaliseBean.setLineManagerName(result.getLineManagerName());
			personaliseBean.setLineManagerDASID(result.getLineManagerDASID());
			personaliseBean.setLineManagerEmail(result.getLineManagerEmail());
			personaliseBean.setLineManagerContactNumber(result
					.getLineManagerContactNumber());
			personaliseBean.setOuManagerName(result.getOuManagerName());
			personaliseBean.setOuManagerDASID(result.getOuManagerDASID());
			personaliseBean.setOuManagerEmail(result.getOuManagerEmail());
			personaliseBean.setOuManagerContactNumber(result
					.getOuManagerContactNumber());
			personaliseBean.setProjectManagerName(result.getProjectManagerName());
			personaliseBean.setProjectManagerDASID(result.getProjectManagerDASID());
			personaliseBean.setProjectManagerEmail(result.getProjectManagerEmail());
			personaliseBean.setProjectManagerContactNumber(result
					.getProjectManagerContactNumber());
			personaliseBean.setResourceManagerName(result.getResourceManagerName());
			personaliseBean.setResourceManagerDASID(result.getResourceManagerDASID());
			personaliseBean.setResourceManagerEmail(result.getResourceManagerEmail());
			personaliseBean.setResourceManagerContactNumber(result
					.getResourceManagerContactNumber());
			personaliseBean.settAndMorFPagreementwithSubCon(result
					.getTandMorFPagreementwithSubCon());
			personaliseBean.setAssignmentAccountOrCustomerName(result
					.getAssignmentAccountOrCustomerName());
			personaliseBean.setProjectDescription(result.getProjectDescription());
			personaliseBean.setType(result.getType());
			personaliseBean.setProjectMargin(result.getProjectMargin());
			personaliseBean.setFirstStartDate(result.getFirstStartDate());
			personaliseBean.setSubcoEndDate(result.getSubcoEndDate());
			personaliseBean.setSubcoExtStartDate(result.getSubcoExtStartDate());
			personaliseBean.setSubcoExtEndDate(result.getSubcoExtEndDate());
			personaliseBean.setGbuComments(result.getGbuComments());
			personaliseBean.setProjectWBSCode(result.getProjectWBSCode());
			personaliseBean.setProjectManagerWBSCode(result.getProjectManagerWBSCode());
			personaliseBean.setWorkLocation(result.getWorkLocation());
			personaliseBean.setDemandStatus(result.getDemandStatus());
			personaliseBean.setAverageDailyCost(result.getAverageDailyCost());
			personaliseBean.setAverageDailySalesRate(result.getAverageDailySalesRate());
			personaliseBean.setAverageFTEinMonth(result.getAverageFTEinMonth());
			personaliseBean.setYearssincefirststartDate(result
					.getYearssincefirststartDate());
			personaliseBean.setSubcoActionPlan(result.getSubcoActionPlan());
			personaliseBean.setImpactinKEuro(result.getImpactinKEuro());
			personaliseBean.setTsHrMay(result.getTsHrMay());
			personaliseBean.setLocalLanguageRequired(result.getLocalLanguageRequired());
			personaliseBean.setLocalLanguage(result.getLocalLanguage());
			personaliseBean.setSecurityClearanceRequired(result
					.getSecurityClearanceRequired());
			personaliseBean.setClearanceNo(result.getClearanceNo());
			personaliseBean.setRemoteWorkingPossibility(result
					.getRemoteWorkingPossibility());
			personaliseBean.setWorkdoneinIndiaOrNearShore(result
					.getWorkdoneinIndiaOrNearShore());
			personaliseBean.setReplacementPossible(result.getReplacementPossible());
			personaliseBean.setReplacedfrom(result.getReplacedfrom());
			
			// new fileds mapping
			personaliseBean.setRequestorComment(result.getRequestorComment());
			personaliseBean.setValidationComment(result.getValidationComment());
			personaliseBean.setNewPrologEstimate(result.getNewPrologEstimate());
			personaliseBean.setTypeOfRequest(result.getTypeOfRequest());
			personaliseBean.setTypeOfContract(result.getTypeOfContract());
			personaliseBean.setyTDExtRevenue(result.getYTDExtRevenue());
			personaliseBean.setyTDProfitMargin(result.getyTDProfitMargin());
			personaliseBean.setBillable(result.getBillable());
			personaliseBean.setOperationalRole(result.getOperationalRole());
			personaliseBean.setReasonForVacancy(result.getReasonForVacancy());
			personaliseBean.setCustomer(result.getCustomer());
		}
		return personaliseBean;
	}
	
	/**
	 * 
	 * @param userBean
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/rmg/resetPswd")
	public ModelAndView resetPswd(@ModelAttribute UserBean userBean,
			HttpServletRequest request) throws IOException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("resetPwdthEmail");
		return modelAndView;
	}
	
	/**
	 * 
	 * @param userBean
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/rmg/resetPwdthEmail")
	public ModelAndView resetPwdthEmail(@ModelAttribute UserBean userBean,
			HttpServletRequest request) throws Exception {
		log.error("resetPwdthEmail called ");
		String usrPwd = null;
		String tempUser = request.getParameter("id");
		log.error("Encrypted id " + tempUser);
		String decryptedValue = EncryptableUtilities.getInstance().getDecryptedValue(tempUser);
		log.error("decryptedValue id " + decryptedValue);
		String tempUserId = decryptedValue.split(";")[0];
		String tempUserPassword = decryptedValue.split(";")[1];
		
		if(userBean!=null && tempUserId != null)
			userBean.setUserID(tempUserId);
		
		String inputoldPassword = tempUserPassword;
		log.error("inputoldPassword " + inputoldPassword);
		String inputnewPassword = request.getParameter("newpassword");
		log.error("inputnewPassword " + inputnewPassword);
		try {
			User user = (User) request.getSession().getAttribute("userObj");
			HttpSession httpSession = null;
			if (user == null) {
				httpSession = request.getSession(true);
			} else {
				httpSession = request.getSession();
			}
			usrPwd = rmgOperationServices.resetPswd(userBean);
		} catch (Exception e) {
			log.error("Error "+e.getMessage());
		}

		ModelAndView modelAndView = new ModelAndView();
		if(inputoldPassword.trim().equalsIgnoreCase(usrPwd.trim())) {
			UserBean user = new UserBean();
			modelAndView.addObject("user", user);
			rmgOperationServices.updatePswd(userBean,inputnewPassword);
			modelAndView.addObject("mess","Password changed successfully Enter your Credentials");
			modelAndView.setViewName("login");
			return modelAndView;
		} else	{
			modelAndView.addObject("mess","Link Expired");
			modelAndView.setViewName("resetPwdthEmail");
		}
		return modelAndView;
	}
	/**
	 * 
	 * @param userBean
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/rmg/hiring")
	public ModelAndView hiring(@ModelAttribute UserBean userBean,
			HttpServletRequest request) throws IOException {
		UserBean user = (UserBean) request.getSession().getAttribute("user");		
		ModelAndView modelAndView = new ModelAndView();
		if(user!=null) {
			modelAndView.addObject("userId", user.getUserID());
			modelAndView.addObject("usrRole", user.getUserWorkflowRole().trim());
		}
		modelAndView.setViewName("hiring");
		return modelAndView;
	}
}
