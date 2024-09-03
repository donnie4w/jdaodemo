package io.github.donnie4w.jdao.example.mapper_example;

import io.github.donnie4w.jdao.action.DataSourceFactory;
import io.github.donnie4w.jdao.dao.Hstest;
import io.github.donnie4w.jdao.handle.*;
import io.github.donnie4w.jdao.mapper.JdaoMapper;
import org.junit.Test;

import java.sql.SQLException;

//SQL映射模块的读写分离
public class Slave {
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
    public void selectSlave() throws JdaoException, JdaoClassException, SQLException {
        //绑定namespace select id 的从库数据源
        JdaoSlave.bindMapper("io.github.donnie4w.jdao.action.Mapperface", "selectHstest", DataSourceFactory.getDataSourceByMysql(), DBType.MYSQL);
        JdaoSlave.bindMapper("io.github.donnie4w.jdao.action.Mapperface", "selectHstest", DataSourceFactory.getDataSourceByPostgre(), DBType.POSTGRESQL);

        //返回备库数据
        Hstest hstest = jdaoMapper.selectOne("io.github.donnie4w.jdao.action.Mapperface.selectHstest", 5, 30);
        System.out.println(hstest);

        //解除绑定
        JdaoSlave.unbindMapper("io.github.donnie4w.jdao.action.Mapperface", "selectHstest");
        //返回主库数据
        Hstest hstest1 = jdaoMapper.selectOne("io.github.donnie4w.jdao.action.Mapperface.selectHstest", 5, 30);
        System.out.println(hstest1);
    }
}
