package io.github.donnie4w.jdaodemo.dataSource;

import java.io.FileReader;
import java.util.Properties;
import javax.sql.DataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import io.github.donnie4w.jdao.base.DaoFactory;

/**
 * 数据源设置 DaoFactory.registDefaultDataSource()
 */
public class DataSourceTest {

	public static void init() {
		DaoFactory.registDefaultDataSource(DataSourceTest.getDataSourceByDruid());
	}

	public static void init(String druidConf) {
		DaoFactory.registDefaultDataSource(DataSourceTest.getDataSourceByDruid(druidConf));
	}
	public static DataSource getDataSourceByC3p0(String db) {
		return new ComboPooledDataSource(db);
	}

	public static DataSource getDataSourceByDruid() {
		String file=System.getProperty("user.dir")+"/druid.properties";
		return getDataSourceByDruid(file);
	}
	public static DataSource getDataSourceByDruid(String filePath) {
		try {
			Properties p = new Properties();
			System.out.println("load druid.properties >> "+filePath);
			p.load(new FileReader(filePath));
			return DruidDataSourceFactory.createDataSource(p);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
