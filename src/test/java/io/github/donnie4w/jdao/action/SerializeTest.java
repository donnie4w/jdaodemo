package io.github.donnie4w.jdao.action;

import io.github.donnie4w.jdao.dao.Hstest;
import io.github.donnie4w.jdao.handle.JdaoException;
import io.github.donnie4w.jdao.util.Serializa;
import org.junit.Test;

import java.util.Date;

public class SerializeTest {

    static Hstest hstest = new Hstest();
    static byte[] nativeBs = null;
    static byte[] jdaoBs = null;

    static {
        hstest.setId(123456789);
        hstest.setAge(123456789);
        hstest.setUpdatetime(new Date());
        hstest.setBody("hello world,hello jdao".getBytes());
        hstest.setValue("hello world,hello jdao");
        hstest.setRowname("hello world");
        hstest.setLevel(1234567890123456789L);
//        hstest.setFloa(BigDecimal.valueOf(1234567890123456789l));
        hstest.setFloa(1234567890);
        try {
            nativeBs = Serializa.encode(hstest);
            jdaoBs = hstest.encode();
        } catch (JdaoException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void Encode() throws JdaoException {
        System.out.println("Jdao serialization size:" + jdaoBs.length);
        System.out.println("java native serialization size:" + nativeBs.length);
    }
}
