package com.myprogram.ormService;

import com.myprogram.dao.Hstest;
import io.github.donnie4w.jdao.base.Where;
import io.github.donnie4w.jdao.handle.JdaoClassException;
import io.github.donnie4w.jdao.handle.JdaoException;

import java.sql.SQLException;
import java.util.List;

/**
 * 表 hstest的抽象层实现类
 */
public class HstestService extends BaseDAO<Hstest>{
    /**
     * @param entity 实体对象
     */
    @Override
    public void save(Hstest entity) throws JdaoException, SQLException {
        entity.insert();
    }

    /**
     * @param entity 实体对象
     */
    @Override
    public void update(Hstest entity) throws JdaoException, SQLException {
        entity.update();
    }

    /**
     * @param entity 实体对象
     */
    @Override
    public void delete(Hstest entity) throws JdaoException, SQLException {
        entity.delete();
    }

    /**
     * @param id 主键
     * @return
     */
    @Override
    public Hstest findById(Object id) throws JdaoException, JdaoClassException, SQLException {
        Hstest entity = new Hstest();
        entity.where(Hstest.ID.EQ(id));
        return entity.select();
    }

    /**
     * @return
     */
    @Override
    public List<Hstest> findAll() throws JdaoException, JdaoClassException, SQLException {
        Hstest hst = new Hstest();
        return hst.selects();
    }


    @Override
    public List<Hstest> findByCriteria(Where<Hstest>... wheres) throws JdaoException, JdaoClassException, SQLException {
        Hstest hst = new Hstest();
        hst.where(wheres);
        return hst.selects();
    }
}
