package io.github.donnie4w.jdaodemo.dbutils;

import java.util.List;

import io.github.donnie4w.jdao.base.DBUtil;
import io.github.donnie4w.jdao.base.JException;
import io.github.donnie4w.jdaodemo.dataSource.DataSourceTest;
import org.junit.jupiter.api.Test;

/**
 * @Copyright (c) 2017, donnie4w@gmail.com All Rights Reserved.
 * @Author: dong
 * @Desc: DBUtil 原生 SQL 操作测试
 */
public class DBUtilTest {

    static {
        String filepath = System.getProperty("user.dir") + "/druid.properties";
        // 可以对不同DBUtils子类 设置不同的数据源
        DataSourceTest.init(filepath);
    }

    @Test
    public void testInsert() throws JException {
        DBUtil dt = new DBUtil();
        System.out.println(dt.execute("insert into hstest(`value`,`rowname`)values(?,?),(?,?) ", "wu1", "66", "wu2", "77"));
    }

    @Test
    /**
     * 针对分页封装的方法
     *
     * @throws Exception
     */
    public void testSelectsPage() throws JException {
        DBUtil rt = new DBUtil();
        // 分页查询方法
        rt.selectToPage(0, 3, "select * from hstest");
        System.out.println("size >>" + rt.rsList().size());
        // selectListPage 会返回 totalcount
        List<DBUtil> list = rt.rsList();
        for (DBUtil r : list) {
            System.out.println(r.getString("value"));
        }
    }

    @Test
    /**
     * 单行返回
     *
     * @throws Exception
     */
    public void testSelect() throws JException {
        DBUtil rt = new DBUtil();
        rt.selectSingle("select * from hstest where id=?", 1);
        System.out.println(rt.getString("value"));
        rt.selectSingle("select * from hstest where id=?", 2);
        System.out.println(rt.getString("value"));
    }

    @Test
    /**
     * 多行返回
     *
     * @throws Exception
     */
    public void testSelects() throws JException {
        DBUtil dt = new DBUtil();
        dt.select("select * from hstest where id>?", 0);
        System.out.println(dt.rsList().size());
        List<DBUtil> list = dt.rsList();
        for (DBUtil r : list) {
            System.out.println(r.getString("value"));
        }
    }

}