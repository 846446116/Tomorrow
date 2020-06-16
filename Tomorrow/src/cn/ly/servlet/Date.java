package cn.ly.servlet;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;


import ly.cn.util.JdbcUtil;
import cn.ly.bean.Comment;
import cn.ly.dao.CommentDao;
/**
 * 修改的过度服务器
 * @author Administrator
 *
 */
@WebServlet("/wood/dd")
public class Date extends HttpServlet {

	private	CommentDao cd =new CommentDao();
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String parameter = (String) req.getServletContext().getAttribute("encoding");
		req.setCharacterEncoding(parameter);
		resp.setCharacterEncoding(parameter);
		resp.setContentType("text/html;charset="+parameter);

		String sid = req.getParameter("id");
		int id = sid==null?0:Integer.parseInt(sid); //把字符转化为int类型
		
		String go = (String) req.getServletContext().getAttribute("go");
		//先查询
		Comment comment = cd.get(id,go);
		
		req.setAttribute("comment", comment);
		
		//转发到编译页面
		req.getRequestDispatcher("/Public/date.jsp").forward(req, resp);
			
		} 




	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

