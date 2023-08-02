###  Jdao  基于Java的持久层框架，零配置，零SQL操作持久层

------------

- 用最简单的方式操作数据库
- 支持全对象方式读写数据；
- 支持事务，缓存，批处理等操作；
- 支持原生sql 操作;
- 支持注册多数据源，设置对象，类，包名对应不同数据源；
- 支持自动生成数据表对应的java Bean对象；
- jdao使用极其简单方便，可用于快速构建持久层封装服务；

------------

###  jdao的使用用例

1. io.github.donnie4w.jdaodemo.table   table.sql  测试数据表创建 sql
2. io.github.donnie4w.jdaodemo.dataSource 设置数据源demo
3. io.github.donnie4w.jdaodemo.bean  两个文件为数据表对应的Bean文件，即用于操作表数据的类文件
4. io.github.donnie4w.jdaodemo.action  Bean文件操作数据的演示
5. io.github.donnie4w.jdaodemo.createDao  Bean文件可以手动创建，也可以调用jdao中CreateDaoFile 直接生成 Bean文件
6. io.github.donnie4w.jdaodemo.dbutils 操作原生sql的演示

------------

###  jdao maven配置项
			<dependency>
				<groupId>io.github.donnie4w</groupId>
				<artifactId>jdao</artifactId>
				<version>2.0.0</version>
			</dependency>

------------

1. [jdao项目地址：https://github.com/donnie4w/jdao](https://github.com/donnie4w/jdao "jdao项目地址：https://github.com/donnie4w/jdao")
2. [jdao项目地址2：https://gitee.com/donnie4w/jdao](https://gitee.com/donnie4w/jdao "jdao项目地址2：https://gitee.com/donnie4w/jdao")