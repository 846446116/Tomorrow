package cn.ly.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;

import cn.ly.bean.User;
import cn.ly.dao.UserDaoImpl;

@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet {
	private   UserDaoImpl ud = new UserDaoImpl();
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String parameter = (String) req.getServletContext().getAttribute("encoding");
		req.setCharacterEncoding(parameter);
		resp.setCharacterEncoding(parameter);
		resp.setContentType("text/html;charset="+parameter);


		String name = req.getParameter("username");
		String password = req.getParameter("userpassword");
		System.out.println(name);
		System.out.println(password);
		
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		
		if (user!=null) {
			//如果成功就挑战到登录界面
			ud.add(user);
			String login= req.getContextPath()+"/login.jsp";
			resp.sendRedirect(login);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
