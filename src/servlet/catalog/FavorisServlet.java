package servlet.catalog;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Article;
import model.Utilisateur;
import service.CatalogService;
import service.UpdateService;

/**
 * Servlet implementation class Favoris
 */
@WebServlet("/Favoris")
public class FavorisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FavorisServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		if(request.getSession().getAttribute("currentUser") != null){
			UpdateService up = UpdateService.getInstance();
			Article art = new Article();
			Utilisateur user = new Utilisateur();
			
			String id = (String) request.getParameter("id");
			art.setId(Integer.parseInt(id));
			user = (Utilisateur) request.getSession().getAttribute("currentUser");
			
			up.insertFavori(art, user);
			
			getServletContext().getRequestDispatcher("/ListFavoris.jsp").forward(
					request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getSession().getAttribute("currentUser") != null){
			UpdateService up = UpdateService.getInstance();
			CatalogService cat = CatalogService.getInstance();
			String[] sel = request.getParameterValues("selection");	
			Utilisateur user = new Utilisateur();
			
			user = (Utilisateur) request.getSession().getAttribute("currentUser");
			for(String id : sel){
				Article arti = new Article();
				
				arti = cat.getArticleById(Integer.parseInt(id));
				
				up.deleteFavori(arti, user);
			}
		}
		getServletContext().getRequestDispatcher("/ListFavoris.jsp").forward(
				request, response);
	}

}
