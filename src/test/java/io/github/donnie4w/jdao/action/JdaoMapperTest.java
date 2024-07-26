package io.github.donnie4w.jdao.action;

import io.github.donnie4w.jdao.dao.Hstest;
import io.github.donnie4w.jdao.dao.Hstest1;
import io.github.donnie4w.jdao.handle.*;
import io.github.donnie4w.jdao.mapper.JdaoMapper;
import org.junit.Test;

import java.sql.SQLException;
import java.util.*;

public class JdaoMapperTest {
    static JdaoMapper jdaoMapper;

    static {
        Jdao.init(DataSourceFactory.getDataSourceBySqlite(), DBType.SQLITE);
        Jdao.setLogger(true);
        try {
            JdaoMapper.build("mappers.xml");
            jdaoMapper = JdaoMapper.newInstance();
        } catch (JdaoException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void selectByMapperId() throws JdaoException, JdaoClassException, SQLException {

        Hstest hs = jdaoMapper.selectOne("io.github.donnie4w.jdao.action.Mapperface.selectHstestById", 2, 26);
        System.out.println(hs);

        List<Hstest> list = jdaoMapper.selectList("io.github.donnie4w.jdao.action.Mapperface.selectHstestById", 4, 30);
        for (Hstest h : list) {
            System.out.println(h);
        }

        List<Hstest1> list2 = jdaoMapper.selectList("io.github.donnie4w.jdao.action.Mapperface.selectHstest1", 5);
        for (Hstest1 h : list2) {
            System.out.println(h);
        }
    }

    @Test
    public void selectByMapperFace() throws JdaoException, JdaoClassException, SQLException {

        Mapperface mapper = jdaoMapper.getMapper(Mapperface.class);

        Hstest hs = mapper.selectHstestById(2, 26);
        // 等价于
        // Hstest hs = jdaoMapper.selectOne("io.github.donnie4w.jdao.action.Mapperface.selectHstestById", 2, 26);
        System.out.println(hs);


        List<Hstest> list = mapper.selectHstestById(Integer.valueOf(4), Integer.valueOf(30));
        // 等价于
        //List<Hstest> list = jdaoMapper.selectList("io.github.donnie4w.jdao.action.Mapperface.selectHstestById", 4, 30);
        for (Hstest h : list) {
            System.out.println(h);
        }


        List<Hstest1> list2 = mapper.selectHstest1(Long.valueOf(5));
        // 等价于
        // List<Hstest1> list2 = jdaoMapper.selectList("io.github.donnie4w.jdao.action.Mapperface2.selectHstest1",  5);
        for (Hstest1 h : list2) {
            System.out.println(h);
        }
    }


    @Test
    public void insertByMapperId() throws JdaoException, SQLException, JdaoClassException {
        //insert test
        Hstest1 hs = new Hstest1();
        hs.setGoto("12345".getBytes());
        hs.setRowname("rowname" + new Random(System.nanoTime()).nextInt());
        hs.setValue("abc".getBytes());
        jdaoMapper.insert("io.github.donnie4w.jdao.action.Mapperface.insertHstest1", hs);

        Hstest1 hs1 = jdaoMapper.selectOne("io.github.donnie4w.jdao.action.Mapperface.selectHstest1", 1);
        System.out.println(hs1);
        System.out.println("hs1 vs hs >> " + hs1.getRowname().equals(hs.getRowname()));
    }

    @Test
    public void selectMapperFace() throws JdaoException, JdaoClassException, SQLException {
        Mapperface mapper = jdaoMapper.getMapper(Mapperface.class);

        List<Hstest> list = mapper.selectHstestById(Integer.valueOf(5), Integer.valueOf(20));
        for (Hstest hs : list) {
            System.out.println(hs);
        }

        Hstest hstest = mapper.selectHstestById(5, 20);
        System.out.println(hstest);

        //同样的条件与结果
        Hstest hstest1 = Jdao.executeQuery(Hstest.class, "select * from hstest where id<? and age<?", 5, 20);
        System.out.println(hstest1);

        //同样的条件与结果
        DataBean dataBean = Jdao.executeQueryBean("select * from hstest where id<? and age<?", 5, 20);
        System.out.println(dataBean);
    }


    @Test
    public void selectMapperFace2() throws JdaoException {
        Mapperface mapper = jdaoMapper.getMapper(Mapperface.class);

        Hstest1 hstest1 = mapper.selectHstest1(1);
        System.out.println("--------------last data----------------");
        System.out.println(hstest1);

        //insert test
        hstest1 = new Hstest1();
        hstest1.setGoto("12345".getBytes());
        hstest1.setRowname("rowname" + new Random(System.nanoTime()).nextInt());
        hstest1.setValue("abc".getBytes());
        mapper.insertHstest1(hstest1);

        System.out.println("--------------insert over----------------");
        hstest1 = mapper.selectHstest1(1);
        System.out.println(hstest1);

        //update test
        hstest1.setRowname("rowname>>>update");
        mapper.updateHstest1(hstest1);

        System.out.println("--------------update over----------------");
        hstest1 = mapper.selectHstest1(1);
        System.out.println(hstest1);

        //delete Test
        mapper.deleteHstest1(hstest1);
        hstest1 = mapper.selectHstest1(1);

        System.out.println("--------------delete over----------------");
        System.out.println(hstest1);
    }

    @Test
    public void selectMapperFace3() throws JdaoException {
        Mapperface mapper = jdaoMapper.getMapper(Mapperface.class);

        //input type : map  , output type : Hstest
        Map<String, Object> map = new HashMap<>();
        map.put("id", 5);
        map.put("age", 20);
        Hstest hstest = mapper.selectHstestByMap(map);
        System.out.println(hstest);
        System.out.println("----------------input type Map---------------------");

        //input type : list , output type : Hstest[]
        List<Object> list = new ArrayList<>();
        list.add(5);
        list.add(20);
        Hstest[] hstest2 = mapper.selectHstestByList(list);
        for (Hstest hs : hstest2) {
            System.out.println(hs);
        }
        System.out.println("----------------input type List---------------------");

        //input type int... or int[] . output type List<Hstest>
        List<Hstest> list2 = mapper.selectHstestByList(5, 20);
        for (Hstest hs : list2) {
            System.out.println(hs);
        }
        System.out.println("----------------input type int[]---------------------");
    }

    @Test
    public void selectMapperFace4() throws JdaoException {
        Mapperface mapper = jdaoMapper.getMapper(Mapperface.class);

        //参数 int
        Hstest1 hstest1 = mapper.selectHstest1(1);
        System.out.println(hstest1);

        //参数 long
        List<Hstest1> list = mapper.selectHstest1(3L);
        for (Hstest1 hs : list) {
            System.out.println(hs);
        }
    }

    @Test
    public void selectCache() throws JdaoException {
        JdaoCache.bindMapper(Mapperface.class, new CacheHandle(100));

        Mapperface mapper = jdaoMapper.getMapper(Mapperface.class);

        List<Hstest> list = mapper.selectHstest(5, 20);
        for (Hstest hs : list) {
            System.out.println(hs);
        }
        System.out.println("------------------SET CACHE---------------------");

        list = mapper.selectHstest(5, 20);
        for (Hstest hs : list) {
            System.out.println(hs);
        }
        System.out.println("------------------GET CACHE---------------------");

        JdaoCache.removeMapper(Mapperface.class);
        list = mapper.selectHstest(5, 20);
        for (Hstest hs : list) {
            System.out.println(hs);
        }
        System.out.println("------------------NO USE CACHE---------------------");
    }

    @Test
    public void selectSlave() throws JdaoException, JdaoClassException, SQLException {
        //set slave DataSource to Mapperface
        JdaoSlave.bindClass(Mapperface.class, DataSourceFactory.getDataSourceByMysql(), DBType.MYSQL);


        Mapperface mf = jdaoMapper.getMapper(Mapperface.class);
        List<Hstest> listSlave = mf.selectHstest(10, 30);
        for (Hstest hstest : listSlave) {
            System.out.println(hstest);
        }

        //use sqlite DateSource
        List<Hstest> listMaster = Jdao.executeQueryList(Hstest.class, "select * from hstest where id<? and age<?", 10, 30);
        for (Hstest hstest : listMaster) {
            System.out.println(hstest);
        }
    }

    @Test
    public void selectSlave2() throws JdaoException, JdaoClassException, SQLException {
        //绑定mapperId的从库数据源
        JdaoSlave.bindMapper("io.github.donnie4w.jdao.dao.Hstest.selectHstest", DataSourceFactory.getDataSourceByMysql(), DBType.MYSQL);

        JdaoMapper session = JdaoMapper.newInstance();
        List<Hstest> listSlave = session.selectList("io.github.donnie4w.jdao.dao.Hstest.selectHstest", 5, 20);
        for (Hstest hstest : listSlave) {
            System.out.println(hstest);
        }
    }
}
