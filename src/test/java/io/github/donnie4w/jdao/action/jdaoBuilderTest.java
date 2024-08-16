package io.github.donnie4w.jdao.action;

import com.myprogram.ormService.DataSourceFactory;
import io.github.donnie4w.jdao.build.JdaoBuilder;
import org.junit.Test;

import javax.sql.DataSource;

public class jdaoBuilderTest {
    String[] tables = new String[] {"hstest","hstest1","hstest2","hstest3","hstest4"};

    @Test
    public void TestBuilder(){
        DataSource dataSource = DataSourceFactory.getDataSourceByMysql();
        for(String table : tables){
            JdaoBuilder.build("src/main/java",table,"mysql","com.myprogram.dao",dataSource);
        }
    }
}
