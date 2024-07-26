package io.github.donnie4w.jdao.practice;

import io.github.donnie4w.jdao.action.DataSourceFactory;
import io.github.donnie4w.jdao.base.Table;
import io.github.donnie4w.jdao.dao.Hstest1;
import io.github.donnie4w.jdao.handle.DBType;
import io.github.donnie4w.jdao.handle.Jdao;
import io.github.donnie4w.jdao.handle.JdaoClassException;
import io.github.donnie4w.jdao.handle.JdaoException;
import org.junit.Test;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

//数据库迁移
public class DataTransfer {


    public <T extends Table<?>> void saveByClass(Class<T> clz, List<T> list) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, JdaoException, SQLException {
        DataSource dataSource = DataSourceFactory.getDataSourceByPostgre();
        Table toTable = clz.getDeclaredConstructor().newInstance();
        toTable.useDataSource(dataSource, DBType.POSTGRESQL);
        for (T t : list) {
            toTable.copy(t);
            toTable.addBatch();
        }
        toTable.executeBatch();
    }


    public <T extends Table<?>> void transferByClass(Class<T> clz) throws JdaoException, SQLException, JdaoClassException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        DataSource dataSource = DataSourceFactory.getDataSourceByMysql();
        Jdao.setDataSource(clz, dataSource, DBType.MYSQL);
        int step = 10;
        int startindex = 0;
        while (true) {
            Table t = clz.getDeclaredConstructor().newInstance();
            t.limit(startindex, step);
            List<T> list = t.selects(); //数据查询
            if (list != null)
                saveByClass(clz, list);
                System.out.println("save size:" + list.size());
            if (list == null || list.size() < step)
                break;
            startindex = startindex + step;
        }
        System.out.println("finish to transfer");
    }

    @Test
    public void TestTransferHstest1() throws JdaoException, JdaoClassException, SQLException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        transferByClass(Hstest1.class);
        System.out.println("--------------完成表Hstest1数据从mysql移动到postgreSql---------------");
        DataSource dataSource = DataSourceFactory.getDataSourceByPostgre();
        Hstest1 hstest1 = new Hstest1().useDataSource(dataSource, DBType.POSTGRESQL);
        List<Hstest1> list = hstest1.selects();
        for (Hstest1 h : list) {
            System.out.println(h);
        }
    }

}
