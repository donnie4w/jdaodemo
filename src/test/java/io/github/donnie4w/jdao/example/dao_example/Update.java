package io.github.donnie4w.jdao.example.dao_example;

import io.github.donnie4w.jdao.action.DataSourceFactory;
import io.github.donnie4w.jdao.dao.Hstest;
import io.github.donnie4w.jdao.handle.DBType;
import io.github.donnie4w.jdao.handle.Jdao;
import io.github.donnie4w.jdao.handle.JdaoException;
import org.junit.Test;

import java.sql.SQLException;

public class Update {
    static {
        Jdao.init(DataSourceFactory.getDataSourceBySqlite(), DBType.SQLITE);
        Jdao.setLogger(true);
    }

    //SQL : insert into hstest(value,rowname)values(?,?)
    //Params: [794693981702800,1724412083534]
    @Test
    public void insert() throws JdaoException, SQLException {
        Hstest hs = new Hstest();
        hs.setRowname(System.currentTimeMillis() + "");
        hs.setValue(System.nanoTime() + "");
        int i = hs.insert();
        System.out.println("insert result: " + i);
    }


    //SQL : delete from hstest where id=?
    //Params: [53]
    @Test
    public void delete() throws JdaoException, SQLException {
        Hstest hs = new Hstest();
        hs.where(Hstest.ID.EQ(getLastId(hs.TableName())));
        int i = hs.delete();
        System.out.println("delete result: " + i);
    }

    //SQL : update hstest set value=?,rowname=? where id=?
    //Params: [795524761487500,1724412914313,52]
    @Test
    public void update() throws JdaoException, SQLException {
        Hstest hs = new Hstest();
        hs.setRowname(System.currentTimeMillis() + "");
        hs.setValue(System.nanoTime() + "");
        hs.where(Hstest.ID.EQ(getLastId(hs.TableName())));
        int i = hs.update();
        System.out.println("update result: " + i);
    }


    public static int getLastId(String table) throws JdaoException, SQLException {
        return Jdao.executeQueryBean("select id from " + table + " order by id desc limit 1").valueInt();
    }
}
