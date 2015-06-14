package exception;

import org.algo.exception.PortfolioException;

/**
 * This class has an exception for a negative balance.
 * 
 * @author mor zloof
 * @since 05.06.15
 */

@SuppressWarnings("serial")
public class BalanceException extends PortfolioException{
	public BalanceException(){
		super("Balance is negative");
	}
}