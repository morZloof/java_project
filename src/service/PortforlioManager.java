package service;

import model.Portfolio;
import model.Stock;

import java.util.Date;
import java.util.Calendar;

public class PortforlioManager {
	/* mor zloof*/
	public Portfolio getPortfolio(){
		Portfolio myPortfolio = new Portfolio();
		myPortfolio.setTitle("Exercise 7 Portfolio");
		myPortfolio.updateBalance(10000f);
		Calendar cal = Calendar.getInstance();
		cal.set(2014, 11, 15);
		
		Date date1 = cal.getTime();
		Date date2 = cal.getTime();
		Date date3 = cal.getTime();
		
		Stock s1 = new Stock("PIH",  10f, 8.5f, date1);
		Stock s2 = new Stock("AAL", 30f, 25.5f, date2);
		Stock s3 = new Stock("CAAS", 20f, 15.5f, date3);
		
		myPortfolio.buyStock(s1, 20);
		myPortfolio.buyStock(s2, 30);
		myPortfolio.buyStock(s3, 40);
		
		myPortfolio.sellStock("AAL", -1);
		myPortfolio.removeStock("CAAS");
		
		return myPortfolio;
		
	}
}
