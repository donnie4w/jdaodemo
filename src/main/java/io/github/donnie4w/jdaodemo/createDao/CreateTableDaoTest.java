package io.github.donnie4w.jdaodemo.createDao;
import io.github.donnie4w.jdao.create.CreateDaoFile;
import io.github.donnie4w.jdaodemo.dataSource.DataSourceTest;
import org.junit.jupiter.api.Test;

/**
 * jdao 支持用 CreateDaoFile 生成数据表 对应的 javaBean对象文件
 */
public class CreateTableDaoTest {

	static {
		//连接池配置文件路径
		String file=System.getProperty("user.dir")+"/druid.properties";
		DataSourceTest.init(file);
	}

	@Test
	public void createHstest() throws Exception {
		// 包名：io.github.donnie4w.jdao.bean
		// 表名：hstest
		// 生产 Hstest.java路径：path
		String path = System.getProperty("user.dir") + "/src/main/java/io/github/donnie4w/jdaodemo/bean";
		CreateDaoFile cdf=new CreateDaoFile();
		cdf.createDaoPackage("hstest","io.github.donnie4w.jdaodemo.bean",path);
	}
}
