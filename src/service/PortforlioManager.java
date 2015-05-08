package service;

import model.Portfolio;
import model.Stock;

import java.util.Date;
import java.util.Calendar;

public class PortforlioManager {
	/* mor zloof*/
	public Portfolio getPortfolio(){
		Portfolio portfolio = new Portfolio();
		portfolio.setTitle("portfolio");	
		Calendar cal = Calendar.getInstance();
		cal.set(2014, 10, 15);
		
		Date date1 = cal.getTime();
		Date date2 = cal.getTime();
		Date date3 = cal.getTime();
		
		Stock s1 = new Stock("PIH", 13.1f, 12.4f, date1);
		Stock s2 = new Stock("AAL", 5.78f, 5.5f, date2);
		Stock s3 = new Stock("CAAS", 32.2f, 31.5f, date3);
		
		portfolio.addStock(s1);
		portfolio.addStock(s2);
		portfolio.addStock(s3);
		
		return portfolio;
		
	}
}
