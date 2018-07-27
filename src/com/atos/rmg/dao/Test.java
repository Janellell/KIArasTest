package com.atos.rmg.dao;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.time.DateUtils;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
      System.out.println(DateUtils.addMonths(new Date(), 1).getMonth());
      System.out.println(Calendar.getInstance().getDisplayName((Calendar.MONTH)+1, Calendar.LONG, Locale.getDefault()));
      System.out.println(LocalDate.now().plusMonths(1).getMonth());
	}

}
