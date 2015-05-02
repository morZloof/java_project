package model;

import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Stock {

	private final static int BUY = 0, SELL = 1, REMOVE = 2, HOLD = 3;
	
	private String symbol;
	private float ask;
	private float bid;
	private Date date;
	private int recommendation;
	private int stockQuantity;

	public Stock()
	{
		symbol = "";
		ask = 0;
		bid = 0;
		setDate(new Date(date.getTime()));
		recommendation = 0;
		stockQuantity = 0;
	}
	
	public Stock(String symbol, float ask, float bid, Date date) {
			this.symbol = symbol;
			this.ask = ask;
			this.bid = bid;
			this.date = date;
			this.recommendation = 0;
			this.stockQuantity = 0;
	}
	
	public Stock(Stock stock){
		this.symbol = stock.getSymbol();
		this.ask = stock.getAsk();
		this.bid = stock.getBid();
		this.date = stock.getDate();
		this.recommendation = stock.getRecommendation();
		this.stockQuantity = stock.getStockQuantity();
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

	public int getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(int recommendation) {
		this.recommendation = recommendation;
	}
	
	public String getHtmlDescription() {
		DateFormat dateFt = new SimpleDateFormat("MM/dd/yyyy");
		String dateStr = dateFt.format(date);
		String resultStr = new String("<b>Stock symbol is: </b>" + getSymbol() + "<b> ask: </b>" + getAsk() + "<b> Bid: </b>" + getBid() + "<b> Date: </b>" + dateStr);
		
		return resultStr;
	}
}