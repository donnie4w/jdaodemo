package io.github.donnie4w.jdao.example.dao_example;

import io.github.donnie4w.jdao.action.DataSourceFactory;
import io.github.donnie4w.jdao.dao.Hstest;
import io.github.donnie4w.jdao.handle.*;
import org.junit.Test;

import java.sql.SQLException;

/**
 * 事务处理示例
 */
public class TransactionDemo {
    static {
        Jdao.init(DataSourceFactory.getDataSourceBySqlite(), DBType.SQLITE);
        Jdao.setLogger(true);
    }

    // [INSERT SQL][insert into hstest(value,age,rowname)values(?,?,?)][796108420697100,35,www>>>>11]
    // [UPDATE SQL][update hstest set value=?,age=?,rowname=? where id=?][796108434179800,36,www>>>>22,52]
    @Test
    public void transaction() throws JdaoException, SQLException, JdaoClassException {
        //事务对象
        Transaction tx = Jdao.newTransaction();

        Hstest hs = new Hstest();
        hs.useTransaction(tx); //设置事务对象
        hs.setValue(System.nanoTime() + "");
        hs.setAge(35);
        hs.setRowname("www>>>>11");
        hs.insert();

        Hstest hs2 = new Hstest();
        hs2.useTransaction(tx); //设置事务对象
        hs2.setValue(System.nanoTime() + "");
        hs2.setAge(36);
        hs2.setRowname("www>>>>22");
        hs2.where(Hstest.ID.EQ(52));
        hs2.update();

        //事务提交
        tx.commit();
    }
}
