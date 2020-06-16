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
		
		//��ȡgo��ֵ
		String go = (String) req.getServletContext().getAttribute("go");
		System.out.println("�²�����="+go);
		// ��ȡaction��ֵ
		String atcion = null;
		atcion = req.getParameter("action");
		System.out.println(atcion);
		// 1.�����������û��б�͵�����ʾ�б�ķ���
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
			out.println("��ӳɹ�");
			String url = req.getContextPath() + "/menu?go1="+go;
//			System.out.println(go);
//			System.out.println(url);
//			out.println("<form action=" + url + "> <input type='submit' value='����'> </form>");
			out.println("<br>");
			out.println("<a href="+url+">����</a>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void date(HttpServletRequest req, HttpServletResponse resp,String go) {
		// ��Ϊ������Ϊ�����һ���������Բ��������map����
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
		
		
		// �޸�
		cd.upd(comment,go);

//		try {
//			resp.getWriter().write("�޸ĳɹ�");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		PrintWriter out;
		try {
			out = resp.getWriter();
			out.println("�޸ĳɹ�");
			String url = req.getContextPath() + "/menu?go1="+go;
			out.println("<br>");
			out.println("<a href="+url+">����</a>");
			
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
//			resp.getWriter().write("ɾ���ɹ�");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		PrintWriter out;
		try {
			out = resp.getWriter();
			out.println("ɾ���ɹ�");
			String url = req.getContextPath() + "/menu?go1="+go;
			out.println("<br>");
			out.println("<a href="+url+">����</a>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void query(HttpServletRequest req, HttpServletResponse resp,String go) {

		//�˴��ж����йط�ҳ���õı��� �������ǿ��԰����Ƿ���һ�� �γ�һ���� Page
		//������ҳ����
		Pages pages = new Pages<Comment>();
		// ��ȡҳ��������ҳ��
		String p = req.getParameter("page");
//		int page =(p==null?1:Integer.parseInt(p));
//		System.out.println(page);
		int page =1;
		try {
			page = (p==null?1:Integer.parseInt(p));
		} catch (Exception e) {
			page = 1;
		}
		
		//ÿҳ��������
		int pagesize=4;
		
		
		//��ȡҪ��ת��ҳ���±�
		String jum = req.getParameter("jum");
		if (jum!=null&&!"".equals(jum)) {
			page=Integer.parseInt(jum);
		}
		pages.setPageSize(pagesize);//����ÿҳ��������
		pages.setCurrentPage(page); //���뵱ǰҳ��ѡ��
	
		//��ȡ��ѯ���� (�˴�ֻ��һ��������ʱ���ܶ� ��װ��һ����ѯ��������)
		String site = req.getParameter("site");
//		��mysql���ݿ��� �ַ���600������600������ ��Ϊ���ǣ�û׼����װ��Comment
		String num1 = req.getParameter("num1"); //��������
		System.out.println(num1);
		String num2 = req.getParameter("num2");//��������
		String exclusive = req.getParameter("exclusive");//����Ȩ
		System.out.println(site);
		
		CommentQuery cq = new CommentQuery();
		cq.setSite(site);
		cq.setNum1(num1);
		cq.setNum2(num2);
		cq.setExclusive(exclusive);
		
		
		// ��ѯ���е�����
		List<Comment> list = cd.list(pages,cq,go);
//		for (Comment comment : list) {
//			System.out.println(comment);
//		}
//		req.setAttribute("commentList", list);
		//��ѯ��ҳ����
//		int toPage = cd.getToPage(pagesize);
		int toPage = cd.getCount(cq,go);
		
		pages.setList(list); //�����ѯ���е�����
		pages.setCount(toPage);//�����ѯ��ҳ����
		
		
		
		req.setAttribute("pages",pages);
//		req.setAttribute("toPage", toPage);//�Ž������
//		req.setAttribute("currentPage", page); //��ǰ���ʵ�ҳ��Ž������
		
		// ת����list.jsp
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
