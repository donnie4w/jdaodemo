## Jdao Test Demo  [[中文](https://github.com/donnie4w/jdaodemo/blob/master/README_zh.md)]

###### This is a test demo program for Jdao. All sample programs can run directly without additional database connections, as the data has already been packaged within the project.

##### The demo program tests the following aspects:

1. How to automatically generate standardized entity classes `builder_example`
2. CRUD operations, transactions, and batch processing for standardized entity classes `dao_example`
3. Serialization of standardized entity classes `serialize_example`
4. CRUD operations, transactions, etc., using native SQL `nativesql_example`
5. How to create dynamic native SQL with SqlBuilder `nativesql_example` `SqlBuilderDemo`
6. Usage of XML-mapped SQL `mapper_example`
7. How to use dynamic SQL in XML-mapped SQL `mapper_example` `DynamicDemo`
8. Example of read-write separation `Slave`
9. Example of data caching `Cache`
10. Example of data table migration `DataTransfer`
11. Example of data table backup `DataBackup`

### For detailed usage of Jdao, please refer to the [documentation](https://tlnet.top/jdaodoc).

### [Jdao Github](https://github.com/donnie4w/jdao)

## Overview

![](https://tlnet.top/statics/tlnet/5767.jpg)

------

#### Getting DataSource in Java Program

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

#### Setting DataSource in Jdao

```java
Jdao.init(DataSourceFactory.getDataSourceBySqlite(), DBType.SQLITE);
// Enable logging in the test environment
Jdao.setLogger(true);
```

#### Basic Operations of Standardized Entity Classes

```java
// Select operation
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

// Insert operation
@Test
public void insert() throws JdaoException, SQLException {
    Hstest hs = new Hstest();
    hs.setRowname(System.currentTimeMillis() + "");
    hs.setValue(System.nanoTime() + "");
    hs.insert();
}    

// Batch processing
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

// Serialization
@Test
public void jdaoSerialize() throws JdaoException, JdaoClassException, SQLException {
    Hstest1 t = new Hstest1();
    t.where(Hstest1.ID.EQ(3));
    Hstest1 hs = t.select();
    byte[] bs = hs.encode(); // Serialization
    System.out.println(String.format("len(bs)>>%d", bs.length));
    Hstest1 hs2 = new Hstest1().decode(bs); // Deserialization
    System.out.println("hs.equals(hs2): " + hs.equals(hs2));
}

// Read-write separation
@Test
public void slave() throws JdaoException, JdaoClassException, SQLException {
    System.out.println(Hstest.class.getPackageName());
    // Configure slave database: in this case, MySQL
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

// Data caching
@Test
public void cache() throws JdaoException, JdaoClassException, SQLException {
    // Bind cache to Hstest.class, cache validity duration is 100 milliseconds
    JdaoCache.bindClass(Hstest.class, new CacheHandle(100));

    Hstest t = new Hstest();
    t.where(Hstest.ID.EQ(3));
    Hstest hs = t.select();
    System.out.println(hs);
    
    Hstest t2 = new Hstest();
    t2.where(Hstest.ID.EQ(3));
    Hstest hs2 = t2.select();
    System.out.println(hs2);
    System.out.println("hs.equals(hs2): " + hs.equals(hs2));

    JdaoCache.unbindClass(Hstest.class); // Remove cached data
    t2 = new Hstest();
    t2.where(Hstest.ID.EQ(3));
    hs2 = t2.select();
    System.out.println(hs2);
}

// Transaction
@Test
public void transaction() throws JdaoException, SQLException, JdaoClassException {
    // Get transaction object
    Transaction tx = Jdao.newTransaction();
    
    Hstest hs = new Hstest();
    hs.useTransaction(tx); // Use transaction
    hs.setRowname(System.currentTimeMillis() + "");
    hs.setValue(System.nanoTime() + "");
    hs.setAge(35);
    hs.setRowname("www>>>>1111");
    hs.setValue(System.nanoTime() + "");
    hs.insert();
    
    Hstest hs2 = new Hstest();
    hs2.useTransaction(tx); // Use transaction
    hs2.setRowname("www>>>>3");
    hs2.setValue(System.nanoTime() + "");
    hs2.where(Hstest.ID.EQ(11).OR(Hstest.ID.EQ(12)));
    hs2.update();

    // Use transaction for basic interface
    Jdao.executeUpdate(tx, "insert into hstest1(`rowname`,`value`) values(?,?)", "uuuuu>>>>1", "ppppppppp");
    
    // Transaction object can execute SQL directly
    tx.executeUpdate("insert into hstest1(`rowname`,`value`) values(?,?)", "abcdefg", "123456");
    
    // Commit transaction
    tx.commit();
}
```

#### Executing Native SQL

```java
// Select operation
@Test
public void select() throws Exception {
    List<DataBean> dblist =  Jdao.executeQueryBeans("select * from Hstest1  order by id desc limit 5");
    for (DataBean dataBean : dblist) {
        System.out.println(dataBean);
    }
}

// Insert operation
@Test
public void insert() throws Exception {
    Jdao.executeUpdate(tx, "insert into hstest1(`rowname`,`value`) values(?,?)", "uuuuu>>>>1", "ppppppppp");
    tx.commit();
}
```
#### Mapper Mapping Module

##### Example 1

```xml
<select id="selectHstestById" parameterType="int" resultType="io.github.donnie4w.jdao.dao.Hstest">
    SELECT * FROM hstest WHERE id &lt; #{id} AND age &lt; #{age}
</select>
```
```java
@Test
public void selectOne() throws JdaoException, JdaoClassException, SQLException {
    Hstest hs = jdaoMapper.selectOne("io.github.donnie4w.jdao.action.Mapperface.selectHstestById", 2, 26);
    System.out.println(hs);
}
```

##### Example 2

```xml
<select id="selectHstest1" parameterType="int" resultType="io.github.donnie4w.jdao.dao.Hstest1">
    SELECT * FROM hstest1 WHERE id &gt; 1 AND id &lt; 10 LIMIT #{limit}
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