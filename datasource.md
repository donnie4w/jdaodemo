## 数据源DataSource 获取示例

##### 以mysql为例
* 连接地址：jdbc:mysql://localhost:3306/yourdatabase
* 用户名：yourusername
* 密码：yourpassword
* 驱动：com.mysql.cj.jdbc.Driver

### 使用 Druid 获取 DataSource

首先，需要在 `pom.xml` 文件中添加 Druid 的依赖：
```xml
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.2.8</version>
</dependency>
```

然后，编写获取 DataSource 的函数：

```java
import com.alibaba.druid.pool.DruidDataSource;
import javax.sql.DataSource;

public class DataSourceUtil {

    public static DataSource getDataSourceByDruid() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/yourdatabase");
        dataSource.setUsername("yourusername");
        dataSource.setPassword("yourpassword");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setInitialSize(5);
        dataSource.setMaxActive(10);
        dataSource.setMinIdle(1);
        dataSource.setMaxWait(60000);
        return dataSource;
    }
}
```

### 使用 C3P0 获取 DataSource

首先，需要在 `pom.xml` 文件中添加 C3P0 的依赖：
```xml
<dependency>
    <groupId>com.mchange</groupId>
    <artifactId>c3p0</artifactId>
    <version>0.9.5.5</version>
</dependency>
```

然后，编写获取 DataSource 的函数：

```java
import com.mchange.v2.c3p0.ComboPooledDataSource;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;

public class DataSourceUtil {

    public static DataSource getDataSourceByC3P0() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/yourdatabase");
        dataSource.setUser("yourusername");
        dataSource.setPassword("yourpassword");
        dataSource.setMinPoolSize(5);
        dataSource.setAcquireIncrement(5);
        dataSource.setMaxPoolSize(20);
        return dataSource;
    }
}
```

### 使用 HikariCP 获取 DataSource

首先，需要在 `pom.xml` 文件中添加 HikariCP 的依赖：
```xml
<dependency>
    <groupId>com.zaxxer</groupId>
    <artifactId>HikariCP</artifactId>
    <version>4.0.3</version>
</dependency>
```

然后，编写获取 DataSource 的函数：

```java
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;

public class DataSourceUtil {

    public static DataSource getDataSourceByHikariCP() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/yourdatabase");
        config.setUsername("yourusername");
        config.setPassword("yourpassword");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(1);
        config.setIdleTimeout(60000);
        return new HikariDataSource(config);
    }
}
```

### 使用 Apache Commons DBCP 获取 DataSource

首先，需要在 `pom.xml` 文件中添加 Apache Commons DBCP 的依赖：
```xml
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-dbcp2</artifactId>
    <version>2.8.0</version>
</dependency>
```

然后，编写获取 DataSource 的函数：

```java
import org.apache.commons.dbcp2.BasicDataSource;
import javax.sql.DataSource;

public class DataSourceUtil {

    public static DataSource getDataSourceByDBCP() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/yourdatabase");
        dataSource.setUsername("yourusername");
        dataSource.setPassword("yourpassword");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setInitialSize(5);
        dataSource.setMaxTotal(10);
        dataSource.setMinIdle(1);
        dataSource.setMaxWaitMillis(60000);
        return dataSource;
    }
}
```

### 使用 Tomcat JDBC Connection Pool 获取 DataSource

首先，需要在 `pom.xml` 文件中添加 Tomcat JDBC Connection Pool 的依赖：
```xml
<dependency>
    <groupId>org.apache.tomcat</groupId>
    <artifactId>tomcat-jdbc</artifactId>
    <version>10.0.8</version>
</dependency>
```

然后，编写获取 DataSource 的函数：

```java
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class DataSourceUtil {

    public static DataSource getDataSourceByTomcat() {
        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:mysql://localhost:3306/yourdatabase");
        p.setUsername("yourusername");
        p.setPassword("yourpassword");
        p.setDriverClassName("com.mysql.cj.jdbc.Driver");
        p.setInitialSize(5);
        p.setMaxActive(10);
        p.setMinIdle(1);
        p.setMaxWait(60000);

        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setPoolProperties(p);
        return dataSource;
    }
}
```

### 完整的 DataSourceUtil 类

```java
import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

public class DataSourceUtil {

    public static DataSource getDataSourceByDruid() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/yourdatabase");
        dataSource.setUsername("yourusername");
        dataSource.setPassword("yourpassword");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setInitialSize(5);
        dataSource.setMaxActive(10);
        dataSource.setMinIdle(1);
        dataSource.setMaxWait(60000);
        return dataSource;
    }

    public static DataSource getDataSourceByC3P0() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/yourdatabase");
        dataSource.setUser("yourusername");
        dataSource.setPassword("yourpassword");
        dataSource.setMinPoolSize(5);
        dataSource.setAcquireIncrement(5);
        dataSource.setMaxPoolSize(20);
        return dataSource;
    }

    public static DataSource getDataSourceByHikariCP() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/yourdatabase");
        config.setUsername("yourusername");
        config.setPassword("yourpassword");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(1);
        config.setIdleTimeout(60000);
        return new HikariDataSource(config);
    }

    public static DataSource getDataSourceByDBCP() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/yourdatabase");
        dataSource.setUsername("yourusername");
        dataSource.setPassword("yourpassword");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setInitialSize(5);
        dataSource.setMaxTotal(10);
        dataSource.setMinIdle(1);
        dataSource.setMaxWaitMillis(60000);
        return dataSource;
    }

    public static DataSource getDataSourceByTomcat() {
        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:mysql://localhost:3306/yourdatabase");
        p.setUsername("yourusername");
        p.setPassword("yourpassword");
        p.setDriverClassName("com.mysql.cj.jdbc.Driver");
        p.setInitialSize(5);
        p.setMaxActive(10);
        p.setMinIdle(1);
        p.setMaxWait(60000);

        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setPoolProperties(p);
        return dataSource;
    }
}
```
------

#### 各个连接池同时也提供了配置文件读取配置参数。因此，也可以采用读取配置文件的方式来获取数据源实例对象。

## 示例

首先，创建 `datasource.properties` 文件：

```properties
# datasource.properties

jdbc.url=jdbc:mysql://localhost:3306/yourdatabase
jdbc.username=yourusername
jdbc.password=yourpassword
jdbc.driverClassName=com.mysql.cj.jdbc.Driver
jdbc.initialSize=5
jdbc.maxActive=10
jdbc.minIdle=1
jdbc.maxWait=60000
```

### 使用 Druid 获取 DataSource

```java
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataSourceUtil {

    public static DataSource getDataSourceByDruid(String filePath) {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(filePath));
            return DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
```

### 使用 C3P0 获取 DataSource

```java
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataSourceUtil {

    public static DataSource getDataSourceByC3P0(String filePath) {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(filePath));
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass(properties.getProperty("jdbc.driverClassName"));
            dataSource.setJdbcUrl(properties.getProperty("jdbc.url"));
            dataSource.setUser(properties.getProperty("jdbc.username"));
            dataSource.setPassword(properties.getProperty("jdbc.password"));
            dataSource.setMinPoolSize(Integer.parseInt(properties.getProperty("jdbc.minIdle")));
            dataSource.setAcquireIncrement(5);
            dataSource.setMaxPoolSize(Integer.parseInt(properties.getProperty("jdbc.maxActive")));
            return dataSource;
        } catch (IOException | PropertyVetoException e) {
            e.printStackTrace();
        }
        return null;
    }
}
```

### 使用 HikariCP 获取 DataSource

```java
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataSourceUtil {

    public static DataSource getDataSourceByHikariCP(String filePath) {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(filePath));
            HikariConfig config = new HikariConfig(properties);
            return new HikariDataSource(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
```

### 使用 Apache Commons DBCP 获取 DataSource

```java
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataSourceUtil {

    public static DataSource getDataSourceByDBCP(String filePath) {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(filePath));
            return BasicDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
```

### 使用 Tomcat JDBC Connection Pool 获取 DataSource

```java
import org.apache.tomcat.jdbc.pool.PoolProperties;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataSourceUtil {

    public static DataSource getDataSourceByTomcat(String filePath) {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(filePath));
            PoolProperties p = new PoolProperties();
            p.setUrl(properties.getProperty("jdbc.url"));
            p.setUsername(properties.getProperty("jdbc.username"));
            p.setPassword(properties.getProperty("jdbc.password"));
            p.setDriverClassName(properties.getProperty("jdbc.driverClassName"));
            p.setInitialSize(Integer.parseInt(properties.getProperty("jdbc.initialSize")));
            p.setMaxActive(Integer.parseInt(properties.getProperty("jdbc.maxActive")));
            p.setMinIdle(Integer.parseInt(properties.getProperty("jdbc.minIdle")));
            p.setMaxWait(Long.parseLong(properties.getProperty("jdbc.maxWait")));

            org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
            dataSource.setPoolProperties(p);
            return dataSource;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
```

### 完整的 DataSourceUtil 类

```java
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataSourceUtil2 {

    private static Properties loadProperties(String filePath) throws IOException {
        Properties properties = new Properties();
        try (FileReader reader = new FileReader(filePath)) {
            properties.load(reader);
        }
        return properties;
    }

    public static DataSource getDataSourceByDruid(String filePath) {
        try {
            Properties properties = loadProperties(filePath);
            return DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DataSource getDataSourceByC3P0(String filePath) {
        try {
            Properties properties = loadProperties(filePath);
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass(properties.getProperty("jdbc.driverClassName"));
            dataSource.setJdbcUrl(properties.getProperty("jdbc.url"));
            dataSource.setUser(properties.getProperty("jdbc.username"));
            dataSource.setPassword(properties.getProperty("jdbc.password"));
            dataSource.setMinPoolSize(Integer.parseInt(properties.getProperty("jdbc.minIdle")));
            dataSource.setAcquireIncrement(5);
            dataSource.setMaxPoolSize(Integer.parseInt(properties.getProperty("jdbc.maxActive")));
            return dataSource;
        } catch (IOException | PropertyVetoException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DataSource getDataSourceByHikariCP(String filePath) {
        try {
            Properties properties = loadProperties(filePath);
            HikariConfig config = new HikariConfig(properties);
            return new HikariDataSource(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DataSource getDataSourceByDBCP(String filePath) {
        try {
            Properties properties = loadProperties(filePath);
            return BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DataSource getDataSourceByTomcat(String filePath) {
        try {
            Properties properties = loadProperties(filePath);
            PoolProperties p = new PoolProperties();
            p.setUrl(properties.getProperty("jdbc.url"));
            p.setUsername(properties.getProperty("jdbc.username"));
            p.setPassword(properties.getProperty("jdbc.password"));
            p.setDriverClassName(properties.getProperty("jdbc.driverClassName"));
            p.setInitialSize(Integer.parseInt(properties.getProperty("jdbc.initialSize")));
            p.setMaxActive(Integer.parseInt(properties.getProperty("jdbc.maxActive")));
            p.setMinIdle(Integer.parseInt(properties.getProperty("jdbc.minIdle")));
            p.setMaxWait(Integer.parseInt(properties.getProperty("jdbc.maxWait")));
            org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
            dataSource.setPoolProperties(p);
            return dataSource;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
```

### 使用示例

在需要使用 DataSource 的地方调用 `DataSourceUtil2` 类中的方法。例如：

```java
public class Main {
    public static void main(String[] args) {
        String filePath = "datasource.properties";
        DataSource dataSource = DataSourceUtil2.getDataSourceByDruid(filePath);
        // 使用 dataSource 进行数据库操作
    }
}
```