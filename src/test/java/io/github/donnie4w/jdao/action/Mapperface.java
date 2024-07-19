package io.github.donnie4w.jdao.action;

import io.github.donnie4w.jdao.dao.Hstest;
import io.github.donnie4w.jdao.dao.Hstest1;

import java.util.List;
import java.util.Map;

public interface Mapperface {

    List<Hstest> selectAllHstest();

    List<Hstest> selectHstest(int id, int age);

    Hstest selectHstestByMap(Map map);

    List<Hstest> selectHstestByList(int id, int age);

    Hstest[] selectHstestByList(List list);

    Hstest selectHstestById(int id, int age);

    List<Hstest> selectHstestById(Integer id, Integer age);

    Hstest1 selectHstest1(int limit);

    List<Hstest1> selectHstest1(long limit);

    int insertHstest1(Hstest1 hs);

    int updateHstest1(Hstest1 hs);

    int deleteHstest1(Hstest1 hs);

}
