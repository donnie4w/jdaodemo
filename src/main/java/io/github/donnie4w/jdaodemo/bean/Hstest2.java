/**
 * https://github.com/donnie4w/jdao
 * Copyright jdao Author. All Rights Reserved.
 * Email: donnie4w@gmail.com
*/

package io.github.donnie4w.jdaodemo.bean;

import io.github.donnie4w.jdao.base.DefName;
import io.github.donnie4w.jdao.base.Table;
import io.github.donnie4w.jdao.type.*;

/**
 * @date 2023-08-02 11:30:41   table bean  for  jdao : hstest2
 */
public class Hstest2 extends Table<Hstest2> {
	public LONG id;
	public STRING name;
	public SHORT age;
	public DATE createtime;
	public DOUBLE money;
	public BINARY binary;
	public FLOAT real;
	@DefName(name = "level")
	public INT level2;
}