package model;

import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import model.Portfolio.ALGO_RECOMMENDATION;

public class Stock {
	
	//Data Members
	private float balance;
	private String symbol;
	private float ask;
	private float bid;
	private Date date;
	private ALGO_RECOMMENDATION recommendation;
	private int stockQuantity;

	/* student : mor zloof */
	public Stock()
	{
		symbol = "";
		ask = 0;
		bid = 0;
		setDate(new Date(date.getTime()));
		//recommendation = ALGO_RECOMMENDATION.HOLD;
		stockQuantity = 0;
	}
	
	public Stock(String symbol, float ask, float bid, Date date) {
			this.symbol = symbol;
			this.ask = ask;
			this.bid = bid;
			this.date = date;
			this.recommendation = ALGO_RECOMMENDATION.HOLD;
			this.stockQuantity = 0;
	}

	public Stock(Stock stock){
		this(new String(stock.getSymbol()), stock.getAsk(), stock.getBid(), new Date(stock.getDate().getTime()));
		
		this.recommendation = stock.getRecommendation();
		this.stockQuantity = stock.getStockQuantity();
	}
	
	public void updateBalance(float amount){
		if (this.balance + amount >= 0){
			this.balance += amount;
		}
		else
			System.out.println("Negative Balance!!!");
	}

	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}

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
	
	public String getHtmlDescription() {
		DateFormat dateFt = new SimpleDateFormat("MM/dd/yyyy");
		String dateStr = dateFt.format(date);
		String resultStr = new String("<b>Stock symbol is: </b>" + getSymbol() + "<b> ask: </b>" + getAsk() + "<b> Bid: </b>" + getBid() + "<b> Date: </b>" + dateStr + " The quantity is: " + this.getStockQuantity());
		
		return resultStr;
	}
}