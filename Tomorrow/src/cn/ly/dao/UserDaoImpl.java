package cn.ly.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.ly.bean.Comment;
import cn.ly.bean.User;
import ly.cn.util.JdbcUtil;

public class UserDaoImpl {

	QueryRunner qr = new  QueryRunner(JdbcUtil.getDataSource());
	//登录查询
	public User login(User user) {
		List<User> list = null;
//	new BeanListHandler<User>(User.class)  在姓名没有唯一（约束）的时候用	
		try {
			list = qr.query("select * from user where name=? and password=?",new BeanListHandler<User>(User.class) , user.getName(),user.getPassword());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return (list==null||list.size()==0)?null:list.get(0);
	}
	
	public User get(int id) {
//		QueryRunner qr = new QueryRunner(JdbcUtil.getDataSource());
		User user = null;
		try {
			user = qr.query("select * from user where id=?", new BeanHandler<User>(User.class),id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
//添加
	public void add(User user) {
		
		try {
			qr.update("insert into user (name,password) values(?,?)",new Object[] {user.getName(),user.getPassword()} );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	//删除
	public void del(int id) {
		try {
			qr.update("delete  from user where id=?",id );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//删除
	public void del(String name,String password) {
		try {
			qr.update("delete  from user where name=? and password=?",name,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
