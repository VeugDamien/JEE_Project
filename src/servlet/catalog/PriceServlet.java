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
 * Servlet implementation class Price
 */
@WebServlet("/Price")
public class PriceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PriceServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String type = request.getParameter("type");
		
		if( type != null && type.equals("up") && request.getAttribute("doGet") == null){
			int idArt = Integer.parseInt(request.getParameter("idArt"));
			int idRev = Integer.parseInt(request.getParameter("idRev"));
			
			if( idArt != 0 && idRev != 0){
				CatalogService cat = CatalogService.getInstance();
				Article art = new Article();
				Revendeur rev = new Revendeur();
				
				art.setId(idArt);
				rev.setId(idRev);
				
				request.setAttribute("price", cat.getPrice(art, rev).getPrice());
				request.setAttribute("idRev", idRev);
				request.setAttribute("idArt", idArt);
				request.setAttribute("type", "up");
				
				getServletContext().getRequestDispatcher("/PriceForm.jsp").forward(
						request, response);
			}
			
		} else if ( type != null && type.equals("new") && request.getAttribute("doGet") == null){
			CatalogService cat = CatalogService.getInstance();
			int idArt = Integer.parseInt(request.getParameter("idArt"));
			
			request.setAttribute("listArticle", cat.getListArticle());
			request.setAttribute("listRevendeur", cat.getListRevendeur());
			
			request.setAttribute("type", "new");
			request.setAttribute("idArt", idArt);
			
			getServletContext().getRequestDispatcher("/PriceForm.jsp").forward(
					request, response);
		} else if (request.getParameter("id") != null){
			String idArticle = request.getParameter("id");
			Article art = new Article();
			
			CatalogService cat = CatalogService.getInstance();
			art = cat.getArticleById(Integer.parseInt(idArticle));
			
			request.setAttribute("id", idArticle);
			request.setAttribute("listPrice", cat.getListPriceArticle(art));
			getServletContext().getRequestDispatcher("/ListPrice.jsp").forward(
					request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if( request.getAttribute("doGet") != null && (boolean) request.getAttribute("doGet")){
			doGet(request, response);
		} else {	
			if( request.getParameter("type") != null && ((String) request.getParameter("type")).equals("del")){
				deletePrice(request, response);
				getServletContext().getRequestDispatcher("/Price?id="+ request.getParameter("article")).forward(
						request, response);
			} else if( request.getParameter("type") != null && request.getParameter("article") != null
					&& request.getParameter("revendeur") != null && request.getParameter("price") != null){
				updateInsertPrice(request, response);
				getServletContext().getRequestDispatcher("/Price?id="+ request.getParameter("article")).forward(
						request, response);
			} else {
				getServletContext().getRequestDispatcher("/Home.jsp").forward(
						request, response);
			}
		}
			
	}
	
	private void deletePrice(HttpServletRequest request, HttpServletResponse response){
		String[] sel = request.getParameterValues("selection");
		UpdateService up = UpdateService.getInstance();
		
		for(String id : sel){
			Prix price = new Prix();
			Article art = new Article();
			Revendeur rev = new Revendeur();
			
			art.setId(Integer.parseInt(request.getParameter("article")));
			rev.setId(Integer.parseInt(id));
			price.setArticle(art);
			price.setRevendeur(rev);
			
			up.deletePrice(price);
		}
		request.setAttribute("doGet", true);
	}
	
	private void updateInsertPrice(HttpServletRequest request, HttpServletResponse response){
		CatalogService cat = CatalogService.getInstance();
		UpdateService up = UpdateService.getInstance();
		
		String type = (String) request.getParameter("type");
		int idArt = Integer.parseInt(request.getParameter("article"));
		int idRev = Integer.parseInt(request.getParameter("revendeur"));
		float price = Float.parseFloat(request.getParameter("price"));
		int count = 0;
		
		Prix prix = new Prix();
		prix.setArticle(cat.getArticleById(idArt));
		prix.setRevendeur(cat.getRevendeurById(idRev));
		prix.setPrice(price);
		
		if( type.equals("up")){
			count = up.updatePrice(prix);
		} else if ( type.equals("new")){
			count = up.insertPrice(prix);
		}
		request.setAttribute("doGet", true);
	}

}
