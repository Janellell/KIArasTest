package com.atos.rmg.dao;

import java.util.List;

import com.atos.rmg.beans.UserBean;
import com.atos.rmg.entity.Country;
import com.atos.rmg.entity.CountryPractise;
import com.atos.rmg.entity.Language;
import com.atos.rmg.entity.Manager;
import com.atos.rmg.entity.Personalise;
import com.atos.rmg.entity.TransactionLog;
import com.atos.rmg.entity.User;

/**
 * 
 * @author A180562
 *
 */
public interface RmgOperationDao {

	public void save(Manager manager) throws Exception;
	
	public void addManager(Manager manager,  int demand_ID, String gbuName, String userId) throws Exception;
	
	public List<Manager> search(String searchCritera) throws Exception;
	
	public boolean validateUser(User user)throws Exception;
	
	public boolean UserDetails(User user)throws Exception; 
	
//	public void addCountryDetails(Country country);
	
	public List<Country> getCountries();
	
	public List<String> getCountriesForGBU(String gbuvalue);
	
	//public void addCountryPracticeDetails(CountryPractise countryPractise);
	
	public String addTransactionLogs(TransactionLog transactionLog,  int demand_ID, String gbu, String country, 
			String globalPractise, String subPractise,String userId);
	
	public List<String> getCountrySubPractices(String gPractiseName,String countryName,String gbuName);
	
	public List<String> getGPraticenamePRM(UserBean userBean);
	
	public List<String> getGPracticesForCountry(String country);
	
	public List<Manager> findAll() throws Exception;
	
	public List<TransactionLog> getTransactionLogs(UserBean userBean);
	public List<Country> getCountries(UserBean userBean);
	public List<CountryPractise> getCountriesPractise(UserBean userBean);
	public List<TransactionLog> getTransactionLogsForFilters(String selectedGbuValue,String selectedCountryValue,
			String selectedGlobalPracticeValue,String selectedAgreementTypeValue,String selectedGCMValue,
			String selectedAssisgmentTypeValue, String selectedProjectMarginValue, 
			String selectedAverageCostValue,String userRole, UserBean userBean);
	//String gbuId,String countryId,String gPracticeId
	
	public List<Country> getFiltersInitialData(UserBean userBean);
	public List<TransactionLog> getTransactionLogForRecord(int subcoDasID);
	
	public List<Country> getCountryInitialValue(int subcoDasID);
	public List<CountryPractise> getCountryPractiseInitialValue(int subcoDasID);
	public List<Language> getLanguages();

	void savePersonalise(Personalise personalise) throws Exception;
	 
}
