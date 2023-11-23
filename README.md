# SMBMS_demo
Java web 入门项目

## 1. **各个层的作用**
  1. Dao：提供对数据库的操作
  1. BaseDao：最基础的数据库操作，包括**提供connection**、**执行sql语句**、**关闭connection/preparement/resultSet对象**等
  2. UserDao：提供对user表的数据库操作
     1. 其中依赖**BaseDao**来对**user**表进行操作
  3. RoleDao：提供对role表的数据库操作
     1. 其中依赖**RoleDao**来对**role**表进行操作
   2. Service：调用Dao/BaseDao/RoleDao来提供服务
      1. 获取用户列表：通过UserDao查询出ResultSet，并将结果进行返回
         1. 通过BaseDao获取connection对象
         2. 通过connection和userDao对象来获取user对象相关信息
   3. Servlet：处理前端发起的请求，需要进行==路由映射==
   4. Filter：拦截前端发起的请求进行处理，同样需要进行==路由映射==
## 2. **依赖关系**：BaseDao->UserDao/RoleDao->Service->Servlet
## 3. **功能实现**：Dao->Service->Servlet
   1. 创建接口和实现类：Dao/DaoImpl、Service/ServiceImpl
   2. 创建Servlet：处理请求和响应
   3. 配置Servlet的路由
## 4. **接口和实现分离：**例如定义**UserDao接口**、**UserDaoImpl实现类**
   1. 这里的需要进行接口和实现分离指的是Dao、Service层
   2. 因为Service不需要为上一层提供服务