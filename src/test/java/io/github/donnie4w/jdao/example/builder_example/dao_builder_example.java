package io.github.donnie4w.jdao.example.builder_example;

import io.github.donnie4w.jdao.action.DataSourceFactory;
import io.github.donnie4w.jdao.build.JdaoBuilder;
import org.junit.Test;

import javax.sql.DataSource;

/**
 *  通过Jdao的JdaoBuilder创建 数据库表的映射类文件
 */
public class dao_builder_example {

    //假设要创建以下表的映射文件
    //注意：hstest.db 中没有hstest4表
    String[] tables = new String[]{"hstest", "hstest1", "hstest2", "hstest3", "hstest4"};

    @Test
    public void TestBuilder() {
        DataSource dataSource = DataSourceFactory.getDataSourceByMysql();
        for (String table : tables) {

            /**
             * 路径："src/test/java"
             * 表名: table："hstest", "hstest1", "hstest2", "hstest3", "hstest4"
             * 数据库类型：mysql
             * 包名："io.github.donnie4w.jdao.dao"
             */
            JdaoBuilder.build("src/test/java", table, "mysql", "io.github.donnie4w.jdao.dao", dataSource);
        }
    }
}
