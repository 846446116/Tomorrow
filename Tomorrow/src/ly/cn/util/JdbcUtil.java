package ly.cn.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
/**
 * ���ӳ�
 * @author Administrator
 *
 */
public class JdbcUtil {

	private static DataSource dataSource;//����Դ


	public static   DataSource getDataSource() {
		return  dataSource;
	}

	//druid���ݳ�����
	static {
		//��Ϊ��ǰ����ֽ����µľ���·��?
		InputStream in = JdbcUtil.class.getClassLoader().getResourceAsStream("druid.properties");
//		InputStream in = ClassLoader.getSystemResourceAsStream("druid.properties");
		
		Properties p = new Properties();
		
		try {
			p.load(in);
			DruidDataSourceFactory factory = new DruidDataSourceFactory();
			dataSource = DruidDataSourceFactory.createDataSource(p);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	//��ȡ����
	public  static Connection getConn() {
		try {
			System.out.println("���?");
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
