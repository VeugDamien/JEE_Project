package servlet.catalog;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Article;
import model.Prix;
import model.Revendeur;
import service.CatalogService;
import service.UpdateService;

/**
 * Servlet implementation class Article
 */
@WebServlet("/Article")
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleServlet() {
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
		String type = (String) request.getParameter("type");
		
		if(type != null && type.equals("up")){
			int id = Integer.parseInt((String) request.getParameter("id"));
			
			request.setAttribute("type", type);
			if(id != 0){
				model.Article art = new model.Article();
				art = cat.getArticleById(id);
				
				request.setAttribute("article", art);
			}
		}
		
		getServletContext().getRequestDispatcher("/ArticleForm.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UpdateService up = UpdateService.getInstance();
		Article art = new model.Article();
		
		String type = request.getParameter("type");
		
		if( type != null && type.equals("up")) {
			int id = Integer.parseInt(request.getParameter("id"));
			
			if(id != 0){
				art.setId(id);
				art.setName(request.getParameter("name"));
				art.setDescription(request.getParameter("description"));
				
				up.updateArticle(art);
			}
		} else if (type != null && type.equals("new")){
			art.setName(request.getParameter("name"));
			art.setDescription(request.getParameter("description"));
			
			up.insertArticle(art);			
		} else if (type != null && type.equals("del")){
			String[] sel = request.getParameterValues("selection");			
			for(String id : sel){
				Article arti = new Article();
				
				arti.setId(Integer.parseInt(id));
				
				up.deleteArticle(arti);
			}
		}
		
		getServletContext().getRequestDispatcher("/Home.jsp").forward(
				request, response);
	}

}
