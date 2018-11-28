package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductTypeDao;

import entity.ProductType;
import entity.User;

public class AddTypeSer extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddTypeSer() {
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
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//��õ�¼�û�����
		User u=(User)request.getSession().getAttribute("user");
		if(u==null){
			out.print("<script>alert('���������������µ�¼......');location.href='login.jsp';</script>");
		}else
		{
			//���Ҫ���ӵ���Ʒ���
			String name = request.getParameter("typename");
			ProductTypeDao ptd = new ProductTypeDao();
			
			//�жϸ���Ʒ����Ƿ����
			ProductType pt1 = ptd.getType(name);
			
			//�����������ӣ���֮����
			if(pt1==null){
				ProductType pt = new ProductType();
				pt.setEpc_name(name);
				int num=ptd.addProductType(pt);
				if(num>0){
					out.print("<script>alert('���ӳɹ���');location.href='TypeManageSer';</script>");
				}else{
					out.print("<script>alert('����ʧ�ܣ�');location.href='manage/productClass-add.jsp';</script>");
				}
			}else{
				out.print("<script>alert('����ʧ�ܣ��Ѵ��ڴ���Ʒ���');location.href='manage/productClass-add.jsp';</script>");
			}
		}
		out.flush();
		out.close();
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