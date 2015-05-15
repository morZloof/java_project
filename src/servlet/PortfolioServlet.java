package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Portfolio;
import service.PortforlioManager;

/** 
 	student: mor zloof
 */
@SuppressWarnings("serial")
public class PortfolioServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("text/html");
		
		PortforlioManager portfolioManager = new PortforlioManager();
		Portfolio portfolio = portfolioManager.getPortfolio();
		
		resp.getWriter().println(portfolio.getHtmlString());

	}
}