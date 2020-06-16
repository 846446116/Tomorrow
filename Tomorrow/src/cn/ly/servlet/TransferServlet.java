package cn.ly.servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;

import cn.ly.bean.User;
import cn.ly.dao.UserDaoImpl;

/**
 *��תվ
 * @author Administrator
 *
 */

@WebServlet("/transfer")
public class TransferServlet extends HttpServlet {
private   UserDaoImpl ud = new UserDaoImpl();
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String parameter = (String) req.getServletContext().getAttribute("encoding");
		req.setCharacterEncoding(parameter);
		resp.setCharacterEncoding(parameter);
		resp.setContentType("text/html;charset="+parameter);
		
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		
		//��¼
				User loginUser = ud.login(user);
				if(loginUser!=null) {
					//�ѵ�¼���û���Ϣ���������
					req.getSession().setAttribute("loginUser", loginUser);
					//����ϵͳ
					String welcome= req.getContextPath()+"/welcome.jsp";
					resp.sendRedirect(welcome);
				}else {
					//��ת��ʧ��ҳ�� ֱ����ת�� ע��
					String registered= req.getContextPath()+"/registered.jsp";
					resp.sendRedirect(registered);
				}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
