package io.github.donnie4w.jdao.example.nativesql_example;

import io.github.donnie4w.jdao.action.DataSourceFactory;
import io.github.donnie4w.jdao.dao.Hstest1;
import io.github.donnie4w.jdao.handle.*;
import org.junit.Test;

import java.sql.SQLException;

public class TransactionDemo {

    static {
        Jdao.init(DataSourceFactory.getDataSourceBySqlite(), DBType.SQLITE);
        Jdao.setLogger(true);
    }

    //事务对象可以直接执行CRUD函数
    @Test
    public void transaction() throws JdaoException, SQLException, JdaoClassException {
        //创建事务对象
        Transaction tx = Jdao.newTransaction();
        //执行insert事务链接
        tx.executeUpdate("insert into hstest1(`rowname`,`value`) values(?,?)", "uuuuu>>>>1", "ppppppppp");
        //执行insert事务链接
        tx.executeUpdate("insert into hstest1(`rowname`,`value`) values(?,?)", "abcdefg", "123456");
        //提交事务
        tx.commit();
    }

    //事务对象也可以作为参数，传入Jdao的CRUD API中
    @Test
    public void transaction2() throws JdaoException, SQLException, JdaoClassException {
        //创建事务对象
        Transaction tx = Jdao.newTransaction();
        //执行insert事务链接
        Jdao.executeUpdate(tx,"insert into hstest1(`rowname`,`value`) values(?,?)", "uuuuu>>>>1", "ppppppppp");
        //执行insert事务链接
        Jdao.executeUpdate(tx,"insert into hstest1(`rowname`,`value`) values(?,?)", "abcdefg", "123456");
        //提交事务
        tx.commit();
    }


    //事务对象在Jdao中是通用的
    @Test
    public void transaction3() throws JdaoException, SQLException, JdaoClassException {
        //创建事务对象
        Transaction tx = Jdao.newTransaction();

        //事务对象传入标准类Hstest1
        Hstest1 hs = new Hstest1();
        hs.useTransaction(tx);
        hs.setRowname("www>>>>1");
        hs.setValue("hello world".getBytes());
        hs.insert();

        //执行insert事务链接
        tx.executeUpdate("insert into hstest1(`rowname`,`value`) values(?,?)", "uuuuu>>>>1", "ppppppppp");
        //执行insert事务链接
        Jdao.executeUpdate(tx,"insert into hstest1(`rowname`,`value`) values(?,?)", "abcdefg", "123456");
        //事务回滚
        tx.rollback();
    }

}
