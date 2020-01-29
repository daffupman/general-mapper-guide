# 通用Mapper

## 介绍

> 文档：  
> https://gitee.com/free/Mapper/wikis/Home
> https://github.com/abel533/Mapper/wiki/4.1.mappergenerator

通用mapper插件可以替我们生成常用的CRUD的SQL语句。根据entity对象动态地生成mapper映射文件。

## 集成通用Mapper的步骤
- 添加依赖
    > https://mvnrepository.com/artifact/org.mybatis/mybatis
    ```xml
    <dependency>
        <groupId>tk.mybatis</groupId>
        <artifactId>mapper</artifactId>
        <version>4.1.5</version>
    </dependency>
    ```
- 修改spring配置信息

    在 `spring-context.xml` 中将 `MapperScannerConfigurer` 的Bean修改为：
    ```xml
    <bean class="tk.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="io.daff.general.mapper"/>
    </bean>
    ```
  
## 常用注解

- @Table：在数据库表名和实体类名之间做映射。默认情况下两者名称是一样的；
    ```java
    @Table(name = "t_employee")
    public class Employee {
        // ...
    } 
    ```
- @Column：在列名和属性名做映射，列名默认下划线风格，属性默认驼峰风格；
    ```java
    @Table(name = "t_employee")
    public class Employee {  
        // ...
        @Column(name = "emp_name")
        private String username;
        // ...
    } 
    ```
- @Id：标记当前字段为主键，与xxxByPrimaryKey的方法使用。如果不明确指出主键，那么通用mapper会把实体类中的所有属性当作一个联合主键；
- @GeneratedValue(strategy=GenerationType.IDENTITY)：insert操作之后的主键回写策略；
    ```java
      @Id
      // mysql数据库的主键自增策略
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name = "emp_id")
      private Integer id;
    ```
- @Transient：通常情况下，实体类的字段和数据库表的列是一一对应的，但有时候会在实体类中增加一些额外的属性，此时这些属性需要使用@Transient注解标记。也就是告诉通用mapper这个属性不是数据库表中的列。
    ```java
      @Transient
      private String other;
    ```
  
## 常用方法
- `T selectOne(T record)`：使用实体类的非空值生成where查询条件，并使用“=”进行比较；并且只能返回一条数据；
- `List<T> select(T record)`
- `List<T> selectAll()`：
- `int selectCount()`
- `<T>T selectByPrimaryKey(Object o)`：需要使用 `@Id` 注解明确标记和数据库表主键对应的实体类字段，否则通用mapper会将所有实体类字段作为联合主键。只能根据主键判断是否存在。
- `int insert(T record)`：
- `int insertSelective(T record)`：属性为null的将不会出现在sql语句中
- `int delete(T record)`：【注意】会删除所有记录；
- `int deleteSelective(T record)`：
- `int update(T record)`：
- `int updateSelective(T record)`：
- `List<T> SelectByExampleRowBoundsMapper<T>`：内存分页，建议使用pageHelper插件；
- `List<T> SelectRowBoundsMapper<T>`
  
## QBC查询

Query By Criteria：即QBC查询，将查询条件通过Java对象进行模块化封装，示例见 `EmployeeMapperTest#testSelectByExample`；

## 通用mapper逆向工程

![](https://raw.githubusercontent.com/daffupman/markdown-img/master/20200128185803.png)
  
## 自定义mapper接口

### 定制mapper接口
在项目中我们可以继承我们自己想要的Mapper，拥有想要的CRUD方法。
```java
public interface BaseMapper<T>
        extends SelectAllMapper, SelectByPrimaryKeyMapper {
}
```
然后需要配置 `MapperScannerConfigurer` 注册自定义的Mapper接口。
```xml
<bean class="tk.mybatis.spring.mapper.MapperScannerConfigurer">
    <property name="basePackage" value="io.daff.mapper"/>
    <!-- 使用自定义的Mapper接口 -->
    <property name="properties">
        <value>
            mappers=io.daff.base.BaseMapper
        </value>
    </property>
</bean>
```
> 注意：自定义的mapper类与生成的Mapper类不能存在于同一个包下。

### 拓展mapper接口

![mapper体系](https://raw.githubusercontent.com/daffupman/markdown-img/master/20200129131151.png)

1、创建自定义的mapper接口

```java
public interface BatchUpdateSelectiveMapper<T> {

    @UpdateProvider(type = BatchUpdateSelectiveProvider.class, method = "dynamicSQL")
    void batchUpdateSelective(List<T> list);
}
```

2、创建对应的Provider类，同时编写一个与拓展Mapper类中方法名一样的方法（返回值可以不一样，参数不一样）

```java
public class BatchUpdateSelectiveProvider extends MapperTemplate {

    public BatchUpdateSelectiveProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String batchUpdateSelective(MappedStatement ms) {
        // TODO 拼接具体的sql
    }
}
```

## 二级缓存
mybatis的二级缓存的开启:
- 在mybatis配置文件中打开缓存
    ```xml
    <settings>
        <setting name="cacheEnabled" value="true"/>
    </settings>
    ```
- 在XxxMapper上添加@CacheNamespace
    ```java
    @CacheNamespace
    public interface EmployeeMapper extends BaseMapper<Employee> {
    }
    ```
使用缓存时，相应的实体类对象必须是实现序列化接口的。可使用redis等高速缓存替代。

## 复杂类型的映射

默认情况下，mybatis只会自动映射简单的类型。而复杂类型，mybatis会选择跳过。TypeHandler可以解决复杂类型字段的存储问题。BaseTypeHandler的几个重要的方法：
```java
public abstract class BaseTypeHandler<T> extends TypeReference<T> implements TypeHandler<T> {

    // other methods...

    // 将Java对象做相应转换，存入数据库表中的相应字段（对象 =》 字符串）
    public abstract void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException;

    // 从结果集中获取数据库对应的查询结果（字符串 =》对象）
    public abstract T getNullableResult(ResultSet rs, String columnName) throws SQLException;

    public abstract T getNullableResult(ResultSet rs, int columnIndex) throws SQLException;

    public abstract T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException;
}
```
在自定义TypeHandler时，需要继承BaseTypeHandler类。然后重新上面的4个抽象方法。再把自定义的TypeHandler注册给通用mapper，注册的方式有：
- 字段级别：@ColumnType注解
- 全局级别：在mybatis配置文件中配置typeHandlers

对于枚举类，可做如下操作：
- 使用value值插入
```xml
<bean class="tk.mybatis.spring.mapper.MapperScannerConfigurer">
    <property name="basePackage" value="io.daff.type.mapper"/>
    <property name="properties">
        <value>
            enumAsSimpleType=true
        </value>
    </property>
</bean>
```

## 其他注意点

mybatis、mybatis-spring和Spring的版本需要匹配。否则报错：`java.lang.AbstractMethodError: org.mybatis.spring.transaction.SpringManagedTransaction.getTimeout()Ljava/lang/Integer`：

![](https://raw.githubusercontent.com/daffupman/markdown-img/master/20200126232058.png)