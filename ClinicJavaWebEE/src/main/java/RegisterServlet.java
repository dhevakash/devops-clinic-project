
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Set User session storage here!
		HttpSession session = request.getSession();

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String full_name = request.getParameter("full_name");
		String email = request.getParameter("email");
		String contact_number = request.getParameter("contact_number");
		String role = request.getParameter("role");
		String password = request.getParameter("password");
		
		System.out.println("Selected Role: " + role);
		
		// Username check exists or not
		boolean username_error = false;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_db", "root", "password");
			PreparedStatement ps = con.prepareStatement("SELECT username FROM users WHERE username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			System.out.println("SQL query executed");
			while (rs.next()) {
				if (rs.getString("username").equals(username)) {
					username_error = true;
					System.out.println("Username exists");
					session.setAttribute("register_username_err", true);
				}else {
					username_error = false;
				}
			}
		} catch (Exception exception) {
			System.out.println(exception);
			out.close();
		}
		System.out.println("Going through the next if statement to attempt and create user");
		System.out.println("Username Taken? : " + username_error);
		if(username_error == false) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic_db", "root", "password");
				PreparedStatement ps = con.prepareStatement("INSERT INTO users VALUES (?,?,?,?,?,?,?)");
				ps.setInt(1, 0);
				ps.setString(2, username);
				ps.setString(3, role);
				ps.setString(4, contact_number);
				ps.setString(5, email);
				ps.setString(6, password);
				ps.setString(7, full_name);
				int i = ps.executeUpdate();
				System.out.println("SQL query executed");
				if (i > 0) {
					System.out.println("Successfully inserted");
					response.sendRedirect("http://localhost:8090/ClinicJavaWebEE/login.jsp");
				}
			} catch (Exception exception) {
				System.out.println(exception);
				out.close();
			}
			doGet(request, response);
		}else {
			response.sendRedirect("http://localhost:8090/ClinicJavaWebEE/register.jsp");
		}
		
	}

}