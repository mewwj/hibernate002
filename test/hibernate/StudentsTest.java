package hibernate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;
import java.util.zip.InflaterInputStream;

import javax.imageio.stream.FileImageInputStream;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StudentsTest {
	
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	
	@Before
	public void init(){
		//配置对象
		Configuration config=new Configuration().configure();
		//服务注册对象
		org.hibernate.service.ServiceRegistry serviceRegistry=new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//创建回话工厂
		sessionFactory = config.buildSessionFactory(serviceRegistry);
		//创建会话对象
		session =sessionFactory.openSession();
		//开启事务
		transaction=session.beginTransaction();
		
		
	}
	@After
    public void destory(){
		transaction.commit();//提交事务
		session.close();  //关闭会话
		sessionFactory.close(); //关闭会话工厂
	}
	@Test
	public void testSaveStudents(){
		Students s=new Students();
		s.setBiirthday(new Date());
		s.setName("孙悟空2");
		Address  address= new Address("深圳市","730073","13109324");
		s.setAddress(address);
		session.save(s);
	}
	@Test
	public void TestGetStudents(){
		Students s=(Students) session.get(Students.class,0 );
		System.out.println(s);
	}
	@Test
	public void TestLoadStudents(){
		Students s=(Students) session.get(Students.class,0 );
		System.out.println(s);
	}
	@Test
	public void TestUpdateStudents(){
		Students s=(Students) session.get(Students.class,0 );
		s.setName("擎天柱");
		session.saveOrUpdate(s);
	
	}
	@Test
	public void TestDeleteStudents(){
		Students s=(Students) session.get(Students.class,0 );
		session.delete(s);
	}
	
}