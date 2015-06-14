package exception;

import org.algo.exception.PortfolioException;

/**
 * 
 * 
 * @author	mor zloof
 * @since 05.06.15
 */
@SuppressWarnings("serial")
public class PortfolioFullException extends PortfolioException{
	public PortfolioFullException(){
		super("Portfolio is Full");
	}
}