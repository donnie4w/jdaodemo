package io.github.donnie4w.jdao.example.mapper_example;

import io.github.donnie4w.jdao.action.DataSourceFactory;
import io.github.donnie4w.jdao.action.Mapperface;
import io.github.donnie4w.jdao.dao.Hstest;
import io.github.donnie4w.jdao.dao.Hstest1;
import io.github.donnie4w.jdao.handle.*;
import io.github.donnie4w.jdao.mapper.JdaoMapper;
import org.junit.Test;

import java.sql.SQLException;
import java.util.*;

// XML配置映射为Java接口
public class InterfaceDemo {
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

    // XML 映射为接口 Mapperface
    @Test
    public void selectMapperFace1() throws JdaoException {
        Mapperface mapper = jdaoMapper.getMapper(Mapperface.class);
        Hstest1 hstest1 = mapper.selectHstest1(1);
        System.out.println(hstest1);
    }

    // XML 映射为接口 Mapperface
    @Test
    public void selectMapperFace2() throws JdaoException, JdaoClassException, SQLException {
        Mapperface mapper = jdaoMapper.getMapper(Mapperface.class);
        List<Hstest> list = mapper.selectHstestById(Integer.valueOf(5), Integer.valueOf(20));
        for (Hstest hs : list) {
            System.out.println(hs);
        }
    }

    // XML 映射为接口 Mapperface
    @Test
    public void selectMapperFace3() throws JdaoException {
        Mapperface mapper = jdaoMapper.getMapper(Mapperface.class);
        List<Hstest> list = mapper.selectHstestByList(5, 20);
        for (Hstest hs : list) {
            System.out.println(hs);
        }
    }



}
