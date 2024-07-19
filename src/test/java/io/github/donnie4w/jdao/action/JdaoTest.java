package io.github.donnie4w.jdao.action;

import io.github.donnie4w.jdao.base.Params;
import io.github.donnie4w.jdao.base.Type;
import io.github.donnie4w.jdao.dao.Hs1;
import io.github.donnie4w.jdao.dao.Hstest;
import io.github.donnie4w.jdao.dao.Hstest1;
import io.github.donnie4w.jdao.dao.Hstest2;
import io.github.donnie4w.jdao.handle.*;
import io.github.donnie4w.jdao.util.Serializa;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class JdaoTest {

    static {
        Jdao.init(DataSourceFactory.getDataSourceBySqlite(), DBType.SQLITE);
        Jdao.setLogger(true);
    }


    @Test
    public void select() throws Exception {
        Hstest1 t = new Hstest1();
        t.where(Hstest1.ID.GT(1));
        t.limit(20,10);
        List<Hstest1> list = t.selects();
        for (Hstest1 hstest1 : list) {
            System.out.println(hstest1);
        }
        System.out.println("-------------------Jdao Hstest1-------------------");
        System.out.println();
        List<DataBean> dblist =  Jdao.executeQueryBeans("select * from Hstest1  order by id desc limit 5");
        for (DataBean dataBean : dblist) {
            System.out.println(dataBean);
        }
        System.out.println("-------------------DataBean-------------------");
    }

    @Test
    public void select2() throws Exception {
        DataSource ds = DataSourceFactory.getDataSourceByMysql();
        Hstest2 t = new Hstest2();
//        t.useDataSource(ds,DBType.MYSQL);
        t.where(Hstest2.NAME.LLIKE("1"));
        t.limit(0,10);
        List<Hstest2> list = t.selects();
        for (Hstest2 hs : list) {
            System.out.println(hs);
        }
        System.out.println("-------------------Jdao Hstest2-------------------");
    }


    @Test
    public void selectForScan() throws Exception {
        Hstest1 t = new Hstest1();
        t.where(Hstest1.ID.GT(1));
        t.limit(20,10);
        List<Hstest1> list = t.selects();
        for (Hstest1 hstest1 : list) {
            System.out.println(hstest1);
        }
        System.out.println("-------------------Jdao Hstest1-------------------");
        System.out.println();
        List<Hs1> dblist =  Jdao.executeQueryList(Hs1.class,"select * from Hstest1  order by id desc limit 5");
        for (Hs1 hs1 : dblist) {
            System.out.println(hs1);
        }
        System.out.println("-----------------自定义JavaBean Hs1-------------------------");
    }

    @Test
    public void selects() throws JdaoException, JdaoClassException, SQLException {
        Hstest2 t = new Hstest2();
        t.where(Hstest2.ID.LE(3), Hstest2.ID.GE(0).OR(Hstest2.ID.EQ(10)));
        t.groupBy(Hstest2.ID);
        t.having(Hstest2.ID.count().LT(2));
        t.orderBy(Hstest2.ID.asc());
        t.limit(0, 5);
        List<Hstest2> list = t.selects();
        for (Hstest2 hs : list) {
            System.out.println(hs);
        }
        System.out.println("---------------select  max(id) as id from hstest2 ------------------");
    }

    @Test
    public void insert() throws JdaoException, SQLException {
        Hstest hs = new Hstest();
        hs.setRowname(System.currentTimeMillis() + "");
        hs.setValue(System.nanoTime() + "");
        hs.insert();
        System.out.println("---------------insert hstest---------------");
    }

    @Test
    public void delete() throws JdaoException, SQLException {
        Hstest hs = new Hstest();
        hs.where(Hstest.ID.EQ(50));
        hs.delete();
        System.out.println("---------------delete hstest---------------");
    }

    @Test
    public void update() throws JdaoException, SQLException, JdaoClassException {
        Hstest hs = new Hstest();
        hs.setRowname(System.currentTimeMillis() + "aaa");
        hs.setValue(System.nanoTime() + "");
        hs.where(Hstest.ID.GT(11), Hstest.ID.LT(12).OR(Hstest.ID.EQ(13)));
        hs.update();
        System.out.println("---------------update hstest---------------");
        hs.reset();  //reset hstest object.
        hs.where(Hstest.ID.GT(11), Hstest.ID.LT(12).OR(Hstest.ID.EQ(13)));
        List<Hstest> list = hs.selects();
        for (Hstest hstest:list){
            System.out.println(hstest);
        }
        System.out.println("---------------update hstest---------------");
    }

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
        System.out.println("-----------------Execute Batch--------------------");
        List<Hstest> list = new Hstest().selects();
        for (Hstest hstest:list){
            System.out.println(hstest);
        }
        System.out.println("-----------------Hstest Data--------------------");
    }

    @Test
    public void transaction() throws JdaoException, SQLException, JdaoClassException {
        Transaction tx = Jdao.newTransaction();
        Hstest hs = new Hstest();
        hs.useTransaction(tx);
        hs.setRowname(System.currentTimeMillis() + "");
        hs.setValue(System.nanoTime() + "");
        hs.setAge(35);
        hs.setRowname("www>>>>1111");
        hs.setValue(System.nanoTime() + "");
        hs.insert();
        Hstest hs2 = new Hstest();
        hs2.useTransaction(tx);
        hs2.setRowname("www>>>>3");
        hs2.setValue(System.nanoTime() + "");
        hs2.where(Hstest.ID.EQ(11).OR(Hstest.ID.EQ(12)));
        hs2.update();
        Jdao.executeUpdate(tx, "insert into hstest1(`rowname`,`value`) values(?,?)", "uuuuu>>>>1", "ppppppppp");

        tx.executeUpdate("insert into hstest1(`rowname`,`value`) values(?,?)", "abcdefg", "123456");
        tx.commit();
        System.out.println("-----------------Transaction Commit--------------------");

        List<Hstest> list = new Hstest().selects();
        for (Hstest hstest:list){
            System.out.println(hstest);
        }
        System.out.println("-----------------Hstest Data--------------------");

        List<Hstest1> list1 = new Hstest1().selects();
        for (Hstest1 hstest1:list1){
            System.out.println(hstest1);
        }
        System.out.println("-----------------Hstest1 Data--------------------");

    }

    @Test
    public void transaction2() throws JdaoException, SQLException {
        Transaction tx = Jdao.newTransaction();
        Hstest hs = new Hstest();
        hs.useTransaction(tx);
        hs.setRowname("www>>>>1");
        hs.setValue(System.nanoTime() + "");
        hs.insert();
        DataBean db = tx.executeQueryBean("select LAST_INSERT_ID() id"); //LAST_INSERT_ID() only for mysql ,in Sqlite,it will throw org.sqlite.SQLiteException: [SQLITE_ERROR] SQL error or missing database (no such function: LAST_INSERT_ID)
        int id = db.findField("id").valueInt();
        System.out.println("id:" + id);
        tx.commit();
    }

    @Test
    public void jdaotest() throws JdaoException, JdaoClassException, SQLException {
        Hstest hs = Jdao.executeQuery(Hstest.class, "select * from hstest where id=?", 1);
        System.out.println(hs);

        List<Hstest> list = Jdao.executeQueryList(Hstest.class, "select * from hstest limit ?,?", 0,30);
        for (Hstest h : list) {
            System.out.println(h);
        }
    }

    @Test
    public void getDataBeans() throws JdaoException, SQLException {
        List<DataBean> list = Jdao.executeQueryBeans("select * from hstest1 limit ?,?", 0, 4);
        for (DataBean dataBean : list) {
            System.out.println(dataBean);
            Hs1 hs = dataBean.scan(Hs1.class);
            System.out.println("Hs1>> " + hs.getId() + ",rowname:" + hs.getRowname() + ",value:" + hs.getValue());
            System.out.println("-------------------DataBean to Hs1-------------------------\n");
        }
    }

    @Test
    public void procedure() throws JdaoException {
        // call proc_test2()
        new ProcedureCall("proc_test2");
    }

    @Test
    public void procedureWithParams() throws JdaoException {
        // call proc_test(?,?,?)
        ProcedureCall pc = new ProcedureCall("proc_test", Params.IN(30), Params.OUT(Type.VARCHAR),
                Params.INOUT("testinout", Type.VARCHAR));
        System.out.println(pc.value(1));
    }

    @Test
    public void nativeSerialize() throws JdaoException, JdaoClassException, SQLException {
        Hstest t = new Hstest();
        t.where(Hstest.ID.EQ(3));
        Hstest hs = t.select();
        System.out.println(hs);
        byte[] bs = Serializa.encode(hs);
        System.out.println(String.format("nativeSerialize len(bs)>>%d bytes",bs.length));
        Hstest hs2 = Serializa.decode(bs, Hstest.class);
        System.out.println(hs2);
        System.out.println("hs.equals(hs2):"+hs.equals(hs2));

        //use hs2
        hs2.where(Hstest.ID.EQ(1));
        hs2 = hs2.select();
        System.out.println(hs2);
    }

    @Test
    public void jdaoSerialize() throws JdaoException, JdaoClassException, SQLException {
        Hstest1 t = new Hstest1();
        t.where(Hstest1.ID.EQ(3));
        Hstest1 hs = t.select();
        System.out.println(hs);
        byte[] bs = hs.encode();
        System.out.println(String.format("jdaoSerialize len(bs)>>%d bytes",bs.length));
        Hstest1 hs2 = new Hstest1().decode(bs);
        System.out.println(hs2);
        System.out.println("hs.equals(hs2):"+hs.equals(hs2));

        //use hs2
        hs2.where(Hstest1.ID.EQ(1));
        hs2 = hs2.select();
        System.out.println(hs2);
    }

    @Test
    public void slave() throws JdaoException, JdaoClassException, SQLException {
        System.out.println(Hstest.class.getPackageName());
        JdaoSlave.bindClass(Hstest.class, DataSourceFactory.getDataSourceByMysql(), DBType.MYSQL);

        Hstest t = new Hstest();
        t.where(Hstest.ID.EQ(3));
        Hstest hs = t.select();
        System.out.println(hs);


        t = new Hstest().useDBhandle(Jdao.getDefaultDBhandle());
        t.where(Hstest.ID.EQ(3));
        hs = t.select();
        System.out.println(hs);
    }

    @Test
    public void cache() throws JdaoException, JdaoClassException, SQLException {
        JdaoCache.bindClass(Hstest.class,new CacheHandle(100));

        Hstest t = new Hstest();
        t.where(Hstest.ID.EQ(3));
        Hstest hs = t.select();
        System.out.println(hs);
        System.out.println("----------------------SET CACHE---------------------");
        Hstest t2 = new Hstest();
        t2.where(Hstest.ID.EQ(3));
        Hstest hs2 = t2.select();
        System.out.println(hs2);
        System.out.println("----------------------GET CACHE---------------------");
        System.out.println("hs.equals(hs2):"+hs.equals(hs2));

        JdaoCache.removeClass(Hstest.class);
        t2 = new Hstest();
        t2.where(Hstest.ID.EQ(3));
        hs2 = t2.select();
        System.out.println(hs2);
        System.out.println("----------------------No Use CACHE---------------------");
    }

}
