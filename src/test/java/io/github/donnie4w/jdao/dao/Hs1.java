package io.github.donnie4w.jdao.dao;

import java.util.Arrays;

/**
 * 自定义 表 Hstest1 对应 javaBean
 */
public class Hs1 {

    private long id;
    private String rowname;
    private byte[] value;
    private byte[] goto_;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRowname() {
        return rowname;
    }

    public void setRowname(String rowname) {
        this.rowname = rowname;
    }

    public byte[] getValue() {
        return value;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }

    public byte[] getGoto_() {
        return goto_;
    }

    public void setGoto_(byte[] goto_) {
        this.goto_ = goto_;
    }

    @Override
    public String toString() {
        return "Hs1{" +
                "id=" + id +
                ", rowname='" + rowname + '\'' +
                ", value=" + Arrays.toString(value) +
                ", goto_=" + Arrays.toString(goto_) +
                '}';
    }
}
