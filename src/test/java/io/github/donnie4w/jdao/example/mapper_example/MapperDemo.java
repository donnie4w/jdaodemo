package io.github.donnie4w.jdao.example.mapper_example;

import io.github.donnie4w.jdao.action.DataSourceFactory;
import io.github.donnie4w.jdao.dao.Hstest;
import io.github.donnie4w.jdao.dao.Hstest1;
import io.github.donnie4w.jdao.handle.DBType;
import io.github.donnie4w.jdao.handle.Jdao;
import io.github.donnie4w.jdao.handle.JdaoClassException;
import io.github.donnie4w.jdao.handle.JdaoException;
import io.github.donnie4w.jdao.mapper.JdaoMapper;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

//通过命名空间namespace与标签Id，调用映射SQL
public class MapperDemo {
    static JdaoMapper jdaoMapper;

    static {
        Jdao.init(DataSourceFactory.getDataSourceBySqlite(), DBType.SQLITE);
        Jdao.setLogger(true);
        try {
            JdaoMapper.build("jdao-config.xml");
            jdaoMapper = JdaoMapper.newInstance();
        } catch (JdaoException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void selectOne() throws JdaoException, JdaoClassException, SQLException {
        Hstest hs = jdaoMapper.selectOne("io.github.donnie4w.jdao.action.Mapperface.selectHstestById", 2, 26);
        System.out.println(hs);
    }

    @Test
    public void selectList() throws JdaoException, JdaoClassException, SQLException {
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
    public void insert() throws JdaoException, SQLException, JdaoClassException {
        Hstest1 hs = new Hstest1();
        hs.setGoto("123456789".getBytes());
        hs.setRowname("tom:" + new Random(System.nanoTime()).nextInt());
        hs.setValue("hello".getBytes());
        int i = jdaoMapper.insert("io.github.donnie4w.jdao.action.Mapperface.insertHstest1", hs);
        System.out.println(i);
    }

}
