package io.github.donnie4w.jdao.example.dao_example;

import io.github.donnie4w.jdao.action.DataSourceFactory;
import io.github.donnie4w.jdao.dao.Hstest1;
import io.github.donnie4w.jdao.dao.Hstest2;
import io.github.donnie4w.jdao.handle.DBType;
import io.github.donnie4w.jdao.handle.Jdao;
import io.github.donnie4w.jdao.handle.JdaoClassException;
import io.github.donnie4w.jdao.handle.JdaoException;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class Select {
    static {
        Jdao.init(DataSourceFactory.getDataSourceBySqlite(), DBType.SQLITE);
        Jdao.setLogger(true);
    }

    // SQL:select * from hstest1 where id>? LIMIT ? OFFSET ?
    // params: [1, 10, 20]
    @Test
    public void select() throws JdaoException, JdaoClassException, SQLException {
        Hstest1 t = new Hstest1();
        t.where(Hstest1.ID.GT(1));
        t.limit(20, 10);
        Hstest1 hstest1 = t.select();
        System.out.println(hstest1);
    }


    // SQL:select * from hstest1 where id>? LIMIT ? OFFSET ?
    // params: [1, 10, 20]
    @Test
    public void selects() throws JdaoException, JdaoClassException, SQLException {
        Hstest1 t = new Hstest1();
        t.where(Hstest1.ID.GT(1));
        t.limit(20, 10);
        List<Hstest1> list = t.selects();
        for (Hstest1 hstest1 : list) {
            System.out.println(hstest1);
        }
    }

    //SQL: select * from hstest2 where id<=? and id>=? or id=? group by id having  count(id) <? order by id asc LIMIT ? OFFSET ?
    //params: [3, 0, 10, 2, 5, 0]
    @Test
    public void selects2() throws JdaoException, JdaoClassException, SQLException {
        Hstest2 hs2 = new Hstest2()
                .where(Hstest2.ID.LE(3), Hstest2.ID.GE(0).OR(Hstest2.ID.EQ(10)))
                .groupBy(Hstest2.ID)
                .having(Hstest2.ID.count().LT(2))
                .orderBy(Hstest2.ID.asc())
                .limit(0, 5);

        List<Hstest2> list = hs2.selects();
        for (Hstest2 hs : list) {
            System.out.println(hs);
        }
    }

}
