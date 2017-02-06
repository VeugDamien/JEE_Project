package servlet.catalog;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Article;
import model.Revendeur;
import service.CatalogService;
import service.UpdateService;

/**
 * Servlet implementation class RevendeurServlet
 */
@WebServlet("/Revendeur")
public class RevendeurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RevendeurServlet() {
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
				Revendeur rev = new Revendeur();
				rev = cat.getRevendeurById(id);
				
				request.setAttribute("revendeur", rev);
			}
		}
		
		getServletContext().getRequestDispatcher("/RevendeurForm.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UpdateService up = UpdateService.getInstance();
		Revendeur rev = new Revendeur();
		
		String type = request.getParameter("type");
		
		if( type != null && type.equals("up")) {
			int id = Integer.parseInt(request.getParameter("id"));
			
			if(id != 0){
				rev.setId(id);
				rev.setName(request.getParameter("name"));
				rev.setDescription(request.getParameter("description"));
				
				up.updateRevendeur(rev);
			}
		} else if (type != null && type.equals("new")){
			rev.setName(request.getParameter("name"));
			rev.setDescription(request.getParameter("description"));
			
			up.insertRevendeur(rev);			
		} else if (type != null && type.equals("del")){
			String[] sel = request.getParameterValues("selection");			
			for(String id : sel){
				Revendeur reve = new Revendeur();
				
				reve.setId(Integer.parseInt(id));
				
				up.deleteRevendeur(reve);
			}
		}
		
		getServletContext().getRequestDispatcher("/ListRevendeur.jsp").forward(
				request, response);
	}

}
