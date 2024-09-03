package io.github.donnie4w.jdao.example.mapper_example;

import io.github.donnie4w.jdao.action.DataSourceFactory;
import io.github.donnie4w.jdao.dao.Hstest;
import io.github.donnie4w.jdao.handle.*;
import io.github.donnie4w.jdao.mapper.JdaoMapper;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//调用动态SQL
public class DynamicDemo {
    static JdaoMapper jdaoMapper;

    static {
        Jdao.init(DataSourceFactory.getDataSourceBySqlite(), DBType.SQLITE);
        Jdao.setLogger(true);
        try {
            JdaoMapper.build("jdao-dynamic.xml");
            jdaoMapper = JdaoMapper.newInstance();
        } catch (JdaoException e) {
            throw new RuntimeException(e);
        }
    }


    //测试 where if标签
    @Test
    public void demo1() throws JdaoException, JdaoClassException, SQLException {
        Hstest hs = new Hstest();
        hs.setId(31);
        hs.setRowname("hello");
        List<Hstest> listSlave = jdaoMapper.selectList("io.github.donnie4w.jdao.action.Dynamic.demo1", hs);
        for (Hstest hstest : listSlave) {
            System.out.println(hstest);
        }
    }

    //测试 trim
    @Test
    public void demo2() throws JdaoException, JdaoClassException, SQLException {
        Hstest hs = new Hstest();
        hs.setId(31);
        hs.setRowname("hello");
        List<Hstest> listSlave = jdaoMapper.selectList("io.github.donnie4w.jdao.action.Dynamic.demo2", hs);
        for (Hstest hstest : listSlave) {
            System.out.println(hstest);
        }
    }

    //测试 foreach
    @Test
    public void demo3() throws JdaoException, SQLException, JdaoClassException {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Hstest> hstest = jdaoMapper.selectList("io.github.donnie4w.jdao.action.Dynamic.demo3", list);
        for (Hstest hstest1 : hstest) {
            System.out.println(hstest1);
        }
    }

    //测试 foreach
    @Test
    public void demo4() throws JdaoException, SQLException, JdaoClassException {
        Hstest hs = new Hstest();
        hs.setId(31);
        Hstest hs2 = new Hstest();
        hs2.setId(32);
        Map<String, Object> map = new HashMap<>();
        List<Hstest> list = Arrays.asList(hs, hs2, new Hstest());
        map.put("hstest", list);
        List<Hstest> hstest = jdaoMapper.selectList("io.github.donnie4w.jdao.action.Dynamic.demo4", map);
        for (Hstest hstest1 : hstest) {
            System.out.println(hstest1);
        }
    }

    //测试 foreach
    @Test
    public void demo5() throws JdaoException, SQLException, JdaoClassException {
        List<Integer> list = Arrays.asList(11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        List<Hstest> hstest = jdaoMapper.selectList("io.github.donnie4w.jdao.action.Dynamic.demo5", list);
        for (Hstest hstest1 : hstest) {
            System.out.println(hstest1);
        }
    }

    //测试 choose
    @Test
    public void demo6() throws JdaoException, SQLException, JdaoClassException {
        Hstest hs = new Hstest();
        hs.setId(31);
        hs.setRowname("hello");
        List<Hstest> hstest = jdaoMapper.selectList("io.github.donnie4w.jdao.action.Dynamic.demo6", hs);
        for (Hstest hstest1 : hstest) {
            System.out.println(hstest1);
        }
    }

    //set
    @Test
    public void dome7() throws JdaoException, SQLException {
        Hstest hs = new Hstest();
        hs.setId(31);
        hs.setValue("22222222");
        hs.setRowname("hello");
        int i = jdaoMapper.update("io.github.donnie4w.jdao.action.Dynamic.dome7", hs);
        //检查是否更新正确
        DataBean db = Jdao.executeQueryBean("select * from hstest1 where id = 31");
        System.out.println(db);
    }

}
