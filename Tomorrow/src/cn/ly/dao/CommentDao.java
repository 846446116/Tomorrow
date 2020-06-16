package cn.ly.dao;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.ly.bean.Comment;
import cn.ly.bean.CommentQuery;
import cn.ly.bean.Pages;
import ly.cn.util.JdbcUtil;


public class CommentDao {
	
	
	QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
	//��ѯ�����û�
	public List<Comment> list(Pages pages,CommentQuery cq,String go){
		
		List<Comment> list = null;
//		"select * from wood where site like ? and exclusive = ? and num between ? and ? limit ?,?"
//		���sql
//		where 1=1 �����
		StringBuffer sqlsb = new StringBuffer("select * from "+ go +" where 1=1");
		if (cq.getSite()!=null &&!"".equals(cq.getSite())) {
			sqlsb.append(" and site like "+"'"+cq.getSite()+"'");
		}
		if (cq.getExclusive()!=null &&!"".equals(cq.getExclusive())) {
			sqlsb.append(" and exclusive ='"+cq.getExclusive()+"'");
		}
		if (cq.getNum1()!=null &&!"".equals(cq.getNum1())&&cq.getNum2()!=null &&!"".equals(cq.getNum2())) {
			sqlsb.append(" and num between "+cq.getNum1()+" and "+cq.getNum2());
		}
		if (cq.getNum1()!=null && !"".equals(cq.getNum1())&&(cq.getNum2()==null ||"".equals(cq.getNum2()))) {
			sqlsb.append(" and num >= "+cq.getNum1());
		}
		if (cq.getNum1()==null &&"".equals(cq.getNum1())&&(cq.getNum2()!=null || !"".equals(cq.getNum2()))) {
			sqlsb.append(" and num <= "+cq.getNum2());
		}
		sqlsb.append(" limit ?,?");
		System.out.println(sqlsb);
		try {
			list = qr.query(sqlsb.toString(), 
					new BeanListHandler<Comment>(Comment.class),
					(pages.getCurrentPage()-1)*pages.getPageSize(),
					pages.getPageSize());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	//����id��ѯ
		public Comment get(int id,String go) {
//			QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
			Comment Comment = null;
			try {
				Comment = qr.query("select * from "+go+" where id=?", new BeanHandler<Comment>(Comment.class),id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return Comment;
		}
		
		//ɾ��
		public  void del(int id,String go) {
//			QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
			
			try {
				qr.update("delete  from "+go+" where id=?",id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//�޸�
		public void upd(Comment com,String go) {
//			QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
			
			try {
				String sql ="update "+go+" set name=?,price=?,num=?,site=?,exclusive=? where id=?";
				System.out.println(sql);
				qr.update(sql,new Object[] {com.getName(),com.getPrice(),com.getNum(),com.getSite(),com.getExclusive(),com.getId()});
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//���
		public void add(Comment c,String go) {
//			QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
			
			try {
				qr.update("insert into "+go+"(id,name,price,num,site,exclusive) values(?,?,?,?,?,?)",new Object[] {c.getId(),c.getName(),c.getPrice(),c.getNum(),c.getSite(),c.getExclusive()} );
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//��ȡ��ҳ��
		public int getToPage(int pagesize) {
			int topage=1;//Ĭ��ҳ�� ��ʼҳ

			try {
				Long query = qr.query("select count(id) from wood ",new ScalarHandler<Long>());
			int value = query.intValue();
			topage=value/pagesize+1;//ҳ����= ������/ÿҳ��Ŀ+1
		
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return topage;
		}
		
		public int getCount(CommentQuery cq,String go) {
			int count=0;
			
			StringBuffer sqlsb = new StringBuffer("select count(id) from "+go+" where 1=1");
			if (cq.getSite()!=null &&!"".equals(cq.getSite())) {
				sqlsb.append(" and site like '"+cq.getSite()+"'");
			}
			if (cq.getExclusive()!=null &&!"".equals(cq.getExclusive())) {
				sqlsb.append(" and exclusive ='"+cq.getExclusive()+"'");
			}
			if (cq.getNum1()!=null &&!"".equals(cq.getNum1())&&cq.getNum2()!=null &&!"".equals(cq.getNum2())) {
				sqlsb.append(" and num between "+cq.getNum1()+" and "+cq.getNum2());
			}else if (cq.getNum1()!=null &&!"".equals(cq.getNum1())&&(cq.getNum2()==null || "".equals(cq.getNum2()))) {
				sqlsb.append(" and num >="+cq.getNum1());
			}else if (cq.getNum1()==null &&"".equals(cq.getNum1())&&(cq.getNum2()!=null ||!"".equals(cq.getNum2()))) {
				sqlsb.append(" and num <="+cq.getNum2());
			}
			System.out.println(sqlsb);
			try {
				Long query = qr.query(sqlsb.toString(),new ScalarHandler<Long>());
			int value = query.intValue();
			count=value;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return count;
		}
}
