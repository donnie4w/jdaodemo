package io.github.donnie4w.jdao.example.dao_example;

import io.github.donnie4w.jdao.action.DataSourceFactory;
import io.github.donnie4w.jdao.dao.Hstest;
import io.github.donnie4w.jdao.handle.*;
import org.junit.Test;

import java.sql.SQLException;


//标准化实体类的缓存绑定，清除，解绑 示例程序
public class Cache {

    static {
        Jdao.init(DataSourceFactory.getDataSourceBySqlite(), DBType.SQLITE);
        Jdao.setLogger(true);
    }

    @Test
    public void cache() throws JdaoException, JdaoClassException, SQLException {
        //绑定类的数据缓存
        JdaoCache.bindClass(Hstest.class);

        //第一次查询，缓存池无数据，则将查询结果放入缓存池
        Hstest t = new Hstest();
        t.where(Hstest.ID.EQ(3));
        Hstest hs = t.select();
        System.out.println(hs);
        System.out.println("----------------------SET CACHE---------------------");

        //第二次查询，缓存池有数据，则从缓存池中获取数据
        Hstest t2 = new Hstest();
        t2.where(Hstest.ID.EQ(3));
        Hstest hs2 = t2.select();
        System.out.println(hs2);
        System.out.println("----------------------GET CACHE---------------------");

        //比较两次查询的对象是否相同
        System.out.println("hs.equals(hs2):" + hs.equals(hs2));

        //清除Hstest类的缓存数据，则后续查询重新将查询结果放入缓存池
        JdaoCache.clear(Hstest.class);
        t2 = new Hstest();
        t2.where(Hstest.ID.EQ(3));
        hs2 = t2.select();
        System.out.println(hs2);
        System.out.println("----------------------SET CACHE AGAIN---------------------");
    }


    @Test
    public void cache2() throws JdaoException, JdaoClassException, SQLException {
        JdaoCache.bindClass(Hstest.class, new CacheHandle(100));
        //绑定类的数据缓存
        JdaoCache.bindClass(Hstest.class);

        //第一次查询，缓存池无数据，则将查询结果放入缓存池
        Hstest t = new Hstest();
        t.where(Hstest.ID.EQ(3));
        Hstest hs = t.select();
        System.out.println(hs);
        System.out.println("----------------------SET CACHE---------------------");

        //第二次查询，缓存池有数据，则从缓存池中获取数据
        Hstest t2 = new Hstest();
        t2.where(Hstest.ID.EQ(3));
        Hstest hs2 = t2.select();
        System.out.println(hs2);
        System.out.println("----------------------GET CACHE---------------------");

        //解绑Hstest类的缓存数据，则后续查询不再将查询结果放入缓存池
        JdaoCache.unbindClass(Hstest.class);
        t2 = new Hstest();
        t2.where(Hstest.ID.EQ(3));
        hs2 = t2.select();
        System.out.println(hs2);
        System.out.println("----------------------NO CACHE---------------------");
    }
}
