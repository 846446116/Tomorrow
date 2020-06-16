package cn.ly.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String parameter = (String) req.getServletContext().getAttribute("encoding");
		req.setCharacterEncoding(parameter);
		resp.setCharacterEncoding(parameter);
		resp.setContentType("text/html;charset="+parameter);
		String go1 = req.getParameter("go1");
		System.out.println("我是"+go1);
		if(go1.equals("wood")) {
			req.getServletContext().setAttribute("go", go1);
		}else if(go1.equals("mine")) {
			req.getServletContext().setAttribute("go", go1);
		}else if(go1.equals("fibre")) {
			req.getServletContext().setAttribute("go", go1);
		}else if (go1.equals("special")) {
			req.getServletContext().setAttribute("go", go1);
		}else if (go1.equals("monster")) {
			req.getServletContext().setAttribute("go", go1);
		}
		
		
		PrintWriter out = resp.getWriter();
		out.append("<h2>菜单</h2>");
		String queryUrl = req.getContextPath()+"/comment?action=query&page=1"; //page 是页码数
		out.append("<a href="+queryUrl+">查看</a><br/>");
			
		String toDate= req.getContextPath()+"/Public/add.jsp";
		out.println("<a href="+toDate+">添加</a><br/>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
