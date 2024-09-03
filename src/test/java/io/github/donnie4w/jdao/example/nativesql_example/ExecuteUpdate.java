package io.github.donnie4w.jdao.example.nativesql_example;

import io.github.donnie4w.jdao.action.DataSourceFactory;
import io.github.donnie4w.jdao.handle.DBType;
import io.github.donnie4w.jdao.handle.Jdao;
import io.github.donnie4w.jdao.handle.JdaoException;
import org.junit.Test;

import java.sql.SQLException;

// Jdao Api
// Jdao执行原生SQL的示例
public class ExecuteUpdate {
    static {
        Jdao.init(DataSourceFactory.getDataSourceBySqlite(), DBType.SQLITE);
        Jdao.setLogger(true);
    }

    @Test
    public void insert() throws JdaoException, SQLException {
        int i = Jdao.executeUpdate("insert into hstest1(`rowname`,`value`) values(?,?)", "uuuuu>>>>1", "ppppppppp");
    }

    @Test
    public void update() throws JdaoException, SQLException {
        int i = Jdao.executeUpdate("insert into hstest1(`rowname`,`value`) values(?,?)", "uuuuu>>>>1", "ppppppppp");
    }

    @Test
    public void delete() throws JdaoException, SQLException {
        int i = Jdao.executeUpdate("insert into hstest1(`rowname`,`value`) values(?,?)", "uuuuu>>>>1", "ppppppppp");
    }
}
