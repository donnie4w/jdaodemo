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
 * dbtype: mysql, database: , table: hstest2
 *
 * @version jdao version 2.0.1
 * @date 2024-08-03 23:06:10
 */
public class Hstest2 extends Table<Hstest2> {

    private static final long serialVersionUID = 6118074828633154000L;

    private final static String TABLENAME_ = "hstest2";
    public final static Fields<Hstest2> ID = new Fields("id");
    public final static Fields<Hstest2> NAME = new Fields("name");
    public final static Fields<Hstest2> AGE = new Fields("age");
    public final static Fields<Hstest2> CREATETIME = new Fields("createtime");
    public final static Fields<Hstest2> MONEY = new Fields("money");
    public final static Fields<Hstest2> BYTES = new Fields("bytes");
    public final static Fields<Hstest2> FLOA = new Fields("floa");
    public final static Fields<Hstest2> LEVEL = new Fields("level");
    public final static Fields<Hstest2> TYPE = new Fields("type");
    public final static Fields<Hstest2> FLOG = new Fields("flog");

    public Hstest2() {
        super(TABLENAME_, Hstest2.class);
        super.initFields(ID, NAME, AGE, CREATETIME, MONEY, BYTES, FLOA, LEVEL, TYPE, FLOG);
    }

    public Hstest2(String tableName) {
        super(tableName, Hstest2.class);
        super.initFields(ID, NAME, AGE, CREATETIME, MONEY, BYTES, FLOA, LEVEL, TYPE, FLOG);
    }
    private long id;
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        fieldPut(ID, id);
        this.id = id;
    }

    private String name;
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        fieldPut(NAME, name);
        this.name = name;
    }

    private byte age;
    public byte getAge() {
        return this.age;
    }
    public void setAge(byte age) {
        fieldPut(AGE, age);
        this.age = age;
    }

    private Date createtime;
    public Date getCreatetime() {
        return this.createtime;
    }
    public void setCreatetime(Date createtime) {
        fieldPut(CREATETIME, createtime);
        this.createtime = createtime;
    }

    private double money;
    public double getMoney() {
        return this.money;
    }
    public void setMoney(double money) {
        fieldPut(MONEY, money);
        this.money = money;
    }

    private byte[] bytes;
    public byte[] getBytes() {
        return this.bytes;
    }
    public void setBytes(byte[] bytes) {
        fieldPut(BYTES, bytes);
        this.bytes = bytes;
    }

    private float floa;
    public float getFloa() {
        return this.floa;
    }
    public void setFloa(float floa) {
        fieldPut(FLOA, floa);
        this.floa = floa;
    }

    private int level;
    public int getLevel() {
        return this.level;
    }
    public void setLevel(int level) {
        fieldPut(LEVEL, level);
        this.level = level;
    }

    private BigDecimal type;
    public BigDecimal getType() {
        return this.type;
    }
    public void setType(BigDecimal type) {
        fieldPut(TYPE, type);
        this.type = type;
    }

    private BigDecimal flog;
    public BigDecimal getFlog() {
        return this.flog;
    }
    public void setFlog(BigDecimal flog) {
        fieldPut(FLOG, flog);
        this.flog = flog;
    }


    @Override
    public String toString() {
        return "id:" + id + ", " + "name:" + name + ", " + "age:" + age + ", " + "createtime:" + createtime + ", " + "money:" + money + ", " + "bytes:" + bytes + ", " + "floa:" + floa + ", " + "level:" + level + ", " + "type:" + type + ", " + "flog:" + flog;
    }

    @Override
    public Hstest2 copy(Hstest2 h) {
        this.setId(h.getId());
        this.setName(h.getName());
        this.setAge(h.getAge());
        this.setCreatetime(h.getCreatetime());
        this.setMoney(h.getMoney());
        this.setBytes(h.getBytes());
        this.setFloa(h.getFloa());
        this.setLevel(h.getLevel());
        this.setType(h.getType());
        this.setFlog(h.getFlog());
        return this;
    }

    @Override
    public void scan(String fieldname, Object obj) throws JdaoException {
        try {
            switch (fieldname) {
                case "id":
                    setId(Util.asLong(obj));
                    break;
                case "name":
                    setName(Util.asString(obj));
                    break;
                case "age":
                    setAge(Util.asByte(obj));
                    break;
                case "createtime":
                    setCreatetime(Util.asDate(obj));
                    break;
                case "money":
                    setMoney(Util.asDouble(obj));
                    break;
                case "bytes":
                    setBytes(Util.asBytes(obj));
                    break;
                case "floa":
                    setFloa(Util.asFloat(obj));
                    break;
                case "level":
                    setLevel(Util.asInt(obj));
                    break;
                case "type":
                    setType(Util.asBigDecimal(obj));
                    break;
                case "flog":
                    setFlog(Util.asBigDecimal(obj));
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
        Hstest2 _Hstest2 = (Hstest2) o;
        return id == _Hstest2.id && Objects.equals(name, _Hstest2.name) && age == _Hstest2.age && Objects.equals(createtime, _Hstest2.createtime) && money == _Hstest2.money && Objects.deepEquals(bytes, _Hstest2.bytes) && floa == _Hstest2.floa && level == _Hstest2.level && Objects.equals(type, _Hstest2.type) && Objects.equals(flog, _Hstest2.flog);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, createtime, money, Arrays.hashCode(bytes), floa, level, type, flog);
    }

    @Override
    public byte[] encode() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.getId());
        map.put("name", this.getName());
        map.put("age", this.getAge());
        map.put("createtime", this.getCreatetime());
        map.put("money", this.getMoney());
        map.put("bytes", this.getBytes());
        map.put("floa", this.getFloa());
        map.put("level", this.getLevel());
        map.put("type", this.getType());
        map.put("flog", this.getFlog());
        return Serializer.encode(map);
    }

    @Override
    public Hstest2 decode(byte[] bs) throws JdaoException {
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
        super.init(Hstest2.class);
    }
}