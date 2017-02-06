package servlet.authentication;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Utilisateur;
import service.AuthenticationService;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		AuthenticationService auth = AuthenticationService.getInstance();
		Utilisateur user = null;
		
		user = auth.checkUserPassword(login, password);
		
		if(user != null){
			HttpSession session = request.getSession();
			
			session.setAttribute("currentUser", user);
			
			request.removeAttribute("error");
			request.setAttribute("login", user.getLogin());
			
			getServletContext().getRequestDispatcher("/Home.jsp").forward(
					request, response);
			
		} else {
			HttpSession session = request.getSession();
			request.setAttribute("error", "1");
			getServletContext().getRequestDispatcher("/Login.jsp").forward(
					request, response);
		}
	}

}
