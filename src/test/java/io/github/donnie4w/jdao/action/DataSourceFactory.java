package io.github.donnie4w.jdao.action;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import io.github.donnie4w.jdao.handle.JdaoRuntimeException;

import javax.sql.DataSource;
import java.io.FileReader;
import java.util.Properties;

public class DataSourceFactory {

    public static String dirRoot = "";

    public static DataSource getDataSourceByMysql() {
        String filePath = dirRoot +"mysql.properties";
        try {
            Properties p = new Properties();
            System.out.println("load druid properties >> " + filePath);
            p.load(new FileReader(filePath));
            return DruidDataSourceFactory.createDataSource(p);
        } catch (Exception e) {
            throw new JdaoRuntimeException(e);
        }
    }

    public static DataSource getDataSourceByPostgre() {
        String filePath = dirRoot +"postgre.properties";
        try {
            Properties p = new Properties();
            System.out.println("load druid properties >> " + filePath);
            p.load(new FileReader(filePath));
            return DruidDataSourceFactory.createDataSource(p);
        } catch (Exception e) {
            throw new JdaoRuntimeException(e);
        }
    }

    public static DataSource getDataSourceBySqlite() {
        String filePath = dirRoot +"sqlite.properties";
        try {
            Properties p = new Properties();
            System.out.println("load druid properties >> " + filePath);
            p.load(new FileReader(filePath));
            return DruidDataSourceFactory.createDataSource(p);
        } catch (Exception e) {
            throw new JdaoRuntimeException(e);
        }
    }
}
