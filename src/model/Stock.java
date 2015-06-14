package model;

import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.algo.model.StockInterface;

import model.Portfolio.ALGO_RECOMMENDATION;

import org.algo.model.StockInterface;
/**
mor zloof
 */

public class Stock implements StockInterface {

	// Data Members
	private float balance;
	private String symbol;
	private float ask;
	private float bid;
	private Date date;
	private ALGO_RECOMMENDATION recommendation;
	private int stockQuantity;

	/**
	 * This method is an empty c'tor of stock which initializes an stock's
	 * instance.
	 * 
	 * @author mor zloof
	 * @param stockInterface
	 * @since 30.04.15
	 */
	public Stock() {
		symbol = null;
		ask = 0;
		bid = 0;
		this.date = null;
		stockQuantity = 0;
	}

	/**
	 * This method is a c'tor of stock.
	 * 
	 * @author mor zloof
	 * @since 30.04.15
	 */
	public Stock(String symbol, float ask, float bid, Date date) {
		this.symbol = symbol;
		this.ask = ask;
		this.bid = bid;
		this.date = date;
		this.recommendation = ALGO_RECOMMENDATION.HOLD;
		this.stockQuantity = 0;
	}

	/**
	 * This method is a copy c'tor of stock.
	 * 
	 * @author mor zloof
	 * @since 30.04.15
	 */

	public Stock(StockInterface stockInterface) {
		this(new String(stockInterface.getSymbol()), stockInterface.getAsk(),
				stockInterface.getBid(), new Date(stockInterface.getDate()
						.getTime()));

		this.recommendation = ((Stock) stockInterface).getRecommendation();
		this.stockQuantity = ((Stock) stockInterface).getStockQuantity();
	}

	public void updateBalance(float amount) {
		if (this.balance + amount >= 0) {
			this.balance += amount;
		} else
			System.out.println("Negative Balance!!!");
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	/* Getters & Setters */
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public float getAsk() {
		return ask;
	}

	public void setAsk(float ask) {
		this.ask = ask;
	}

	public float getBid() {
		return bid;
	}

	public void setBid(float bid) {
		this.bid = bid;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public ALGO_RECOMMENDATION getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
		this.recommendation = recommendation;
	}

	/**
	 * This method is meant for setting the current date and format and
	 * returning it back as a string.
	 * 
	 * @author mor zloof
	 * @since 30.04.15
	 */
	public String getHtmlDescription() {
		DateFormat dateFt = new SimpleDateFormat("MM/dd/yyyy");
		String dateStr = dateFt.format(date);
		String resultStr = new String("<b>Stock symbol is: </b>" + getSymbol()
				+ "<b> ask: </b>" + getAsk() + "<b> Bid: </b>" + getBid()
				+ "<b> Date: </b>" + dateStr + " The quantity is: "
				+ this.getStockQuantity());

		return resultStr;
	}
}