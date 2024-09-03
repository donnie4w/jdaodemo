package io.github.donnie4w.jdao.example.mapper_example;

import io.github.donnie4w.jdao.action.DataSourceFactory;
import io.github.donnie4w.jdao.action.Mapperface;
import io.github.donnie4w.jdao.dao.Hstest;
import io.github.donnie4w.jdao.handle.*;
import io.github.donnie4w.jdao.mapper.JdaoMapper;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

//SQL映射模块的缓存测试
public class Cache {
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
    public void cache1() throws JdaoException, JdaoClassException, SQLException {
        //绑定namespace:io.github.donnie4w.jdao.action.Mapperface   select id:selectHstest
        JdaoCache.bindMapper("io.github.donnie4w.jdao.action.Mapperface","selectHstest");

        //第一次查询，缓存池没有数据，则将查询结果放入缓冲池
        List<Hstest> list = jdaoMapper.selectList("io.github.donnie4w.jdao.action.Mapperface.selectHstest",5,20);
        for (Hstest hs : list) {
            System.out.println(hs);
        }
        System.out.println("------------------SET CACHE---------------------");

        //第二次查询，缓存池有数据，则将缓冲池数据返回
        list = jdaoMapper.selectList("io.github.donnie4w.jdao.action.Mapperface.selectHstest",5,20);
        for (Hstest hs : list) {
            System.out.println(hs);
        }
        System.out.println("------------------GET CACHE---------------------");

        //清除缓存  ，则重新则将查询结果放入缓冲池
        JdaoCache.clear("io.github.donnie4w.jdao.action.Mapperface","selectHstest");
        list = jdaoMapper.selectList("io.github.donnie4w.jdao.action.Mapperface.selectHstest",5,20);
        for (Hstest hs : list) {
            System.out.println(hs);
        }
        System.out.println("------------------SET CACHE AGAIN---------------------");
    }

    @Test
    public void cache2() throws JdaoException {
        //JdaoCache 绑定映射接口是所有方法
        JdaoCache.bindMapper(Mapperface.class);

        //获取 接口映射对象
        Mapperface mapper = jdaoMapper.getMapper(Mapperface.class);

        //第一次查询，缓存池没有数据，则将查询结果放入缓冲池
        List<Hstest> list = mapper.selectHstest(5, 20);
        for (Hstest hs : list) {
            System.out.println(hs);
        }
        System.out.println("------------------SET CACHE---------------------");

        //第二次查询，缓存池有数据，则将缓冲池数据返回
        list = mapper.selectHstest(5, 20);
        for (Hstest hs : list) {
            System.out.println(hs);
        }
        System.out.println("------------------GET CACHE---------------------");

        //清除缓存  ，则重新则将查询结果放入缓冲池
        JdaoCache.clear(Mapperface.class.getName(),"selectHstest");
        list = mapper.selectHstest(5, 20);
        for (Hstest hs : list) {
            System.out.println(hs);
        }
        System.out.println("------------------SET CACHE AGAIN---------------------");
    }


    @Test
    public void cache3() throws JdaoException, JdaoClassException, SQLException {
        //绑定namespace:io.github.donnie4w.jdao.action.Mapperface   select id:selectHstest
        JdaoCache.bindMapper("io.github.donnie4w.jdao.action.Mapperface","selectHstest");

        //第一次查询，缓存池没有数据，则将查询结果放入缓冲池
        List<Hstest> list = jdaoMapper.selectList("io.github.donnie4w.jdao.action.Mapperface.selectHstest",5,20);
        for (Hstest hs : list) {
            System.out.println(hs);
        }
        System.out.println("------------------SET CACHE---------------------");

        //解除绑定 ，则不再将查询结果放入缓冲池
        JdaoCache.unbindMapper("io.github.donnie4w.jdao.action.Mapperface","selectHstest");
        list = jdaoMapper.selectList("io.github.donnie4w.jdao.action.Mapperface.selectHstest",5,20);
        for (Hstest hs : list) {
            System.out.println(hs);
        }
        System.out.println("------------------NO CACHE---------------------");
    }
}
