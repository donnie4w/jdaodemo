package io.github.donnie4w.jdaodemo.action;

import java.util.List;

import io.github.donnie4w.jdao.base.DBUtil;
import io.github.donnie4w.jdao.base.JException;
import io.github.donnie4w.jdao.base.PageBean;
import io.github.donnie4w.jdao.base.Transaction;
import io.github.donnie4w.jdaodemo.dataSource.DataSourceTest;
import io.github.donnie4w.jdaodemo.bean.Hstest;
import org.junit.jupiter.api.Test;

/**
 * jdao 对象操作测试
 */
public class JdaoTest {
    static {
        String filePth=System.getProperty("user.dir")+"/druid.properties";
        DataSourceTest.init(filePth);
    }

    /**
     * 查询测试
     * @throws Exception
     */
    @Test
    public  void select() throws Exception {
        // select value,rowname from hstest
        // where id<=10 and id>=0 and rowname like '%tom%' group by id
        // order by id asc,rowname desc
        // limit 0,10
        Hstest t = new Hstest();
        t.setLoggerOn(true);
        t.where(t.id.LE(10), t.id.GE(0), t.rowname.LIKE("tom"));
        t.groupBy(t.id);
        t.sort(t.id.asc(),t.rowname.desc());
        t.limit(0, 10);
        List<Hstest> list = t.select(t.id,t.value,t.rowname);
        for (Hstest h : list) {
            System.out.println(h.rowname.getValue()+" >>"+h.value.getValue());
        }
    }

    /**
     * 事务测试
     * @throws Exception
     */
    @Test
    public  void transaction() throws Exception {
        Transaction t = new Transaction(DataSourceTest.getDataSourceByDruid());
        Hstest hstest = new Hstest();
        hstest.setLoggerOn(true);
        hstest.setTransaction(t);
        hstest.rowname.setValue("wu");
        hstest.value.setValue("dong");
        hstest.insert();
        Hstest hstest2 = new Hstest();
        hstest2.setLoggerOn(true);
        hstest2.setTransaction(t);
        hstest2.rowname.setValue("wu2");
        hstest2.value.setValue("dong2");
        hstest2.insert();
        DBUtil dt = new DBUtil();
        dt.setTransaction(t);
        dt.execute("insert into hstest(`rowname`,`value`)values(?,?)", 1, 2);
//      t.commit();
        t.rollBackAndClose();
    }


    @Test
    /**
     * 翻页多行返回
     * @throws Exception
     */
    public  void testPageTurn() throws JException {
        Hstest ht = new Hstest();
        ht.setPageTurn(true);
        ht.where(ht.id.GE(0));
        List<Hstest> list = ht.select();
        System.out.println("totalcount:" + list.get(0).getTotalcount());
        for (Hstest h : list) {
            System.out.println(h.rowname.getValue() + " " + h.value.getValue());
        }
    }

    @Test
    /**
     * 翻页多行返回
     *
     * @throws Exception
     */
    public  void testPageDao() throws Exception {
        Hstest ht = new Hstest();
        ht.where(ht.id.GE(0));
        PageBean<Hstest> pd = ht.selectListPage();
        System.out.println("totalcount:" + pd.getTotalcount());
        List<Hstest> list = pd.getList();
        for (Hstest h : list) {
            System.out.println(h.rowname.getValue() + " " + h.value.getValue());
        }
    }

    /**
     * batch 测试
     */
    @Test
    public void testBatch() throws JException {
        Hstest ht = new Hstest();
        ht.rowname.setValue("1111");
        ht.value.setValue("2222");
        ht.addBatch();
        ht.rowname.setValue("3333");
        ht.value.setValue("4444");
        ht.addBatch();
        ht.endBatch();
    }
}
