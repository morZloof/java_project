package mor_project;
import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Mor_projectServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		int num1 = 4;
		int num2 = 3;
		int num3 = 7;
		
		// create number 49 from this numbers 4,3,7 
		int num123 = num1*num1*num1-num2-num1-num3-num1+num2;
		
		int number2 = 2;
		int number5 = 5;
		String resulte = "Result of " + number5 +"*" + number2 + " = " + (number2*number5); 
		
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello Hanan, have a beautiful day(and try to give me 100 because i am realy try to be smart)!");
		// create number 10 from 5 and 2
		resp.getWriter().println(resulte);
	}
}	
