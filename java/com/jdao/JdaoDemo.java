package com.jdao;

import java.util.Properties;
import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.jdao.base.DaoFactory;
import com.jdao.base.QueryDao;
import com.jdao.dbHandler.JdaoHandler;
import com.jdao.dbHandler.JdaoHandlerFactory;
import com.jdao.util.CreateDaoUtil;

/**
 * @File:jdao: com.jdao.action :ActionTest1_1_2.java
 * @Copyright (c) 2017, donnie4w@gmail.com All Rights Reserved.
 * @Author: dong
 * @Desc: 测试 druid
 */
public class JdaoDemo {

	// 数据源
	public static DataSource getDataSource() throws Exception {
		Properties p = new Properties();
		p.load(JdaoDemo.class.getClassLoader().getResourceAsStream("druid.properties"));
		return DruidDataSourceFactory.createDataSource(p);
	}

	// 表结构CREATE TABLE `hstest` ( `id` int(10) DEFAULT NULL, `value` varchar(50)  DEFAULT '',`rowname` varchar(50) DEFAULT '')
	// 插入1条数据 insert into hstest values (1,'aa','bb')

	// 生成hstest表 对应的Hstest.java文件
	public static void createHstest() throws Exception {
		CreateDaoUtil.createFile("com.jdao", "hstest", System.getProperty("user.dir") + "\\src\\main\\java\\com\\jdao",getDataSource().getConnection(), "utf-8");
	}

	//
	public static void Test() throws Exception {
		JdaoHandler jdaohandler = JdaoHandlerFactory.getJdaoHandler(getDataSource());
		DaoFactory.setJdaoHandler(jdaohandler);
		QueryDao qd = new QueryDao("select * from hstest where id=1 limit ?,?", 0, 1);
		System.out.println(qd.field2Int("id"));
		System.out.println(qd.field2String("value"));
		System.out.println(qd.field2String("rowname"));
		// select id,rowname from hstest where id=1 limit 0,1
		Hstest h = new Hstest();
		h.where(Hstest.ID.GE(1));
		h.limit(0, 10);
		h = h.queryById(Hstest.ID, Hstest.ROWNAME);
		System.out.println(h.getId());
		System.out.println(h.getValue()); // 打印 NULL ，是因为查询字段里面只写了 Hstest.ID, Hstest.ROWNAME
		System.out.println(h.getRowname());
	}

	public static void Test2() throws Exception {
		JdaoHandler jdaohandler = JdaoHandlerFactory.getJdaoHandler(getDataSource());
		jdaohandler.executeUpdate("insert into hstest (id,rowname,value) values(2,\"donnie\",\"wuxiaodong\")");

	}

	public static void main(String[] args) throws Exception {
		createHstest();
		Test();
	}
}
