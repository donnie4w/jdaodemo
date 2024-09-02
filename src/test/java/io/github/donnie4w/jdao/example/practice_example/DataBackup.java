package io.github.donnie4w.jdao.example.practice_example;

import io.github.donnie4w.jdao.action.DataSourceFactory;
import io.github.donnie4w.jdao.base.JStruct;
import io.github.donnie4w.jdao.dao.Hstest;
import io.github.donnie4w.jdao.handle.DBType;
import io.github.donnie4w.jdao.handle.Jdao;
import io.github.donnie4w.jdao.handle.JdaoClassException;
import io.github.donnie4w.jdao.handle.JdaoException;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * 备份数据库表数据的示例程序
 */
public class DataBackup {

    String filePath = "backupfile.db";
    BufferedOutputStream bos = null;

    {
        DataSource dataSource = DataSourceFactory.getDataSourceByMysql();
        Jdao.init(dataSource, DBType.MYSQL);
        Jdao.setLogger(true);
        try {
            bos = new BufferedOutputStream(new FileOutputStream(filePath, true));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //数据写入文件
    public void AppendToFile(byte[] bs) throws IOException {
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeInt(bs.length);
        bos.write(bs);
        bos.flush();
    }

    //关闭流对象
    public void close() throws IOException {
        bos.close();
    }

    //备份指定表的数据到指定文件中
    public <T extends JStruct<?>> void dataBackUpByClass(Class<T> clz) throws JdaoException, SQLException, JdaoClassException, IOException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        int step = 10;
        int startindex = 0;
        while (true) {
            JStruct t = clz.getDeclaredConstructor().newInstance();
            t.limit(startindex, step);
            List<T> list = t.selects(); //数据查询
            if (list != null)
                for (T hs : list) {
                    AppendToFile(hs.encode());
                }
            if (list == null || list.size() < step)
                break;
            startindex = startindex + step;
        }
        close();
        System.out.println("finish to backup");
    }

    //从指定文件中恢复备份的数据，并打印
    public <T extends JStruct<?>> void recoverByClass(Class<T> clz) throws IOException, JdaoException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));
             DataInputStream dis = new DataInputStream(bis)) {
            while (dis.available() > 0) {
                byte[] buffer = new byte[dis.readInt()]; // 读取下一个对象的长度
                dis.readFully(buffer); // 读取对象数据
                T t = clz.getDeclaredConstructor().newInstance(); // 创建一个table对象用于反序列化
                t.decode(buffer); // 反序列化
                System.out.println(t);
            }
        }
    }

    /**
     * 恢复 Hstest表的备份数据，并打印
     */
    @Test
    public void TestRecoverHstest() throws IOException, JdaoException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        recoverByClass(Hstest.class);
    }

    /**
     * 备份Hstest表的数据到文件中
     */
    @Test
    public void TestBackUpHstest() throws JdaoException, SQLException, JdaoClassException, IOException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        dataBackUpByClass(Hstest.class);
    }

}
