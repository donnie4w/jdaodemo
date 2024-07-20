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


/**
 * dbtype:mysql ,database:hstest ,table:hstest1
 *
 * @version jdao version 2.0.1
 * @date 2024-07-19 21:12:49 
 */
public class Hstest1 extends Table<Hstest1> implements Serializable {

    private static final long serialVersionUID = 6118074828633154000L;

    private final static String TABLENAME_ = "hstest1";

	public final static Fields<Hstest1> ID = new Fields("id");
	public final static Fields<Hstest1> ROWNAME = new Fields("rowname");
	public final static Fields<Hstest1> VALUE = new Fields("value");
	public final static Fields<Hstest1> GOTO = new Fields("goto");
	
	public Hstest1() {
        super(TABLENAME_, Hstest1.class);
        super.initFields(ID,ROWNAME,VALUE,GOTO);
    }

    public Hstest1(String tableName) {
        super(tableName, Hstest1.class);
        super.initFields(ID,ROWNAME,VALUE,GOTO);
    }

    public void toJdao() {
        super.init(Hstest1.class);
    }

	private int id;
	private String rowname;
	private byte[] value;
	private byte[] goto_;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        fieldPut(ID, id);
        this.id = id;
    }

    public String getRowname() {
        return this.rowname;
    }

    public void setRowname(String rowname) {
        fieldPut(ROWNAME, rowname);
        this.rowname = rowname;
    }

    public byte[] getValue() {
        return this.value;
    }

    public void setValue(byte[] value) {
        fieldPut(VALUE, value);
        this.value = value;
    }

    public byte[] getGoto() {
        return this.goto_;
    }

    public void setGoto(byte[] goto_) {
        fieldPut(GOTO, goto_);
        this.goto_ = goto_;
    }

    public String toString() {
        return "id:" + id + " , " + "rowname:" + rowname + " , " + "value:" + value + " , " + "goto:" + goto_;
    }

	public Hstest1 copy(Hstest1 h) {
		this.setId(h.getId());
		this.setRowname(h.getRowname());
		this.setValue(h.getValue());
		this.setGoto(h.getGoto());
		return this;
	}

    public void scan(String fieldname, Object obj) throws JdaoException {
        try {
            switch (fieldname) {
                case "id":
                    setId(Util.asInt(obj));
                    break;
                case "rowname":
                    setRowname(Util.asString(obj));
                    break;
                case "value":
                    setValue(Util.asBytes(obj));
                    break;
                case "goto":
                    setGoto(Util.asBytes(obj));
                    break;
            }
        } catch (Exception e) {
             throw new JdaoException(e);
        }
	}

	public byte[] encode() {
		Map<String, Object> map = new HashMap();
		map.put("id", this.getId());
		map.put("rowname", this.getRowname());
		map.put("value", this.getValue());
		map.put("goto", this.getGoto());
		return Serializer.encode(map);
	}

	public Hstest1 decode(byte[] bs) throws JdaoException {
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
        Hstest1 _Hstest1 = (Hstest1) o;
        return id == _Hstest1.id && Objects.equals(rowname, _Hstest1.rowname) && Objects.deepEquals(value, _Hstest1.value) && Objects.deepEquals(goto_, _Hstest1.goto_);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id , rowname , Arrays.hashCode(value) , Arrays.hashCode(goto_));
    }
}