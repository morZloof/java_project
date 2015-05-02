package mor_project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Mor_projectServlet extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		resp.setContentType("text/html");
		
		/*                    Question number 1                    */
		double area = 50;
		double radius = area * Math.PI;
		String line1 = new String("<b>Calculation 1:</b> Area of circle with radius " + area + " is: " + radius);
		
		/*                    Question number 2                    */
		int degrees = 30;
		int hypotenuse = 50;
		double result = Math.sin(Math.PI*degrees / hypotenuse);
		String line2 = new String("<b>Calculation 2:</b> Length of opposite where angle B is " + degrees + " and hypotenuse length is " + hypotenuse + "cm is " + result + "cm");
		
		/*                    Question number 3                    */
		int base = 20;
		int exp = 13;
		long resulte3 = 20;
		
		for(int i=1; i<exp ;i++){
			resulte3 *= base;
		}
		String line3 = new String("<b>Calculation 3:</b>  Power of " + base + " with exp of " + exp + " is " + resulte3);
		
		/*                    result                    */
		String resultStr = line1 + "<br>" + line2 + "<br>" + line3;
		resp.getWriter().println(resultStr);
	}
}
