package model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Portfolio {
	private final static int MAX_PORTFOLIO_SIZE = 5; 
	private Stock[] stocks;
	private String title = "My Portfolio";
	private int portfolioSize = 0;
	
	public Portfolio() {
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.title = new String("");	
		this.portfolioSize = 0;
	}
	

	public Portfolio(Portfolio portfolio){
		this();
		this.setTitle(portfolio.getTitle());
		this.setPortfolioSize(portfolio.getPortfolioSize());
						
		for (int i = 0; i < this.portfolioSize; i++)
		{
			this.stocks[i] = new Stock(portfolio.getStocks()[i]);
		}
	}

	public Stock[] getStocks() {
		return stocks;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getPortfolioSize() {
		return portfolioSize;
	}

	public void setPortfolioSize(int portfolioSize) {
		this.portfolioSize = portfolioSize;
	}	
	
	public String getHtmlString(){
		String resStr = new String("<h1>" + getTitle() + "</h1>");
		for (int i = 0; i < portfolioSize; i++){
			Stock tempS = stocks[i];
			resStr+= tempS.getHtmlDescription() + "<br>";
		}
		return resStr;
	}
	
	public void addStock(Stock stock){
		if (stock != null && portfolioSize <  MAX_PORTFOLIO_SIZE){
			stocks[portfolioSize] = stock;
			portfolioSize++;	
		}
	}
	
	public void removeStock(Stock stock) {
		for (int i = 0; i < portfolioSize; i++){
			if (stock == null){ //check validate input
				return;
			}
			if (stock.getSymbol().equals(stocks[i].getSymbol()))
			{
				stocks[i] = stocks[portfolioSize - 1];
				stocks[portfolioSize - 1] = null;
				portfolioSize--;
			}
		}
	}
}