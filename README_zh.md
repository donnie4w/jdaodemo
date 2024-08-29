##  Jdao Test Demo [[English](https://github.com/donnie4w/jdaodemo/blob/master/README.md)]

###### 这是Jdao的测试demo程序。所有示例程序均可以直接运行，无需额外再连接数据库， 数据已经打包工程中。

##### demo程序测试以下几个方面

1. 标准化实体类如何自动生成  `builder_example`
2. 标准化实体类的增删改查，事务，批处理操作 `dao_example`
3. 标准化实体类的序列化  `serizlize_example`
4. 原生SQL的增删改查，事务等操作  `nativesql_example`
5. SqlBuilder如何创建动态原生SQL `nativesql_example` `SqlBuilderDemo`
6. XML映射SQL的使用   `mapper_example`
7. XML映射SQL的动态SQL如何使用  `mapper_example` `DynamicDemo`
8. 数据读写分离使用示例    `Slave` 
9. 数据缓存使用示例       `Cache`
10. 数据表数据迁移示例     `DataTransfer` 
11. 数据表数据备份示例     `DataBackup`

### Jdao的详细使用说明请查看[使用文档](https://tlnet.top/jdaodoc)

## 概述

![](https://tlnet.top/statics/tlnet/5767.jpg)

------

#### java程序数据源获取

```java
public static DataSource getDataSourceBySqlite() {
    String filePath = "sqlite.properties";
    try {
        Properties p = new Properties();
        System.out.println("load druid properties >> " + filePath);
        p.load(new FileReader(filePath));
        return DruidDataSourceFactory.createDataSource(p);
    } catch (Exception e) {
        throw new JdaoRuntimeException(e);
    }
}
```

#### Jdao设置数据源

```java
Jdao.init(DataSourceFactory.getDataSourceBySqlite(), DBType.SQLITE);
//测试环境开启日志打印
Jdao.setLogger(true);
```

#### 标准化实体类的基础操作

```java
//查询
@Test
public void select() throws Exception {
    Hstest1 t = new Hstest1();
    t.where(Hstest1.ID.GT(1));
    t.limit(20,10);
    List<Hstest1> list = t.selects();
    for (Hstest1 hstest1 : list) {
        System.out.println(hstest1);
    }
}

//新增
@Test
public void insert() throws JdaoException, SQLException {
    Hstest hs = new Hstest();
    hs.setRowname(System.currentTimeMillis() + "");
    hs.setValue(System.nanoTime() + "");
    hs.insert();
}    

//批处理
@Test
public void batch() throws JdaoException, JdaoClassException, SQLException {
    Hstest hs = new Hstest();
    hs.setAge(30);
    hs.setRowname("www>>>>1");
    hs.setValue(System.nanoTime() + "");
    hs.setUpdatetime(new Date());
    hs.addBatch();
    hs.setAge(31);
    hs.setRowname("www>>>>2");
    hs.setValue(System.nanoTime() + "");
    hs.setUpdatetime(new Date());
    hs.addBatch();
    hs.setAge(32);
    hs.setRowname("www>>>>3");
    hs.setValue(System.nanoTime() + "");
    hs.setUpdatetime(new Date());
    hs.addBatch();
    hs.executeBatch();
}    

//序列化
@Test
public void jdaoSerialize() throws JdaoException, JdaoClassException, SQLException {
    Hstest1 t = new Hstest1();
    t.where(Hstest1.ID.EQ(3));
    Hstest1 hs = t.select();
    byte[] bs = hs.encode(); //序列化
    System.out.println(String.format("len(bs)>>%d",bs.length));
    Hstest1 hs2 = new Hstest1().decode(bs); //发序列化
    System.out.println("hs.equals(hs2):"+hs.equals(hs2));
}

//读写分离
@Test
public void slave() throws JdaoException, JdaoClassException, SQLException {
    System.out.println(Hstest.class.getPackageName());
    //配置备库数据源：这里为mysql  
    JdaoSlave.bindClass(Hstest.class, DataSourceFactory.getDataSourceByMysql(), DBType.MYSQL);

    Hstest t = new Hstest();
    t.where(Hstest.ID.EQ(3));
    Hstest hs = t.select();
    System.out.println(hs);

    t = new Hstest();
    t.where(Hstest.ID.EQ(3));
    hs = t.select();
    System.out.println(hs);
}

//数据缓存
@Test
public void cache() throws JdaoException, JdaoClassException, SQLException {
    //缓存绑定Hstest.class，数据缓存时效为 100毫秒
    JdaoCache.bindClass(Hstest.class,new CacheHandle(100));

    Hstest t = new Hstest();
    t.where(Hstest.ID.EQ(3));
    Hstest hs = t.select();
    System.out.println(hs);
    
    Hstest t2 = new Hstest();
    t2.where(Hstest.ID.EQ(3));
    Hstest hs2 = t2.select();
    System.out.println(hs2);
    System.out.println("hs.equals(hs2):"+hs.equals(hs2)); 

    JdaoCache.unbindClass(Hstest.class);//移除缓存数据
    t2 = new Hstest();
    t2.where(Hstest.ID.EQ(3));
    hs2 = t2.select();
    System.out.println(hs2);
}

//事务
@Test
public void transaction() throws JdaoException, SQLException, JdaoClassException {
    //获取事务对象
    Transaction tx = Jdao.newTransaction();
    
    Hstest hs = new Hstest();
    hs.useTransaction(tx); //使用事务
    hs.setRowname(System.currentTimeMillis() + "");
    hs.setValue(System.nanoTime() + "");
    hs.setAge(35);
    hs.setRowname("www>>>>1111");
    hs.setValue(System.nanoTime() + "");
    hs.insert();
    
    Hstest hs2 = new Hstest();
    hs2.useTransaction(tx); //使用事务
    hs2.setRowname("www>>>>3");
    hs2.setValue(System.nanoTime() + "");
    hs2.where(Hstest.ID.EQ(11).OR(Hstest.ID.EQ(12)));
    hs2.update();

    //基础接口使用事务
    Jdao.executeUpdate(tx, "insert into hstest1(`rowname`,`value`) values(?,?)", "uuuuu>>>>1", "ppppppppp");
    
    //事务对象可以直接执行SQL
    tx.executeUpdate("insert into hstest1(`rowname`,`value`) values(?,?)", "abcdefg", "123456");
    
    //事务提交
    tx.commit();
}
```

####  原生SQL执行

```java
//查询
@Test
public void select() throws Exception {
    List<DataBean> dblist =  Jdao.executeQueryBeans("select * from Hstest1  order by id desc limit 5");
    for (DataBean dataBean : dblist) {
        System.out.println(dataBean);
    }
}

//新增
@Test
public void insert() throws Exception {
    Jdao.executeUpdate(tx, "insert into hstest1(`rowname`,`value`) values(?,?)", "uuuuu>>>>1", "ppppppppp");
    tx.commit();
}
```
#### Mapper映射模块

##### 示例1 

```xml
<select id="selectHstestById" parameterType="int" resultType="io.github.donnie4w.jdao.dao.Hstest">
    SELECT *  FROM hstest  WHERE id &lt; #{id}  and age &lt; #{age}
</select>
```
```java
@Test
public void selectOne() throws JdaoException, JdaoClassException, SQLException {
    Hstest hs = jdaoMapper.selectOne("io.github.donnie4w.jdao.action.Mapperface.selectHstestById", 2, 26);
    System.out.println(hs);
}
```

##### 示例2

```xml
<select id="selectHstest1" parameterType="int" resultType="io.github.donnie4w.jdao.dao.Hstest1">
    SELECT *  FROM hstest1  where id &gt; 1  and id &lt; 10 limit #{limit}
</select>
```
```java
@Test
public void selectList() throws JdaoException, JdaoClassException, SQLException {
    List<Hstest1> list2 = jdaoMapper.selectList("io.github.donnie4w.jdao.action.Mapperface.selectHstest1", 5);
    for (Hstest1 h : list2) {
        System.out.println(h);
    }
}
```