package model;

import java.sql.Date;

import model.Portfolio.ALGO_RECOMMENDATION;

import org.algo.model.StockInterface;
/**
mor zloof
 */

@SuppressWarnings("unused")
public class Stock implements StockInterface {

	private String symbol;
	private float ask;
	private float bid;
	private transient java.util.Date date;
	private service.PortfolioManager.ALGO_RECOMMENDATION recommendation;
	private int stockQuantity=0;

	public Stock(Stock stock) {
		this.symbol = new String(stock.getSymbol());
		this.ask = stock.ask;
		this.bid = stock.bid;
		this.date = new java.util.Date(stock.getDate().getTime());
	}

	public Stock(String newSymbol, float newBid, float newAsk,
			java.util.Date newDate) { // creating a new stock with a known data
		this.symbol = newSymbol;
		this.bid = newBid;
		this.ask = newAsk;
		this.date = newDate;

	}

	/**
	 * + * Returns html string that described this stock + * @return html string
	 * that represents this stock +
	 */
	public String getHtmlDescription() {
		return "<b>Stock Symbol: </b>" + getSymbol() + ",<b> ask: </b>"
				+ getAsk() + ", <b>bid: </b>" + getBid() + ", <b>date: </b>"
				+ getDate().getDate() + "/" + getDate().getMonth() + "/"
				+ getDate().getYear() + ",<b> quantity: </b>"
				+ getStockQuantity();
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

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public service.PortfolioManager.ALGO_RECOMMENDATION getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(service.PortfolioManager.ALGO_RECOMMENDATION algo_RECOMMENDATION) {
		this.recommendation = algo_RECOMMENDATION;
	}

	public void setDate(long time) {
		this.date = new Date(time * 1000);
	}
}