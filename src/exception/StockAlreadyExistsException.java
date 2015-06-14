package exception;

import org.algo.exception.PortfolioException;
/**
 * 
 * @author mor zloof
 * @since 05.06.15
 */

@SuppressWarnings("serial")
public class StockAlreadyExistsException extends PortfolioException{
	public StockAlreadyExistsException(){
		super("Stock's already exists");
	}
	public StockAlreadyExistsException(String message){
		super(message);
	}
}