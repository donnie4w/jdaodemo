package com.myprogram.business;

import com.myprogram.dao.Hstest;
import com.myprogram.ormService.BaseDAO;
import com.myprogram.ormService.HstestService;
import io.github.donnie4w.jdao.base.Where;
import io.github.donnie4w.jdao.handle.DBType;
import io.github.donnie4w.jdao.handle.Jdao;
import io.github.donnie4w.jdao.handle.JdaoClassException;
import io.github.donnie4w.jdao.handle.JdaoException;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * 一般项目业务示例
 * 与持久层接口交互示例
 */
public class BusinessOne {

    static BaseDAO<Hstest> hstestService;

    static {
        Jdao.init(DataSourceFactory.getDataSourceBySqlite(), DBType.SQLITE);
        Jdao.setLogger(true);
        hstestService = new HstestService();
    }

    public Hstest hstestById(Object id) throws JdaoException, JdaoClassException, SQLException {
        return hstestService.findById(id);
    }

    public List<Hstest> hstestByCriteria(Where<Hstest>... wheres) throws JdaoException, JdaoClassException, SQLException {
        return hstestService.findByCriteria(wheres);
    }

    /**
     * 表hstest 相关业务操作
     */
    @Test
    public void BusinessWithHstest() throws JdaoException, JdaoClassException, SQLException {
        BusinessOne businessOne = new BusinessOne();
        System.out.println(businessOne.hstestById(1));

        List<Hstest> list = businessOne.hstestByCriteria(Hstest.ID.BETWEEN(1, 10));
        for (Hstest hstest : list) {
            System.out.println(hstest);
        }
    }

}
