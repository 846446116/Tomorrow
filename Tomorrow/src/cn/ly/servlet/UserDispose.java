package cn.ly.servlet;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;

import cn.ly.bean.User;
import cn.ly.dao.UserDaoImpl;



@WebServlet("/delete")
public class UserDispose extends HttpServlet {
private	UserDaoImpl ud =new UserDaoImpl();
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String parameter = (String) req.getServletContext().getAttribute("encoding");
		req.setCharacterEncoding(parameter);
		resp.setCharacterEncoding(parameter);
		resp.setContentType("text/html;charset="+parameter);
		
		String sid = req.getParameter("id");
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		System.out.println(sid);
		int id = sid == null ? 0 : Integer.parseInt(sid);

			User user = ud.get(id);	
			 System.out.println(user);
		 if (user!=null) {
			ud.del(id);
			resp.getWriter().write("销户成功");
		}else if (name!=null || name!="" && password!=null||password!="") {
			ud.del(name, password);
			resp.getWriter().write("销户成功");
		}else {
			resp.getWriter().write("销户失败");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
