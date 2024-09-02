package io.github.donnie4w.jdao.example.dao_example;

import io.github.donnie4w.jdao.action.DataSourceFactory;
import io.github.donnie4w.jdao.dao.Hstest;
import io.github.donnie4w.jdao.handle.*;
import org.junit.Test;

import java.sql.SQLException;

// 标准化实体类的读写分离示例程序
public class Slave {
    static {
        Jdao.init(DataSourceFactory.getDataSourceBySqlite(), DBType.SQLITE);
        Jdao.setLogger(true);
    }

    @Test
    public void slave() throws JdaoException, JdaoClassException, SQLException {
        //绑定Hstest类数据操作时，读取数据的两个备库数据源，读取按照随机正太分布向其中一个数据源读取数据
        JdaoSlave.bindClass(Hstest.class, DataSourceFactory.getDataSourceByMysql(), DBType.MYSQL);
        JdaoSlave.bindClass(Hstest.class, DataSourceFactory.getDataSourceByPostgre(), DBType.POSTGRESQL);

        Hstest t = new Hstest();
        t.where(Hstest.ID.EQ(3));
        Hstest hs = t.select();
        System.out.println(hs);

        //解除绑定Hstest类，所有操作向主库请求
        JdaoSlave.unbindClass(Hstest.class);
        t = new Hstest();
        t.where(Hstest.ID.EQ(3));
        hs = t.select();
        System.out.println(hs);
    }

}
