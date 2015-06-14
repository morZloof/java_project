package exception;

import org.algo.exception.PortfolioException;

@SuppressWarnings("serial")
public class SellingOverQuantityException extends PortfolioException{
	public SellingOverQuantityException(){
		super("You're trying to sell a quantity which is way over what you can sell!");
	}
}