package model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Portfolio {
	/** 
	 student:  mor zloof
	 */
	
	public enum ALGO_RECOMMENDATION{
		BUY, SELL, REMOVE, HOLD;
	}
	
	private final static int MAX_PORTFOLIO_SIZE = 5; //const - "Physical" size
	private Stock[] stocks; //an array of stocks
	private String title = "My Portfolio"; //title of portfolio
	private int portfolioSize = 0; //"logical" size
	private float balance = 0;
	
	public Portfolio() {
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.title = new String("");	
		this.portfolioSize = 0;
		this.balance = 0;
	}
	
	public Portfolio(Portfolio portfolio){
		this.title = new String(portfolio.title);
		this.portfolioSize = portfolio.getPortfolioSize();
		
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		for (int i = 0; i < this.portfolioSize; i++)
		{
			this.stocks[i] = new Stock(portfolio.getStocks()[i]);
		}
		this.balance = portfolio.balance;
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
	
	public float getBalance() {
		return balance;
	}
	
	public void addStock(Stock stock){
		boolean isExist = false;
		if (stock == null){
			System.out.println("Check validance of stock!");
			return;
		}
		
		if (portfolioSize == MAX_PORTFOLIO_SIZE){
			System.out.println("Can't add new stock, portfolio can have only" + MAX_PORTFOLIO_SIZE + "stocks");
			return;
		}
		
		for (int i = 0; i < portfolioSize; i++){
			if (stock.getSymbol().equals(stocks[i].getSymbol())){
				System.out.println("Stock's already inside the portfolio");
				isExist = true;
				return;
			}
		}
		if (isExist == false){
			if (portfolioSize <  MAX_PORTFOLIO_SIZE){
				stock.setStockQuantity(0);
				stocks[portfolioSize] = stock;
				portfolioSize++;
				return;
				}
			}
	}

	public void updateBalance(float amount){
		float temp = this.balance + amount;
		if (temp < 0){
			System.out.println("negative balance");
			return;
		}
		else{
			this.balance = temp;
			System.out.println("Balance updated!");
			return;
		}
	}

	public boolean removeStock(String symbol) {
		boolean StillRun = true;
		if (symbol == null){ //check validate input, if bad input return unsucceed.
			System.out.println("Invalid stock!");
			return false;
		}
		if (portfolioSize == 1 || symbol.equals(stocks[portfolioSize-1].getSymbol())){
			/*in case the stocks array has 1 stock or symbol is last node in array*/
			this.sellStock(stocks[portfolioSize-1].getSymbol(), -1);
			stocks[portfolioSize-1] = null;
			portfolioSize--;
			return true; //operation succeed
		}
		for (int i = 0; i < portfolioSize && StillRun; i++){
			if (symbol.equals(stocks[i].getSymbol())){
			
				this.sellStock(stocks[i].getSymbol(), -1);
				stocks[i] = stocks[portfolioSize - 1];
				stocks[portfolioSize - 1] = null;
				portfolioSize--;
				StillRun = false; //no need to "re-run" in loop
			}
		}
		return true;
	}
	
	public boolean sellStock(String symbol, int quantity){
		if (symbol == null || quantity < -1){ // validance check
			System.out.println("Error! not stock like this to sell or invalid quantity");
			return false;
		}
		for (int i = 0; i <this.portfolioSize; i++){
			if (symbol.equals(stocks[i].getSymbol()) == true){
				
				if (this.stocks[i].getStockQuantity() - quantity < 0){
					System.out.println("Not enough stocks!");
					return false;
				}
				else if (quantity == -1){
					this.updateBalance(this.stocks[i].getStockQuantity()*this.stocks[i].getBid());
					this.stocks[i].setStockQuantity(0);
					System.out.println("All stocks of " + symbol + " were sold!");
					return true;
				}
				else{ //any other case
					this.updateBalance(quantity*this.stocks[i].getBid());
					this.stocks[i].setStockQuantity(stocks[i].getStockQuantity() - quantity);
					System.out.println("An amount of " + quantity + " of stock: " + symbol + " were sold!");
					return true;
				}	
			}	
		}
		System.out.println("Stock's not in portfolio!");
		return false;

	}
	
	public boolean buyStock(Stock stock, int quantity){
		int i = 0;
		if (stock == null || quantity < -1){
			System.out.println("Error! Try again");
			return false;
		}
		if (quantity*stock.getAsk() > this.balance){
			System.out.println("You don't have enough cash to complete the purchase!");
			return false;
		}
		
		for (i = 0; i < this.portfolioSize; i++){
			if (stock.getSymbol().equals(this.stocks[i].getSymbol()) == true){
				
				if (quantity == -1){ // buy all of the specific stock's quantity
					int numOfStock = (int)this.balance/(int)this.stocks[i].getAsk();
					if (numOfStock < 1){
						System.out.println("Not enough balance to complete purchase");
						return false;
					}
					this.updateBalance(-(numOfStock*this.stocks[i].getAsk()));
					this.stocks[i].setStockQuantity(this.stocks[i].getStockQuantity()+numOfStock);
					System.out.println("All stocks of " + stock.getSymbol() + " Were bought successfuly!");
					return true;
				}
				else{ //any other case
					if (quantity*this.stocks[i].getAsk() > this.balance){
						System.out.println("Not enough balance to complete purchase");
						return false;
					}
					this.updateBalance(-(quantity*this.stocks[i].getAsk()));
					this.stocks[i].setStockQuantity(stocks[i].getStockQuantity() + quantity);
					System.out.println("Amount of " + quantity + "stocks  of the stock " + stock.getSymbol() + " Were bought successfuly!");
					return true;
					
				}
			}
		}
		
		if (i == MAX_PORTFOLIO_SIZE){ //cannot add another stock
			System.out.println("Portfolio's full!");
			return false;
		}
		else{ 
			if(quantity == -1){
				this.addStock(stock);
				int numOfStock = (int)this.balance/(int)this.stocks[i].getAsk();
				this.updateBalance(-(numOfStock*this.stocks[i].getAsk()));
				this.stocks[i].setStockQuantity(this.stocks[this.portfolioSize-1].getStockQuantity() + numOfStock);
				System.out.println("All stocks of " + stock.getSymbol() + " Were bought successfuly!");
				return true;
				
			}
			else{ //any specific quantity of buying (not in array) stock.
				this.addStock(stock);
				this.updateBalance(-(quantity*this.stocks[i].getAsk()));
				this.stocks[i].setStockQuantity(quantity);
				System.out.println("Amount of " + quantity + " of the stock: " + stock.getSymbol() + " Were bought!");
				return true;
			}
		}
	}
	
	public float getStocksValue(){
		float allValue = 0;
		for (int i = 0; i < this.portfolioSize; i++){
			allValue += this.stocks[i].getStockQuantity()*this.stocks[i].getBid();
		}
		return allValue;
	}
	
	public float getTotalValue(){
		return this.getStocksValue() + this.balance;
	}

	public String getHtmlString(){
		String resStr = new String("<h1>" + getTitle() + "</h1>");
		for (int i = 0; i < portfolioSize; i++){
			Stock tempS = stocks[i];
			resStr+= tempS.getHtmlDescription() + "<br>";
		}
		resStr += "Total Portfolio: " + this.getTotalValue() + "$.<br>"+
		"Total Stocks: " + this.getStocksValue() + "$. <br>" + "Balance: " + this.balance + "$.";
		return resStr;
	}
}