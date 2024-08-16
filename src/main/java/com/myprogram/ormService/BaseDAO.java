package com.myprogram.ormService;

import io.github.donnie4w.jdao.base.Where;
import io.github.donnie4w.jdao.handle.JdaoClassException;
import io.github.donnie4w.jdao.handle.JdaoException;

import java.sql.SQLException;
import java.util.List;

/**
 * 持久层 抽象层 示例
 * 一般用于单表增删改查的简单操作的抽象层
 *
 * @param <T> T为标准化实体类泛型
 */
public abstract class BaseDAO<T> {

    /**
     * 保存实体对象
     *
     * @param entity 实体对象
     */
    public abstract void save(T entity) throws JdaoException, SQLException;

    /**
     * 更新实体对象
     *
     * @param entity 实体对象
     */
    public abstract void update(T entity) throws JdaoException, SQLException;

    /**
     * 删除实体对象
     *
     * @param entity 实体对象
     */
    public abstract void delete(T entity) throws JdaoException, SQLException;

    /**
     * 根据主键获取实体对象
     *
     * @param id 主键
     * @return 实体对象
     */
    public abstract T findById(Object id) throws JdaoException, JdaoClassException, SQLException;

    /**
     * 获取所有实体对象
     *
     * @return 所有实体对象列表
     */
    public abstract List<T> findAll() throws JdaoException, JdaoClassException, SQLException;

    /**
     * 根据查询条件获取实体对象列表
     *
     * @param whereList 查询条件
     * @return 实体对象列表
     */
    public abstract List<T> findByCriteria(Where<T>... wheres) throws JdaoException, JdaoClassException, SQLException;
}