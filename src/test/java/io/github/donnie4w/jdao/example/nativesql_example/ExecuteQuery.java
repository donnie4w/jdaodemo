package io.github.donnie4w.jdao.example.nativesql_example;

import io.github.donnie4w.jdao.action.DataSourceFactory;
import io.github.donnie4w.jdao.dao.Hstest1;
import io.github.donnie4w.jdao.handle.DBType;
import io.github.donnie4w.jdao.handle.DataBean;
import io.github.donnie4w.jdao.handle.Jdao;
import org.junit.Test;

import java.util.List;


// Jdao Api
// Jdao执行原生SQL的示例
public class ExecuteQuery {

    static {
        Jdao.init(DataSourceFactory.getDataSourceBySqlite(), DBType.SQLITE);
        Jdao.setLogger(true);
    }

    @Test
    public void executeQueryBean() throws Exception {
        DataBean dataBean = Jdao.executeQueryBean("select * from Hstest1 limit 1");
        System.out.println(dataBean);
    }


    @Test
    public void executeQueryBeans() throws Exception {
        List<DataBean> dblist = Jdao.executeQueryBeans("select * from Hstest1 limit 5");
        for (DataBean dataBean : dblist) {
            System.out.println(dataBean);
        }
    }


    @Test
    public void executeQuery() throws Exception {
        Hstest1 hstest1 = Jdao.executeQuery(Hstest1.class, "select * from Hstest1 limit 1");
        System.out.println(hstest1);
    }

    @Test
    public void executeQueryList() throws Exception {
        List<Hstest1> hstest1s = Jdao.executeQueryList(Hstest1.class, "select * from Hstest1 limit 5");
        for (Hstest1 hstest1 : hstest1s) {
            System.out.println(hstest1);
        }
    }


}
