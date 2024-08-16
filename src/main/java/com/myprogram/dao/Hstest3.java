/*
* Copyright (c) 2024, donnie4w <donnie4w@gmail.com> All rights reserved.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*
* github.com/donnie4w/jdao
*/

package com.myprogram.dao;

import java.util.Objects;
import java.util.Map;
import java.util.HashMap;
import io.github.donnie4w.jdao.base.Fields;
import io.github.donnie4w.jdao.base.Table;
import io.github.donnie4w.jdao.base.Util;
import io.github.donnie4w.jdao.util.Serializer;
import io.github.donnie4w.jdao.handle.JdaoException;
import java.util.Date;
import java.math.BigDecimal;
import java.util.Arrays;
/**
 * dbtype: mysql, database: , table: hstest3
 *
 * @version jdao version 2.0.1
 * @date 2024-08-03 23:06:10
 */
public class Hstest3 extends Table<Hstest3> {

    private static final long serialVersionUID = 6118074828633154000L;

    private final static String TABLENAME_ = "hstest3";
    public final static Fields<Hstest3> ID = new Fields("id");
    public final static Fields<Hstest3> AGE = new Fields("age");
    public final static Fields<Hstest3> ROWNAME = new Fields("rowname");
    public final static Fields<Hstest3> VALUE = new Fields("value");
    public final static Fields<Hstest3> UPDATETIME = new Fields("updatetime");
    public final static Fields<Hstest3> BODY = new Fields("body");
    public final static Fields<Hstest3> FLOA = new Fields("floa");
    public final static Fields<Hstest3> LEVEL = new Fields("level");

    public Hstest3() {
        super(TABLENAME_, Hstest3.class);
        super.initFields(ID, AGE, ROWNAME, VALUE, UPDATETIME, BODY, FLOA, LEVEL);
    }

    public Hstest3(String tableName) {
        super(tableName, Hstest3.class);
        super.initFields(ID, AGE, ROWNAME, VALUE, UPDATETIME, BODY, FLOA, LEVEL);
    }
    private int id;
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        fieldPut(ID, id);
        this.id = id;
    }

    private byte age;
    public byte getAge() {
        return this.age;
    }
    public void setAge(byte age) {
        fieldPut(AGE, age);
        this.age = age;
    }

    private String rowname;
    public String getRowname() {
        return this.rowname;
    }
    public void setRowname(String rowname) {
        fieldPut(ROWNAME, rowname);
        this.rowname = rowname;
    }

    private String value;
    public String getValue() {
        return this.value;
    }
    public void setValue(String value) {
        fieldPut(VALUE, value);
        this.value = value;
    }

    private Date updatetime;
    public Date getUpdatetime() {
        return this.updatetime;
    }
    public void setUpdatetime(Date updatetime) {
        fieldPut(UPDATETIME, updatetime);
        this.updatetime = updatetime;
    }

    private byte[] body;
    public byte[] getBody() {
        return this.body;
    }
    public void setBody(byte[] body) {
        fieldPut(BODY, body);
        this.body = body;
    }

    private BigDecimal floa;
    public BigDecimal getFloa() {
        return this.floa;
    }
    public void setFloa(BigDecimal floa) {
        fieldPut(FLOA, floa);
        this.floa = floa;
    }

    private long level;
    public long getLevel() {
        return this.level;
    }
    public void setLevel(long level) {
        fieldPut(LEVEL, level);
        this.level = level;
    }


    @Override
    public String toString() {
        return "id:" + id + ", " + "age:" + age + ", " + "rowname:" + rowname + ", " + "value:" + value + ", " + "updatetime:" + updatetime + ", " + "body:" + body + ", " + "floa:" + floa + ", " + "level:" + level;
    }

    @Override
    public Hstest3 copy(Hstest3 h) {
        this.setId(h.getId());
        this.setAge(h.getAge());
        this.setRowname(h.getRowname());
        this.setValue(h.getValue());
        this.setUpdatetime(h.getUpdatetime());
        this.setBody(h.getBody());
        this.setFloa(h.getFloa());
        this.setLevel(h.getLevel());
        return this;
    }

    @Override
    public void scan(String fieldname, Object obj) throws JdaoException {
        try {
            switch (fieldname) {
                case "id":
                    setId(Util.asInt(obj));
                    break;
                case "age":
                    setAge(Util.asByte(obj));
                    break;
                case "rowname":
                    setRowname(Util.asString(obj));
                    break;
                case "value":
                    setValue(Util.asString(obj));
                    break;
                case "updatetime":
                    setUpdatetime(Util.asDate(obj));
                    break;
                case "body":
                    setBody(Util.asBytes(obj));
                    break;
                case "floa":
                    setFloa(Util.asBigDecimal(obj));
                    break;
                case "level":
                    setLevel(Util.asLong(obj));
                    break;
            }
        } catch (Exception e) {
            throw new JdaoException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hstest3 _Hstest3 = (Hstest3) o;
        return id == _Hstest3.id && age == _Hstest3.age && Objects.equals(rowname, _Hstest3.rowname) && Objects.equals(value, _Hstest3.value) && Objects.equals(updatetime, _Hstest3.updatetime) && Objects.deepEquals(body, _Hstest3.body) && Objects.equals(floa, _Hstest3.floa) && level == _Hstest3.level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, age, rowname, value, updatetime, Arrays.hashCode(body), floa, level);
    }

    @Override
    public byte[] encode() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.getId());
        map.put("age", this.getAge());
        map.put("rowname", this.getRowname());
        map.put("value", this.getValue());
        map.put("updatetime", this.getUpdatetime());
        map.put("body", this.getBody());
        map.put("floa", this.getFloa());
        map.put("level", this.getLevel());
        return Serializer.encode(map);
    }

    @Override
    public Hstest3 decode(byte[] bs) throws JdaoException {
        Map<String, Object> map = Serializer.decode(bs);
        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                scan(entry.getKey(), entry.getValue());
            }
        }
        return this;
    }

    @Override
    public void toJdao() {
        super.init(Hstest3.class);
    }
}