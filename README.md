## Jdao Test Demo  [[中文](https://github.com/donnie4w/jdaodemo/blob/master/README_zh.md)]

###### This is a test demo program for Jdao, which includes a packaged SQLite test database `hstest.db` file with generated sample data. The demo program can run directly, except for testing read-write separation or multi-data source operations. Other Test operations by default manipulate the `hstest.db` database and can be executed directly to view data operation results.

##### The demo program tests the following aspects:

1. Data operations using Jdao's mapping files
2. Jdao's transactions, stored procedures, batch processing, and serialization operations
3. CRUD function usage of Jdao interfaces
4. Usage of JdaoCache caching interface
5. Binding and removing operations of JdaoSlave for data read-write separation
6. SQL file mapping and interface invocation operations with JdaoMapper

### The detailed usage instructions for Jdao can be found in the user documentation: https://tlnet.top/jdaoendoc

## The following is an overview of the demo

### Generating Database Table Mapping Files

#### Code Construction Tool Download: [https://tlnet.top/download](https://tlnet.top/download)

##### Example in a Windows Environment

1. Generate the Configuration File: `jdao.json`

```bash
// Generate configuration file
win201_jdao.exe init
```

2. Modify the `jdao.json` Database Connection

```json
{
  "dbtype": "mysql",
  "dbhost": "localhost",
  "dbport": 3306,
  "dbname": "hstest",
  "dbuser": "root",
  "dbpwd": "123456",
  "package": "io.github.donnie4w.jdao.dao"
}
```

3. Execute the Command to Generate Data Files

```bash
win201_jdao.exe -c jdao.json
```

Execution results in generating the following files:

```text
io/github/donnie4w/jdao/dao/Hstest.java
io/github/donnie4w/jdao/dao/Hstest1.java
io/github/donnie4w/jdao/dao/Hstest2.java
```

4. Configure the Data Source: The demo uses the Druid connection pool and SQLite database

##### `sqlite.properties`

```text
driverClassName=org.sqlite.JDBC
url=jdbc:sqlite:hstest.db
initialSize=10
maxActive=20
```

##### Java Program Data Source Retrieval

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

5. Jdao Data Source Initialization

```java
Jdao.init(DataSourceFactory.getDataSourceBySqlite(), DBType.SQLITE);
// Enable logging in the test environment
Jdao.setLogger(true);
```

6. Basic Operations with Mapping Files

```java
// Select Query
@Test
public void select() throws Exception {
    Hstest1 t = new Hstest1();
    t.where(Hstest1.ID.GT(1));
    t.limit(20, 10);
    List<Hstest1> list = t.selects();
    for (Hstest1 hstest1 : list) {
        System.out.println(hstest1);
    }
}

// Insert Operation
@Test
public void insert() throws JdaoException, SQLException {
    Hstest hs = new Hstest();
    hs.setRowname(System.currentTimeMillis() + "");
    hs.setValue(System.nanoTime() + "");
    hs.insert();
}    

// Batch Processing
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
    System.out.println("hs.equals(hs2):" + hs.equals(hs2));
}

// Read-Write Separation
@Test
public void slave() throws JdaoException, JdaoClassException, SQLException {
    System.out.println(Hstest.class.getPackageName());
    // Configure the secondary data source: here it is MySQL  
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

// Data Caching
@Test
public void cache() throws JdaoException, JdaoClassException, SQLException {
    // Bind cache to Hstest.class, with a data cache expiry of 100 milliseconds
    JdaoCache.bindClass(Hstest.class, new CacheHandle(100));

    Hstest t = new Hstest();
    t.where(Hstest.ID.EQ(3));
    Hstest hs = t.select();
    System.out.println(hs);
    Hstest t2 = new Hstest();
    t2.where(Hstest.ID.EQ(3));
    Hstest hs2 = t2.select();
    System.out.println(hs2);
    System.out.println("hs.equals(hs2):" + hs.equals(hs2)); 

    JdaoCache.removeClass(Hstest.class); // Remove cache data
    t2 = new Hstest();
    t2.where(Hstest.ID.EQ(3));
    hs2 = t2.select();
    System.out.println(hs2);
}

// Transactions
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

    // Use transaction with basic interface
    Jdao.executeUpdate(tx, "insert into hstest1(`rowname`,`value`) values(?,?)", "uuuuu>>>>1", "ppppppppp");
    
    // Execute SQL directly with transaction object
    tx.executeUpdate("insert into hstest1(`rowname`,`value`) values(?,?)", "abcdefg", "123456");
    
    // Commit transaction
    tx.commit();
}
```

7. Jdao Basic CRUD Interfaces

```java
// Select Query
@Test
public void select() throws Exception {
    List<DataBean> dblist = Jdao.executeQueryBeans("select * from Hstest1 order by id desc limit 5");
    for (DataBean dataBean : dblist) {
        System.out.println(dataBean);
    }
}

// Insert Operation
@Test
public void insert() throws Exception {
    Jdao.executeUpdate(tx, "insert into hstest1(`rowname`,`value`) values(?,?)", "uuuuu>>>>1", "ppppppppp");
    tx.commit();
}
```
##### mapper maps java interfaces

```java
public interface Mapperface {
    List<Hstest> selectAllHstest();
    List<Hstest> selectHstest(int id, int age);
    Hstest selectHstestByMap(Map map);
    List<Hstest> selectHstestByList(int id, int age);
    Hstest[] selectHstestByList(List list);
    Hstest selectHstestById(int id, int age);
    List<Hstest> selectHstestById(Integer id, Integer age);
    Hstest1 selectHstest1(int limit);
    List<Hstest1> selectHstest1(long limit);
    int insertHstest1(Hstest1 hs);
    int updateHstest1(Hstest1 hs);
    int deleteHstest1(Hstest1 hs);
}


@Test
public void selectMapperFace() throws JdaoException, JdaoClassException, SQLException {
    JdaoMapper jdaoMapper = JdaoMapper.newInstance();
    Mapperface mapper = jdaoMapper.getMapper(Mapperface.class);

    List<Hstest> list = mapper.selectHstestById(Integer.valueOf(5), Integer.valueOf(20));
    for (Hstest hs : list) {
        System.out.println(hs);
    }

    Hstest hstest = mapper.selectHstestById(5, 20);
    System.out.println(hstest);
}
```