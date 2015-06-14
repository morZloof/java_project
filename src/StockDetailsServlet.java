import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import model.Stock;

import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("serial")
public class StockDetailsServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.getWriter().println("<style> table, th, td { border: 1px solid black; border-collapse: collapse; }"
				+ " th, td { padding: 5px; } "
				+ " table {width:25%}</style>");
		resp.setContentType("text/html");
		
		Calendar cal = Calendar.getInstance();
		cal.set(2014, 10, 15);
		
		Date date1 = cal.getTime();
		Date date2 = cal.getTime();
		Date date3 = cal.getTime();
		
		Stock pih = new Stock("PIH", 13.1f,12.4f, date1);
		Stock aal = new Stock("AAL", 5.78f,5.5f,date2);
		Stock caas = new Stock("CAAS", 32.2f,31.5f,date3);
		
		String str1 = new String(pih.getHtmlDescription());
		String str2 = new String(aal.getHtmlDescription());
		String str3 = new String(caas.getHtmlDescription());
		
		String resStr = new String("<table> <tr> <th><b>stock symbol</b></th> <th><b>ask</b></th> <th><b>bid</b></th> <th><b>date</b></th> </tr>" + str1 + "<br>" + str2 + "<br>" + str3 + "</table>");
		resp.getWriter().println(resStr);
	}
}