package io.github.donnie4w.jdao.datasource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataSourceUtil2 {

    private static Properties loadProperties(String filePath) throws IOException {
        Properties properties = new Properties();
        try (FileReader reader = new FileReader(filePath)) {
            properties.load(reader);
        }
        return properties;
    }

    public static DataSource getDataSourceByDruid(String filePath) {
        try {
            Properties properties = loadProperties(filePath);
            return DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DataSource getDataSourceByC3P0(String filePath) {
        try {
            Properties properties = loadProperties(filePath);
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass(properties.getProperty("jdbc.driverClassName"));
            dataSource.setJdbcUrl(properties.getProperty("jdbc.url"));
            dataSource.setUser(properties.getProperty("jdbc.username"));
            dataSource.setPassword(properties.getProperty("jdbc.password"));
            dataSource.setMinPoolSize(Integer.parseInt(properties.getProperty("jdbc.minIdle")));
            dataSource.setAcquireIncrement(5);
            dataSource.setMaxPoolSize(Integer.parseInt(properties.getProperty("jdbc.maxActive")));
            return dataSource;
        } catch (IOException | PropertyVetoException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DataSource getDataSourceByHikariCP(String filePath) {
        try {
            Properties properties = loadProperties(filePath);
            HikariConfig config = new HikariConfig(properties);
            return new HikariDataSource(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DataSource getDataSourceByDBCP(String filePath) {
        try {
            Properties properties = loadProperties(filePath);
            return BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DataSource getDataSourceByTomcat(String filePath) {
        try {
            Properties properties = loadProperties(filePath);
            PoolProperties p = new PoolProperties();
            p.setUrl(properties.getProperty("jdbc.url"));
            p.setUsername(properties.getProperty("jdbc.username"));
            p.setPassword(properties.getProperty("jdbc.password"));
            p.setDriverClassName(properties.getProperty("jdbc.driverClassName"));
            p.setInitialSize(Integer.parseInt(properties.getProperty("jdbc.initialSize")));
            p.setMaxActive(Integer.parseInt(properties.getProperty("jdbc.maxActive")));
            p.setMinIdle(Integer.parseInt(properties.getProperty("jdbc.minIdle")));
            p.setMaxWait(Integer.parseInt(properties.getProperty("jdbc.maxWait")));
            org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
            dataSource.setPoolProperties(p);
            return dataSource;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
