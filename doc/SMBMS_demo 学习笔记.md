# 1. 新建和运行项目

## 1.1 新建maven项目

1. 新建项目
2. 选择maven项目
3. 模板选择Javaweb

![image-20231022160851822](https://pic-go1.oss-cn-guangzhou.aliyuncs.com/image-20231022160851822.png)

## 1.2 配置Tomcat

1. 新增 local tomcat

![image-20231022161804916](https://pic-go1.oss-cn-guangzhou.aliyuncs.com/image-20231022161804916.png)

2. 新增deployment：选择war exploded
3. 同界面下方设置项目的访问根路径

![image-20231022161416596](https://pic-go1.oss-cn-guangzhou.aliyuncs.com/image-20231022161416596.png)

## 1.3 设置依赖

> 这里就是设置各种依赖的jdk版本一致，本人也不太明白为什么每次项目的依赖初始化版本都是jdk6

![image-20231022161036113](https://pic-go1.oss-cn-guangzhou.aliyuncs.com/image-20231022161036113.png)

## 1.4 运行项目

![image-20231022161518718](https://pic-go1.oss-cn-guangzhou.aliyuncs.com/image-20231022161518718.png)

# 2. 项目包结构

- `pojo/entity`
  - 实体类，每个实体类都对应着一个数据库表；
  - 表的查询结果以实体类对象/列表的形式进行返回
- `dao`——具体数据库操作层(增删改查)；
  - BaseDao：提供最基础的增删改查，提供connection、执行SQL语句、释放connection/preparement/resultSet对象
  - UserDao、BillDao...：提供对不同表的增删改查，其中
- `service`——业务层；
  - 利用BaseDao来**获取connction对象**、**执行sql语句**、**释放connection/preparement/resultSet对象**，作为参数传给UserDao/BillDao对象，来对user/bill表进行增删查改。
- `test/view`——表示层 / 测试层；
- `utils`——工具类；
- `filter`——过滤层，放置过滤器；
  - CharacterEncodingFilter：设置请求和响应结果的编码
  - 
- `servlet`——控制层，调用业务层代码；
  - 处理请求和响应，需要在web.xml中配置路由

![image-20231022162439863](https://pic-go1.oss-cn-guangzhou.aliyuncs.com/image-20231022162439863.png)

# 3 如何在pom.xml中导入相关依赖

> 接下来的例子以添加lombok依赖为例

1. 打开[maven仓库在线网站](https://mvnrepository.com/)

2. 搜索lombok

   ![image-20231022162949709](https://pic-go1.oss-cn-guangzhou.aliyuncs.com/image-20231022162949709.png)

3. 选择一个使用量高的，复制相关配置到项目的pom.xml中

   ![image-20231022163017089](https://pic-go1.oss-cn-guangzhou.aliyuncs.com/image-20231022163017089.png)

![image-20231022163143949](https://pic-go1.oss-cn-guangzhou.aliyuncs.com/image-20231022163143949.png)

4. 在==dependencies==标签下进行粘贴

![image-20231022163334912](https://pic-go1.oss-cn-guangzhou.aliyuncs.com/image-20231022163334912.png)

5. 重载maven项目，下载新增的依赖

![image-20231022163618727](https://pic-go1.oss-cn-guangzhou.aliyuncs.com/image-20231022163618727.png)

# 4 git版本控制

1. 点击VCS，设置git为版本控制系统

![image-20231022165821892](https://pic-go1.oss-cn-guangzhou.aliyuncs.com/image-20231022165821892.png)

![image-20231022165754294](https://pic-go1.oss-cn-guangzhou.aliyuncs.com/image-20231022165754294.png)

2. 设置git作为版本控制系统之后，工具栏的VCS变为GIT

![image-20231022165916570](https://pic-go1.oss-cn-guangzhou.aliyuncs.com/image-20231022165916570.png)

3. 添加.gitignore文件，设置不追踪.idea和target文件夹下的内容

   ![image-20231022170042305](https://pic-go1.oss-cn-guangzhou.aliyuncs.com/image-20231022170042305.png)

4. 第一次提交

   ![image-20231022170235721](https://pic-go1.oss-cn-guangzhou.aliyuncs.com/image-20231022170235721.png)

5. 关联到远程仓库
   1. 在远程新建仓库，并记录远程仓库的地址
   2. 在idea中进行push操作，由于没有远程分支，需要先关联远程分支
   3. 将远程仓库地址粘贴进行关联

![image-20231112011836221](https://pic-go1.oss-cn-guangzhou.aliyuncs.com/image-20231112011836221.png)

# 5 数据库环境搭建

## 5.1 phpstudy 启动mysql服务

![image-20231023102013150](https://pic-go1.oss-cn-guangzhou.aliyuncs.com/image-20231023102013150.png)

## 5.2 nevicat 操作教程

### sql命令行

1. 点击**新建查询**
2. 选择使用的**数据库连接**和**数据库名称**
3. 输出相应的sql语句
4. 点击运行

![image-20231023103311141](https://pic-go1.oss-cn-guangzhou.aliyuncs.com/image-20231023103311141.png)

### 数据库/表的增删改

1. 数据库/表【的属性】的增删改查：直接在相应的数据库连接/数据库/表上右键，选择相应的选项进行增删改查

2. 表数据的增删改查：

   1. 双击表格进行修改
   2. 底下的+或者-用于新增或者删除一行数据

   ![image-20231023103745543](https://pic-go1.oss-cn-guangzhou.aliyuncs.com/image-20231023103745543.png)

### 执行sql文件

1. 新建连接

   ![image-20231023101917026](https://pic-go1.oss-cn-guangzhou.aliyuncs.com/image-20231023101917026.png)

2. 执行sql文件：生成数据库以及相应的表和数据

   ![image-20231023102122241](https://pic-go1.oss-cn-guangzhou.aliyuncs.com/image-20231023102122241.png)

3. 刷新，发现已经生成数据库、表和数据

   1. 我这里刷新的是主机，不确定刷新其他的选项能不能成功。如果不行就多尝试刷新。

   ![image-20231023102209276](https://pic-go1.oss-cn-guangzhou.aliyuncs.com/image-20231023102209276.png)

### 导出sql文件

1. 导出类型

   1. 导出数据库以及其中的表和数据
   2. 导出表和数据

2. 导出方式：在需要导出的数据库/表上右键，选择**转储SQL文件**

   ![image-20231023102702061](https://pic-go1.oss-cn-guangzhou.aliyuncs.com/image-20231023102702061.png)

3. 导出数据库对应的sql文件

4. 导出表对应的sql文件


## 5.3 idea 操作教程

> [idea 数据库操作教程 - b站视频教程](https://www.bilibili.com/video/BV1vN411Z7VY/?spm_id_from=333.337.search-card.all.click&vd_source=ffa7e716e2dd342c5c55c4f7398ab94c)
>
> 使用用法跟navicat差不多，都是通过右键和双击编辑（idea多了一个提交修改数据的操作）

### 选择连接的数据库

![image-20231023101740782](https://pic-go1.oss-cn-guangzhou.aliyuncs.com/image-20231023101740782.png)

![image-20231023105741654](https://pic-go1.oss-cn-guangzhou.aliyuncs.com/image-20231023105741654.png)

### 修改数据

1. 修改完数据之后点击提交修改才会生效
2. 修改完数据之后不想提交？
   1. 点击刷新键，恢复原有的数据

![image-20231023110321969](https://pic-go1.oss-cn-guangzhou.aliyuncs.com/image-20231023110321969.png)

### sql命令行

> **可以选中需要执行的命令再点击运行**，不选中的话默认执行所有sql命令

![image-20231023110839724](https://pic-go1.oss-cn-guangzhou.aliyuncs.com/image-20231023110839724.png)

# 6 使用Junit中的Test注解

1. 添加依赖

   ```
   <!-- https://mvnrepository.com/artifact/junit/junit -->
   <dependency>
       <groupId>junit</groupId>
       <artifactId>junit</artifactId>
       <version>4.13.2</version>
       <scope>test</scope>
   </dependency>
   
   ```

2. 使用@Test注解

# 7 JSP相关问题

## 缺少依赖

```xml
    <dependency>
        <groupId>javax.servlet.jsp</groupId>
        <artifactId>javax.servlet.jsp-api</artifactId>
        <version>2.3.3</version>
    </dependency>
    <dependency>
      <groupId>javax.servlet.jsp.jstl</groupId>
      <artifactId>jstl</artifactId>
      <version>1.2</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/taglibs/standard -->
    <dependency>
      <groupId>taglibs</groupId>
      <artifactId>standard</artifactId>
      <version>1.1.2</version>
    </dependency>

```

![image-20231031193150300](https://pic-go1.oss-cn-guangzhou.aliyuncs.com/image-20231031193150300.png)

### 无法解析EL表达式

1. 设置isELIgnored属性为false

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false"
%>
```

2. 设置maven版本为4.0，默认的版本是2.3，不支持EL
   1. 修改web-app标签的内容如下所示
   2. [参考csdn](https://blog.csdn.net/qq_37823003/article/details/109503916)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
```

# 8. 清空浏览器缓存并刷新界面

1. ctrl+f5 刷新

# 9. !StringUtils.isNullOrEmpty(bill.getProductName())

1. 可以替换为 bill.getProductName() != null && !bill.getProductName().isEmpty()

# 10. idea控制台中文乱码问题

> [参考](https://blog.csdn.net/haohaoxueqd/article/details/128888703)

1. 注意事项，修改好配置之后，==需要重启IDEA==

-Dfile.encoding=UTF-8

# 11. 过滤未登录请求

1. **rq.getSession()在sessionId**不存在/或者为空时将会创建新的session，并且响应set-cookie设置新的JSESSIONID

```java
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    System.out.println("-------SysFilter--------");
    HttpServletRequest rq = (HttpServletRequest) request;
    HttpServletResponse rp = (HttpServletResponse) response;

    // rq.getSession()在sessionId不存在/或者为空时将会创建新的session，并且响应set-cookie设置新的JSESSIONID
    User userSession = (User) rq.getSession().getAttribute("userSession");  // 如果session不存在，当然userSession属性也不存在

    // 请求包中JSESSIONID不存在时为true
    if(userSession == null){
        // 重定向到error.jsp，并响应set-cookie设置新的JSESSIONID
        System.out.println("userSession is null");
        rp.sendRedirect("/smbms/error.jsp");
    }else {
        chain.doFilter(request,response);
    }
}
```

![image-20231124025029375](https://pic-go1.oss-cn-guangzhou.aliyuncs.com/image-20231124025029375.png)