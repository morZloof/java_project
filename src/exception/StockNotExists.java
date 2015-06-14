package exception;

import org.algo.exception.PortfolioException;

/** 
 * @author mor zloof
 * @since 05.06.15
 */
@SuppressWarnings("serial")
public class StockNotExists extends PortfolioException{
	public StockNotExists(){
		super("Stock's not exists");
	}
	public StockNotExists(String message){
		super(message);
	}
}