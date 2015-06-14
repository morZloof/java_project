package model;

import org.algo.model.PortfolioInterface;
import org.algo.model.StockInterface;

import model.Stock;

/**
 mor zloof
 */
import exception.*;

public class Portfolio implements PortfolioInterface {
	public enum ALGO_RECOMMENDATION {
		BUY, SELL, REMOVE, HOLD;
	}

	private final static int MAX_PORTFOLIO_SIZE = 5; // const - "Physical" size
	private StockInterface[] stocks; // an array of stocks
	private String title = "My Portfolio"; // title of portfolio
	private int portfolioSize = 0; // "logical" size
	private float balance = 0;

	/**
	 * This is an empty c'tor.
	 * 
	 * @author Oz Gonen
	 * @since 30.4.15
	 */

	public Portfolio() {
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.title = new String("");
		this.portfolioSize = 0;
		this.balance = 0;
	}

	/**
	 * This is a copy c'tor (including the array).
	 * 
	 * @author Oz Gonen
	 * @since 30.4.15
	 */
	public Portfolio(Portfolio portfolio) {
		this.title = new String(portfolio.title);
		this.portfolioSize = portfolio.getPortfolioSize();

		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		for (int i = 0; i < this.portfolioSize; i++) {
			this.stocks[i] = new Stock(portfolio.getStocks()[i]);
		}
		this.balance = portfolio.balance;
	}

	public Portfolio(Stock[] stockArray) {
		this.portfolioSize = 0;
		this.balance = 0;
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.title = null;
		for (int i = 0; i < stockArray.length; i++) {
			this.stocks[i] = stockArray[i];
			this.portfolioSize++;
		}
	}

	// Getters & Setters - only what needed (not all of, cause there is no set
	// for a const, for example)
	public StockInterface[] getStocks() {
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

	public void setBalance(float balance) {
		this.balance = balance;
	}

	/**
	 * This method is recieving a stock and adding it into the array, checking
	 * validance at first.
	 * 
	 * @author Oz Gonen
	 * @throws StockNotExists
	 * @throws StockAlreadyExistsException
	 * @throws PortfolioFullException
	 * @since 30.04.15
	 */

	public void addStock(Stock stock) throws PortfolioFullException,
			StockAlreadyExistsException, StockNotExists {
		if (this.portfolioSize == MAX_PORTFOLIO_SIZE) {
			throw new PortfolioFullException();
		}
		if (stock == null) {
			throw new StockNotExists("Check input validance!");
		}
		for (int i = 0; i < this.portfolioSize; i++) {
			if (stock.getSymbol().equals(stocks[i].getSymbol())) {
				throw new StockAlreadyExistsException(
						"You already own that stock!");
			}
		}
		if (this.portfolioSize < MAX_PORTFOLIO_SIZE) {
			stocks[this.portfolioSize] = stock;
			((Stock) stocks[this.portfolioSize]).setStockQuantity(0); // already zero
																	// at
																	// start...
			this.portfolioSize++;
		}
	}

	/**
	 * This method is updating balance of portfolio, checking validance at
	 * first.
	 * 
	 * @author Oz Gonen
	 * @since 30.04.15
	 */
	public void updateBalance(float amount) throws BalanceException {
		float temp = this.balance + amount;
		if (temp < 0) {
			throw new BalanceException();
		} else {
			this.balance = temp;
			System.out.println("Balance updated!");
		}
	}

	/**
	 * This method is removing a stock, checking validance at first.
	 * 
	 * @author Oz Gonen
	 * @since 30.04.15
	 */

	public void removeStock(String symbol) throws StockNotExists,
			BalanceException, SellingOverQuantityException {
		boolean StillRun = true;
		if (symbol == null) { // check validate input, if bad input return
								// unsucceed.
			throw new StockNotExists("invalid input, please try again");
		}
		if (portfolioSize == 1
				|| symbol.equals(stocks[portfolioSize - 1].getSymbol())) {
			/*
			 * in case the stocks array has 1 stock or symbol is last node in
			 * array
			 */
			
			this.sellStock(stocks[portfolioSize - 1].getSymbol(), -1);

			stocks[portfolioSize - 1] = null;
			portfolioSize--;
			// operation succeed
		}
		for (int i = 0; i < portfolioSize && StillRun; i++) { // any other case,
																// 2-5 stocks in
																// portfolio
			if (symbol.equals(stocks[i].getSymbol())) {
				this.sellStock(stocks[portfolioSize - 1].getSymbol(), -1);
				stocks[i] = stocks[portfolioSize - 1];
				stocks[portfolioSize - 1] = null;
				portfolioSize--;
				StillRun = false; // no need to "re-run" in loop
			}
		}
		if (StillRun == true){
			throw new StockNotExists();
		}
	}

	/**
	 * This method is selling quantity of wanted stock, checking validance at
	 * first.
	 * 
	 * @author Oz Gonen
	 * @since 30.04.15
	 */

	public void sellStock(String symbol, int quantity) throws StockNotExists,
			SellingOverQuantityException {
		boolean Exist = false;
		if (symbol == null || quantity < -1) { // validance check
			throw new StockNotExists("Invalid input, please try again!");
		}
		for (int i = 0; i < this.portfolioSize && Exist == false; i++) {
			if (symbol.equals(stocks[i].getSymbol())) {
				Exist = true;
				if (((Stock) this.stocks[i]).getStockQuantity() - quantity < 0) {
					throw new SellingOverQuantityException();

				} else if (quantity == -1) {
					this.balance += ((Stock) this.stocks[i]).getStockQuantity()
							* this.stocks[i].getBid();
					((Stock) this.stocks[i]).setStockQuantity(0);
					System.out.println("All stocks of " + symbol
							+ " were sold!");

				} else { // any other case
					this.balance += quantity * this.stocks[i].getBid();
					((Stock) this.stocks[i])
							.setStockQuantity(((Stock) stocks[i])
									.getStockQuantity() - quantity);
					System.out.println("An amount of " + quantity
							+ " of stock: " + symbol + " were sold!");
				}
			}
		}
		if (Exist == false) {
			throw new StockNotExists(
					"The stock you're trying to sell doesn't appear in portfolio");
		}
	}

	/**
	 * This method is buying a stock, checking validance at first.
	 * 
	 * @author Oz Gonen
	 * @throws StockAlreadyExistsException
	 * @throws BalanceException
	 * @throws StockNotExists
	 * @throws PortfolioFullException
	 * @since 30.04.15
	 */
	public void buyStock(Stock stock, int quantity) throws BalanceException,
			PortfolioFullException, StockNotExists, StockAlreadyExistsException {
		boolean Exist = false;
		int i = 0;
		if (stock == null || quantity < -1) {
			throw new StockNotExists("No null stock OR invalid quantity!");
		}
		if (quantity * stock.getAsk() > this.balance) {
			throw new BalanceException();
		}	
		for (i = 0; i < this.portfolioSize; i++) {
			if (stock.getSymbol().equals(this.stocks[i].getSymbol())) { //if exists
				Exist = true;
				if (quantity == -1) { // buy all of stock's quantity
					int numOfStock = (int) this.balance
							/ (int) this.stocks[i].getAsk();
					this.balance -= numOfStock * this.stocks[i].getAsk();
					((Stock) this.stocks[i])
							.setStockQuantity(((Stock) this.stocks[i])
									.getStockQuantity() + numOfStock);
					System.out.println("All stocks of " + stock.getSymbol()
							+ " Were bought successfuly!");

				} else { // any other case
					this.balance -= quantity * this.stocks[i].getAsk();
					((Stock) this.stocks[i])
							.setStockQuantity(((Stock) stocks[i])
									.getStockQuantity() + quantity);
					System.out.println("Amount of " + quantity
							+ " stocks  of the stock " + stock.getSymbol()
							+ " Were bought!");
				
				}
				break; //stop the for loop, no need for more iterates in order for buying stocks.
			}
		}
		// if the stock's not in array!
		if (i == MAX_PORTFOLIO_SIZE) { // cannot add another stock
			throw new PortfolioFullException();
		} /*else if (Exist == false){ //Stock's not in array and we can add it!
			// add the stock to the array
			if (quantity == -1){
			
				int numOfStock = (int) this.balance
						/ (int) this.stocks[i].getAsk();
				this.balance -= numOfStock * this.stocks[i].getAsk();
				((Stock) this.stocks[i])
						.setStockQuantity(((Stock) this.stocks[this.portfolioSize - 1])
								.getStockQuantity() + numOfStock);
				System.out.println("All stocks of " + stock.getSymbol()
						+ " Were bought successfuly!");
			} else {
				this.balance -= quantity * this.stocks[i].getAsk();
				((Stock) this.stocks[i]).setStockQuantity(quantity);
				System.out.println("Amount of " + quantity + " of the stock: "
						+ stock.getSymbol() + " Were bought!");
			}
		}*/
	}
	

	/**
	 * This method is getting all stocks value in portfolio.
	 * 
	 * @author Oz Gonen
	 * @since 30.04.15
	 */

	public float getStocksValue() {
		float allValue = 0;
		for (int i = 0; i < this.portfolioSize; i++) {
			allValue += ((Stock) this.stocks[i]).getStockQuantity()
					* this.stocks[i].getBid();
		}
		return allValue;
	}

	public float getTotalValue() {
		return this.getStocksValue() + this.balance;
	}

	/**
	 * This method is meant for returning a string that will keep all stocks in
	 * the portfolio.
	 * 
	 * @author Oz Gonen
	 * @since 23.04.15
	 */
	public String getHtmlString() {
		String resStr = new String("<h1>" + getTitle() + "</h1>");
		for (int i = 0; i < portfolioSize; i++) {
			StockInterface tempS = stocks[i];
			resStr += ((Stock) tempS).getHtmlDescription() + "<br>";
		}
		resStr += "Total Portfolio Value is :" + this.getTotalValue()
				+ "$.<br>" + "Total Stocks Value :" + this.getStocksValue()
				+ "$. <br>" + "Balance is:" + this.balance + "$.";
		return resStr;
	}

	public static int getMaxSize() {
		return MAX_PORTFOLIO_SIZE;
	}

	/**
	 * This method finds a stock by its symbol validance at first.
	 * 
	 * @author Oz Gonen
	 * @since 01.06.15
	 */

	public StockInterface findStock(String symbol) {
		int i = 0;
		if (symbol == null) {
			return null;
		}
		for (i = 0; i < this.portfolioSize; i++) {
			if (symbol.equals(this.stocks[i].getSymbol())) {
				return this.stocks[i];
			}
		}
		
		return null;
	}
}