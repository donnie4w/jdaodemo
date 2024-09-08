package io.github.donnie4w.jdao.example.nativesql_example;

import io.github.donnie4w.jdao.action.DataSourceFactory;
import io.github.donnie4w.jdao.dao.Hstest;
import io.github.donnie4w.jdao.handle.*;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Jdao API 构建动态SQL示例程序
public class SqlBuilderDemo {
    static {
        Jdao.init(DataSourceFactory.getDataSourceBySqlite(), DBType.SQLITE);
        Jdao.setLogger(true);
    }

    //append appendIf
    @Test
    public void testAppendIf() throws JdaoException, SQLException {
        Map<String, Object> context = new HashMap<>();
        context.put("id", 31);

        SqlBuilder builder = SqlBuilder.newInstance();
        builder.append("SELECT * FROM HSTEST where 1=1")
                .appendIf("id>0", context, "and id=?", context.get("id"))
                .append("ORDER BY id ASC");

        List<DataBean> list = builder.selectList();
        for (DataBean bean : list) {
            System.out.println(bean);
        }
    }

    //appendChoose
@Test
public void testAppendChoose() throws JdaoException, SQLException {
    Hstest hstest = new Hstest();
    hstest.setRowname("www>>>>2");
    hstest.setId(31);
    hstest.setValue("2421209491375900");
    hstest.setAge(31);

    SqlBuilder builder = SqlBuilder.newInstance();
    builder.append("SELECT * FROM HSTEST where 1=1")
            .appendChoose(hstest, choose -> choose
                    .when(" age <30 && age>10", "AND age =?", hstest.getAge())
                    .when("rowname!=null", "AND rowname like ?", "%" + hstest.getRowname() + "%")
                    .otherwise("AND id >0")).append("limit 2");

    List<DataBean> list = builder.selectList();
    for (DataBean bean : list) {
        System.out.println(bean);
    }
}

    //appendTrim
    @Test
    public void testAppendTrim() throws JdaoException, SQLException {
        Map<String, Object> context = new HashMap();
        context.put("id", 30);
        context.put("rowname", "www>>>10");

        SqlBuilder builder = SqlBuilder.newInstance();
        builder.append("SELECT * FROM hstest")
                .appendTrim("WHERE", null, "AND|OR", null, trimBuilder -> {
                    trimBuilder.appendIf("id > 10", context, "AND id > ?", context.get("id"))
                            .appendChoose(context, choose -> choose
                                    .when(" id > 40 && id<50", "AND id <> ?", context.get("id"))
                                    .when("rowname!=null", "AND rowname like ?", "%www%")
                                    .otherwise("AND id >0")
                            );
                })
                .append("limit 2");


        List<DataBean> list = builder.selectList();
        for (DataBean bean : list) {
            System.out.println(bean);
        }
    }

    //appendForeach
    @Test
    public void testAppendForeach() throws JdaoException, SQLException {
        SqlBuilder builder = SqlBuilder.newInstance();
        builder.append("SELECT * FROM hstest where id in")
                .appendForeach(null, new int[]{31, 32, 33}, "item", ",", "(", ")", foreachBuilder -> foreachBuilder
                        .body("#{item}")
                );
        List<DataBean> list = builder.selectList();
        for (DataBean bean : list) {
            System.out.println(bean);
        }
    }

    //appendForeach
    @Test
    public void testAppendForeach2() throws JdaoException, SQLException {
        SqlBuilder builder = SqlBuilder.newInstance();
        builder.append("SELECT * FROM hstest where id in")
                .appendForeach("list", new int[]{31, 32, 33}, "item", ",", "(", ")", foreachBuilder -> foreachBuilder
                        .body("#{item}")
                );
        List<DataBean> list = builder.selectList();
        for (DataBean bean : list) {
            System.out.println(bean);
        }
    }


    //appendForeach
    @Test
    public void testAppendForeach3() throws JdaoException, SQLException {
        Map<String, Object> context = new HashMap<>();
        context.put("ids", Arrays.asList(31, 32, 33));
        SqlBuilder builder = SqlBuilder.newInstance();
        builder.append("SELECT * FROM hstest where id in")
                .appendForeach("ids", context, "item", ",", "(", ")", foreachBuilder -> foreachBuilder
                        .body("#{item}")
                );
        List<DataBean> list = builder.selectList();
        for (DataBean bean : list) {
            System.out.println(bean);
        }
    }

    //appendForeach
    @Test
    public void sqlbuilder5() throws JdaoException, SQLException {
        Map<String, Object> context = new HashMap<>();
        context.put("id", 30);
        context.put("rowname", "www>>>10");
        context.put("ids", new int[]{31, 32, 33, 34, 35});

        SqlBuilder builder = SqlBuilder.newInstance();
        builder.append("SELECT * FROM hstest where 1=1")
                .appendChoose(context, choose -> choose
                        .when(" id > 40 && id<50", "AND id <> ?", context.get("id"))
                        .when("rowname!=null", "AND rowname like ?", "%www%")
                        .otherwise("AND id >0")
                )
                .append("AND id in")
                .appendForeach("ids", context, "item", ",", "(", ")", foreachBuilder -> foreachBuilder
                        .body("#{item}")
                )
                .append("limit 2");

        List<DataBean> list = builder.selectList();
        for (DataBean bean : list) {
            System.out.println(bean);
        }
    }

    //appendSet
    @Test
    public void sqlbuilder6() throws JdaoException, SQLException {
        Hstest hstest = new Hstest();
        hstest.setRowname("helloworld");
        hstest.setId(31);
        hstest.setValue("2421209491375900");
        hstest.setAge(35);

        SqlBuilder builder = SqlBuilder.newInstance();
        builder.append("update hstest")
                .appendSet(setBuilder -> setBuilder
                        .appendTrim(null, null, null, ",", trimBuidler -> trimBuidler
                                .appendIf("value!=null", hstest, "value=?,", hstest.getValue())
                                .appendIf("age>0", hstest, "age=?,", hstest.getAge())
                                .appendIf("rowname!=null", hstest, "rowname=?,", hstest.getRowname())
                        )
                )
                .appendIf("id==31", hstest, "where id=?", hstest.getId())
                .appendIf("id!=31", hstest, "where id=?", 1);

        int i = builder.exec();
    }
}