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
import java.util.Arrays;
/**
 * dbtype: mysql, database: , table: hstest
 *
 * @version jdao version 2.0.1
 * @date 2024-08-23 17:44:12
 */
public class Hstest extends Table<Hstest> {

    private static final long serialVersionUID = 6118074828633154000L;

    private final static String TABLENAME_ = "hstest";
    public final static Fields<Hstest> ID = new Fields("id");
    public final static Fields<Hstest> AGE = new Fields("age");
    public final static Fields<Hstest> ROWNAME = new Fields("rowname");
    public final static Fields<Hstest> VALUE = new Fields("value");
    public final static Fields<Hstest> UPDATETIME = new Fields("updatetime");
    public final static Fields<Hstest> BODY = new Fields("body");
    public final static Fields<Hstest> FLOA = new Fields("floa");
    public final static Fields<Hstest> LEVEL = new Fields("level");
    public final static Fields<Hstest> CREATED_AT = new Fields("created_at");
    public final static Fields<Hstest> UPDATED_AT = new Fields("updated_at");
    public final static Fields<Hstest> DELETED_AT = new Fields("deleted_at");

    public Hstest() {
        super(TABLENAME_, Hstest.class);
        super.initFields(ID, AGE, ROWNAME, VALUE, UPDATETIME, BODY, FLOA, LEVEL, CREATED_AT, UPDATED_AT, DELETED_AT);
    }

    public Hstest(String tableName) {
        super(tableName, Hstest.class);
        super.initFields(ID, AGE, ROWNAME, VALUE, UPDATETIME, BODY, FLOA, LEVEL, CREATED_AT, UPDATED_AT, DELETED_AT);
    }
    private int id;
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        fieldPut(ID, id);
        this.id = id;
    }

    private long age;
    public long getAge() {
        return this.age;
    }
    public void setAge(long age) {
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

    private byte[] floa;
    public byte[] getFloa() {
        return this.floa;
    }
    public void setFloa(byte[] floa) {
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

    private Date created_at;
    public Date getCreated_at() {
        return this.created_at;
    }
    public void setCreated_at(Date created_at) {
        fieldPut(CREATED_AT, created_at);
        this.created_at = created_at;
    }

    private Date updated_at;
    public Date getUpdated_at() {
        return this.updated_at;
    }
    public void setUpdated_at(Date updated_at) {
        fieldPut(UPDATED_AT, updated_at);
        this.updated_at = updated_at;
    }

    private Date deleted_at;
    public Date getDeleted_at() {
        return this.deleted_at;
    }
    public void setDeleted_at(Date deleted_at) {
        fieldPut(DELETED_AT, deleted_at);
        this.deleted_at = deleted_at;
    }


    @Override
    public String toString() {
        return "id:" + id + ", " + "age:" + age + ", " + "rowname:" + rowname + ", " + "value:" + value + ", " + "updatetime:" + updatetime + ", " + "body:" + body + ", " + "floa:" + floa + ", " + "level:" + level + ", " + "created_at:" + created_at + ", " + "updated_at:" + updated_at + ", " + "deleted_at:" + deleted_at;
    }

    @Override
    public Hstest copy(Hstest h) {
        this.setId(h.getId());
        this.setAge(h.getAge());
        this.setRowname(h.getRowname());
        this.setValue(h.getValue());
        this.setUpdatetime(h.getUpdatetime());
        this.setBody(h.getBody());
        this.setFloa(h.getFloa());
        this.setLevel(h.getLevel());
        this.setCreated_at(h.getCreated_at());
        this.setUpdated_at(h.getUpdated_at());
        this.setDeleted_at(h.getDeleted_at());
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
                    setAge(Util.asLong(obj));
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
                    setFloa(Util.asBytes(obj));
                    break;
                case "level":
                    setLevel(Util.asLong(obj));
                    break;
                case "created_at":
                    setCreated_at(Util.asDate(obj));
                    break;
                case "updated_at":
                    setUpdated_at(Util.asDate(obj));
                    break;
                case "deleted_at":
                    setDeleted_at(Util.asDate(obj));
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
        Hstest _Hstest = (Hstest) o;
        return id == _Hstest.id && age == _Hstest.age && Objects.equals(rowname, _Hstest.rowname) && Objects.equals(value, _Hstest.value) && Objects.equals(updatetime, _Hstest.updatetime) && Objects.deepEquals(body, _Hstest.body) && Objects.deepEquals(floa, _Hstest.floa) && level == _Hstest.level && Objects.equals(created_at, _Hstest.created_at) && Objects.equals(updated_at, _Hstest.updated_at) && Objects.equals(deleted_at, _Hstest.deleted_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, age, rowname, value, updatetime, Arrays.hashCode(body), Arrays.hashCode(floa), level, created_at, updated_at, deleted_at);
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
        map.put("created_at", this.getCreated_at());
        map.put("updated_at", this.getUpdated_at());
        map.put("deleted_at", this.getDeleted_at());
        return Serializer.encode(map);
    }

    @Override
    public Hstest decode(byte[] bs) throws JdaoException {
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
        super.init(Hstest.class);
    }
}