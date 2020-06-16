package cn.ly.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.ly.bean.Comment;
import cn.ly.bean.CommentQuery;
import cn.ly.bean.Pages;
import cn.ly.dao.CommentDao;

@WebServlet("/comment")
public class CommentServlet extends HttpServlet {

	private CommentDao cd = new CommentDao();

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String parameter = (String) req.getServletContext().getAttribute("encoding");
		req.setCharacterEncoding(parameter);
		resp.setCharacterEncoding(parameter);
		resp.setContentType("text/html;charset="+parameter);
		
		//获取go的值
		String go = (String) req.getServletContext().getAttribute("go");
		System.out.println("猜猜我是="+go);
		// 获取action的值
		String atcion = null;
		atcion = req.getParameter("action");
		System.out.println(atcion);
		// 1.如果是请求的用户列表就调用显示列表的方法
		if ("query".equals(atcion)) {
			query(req, resp,go);
		} else if ("del".equals(atcion)) {
			del(req, resp,go);
		} else if ("date".equals(atcion)) {
			date(req, resp,go);
		} else if ("add".equals(atcion)) {
			add(req, resp,go);
		}
	}

	private void add(HttpServletRequest req, HttpServletResponse resp,String go) {
//		Map<String, String[]> parameterMap = req.getParameterMap();
		Comment com = new Comment();
//		try {
//			BeanUtils.copyProperties(comment, parameterMap);
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			e.printStackTrace();
//		}
		com.setId(Integer.parseInt(req.getParameter("id")));
		com.setName(req.getParameter("name"));
		com.setPrice(Integer.parseInt(req.getParameter("price")));
		com.setNum(Integer.parseInt(req.getParameter("num")));
		com.setSite(req.getParameter("site"));
		com.setExclusive(req.getParameter("exclusive"));
		cd.add(com,go);

		PrintWriter out;
		try {
			out = resp.getWriter();
			out.println("添加成功");
			String url = req.getContextPath() + "/menu?go1="+go;
//			System.out.println(go);
//			System.out.println(url);
//			out.println("<form action=" + url + "> <input type='submit' value='返回'> </form>");
			out.println("<br>");
			out.println("<a href="+url+">返回</a>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void date(HttpServletRequest req, HttpServletResponse resp,String go) {
		// 因为我们人为添加了一个变量所以不能用这个map集合
//		Map<String, String[]> parameterMap = req.getParameterMap();
		Comment comment = new Comment();
//		try {
//			
////			BeanUtils.copyProperties(comment, parameterMap);
//		} catch (IllegalAccessException | InvocationTargetException e) {
//			e.printStackTrace();
//		}
		System.out.println("niaho"+Integer.parseInt(req.getParameter("id")));
		System.out.println(Integer.parseInt(req.getParameter("price")));
		System.out.println(Integer.parseInt(req.getParameter("num")));
		System.out.println(req.getParameter("site"));
		System.out.println(req.getParameter("exclusive"));

//		comment.setId(comment.getId());
		comment.setId(Integer.parseInt(req.getParameter("id")));
		comment.setName(req.getParameter("name"));
		comment.setPrice(Integer.parseInt(req.getParameter("price")));
		comment.setNum(Integer.parseInt(req.getParameter("num")));
		comment.setSite(req.getParameter("site"));
		comment.setExclusive(req.getParameter("exclusive"));
		
		
		// 修改
		cd.upd(comment,go);

//		try {
//			resp.getWriter().write("修改成功");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		PrintWriter out;
		try {
			out = resp.getWriter();
			out.println("修改成功");
			String url = req.getContextPath() + "/menu?go1="+go;
			out.println("<br>");
			out.println("<a href="+url+">返回</a>");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void del(HttpServletRequest req, HttpServletResponse resp,String go) {
		String sid = req.getParameter("id");
		System.out.println(sid);
		int id = sid == null ? 0 : Integer.parseInt(sid);

		cd.del(id,go);

//		try {
//			resp.getWriter().write("删除成功");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		PrintWriter out;
		try {
			out = resp.getWriter();
			out.println("删除成功");
			String url = req.getContextPath() + "/menu?go1="+go;
			out.println("<br>");
			out.println("<a href="+url+">返回</a>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void query(HttpServletRequest req, HttpServletResponse resp,String go) {

		//此处有都是有关分页设置的变量 所以我们可以把他们放在一起 形成一个类 Page
		//创建分页对象
		Pages pages = new Pages<Comment>();
		// 获取页码数（首页）
		String p = req.getParameter("page");
//		int page =(p==null?1:Integer.parseInt(p));
//		System.out.println(page);
		int page =1;
		try {
			page = (p==null?1:Integer.parseInt(p));
		} catch (Exception e) {
			page = 1;
		}
		
		//每页限制条数
		int pagesize=4;
		
		
		//获取要跳转的页面下标
		String jum = req.getParameter("jum");
		if (jum!=null&&!"".equals(jum)) {
			page=Integer.parseInt(jum);
		}
		pages.setPageSize(pagesize);//传入每页限制条数
		pages.setCurrentPage(page); //传入当前页面选择
	
		//获取查询条件 (此处只有一个但是有时候会很多 封装出一个查询条件对象)
		String site = req.getParameter("site");
//		在mysql数据库中 字符串600和数字600都可以 因为我们，没准备封装到Comment
		String num1 = req.getParameter("num1"); //搜索下限
		System.out.println(num1);
		String num2 = req.getParameter("num2");//搜索上线
		String exclusive = req.getParameter("exclusive");//所有权
		System.out.println(site);
		
		CommentQuery cq = new CommentQuery();
		cq.setSite(site);
		cq.setNum1(num1);
		cq.setNum2(num2);
		cq.setExclusive(exclusive);
		
		
		// 查询所有的数据
		List<Comment> list = cd.list(pages,cq,go);
//		for (Comment comment : list) {
//			System.out.println(comment);
//		}
//		req.setAttribute("commentList", list);
		//查询总页面数
//		int toPage = cd.getToPage(pagesize);
		int toPage = cd.getCount(cq,go);
		
		pages.setList(list); //传入查询所有的数据
		pages.setCount(toPage);//传入查询总页面数
		
		
		
		req.setAttribute("pages",pages);
//		req.setAttribute("toPage", toPage);//放进域对象
//		req.setAttribute("currentPage", page); //当前访问的页面放进域对象
		
		// 转发到list.jsp
		try {
			req.getRequestDispatcher("/Public/query.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
