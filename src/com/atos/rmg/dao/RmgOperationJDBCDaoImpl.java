package com.atos.rmg.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.log4j.Logger;
import com.atos.rmg.beans.Summary;

/**
 * 
 * @author A180562
 *
 */
public class RmgOperationJDBCDaoImpl {

	 static final Logger logger =
		        Logger.getLogger(RmgOperationJDBCDaoImpl.class.getName());
	  
	 
	/** This(getSummaryDetails()) method return summary details like:
	 1)GBU
	 2)Country	
	 3)Global Practice
	 4) Months
	 5) Total summary 
    */
	public List<Summary> getSummaryDetails(String dbURL) throws ClassNotFoundException, SQLException {
		 
		List<Summary> summaryList = null;
		String years = getYearsString();	

	    PreparedStatement stmt = null;
	    Connection con = null;
	    ResultSet rs = null;

	    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		/*String dbURL ="jdbc:sqlserver://172.16.34.62:1433; instanceName = SQLEXPRESS; "
				+ "databaseName = Internal_Project;instanintegratedSecurity=true;user=AppUser;password=@##%User@!@#3";
		*/
		try {
			con = DriverManager.getConnection(dbURL);
			
		    String query = "select GBUName,CountryName,GpracticeName,"+years+", ("+years.replaceAll(",", "+")
		    +") TOTAL from (select * from ( select upper(cm.GBUName) GBUName, upper(cm.CountryName) CountryName, "
		    + "(select distinct upper(GpracticeName) "
		    + "from dbo.Country_PracticeMaster where GpracticeID=tl.GpracticeID) "
		    + "GpracticeName, FORMAT(tl.SubcoEDateWithAtos,'MM/yyyy') "
		    + "end_date,tl.SubcoDemandID from Subcon_Transaction_Log tl ,"
		    + "GBU_CountryMaster cm where tl.GBUID=cm.GBUID AND tl.CountryID=cm.CountryID "
		    + "AND tl.SubcoEDateWithAtos between DATEADD(s,1,DATEADD(mm, DATEDIFF(m,0,GETDATE())-1,0)) "
		    + "AND DATEADD(s,-1,DATEADD(mm, DATEDIFF(m,0,GETDATE())+6,0)) ) src pivot ( count(SubcoDemandID) "
		    + "for end_date in ("+years+") ) piv )  tab order by GBUName,CountryName,GpracticeName";
		    if (con != null && !con.isClosed()) {
		    	logger.info("Connected");
			}
			summaryList = new ArrayList<Summary>();
	        stmt = con.prepareStatement(query);
	        rs = stmt.executeQuery();

	        Summary summary = null;
	        if(rs != null) {
		        while (rs.next()) {
		        	summary=new Summary();
		        	summary.setGbuid(rs.getString(1));
		        	summary.setCountryid(rs.getString(2));
		        	summary.setGpracticeid(rs.getString(3));
		        	summary.setMonthMinusOne(rs.getString(4));
		        	summary.setMonth(rs.getString(5));
		        	summary.setMonthPlusOne(rs.getString(6));
		        	summary.setMonthPlusTwo(rs.getString(7));
		        	summary.setMonthPlusThree(rs.getString(8));
		        	summary.setMonthPlusFour(rs.getString(9));
		        	summary.setMonthPlusFive(rs.getString(10));
		        	summary.setTotal(rs.getString(11));
		        	summaryList.add(summary);
		        }
		        rs.close();
	        }
	        
	        logger.info("successful");
	    } catch (SQLException e ) {
	       logger.error("SQL Error : "+e.getMessage());
	    } finally {
	        if (stmt != null && !stmt.isClosed()) { stmt.close(); }
	        if(con !=null  && !con.isClosed()) { con.close(); }
	        if(rs != null && !rs.isClosed()) { rs.close(); }
	    }
		return summaryList;		

	}

	/**
	 * 
	 * @return
	 */
	private String getYearsString() {
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		String years = "";
		if(currentMonth == 0){
			years = "[12/"+(currentYear-1)+"], [01/"+(currentYear)+"], [02/"+(currentYear)+"],[03/"
		+(currentYear)+"], [04/"+(currentYear)+"],[05/"+(currentYear)+"],[06/"+(currentYear)+"]";
		} else if(currentMonth == 1){
			years = "[01/"+currentYear+"], [02/"+(currentYear)+"], [03/"+(currentYear)+"],[04/"
		+(currentYear)+"], [05/"+(currentYear)+"],[06/"+(currentYear)+"],[07/"+(currentYear)+"]";
		} else if(currentMonth == 2){
			years = "[02/"+currentYear+"], [03/"+(currentYear)+"], [04/"+(currentYear)+"],[05/"
		+(currentYear)+"], [06/"+(currentYear)+"],[07/"+(currentYear)+"],[08/"+(currentYear)+"]";
		} else if(currentMonth == 3){
			years = "[03/"+currentYear+"], [04/"+(currentYear)+"], [05/"+(currentYear)+"],[06/"
		+(currentYear)+"], [07/"+(currentYear)+"],[08/"+(currentYear)+"],[09/"+(currentYear)+"]";
		} else if(currentMonth == 4){
			years = "[04/"+currentYear+"], [05/"+(currentYear)+"], [06/"+(currentYear)+"],[07/"
		+(currentYear)+"], [08/"+(currentYear)+"],[09/"+(currentYear)+"],[10/"+(currentYear)+"]";
		} else if(currentMonth == 5){
			years = "[05/"+currentYear+"], [06/"+(currentYear)+"], [07/"+(currentYear)+"],[08/"
		+(currentYear)+"], [09/"+(currentYear)+"],[10/"+(currentYear)+"],[11/"+(currentYear)+"]";
		} else if(currentMonth == 6){
			years = "[06/"+currentYear+"], [07/"+(currentYear)+"], [08/"+(currentYear)+"],[09/"
		+(currentYear)+"], [10/"+(currentYear)+"],[11/"+(currentYear)+"],[12/"+(currentYear)+"]";
		} else if(currentMonth == 7){
			years = "[07/"+currentYear+"], [08/"+(currentYear)+"], [09/"+(currentYear)+"],[10/"
		+(currentYear)+"], [11/"+(currentYear)+"],[12/"+(currentYear)+"],[02/"+(currentYear+1)+"]";
		} else if(currentMonth == 8){
			years = "[08/"+currentYear+"], [09/"+(currentYear)+"], [10/"+(currentYear)+"],[11/"
		+(currentYear)+"], [12/"+(currentYear)+"],[01/"+(currentYear+1)+"],[02/"+(currentYear+1)+"]";
		} else if(currentMonth == 9){
			years = "[09/"+currentYear+"], [10/"+(currentYear)+"], [11/"+(currentYear)+"],[12/"
		+(currentYear)+"], [01/"+(currentYear+1)+"],[02/"+(currentYear+1)+"],[03/"+(currentYear+1)+"]";
		} else if(currentMonth == 10){
			years = "[10/"+currentYear+"], [11/"+(currentYear)+"], [12/"+(currentYear)+"],[01/"
		+(currentYear+1)+"], [02/"+(currentYear+1)+"],[03/"+(currentYear+1)+"],[04/"+(currentYear+1)+"]";
		} else if(currentMonth == 11){
			years = "[11/"+currentYear+"], [12/"+(currentYear)+"], [01/"+(currentYear+1)+"],[02/"
		+(currentYear+1)+"], [03/"+(currentYear+1)+"],[04/"+(currentYear+1)+"],[05/"+(currentYear+1)+"]";
		} else if(currentMonth == 12){
			years = "[12/"+currentYear+"], [01/"+(currentYear+1)+"], [02/"+(currentYear+1)+"],[03/"
		+(currentYear+1)+"], [04/"+(currentYear+1)+"],[05/"+(currentYear+1)+"],[06/"+(currentYear+1)+"]";
		}
		return years;
	}

}
