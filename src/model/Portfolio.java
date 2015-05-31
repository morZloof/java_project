package model;

import org.algo.model.PortfolioInterface;
import org.algo.model.StockInterface;

import model.Stock;

/**
 mor zloof
 */
public class Portfolio implements PortfolioInterface {

	public enum ALGO_RECOMMENDATION {
		BUY, SELL, REMOVE, HOLD
	}

	private String title;
	private final static int MAX_PORTFOLIO_SIZE = 5;
	private Stock[] stocks;
	private int portfolioSize = 0;
	private float balance;

	public Portfolio() {
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
	}

	public Portfolio(String title) {
		this.title = title;
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.portfolioSize = 0;
	}

	/**
	 * Create and returns a copy of the given portfolio, using copy-ctor
	 * 
	 * @param portfolio
	 *            - the portfolio you want to copy
	 * @return new copied portfolio
	 */
	public Portfolio(Portfolio portfolio) {
		this(new String(portfolio.getTitle()));

		for (int i = 0; i < portfolio.getPortfolioSize(); i++) {
			this.addStock(new Stock(portfolio.stocks[i]));
		}
	}

	public Portfolio(Stock[] stockArray) {
		this.title = new String();
		this.portfolioSize = getPortfolioSize();
		this.balance = getBalance();
		this.stocks = stockArray;
	}
	

	/**
	 * Updating the portfolio balance
	 * 
	 * @param amount
	 *            - the amount that need to be added to the balance
	 */
	public void updateBalance(float amount) {
		if (this.balance + amount >= 0) {
			this.balance += amount;
		}
	}

	/**
	 * Adding stock to the portfolio
	 * 
	 * @param stock
	 *            - the stock to add to this portfolio
	 */
	public void addStock(Stock newStock) {
		boolean check = false;
		for (int i = 0; i < getPortfolioSize(); i++) {
			if (stocks[i].getSymbol().equals(newStock.getSymbol())) {
				check = true;
				break;
			}
		}
			if (check == false) {
			stocks[portfolioSize] = newStock;
			stocks[portfolioSize].setStockQuantity(0);
			portfolioSize++;
		}

		else if (portfolioSize == MAX_PORTFOLIO_SIZE) {
			System.out.println("Can’t add new stock, portfolio can have only "
					+ MAX_PORTFOLIO_SIZE + " stocks");
		}
	}

	/**
	 * Removing a stock from the portfolio
	 * 
	 * @param symbol
	 *            - the symbol of the stock you want to remove
	 * @return true if the stock was successfully removed. false if not.
	 */
	public boolean removeStock(String symbol) {
		float check = portfolioSize;
		for (int i = 0; i < portfolioSize; i++) {
			if (stocks[i].getSymbol().equals(symbol)) {
				sellStock(symbol, -1);
				stocks[i] = null;
				portfolioSize--;
			}
		}
		int j = 0;
		while (j < portfolioSize) {
			if (stocks[j] == null && stocks[j + 1] != null) {
				stocks[j] = stocks[j + 1];
				stocks[j + 1] = null;
			}
			j++;
		}
		if (portfolioSize < check) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Selling a stock in the portfolio
	 * 
	 * @param symbol
	 *            - the symbol of the stock you want to sell
	 * @param quantity
	 *            - the quantity you want to sell
	 * @return true if the stock was successfully sell, false if not
	 */
	public boolean sellStock(String symbol, int quantity) {
		for (int i = 0; i < portfolioSize; i++)
			if (stocks[i].getSymbol().equals(symbol)) {
				if (stocks[i].getStockQuantity() - quantity < 0) {
					System.out.println("Not enough stocks to sell");
					return false;
				} else if (quantity == -1) {
					balance += stocks[i].getStockQuantity()
							* stocks[i].getBid();
					stocks[i].setStockQuantity(0);
					return true;
				} else {
					balance += quantity * stocks[i].getBid();
					stocks[i].setStockQuantity(stocks[i].getStockQuantity()
							- quantity);
					return true;
				}
			}
		return false;
	}

	/**
	 * Buying a stock in the portfolio
	 * 
	 * @param symbol
	 *            - the symbol of the stock you want to buy
	 * @param quantity
	 *            - the quantity you want to buy
	 * @return true if the stock was successfully bought, false if not
	 */
	public boolean buyStock(Stock stock, int quantity) {
		boolean check = false;
		for (int i = 0; i < portfolioSize; i++) {
			if (stock.getSymbol().equals(stocks[i].getSymbol()))
				check = true;
		}
		if (check == false) {
			addStock(stock);
		}

		for (int i = 0; i < portfolioSize; i++)
			if (stock.getSymbol().equals(stocks[i].getSymbol())) {
				if (quantity == -1) {
					int temp = (int) (balance / stocks[i].getAsk());

					balance = balance - (stocks[i].getAsk() * temp);
					stocks[i].setStockQuantity(stocks[i].getStockQuantity()
							+ temp);
					return true;
				}
				if (balance < stocks[i].getAsk() * quantity) {
					System.out
							.println("Not enough balance to complete purchase");
					return false;
				} else {
					balance = balance - (stocks[i].getAsk() * quantity);
					stocks[i].setStockQuantity(stocks[i].getStockQuantity()
							+ quantity);
					return true;
				}
			}
		return false;
	}

	public float getStocksValue() { // returns total value of all stocks
		float sum = 0;
		for (int i = 0; i < portfolioSize; i++) {
			sum += stocks[i].getStockQuantity() * stocks[i].getAsk();
		}
		return sum;
	}

	/**
	 * Returns html string that described this portfolio
	 * 
	 * @return html string that represents this current portfolio and its stocks
	 */
	public String getHtmlString() {
		String ret = ("<br><h1>" + getTitle() + "</h1>");
		int i = 0;
		while (stocks[i] != null) {
			ret += "<br>" + stocks[i].getHtmlDescription();
			i++;
		}

		ret += ("<br><br>Portfolio title: " + getTitle()
				+ "<br>Total Portfolio value: " + getTotalValue()
				+ "$, Total Stock Value: " + getStocksValue() + "$, Balance: "
				+ getBalance() + "$.");
		return ret;
	}

	public float getTotalValue() {
		return getStocksValue() + balance;
	}

	public float getBalance() {
		return balance;
	}

	public StockInterface[] getStocks() {
		return (StockInterface[]) stocks;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getMAX_PORTFOLIO_SIZE() {
		return MAX_PORTFOLIO_SIZE;
	}

	public int getPortfolioSize() {
		return portfolioSize;
	}

	public void setPortfolioSize(int portfolioSize) {
		this.portfolioSize = portfolioSize;
	}

	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}

	public static int getMaxSize() {
		return MAX_PORTFOLIO_SIZE;
	}

	public StockInterface findStock(String symbol) {
		
		for (int i = 0; i < getPortfolioSize(); i++) {
			if (stocks[i].getSymbol().equals(symbol)) 
					return this.stocks[i];
		}
		return null;
		}
}