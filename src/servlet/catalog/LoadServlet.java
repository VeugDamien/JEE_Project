package servlet.catalog;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Utilisateur;
import service.CatalogService;

/**
 * Servlet implementation class Load
 */
@WebServlet("/Load")
public class LoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		CatalogService cat = CatalogService.getInstance();
		
		if( request.getParameter("favoris") != null){
			if(request.getSession().getAttribute("currentUser") != null){
				Utilisateur user = (Utilisateur) request.getSession().getAttribute("currentUser");
				request.setAttribute("listFavoris", cat.getListFavoris(user));
				getServletContext().getRequestDispatcher("/ListFavoris.jsp").forward(
						request, response);
			} else {
				getServletContext().getRequestDispatcher("/Login.jsp").forward(
						request, response);
			}
			
		} else if (request.getParameter("revendeur") != null){ 
			if(request.getSession().getAttribute("currentUser") != null){
				request.setAttribute("listRevendeur", cat.getListRevendeur());
				getServletContext().getRequestDispatcher("/ListRevendeur.jsp").forward(
						request, response);
			} else {
				getServletContext().getRequestDispatcher("/Login.jsp").forward(
						request, response);
			}
		} else {
			request.setAttribute("listArticle", cat.getListArticle());
			getServletContext().getRequestDispatcher("/ListArticle.jsp").forward(
					request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
