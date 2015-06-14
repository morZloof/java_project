import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class JavastockrepoServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		//VAR DECLARE 
		int num1;
		int num2;
		int num3;
		int result;
		//VAR ASSIGN
		num1 = 4;
		num2 = 3;
		num3 = 7;
		//CALCULATION
		result = (num1+num2)*num3; // (4+3)*7
		// STRING CALC 
		String resultStr = new String("<hr1>Result Of ("+num1+"+"+num2+")*"+num3+"="+result+"</h1>");
		resp.getWriter().println(resultStr);
	}
}
