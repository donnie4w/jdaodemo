package io.github.donnie4w.jdaodemo.action;

import io.github.donnie4w.jdao.base.JException;
import io.github.donnie4w.jdaodemo.dataSource.DataSourceTest;
import io.github.donnie4w.jdaodemo.bean.Hstest2;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

/**
 * jdao 对象操作测试
 */
public class JDaoTest2 {
    static {
        String filePth=System.getProperty("user.dir")+"/druid.properties";
        DataSourceTest.init(filePth);
    }

    @Test
    public  void insert() throws JException {
        Hstest2 h = new Hstest2();
        h.money.setValue(11.2);
        h.age.setValue((short) 22);
        h.name.setValue("tom");
        h.binary.setValue("hello".getBytes(StandardCharsets.UTF_8));
        h.createtime.setValue(new Date());
        h.real.setValue(33.3f);
        h.level2.setValue(33);
        h.insert();
        System.out.println(h.getLastInsertId4Mysql());
    }
    @Test
    public  void update() throws JException {
        Hstest2 h = new Hstest2();
        h.money.setValue(33.3);
        h.age.setValue((short) 333);
        h.name.setValue("tom333");
        h.binary.setValue("hello".getBytes(StandardCharsets.UTF_8));
        h.createtime.setValue(new Date());
        h.real.setValue(33.3f);
        h.where(h.id.EQ(1));
        h.update();
    }
    @Test
    public  void selectById() throws JException {
        Hstest2 h = new Hstest2();
        h.where(h.id.EQ(1));
        Hstest2 ht = h.selectById();
        System.out.println(ht.id.getValue() + "," + ht.age.getValue() + "," + ht.name.getValue() + "," + ht.real.getValue() + "," + new String(ht.binary.getValue() == null ? new byte[]{0} : ht.binary.getValue(), StandardCharsets.UTF_8) + "," + ht.createtime.getValue() + "," + ht.money.getValue()+","+ht.level2.getValue());
    }
    @Test
    public  void select() throws JException {
        Hstest2 h = new Hstest2();
        h.where(h.id.GE(1),h.name.LIKE("tom"));
        List<Hstest2> list = h.select();
        for (Hstest2 ht : list) {
            System.out.println(ht.id.getValue() + "," + ht.age.getValue() + "," + ht.name.getValue() + "," + ht.real.getValue() + "," + new String(ht.binary.getValue() == null ? new byte[]{0} : ht.binary.getValue(), StandardCharsets.UTF_8) + "," + ht.createtime.getValue() + "," + ht.money.getValue()+","+ht.level2.getValue());
        }
    }
}
