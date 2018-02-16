package pl.coderslab.controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.ActiveRecord;
import pl.coderslab.model.Customer;
import pl.coderslab.model.Employee;
import pl.coderslab.model.Orders;
import pl.coderslab.model.Vehicle;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Test() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActiveRecord user = new Orders();
		request.setAttribute("user", user);
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/form.jsp").forward(request, response);

		// user.getById(6);
		// for (String key : user.getFields()) {
		// response.getWriter().append(key + ":" + user.getValue(key) + "<br>");
		// }
		// user.setValue("email", "jan.kowalski@gmail.com");
		// for (String key : user.getFields()) {
		// response.getWriter().append(key + ":" + user.getValue(key) + "<br>");
		// }
	}

	public boolean validate(String text) {
		return text != null && !"".equals(text);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActiveRecord user = new Orders();
		for (String name : user.getFields()) {
			if (validate(request.getParameter(name))) {
				System.out.println(name);
				user.setValue(name, request.getParameter(name));
			} else {
			}
			
		}
		user.save(0, "");// powinna zwracać id
		response.sendRedirect("Test?message=Dodano!!!");
		// doGet()

	}

}
