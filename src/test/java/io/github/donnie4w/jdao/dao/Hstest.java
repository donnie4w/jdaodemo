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

package io.github.donnie4w.jdao.dao;

import io.github.donnie4w.jdao.base.Fields;
import io.github.donnie4w.jdao.base.Table;
import io.github.donnie4w.jdao.base.Util;
import io.github.donnie4w.jdao.handle.JdaoException;
import io.github.donnie4w.jdao.util.Serializer;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.Date;
import java.math.BigDecimal;
/**
 * dbtype:mysql ,database:hstest ,table:hstest
 *
 * @version jdao version 2.0.1
 * @date 2024-07-19 10:34:13 
 */
public class Hstest extends Table<Hstest> implements Serializable {

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
	
	public Hstest() {
        super(TABLENAME_, Hstest.class);
        super.initFields(ID,AGE,ROWNAME,VALUE,UPDATETIME,BODY,FLOA,LEVEL);
    }

    public Hstest(String tableName) {
        super(tableName, Hstest.class);
        super.initFields(ID,AGE,ROWNAME,VALUE,UPDATETIME,BODY,FLOA,LEVEL);
    }

    public void toJdao() {
        super.init(Hstest.class);
    }

	private int id;
	private long age;
	private String rowname;
	private String value;
	private Date updatetime;
	private byte[] body;
	private BigDecimal floa;
	private long level;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        fieldPut(ID, id);
        this.id = id;
    }

    public long getAge() {
        return this.age;
    }

    public void setAge(long age) {
        fieldPut(AGE, age);
        this.age = age;
    }

    public String getRowname() {
        return this.rowname;
    }

    public void setRowname(String rowname) {
        fieldPut(ROWNAME, rowname);
        this.rowname = rowname;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        fieldPut(VALUE, value);
        this.value = value;
    }

    public Date getUpdatetime() {
        return this.updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        fieldPut(UPDATETIME, updatetime);
        this.updatetime = updatetime;
    }

    public byte[] getBody() {
        return this.body;
    }

    public void setBody(byte[] body) {
        fieldPut(BODY, body);
        this.body = body;
    }

    public BigDecimal getFloa() {
        return this.floa;
    }

    public void setFloa(BigDecimal floa) {
        fieldPut(FLOA, floa);
        this.floa = floa;
    }

    public long getLevel() {
        return this.level;
    }

    public void setLevel(long level) {
        fieldPut(LEVEL, level);
        this.level = level;
    }

    public String toString() {
        return "id:" + id + " , " + "age:" + age + " , " + "rowname:" + rowname + " , " + "value:" + value + " , " + "updatetime:" + updatetime + " , " + "body:" + body + " , " + "floa:" + floa + " , " + "level:" + level;
    }

	public void copy(Hstest h) {
		this.setId(h.getId());
		this.setAge(h.getAge());
		this.setRowname(h.getRowname());
		this.setValue(h.getValue());
		this.setUpdatetime(h.getUpdatetime());
		this.setBody(h.getBody());
		this.setFloa(h.getFloa());
		this.setLevel(h.getLevel());
	}

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

	public byte[] encode() {
		Map<String, Object> map = new HashMap();
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hstest _Hstest = (Hstest) o;
        return id == _Hstest.id && age == _Hstest.age && Objects.equals(rowname, _Hstest.rowname) && Objects.equals(value, _Hstest.value) && Objects.equals(updatetime, _Hstest.updatetime) && Objects.deepEquals(body, _Hstest.body) && Objects.equals(floa, _Hstest.floa) && level == _Hstest.level;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id , age , rowname , value , updatetime , Arrays.hashCode(body) , floa , level);
    }
}