package mor_project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Stock;

import java.util.Date;
import java.util.Calendar;

public class StockDetailsServlet extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		resp.setContentType("text/html");
	
		Calendar cal = Calendar.getInstance();
		cal.set(2014, 10, 15);
		
		Date date1 = cal.getTime();
		Date date2 = cal.getTime();
		Date date3 = cal.getTime();
		
		Stock stock1 = new Stock("PIH", 13.1f, 12.4f, date1);
		Stock stock2 = new Stock("PIH", 5.78f, 5.5f, date2);
		Stock stock3 = new Stock("PIH", 32.2f, 31.5f, date3);
		
		String stockHtml1 = new String(stock1.getHtmlDescription());
		String stockHtml2 = new String(stock2.getHtmlDescription());
		String stockHtml3 = new String(stock3.getHtmlDescription());
		
		resp.getWriter().println(stockHtml1 + "<br>" + stockHtml2 + "<br>" + stockHtml3);
	}
}
