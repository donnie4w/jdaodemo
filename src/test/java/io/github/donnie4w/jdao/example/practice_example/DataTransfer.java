package io.github.donnie4w.jdao.example.practice_example;

import io.github.donnie4w.jdao.action.DataSourceFactory;
import io.github.donnie4w.jdao.base.Table;
import io.github.donnie4w.jdao.dao.Hstest1;
import io.github.donnie4w.jdao.handle.*;
import org.junit.Test;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * 迁移数据库表数据的示例程序
 */
public class DataTransfer {


    public <T extends Table<?>> void saveByClass(DBhandle dBhandle, Class<T> clz, List<T> list) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, JdaoException, SQLException {
        Table toTable = clz.getDeclaredConstructor().newInstance().useDBhandle(dBhandle);
        for (T t : list) {
            toTable.copy(t);
            toTable.addBatch();
        }
        toTable.executeBatch();
    }


    public <T extends Table<?>> void transferByClass(DBhandle toDB, Class<T> clz) throws JdaoException, SQLException, JdaoClassException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        DataSource dataSource = DataSourceFactory.getDataSourceByMysql();
        Jdao.bindDataSource(clz, dataSource, DBType.MYSQL);
        int step = 10;
        int startindex = 0;
        while (true) {
            Table t = clz.getDeclaredConstructor().newInstance();
            t.limit(startindex, step);
            List<T> list = t.selects(); //query data
            if (list != null)
                saveByClass(toDB, clz, list);
            System.out.println("save size:" + list.size());
            if (list == null || list.size() < step)
                break;
            startindex = startindex + step;
        }
        System.out.println("finish to transfer");
    }

    @Test
    public void TestTransferHstest1() throws JdaoException, JdaoClassException, SQLException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        DBhandle toDB = Jdao.newDBhandle(DataSourceFactory.getDataSourceByPostgre(), DBType.POSTGRESQL);
        deleteTableData(toDB,Hstest1.class);
        showTableData(toDB,Hstest1.class);
        transferByClass(toDB, Hstest1.class);
        System.out.println("--------------Complete Table Hstest1 data move from mysql to postgreSql---------------");
        showTableData(toDB, Hstest1.class);
    }

   //delete from table
    public <T extends Table<?>> void deleteTableData(DBhandle dBhandle, Class<T> clz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, JdaoException, SQLException {
        Table t = clz.getDeclaredConstructor().newInstance().useDBhandle(dBhandle);
        int i = t.delete();
        System.out.println("delete from table:" + t.TableName() + ",delete size:" + i);
    }

    //show all data from table
    public <T extends Table<?>> void showTableData(DBhandle dBhandle, Class<T> clz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, JdaoException, JdaoClassException, SQLException {
        Table t = clz.getDeclaredConstructor().newInstance().useDBhandle(dBhandle);
        List<T> list = t.selects();
        for (T v : list) {
            System.out.println(v);
        }
    }

}
