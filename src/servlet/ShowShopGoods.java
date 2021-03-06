package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ShoppingDao;
import entity.Product;
import entity.User;

public class ShowShopGoods extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShowShopGoods() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//获得登录用户对象
		User u=(User)request.getSession().getAttribute("user");
		String mess="";//该字段判断是否用户登录成功，用于服务器重启操作
		if(u!=null){
			mess="已登录用户";
		}
		if(u==null){
			out.print("<script>alert('登录后才能操作，请先登录......');location.href='login.jsp';</script>");
		}else if(u==null&&mess!="")
		{
			out.print("<script>alert('您掉线啦，请重新登录......');location.href='login.jsp';</script>");
		}
		else 
		{
			ShoppingDao sd=new ShoppingDao();
			List<Product> lists=sd.getShopList(u.getEu_user_id());
			if(lists!=null){
				request.setAttribute("lists", lists);
				request.getRequestDispatcher("shopping.jsp").forward(request, response);
			}
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
