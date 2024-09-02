package io.github.donnie4w.jdao.example.dao_example;

import io.github.donnie4w.jdao.action.DataSourceFactory;
import io.github.donnie4w.jdao.dao.Hstest;
import io.github.donnie4w.jdao.handle.DBType;
import io.github.donnie4w.jdao.handle.Jdao;
import io.github.donnie4w.jdao.handle.JdaoClassException;
import io.github.donnie4w.jdao.handle.JdaoException;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Date;

/**
 * 批处理处理示例
 */
public class Batch {
    static {
        Jdao.init(DataSourceFactory.getDataSourceBySqlite(), DBType.SQLITE);
        Jdao.setLogger(true);
    }

    //insert into hstest(value,age,rowname,updatetime)values(?,?,?,?)
    //[
    //  [796271212462700, 30, www>>>>1, Fri Aug 23 19:47:40 CST 2024],
    //  [796271212954200, 31, www>>>>2, Fri Aug 23 19:47:40 CST 2024],
    //  [796271213028900, 32, www>>>>3, Fri Aug 23 19:47:40 CST 2024]
    //]
    @Test
    public void batch() throws JdaoException, JdaoClassException, SQLException {
        Hstest hs = new Hstest();
        hs.setAge(30);
        hs.setRowname("www>>>>1");
        hs.setValue(System.nanoTime() + "");
        hs.setUpdatetime(new Date());
        hs.addBatch();

        hs.setAge(31);
        hs.setRowname("www>>>>2");
        hs.setValue(System.nanoTime() + "");
        hs.setUpdatetime(new Date());
        hs.addBatch();

        hs.setAge(32);
        hs.setRowname("www>>>>3");
        hs.setValue(System.nanoTime() + "");
        hs.setUpdatetime(new Date());
        hs.addBatch();

        hs.executeBatch();
    }
}
