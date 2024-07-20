##  Jdao Test Demo [[English](https://github.com/donnie4w/jdaodemo/blob/master/README.md)]

###### 这是Jdao的测试demo程序，已经打包sqlite测试数据库hstest.db文件,并生成元素数据。demo程序可以直接运行，除了需要测试读写分离或多数据源操作，其他Test默认操作hstest.db数据库数据，可以直接运行，查看数据操作结果。

##### demo程序测试以下几个方面

1. jdao的映射文件数据操作
2. Jdao的事务，存储过程，批处理，序列化等操作
3. Jdao接口的CRUD函数使用
4. JdaoCache 缓存接口使用
5. JdaoSlave数据读写分离绑定与移除操作
6. JdaoMapper SQL文件映射与接口调用操作

### Jdao的详细使用说明请查看使用文档：https://tlnet.top/jdaodoc

## 以下是dome概述

### 生成数据库表映射文件

#####  代码构建工具下载：https://tlnet.top/download

###### 以window环境为例

1.  生成配置文件: jdao.json

```bash
//生成配置文件
jdao.exe init
```
2.  修改jdao.json的数据库连接

```text
  "dbtype": "mysql",
  "dbhost": "localhost",
  "dbport": 3306,
  "dbname": "hstest",
  "dbuser": "root",
  "dbpwd": "123456",
  "package": "io.github.donnie4w.jdao.dao",
```
3.  执行数据文件生成命令
```bash
jdao.exe -c jdao.json
```
##### 执行结果，生成文件

```text
io/github/donnie4w/jdao/dao/Hstest.java
io/github/donnie4w/jdao/dao/Hstest1.java
io/github/donnie4w/jdao/dao/Hstest2.java
```

4.  配置数据源，demo使用druid 数据连接池，并使用sqlite数据库

##### sqlite.properties

```text
driverClassName=org.sqlite.JDBC
url=jdbc:sqlite:hstest.db
initialSize=10
maxActive=20
```
##### java程序数据源获取

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

5.  Jdao设置数据源

```java
Jdao.init(DataSourceFactory.getDataSourceBySqlite(), DBType.SQLITE);
//测试环境开启日志打印
Jdao.setLogger(true);
```

6.  映射文件的基础操作

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

    JdaoCache.removeClass(Hstest.class);//移除缓存数据
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

7.  Jdao基础CURD接口

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
public void select() throws Exception {
    Jdao.executeUpdate(tx, "insert into hstest1(`rowname`,`value`) values(?,?)", "uuuuu>>>>1", "ppppppppp");
    tx.commit();
}
```
##### mapper映射为java接口

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