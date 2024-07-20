package io.github.donnie4w.jdao.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbcp2.BasicDataSource;

import org.apache.tomcat.jdbc.pool.PoolProperties;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

public class DataSourceUtil {

    public static DataSource getDataSourceByDruid() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/yourdatabase");
        dataSource.setUsername("yourusername");
        dataSource.setPassword("yourpassword");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setInitialSize(5);
        dataSource.setMaxActive(10);
        dataSource.setMinIdle(1);
        dataSource.setMaxWait(60000);
        return dataSource;
    }

    public static DataSource getDataSourceByC3P0() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/yourdatabase");
        dataSource.setUser("yourusername");
        dataSource.setPassword("yourpassword");
        dataSource.setMinPoolSize(5);
        dataSource.setAcquireIncrement(5);
        dataSource.setMaxPoolSize(20);
        return dataSource;
    }

    public static DataSource getDataSourceByHikariCP() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/yourdatabase");
        config.setUsername("yourusername");
        config.setPassword("yourpassword");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(1);
        config.setIdleTimeout(60000);
        return new HikariDataSource(config);
    }

    public static DataSource getDataSourceByDBCP() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/yourdatabase");
        dataSource.setUsername("yourusername");
        dataSource.setPassword("yourpassword");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setInitialSize(5);
        dataSource.setMaxTotal(10);
        dataSource.setMinIdle(1);
        dataSource.setMaxWaitMillis(60000);
        return dataSource;
    }

    public static DataSource getDataSourceByTomcat() {
        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:mysql://localhost:3306/yourdatabase");
        p.setUsername("yourusername");
        p.setPassword("yourpassword");
        p.setDriverClassName("com.mysql.cj.jdbc.Driver");
        p.setInitialSize(5);
        p.setMaxActive(10);
        p.setMinIdle(1);
        p.setMaxWait(60000);

        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setPoolProperties(p);
        return dataSource;
    }
}