# 说在前面
程序在运行的时候，基本上每个模块都可以独立运行的，不同的模块具有的功能不同。具体哪个模块具有什么功能，需要阅读【系统整体架构图】来查看模块与模块之间的关系。比如运行【menu】模块，会同时具有用户、角色等功能。

# 运行前准备
运行之前，需要先修改 common/config/src/main/resources/application.yaml文件，修改数据库的URL、账户、密码等信息，并把oa_1805.sql里面的脚本，导入到数据库中。

# 运行程序
两种方式运行：
* 使用mvn命令
此时需要先切换到项目所在的位置，运行mvn install。然后再切换到要运行的模块里面，执行mvn spring-boot:run命令启动程序。
* 使用IDE工具运行
把项目导入IDE工具中（比如Eclipse、IDEA等），然后选择要运行的模块，利用工具自身的方式来运行程序，通常不需要安装。

# 使用程序
需要先登录到系统中才能正常使用，访问任何的页面，都需要登录的，如果使用了【oa_1805.sql】里面的数据，那么测试账户为guanyu，密码是1234

# 系统整体架构
![](https://github.com/fkjava/oa_1805/raw/master/snashots/%E7%B3%BB%E7%BB%9F%E6%95%B4%E4%BD%93%E6%9E%B6%E6%9E%84.png)

# 登录
![](https://github.com/fkjava/oa_1805/raw/master/snashots/01-%E7%99%BB%E5%BD%95.png)

# 首页
![](https://github.com/fkjava/oa_1805/raw/master/snashots/02-%E9%A6%96%E9%A1%B5.png)

# 角色管理
![](https://github.com/fkjava/oa_1805/raw/master/snashots/03-%E8%A7%92%E8%89%B2%E7%AE%A1%E7%90%86.png)

# 用户管理
![](https://github.com/fkjava/oa_1805/raw/master/snashots/04-%E7%94%A8%E6%88%B7%E7%AE%A1%E7%90%86.png)

# 编辑用户信息
![](https://github.com/fkjava/oa_1805/raw/master/snashots/05-%E7%BC%96%E8%BE%91%E7%94%A8%E6%88%B7%E4%BF%A1%E6%81%AF.png)

# 菜单管理
![](https://github.com/fkjava/oa_1805/raw/master/snashots/06-%E8%8F%9C%E5%8D%95%E7%AE%A1%E7%90%86.png)

# 文件管理
![](https://github.com/fkjava/oa_1805/raw/master/snashots/07-%E6%96%87%E4%BB%B6%E7%AE%A1%E7%90%86.png)

# 公告类型
![](https://github.com/fkjava/oa_1805/raw/master/snashots/08-%E5%85%AC%E5%91%8A%E7%B1%BB%E5%9E%8B.png)

# 公告管理
![](https://github.com/fkjava/oa_1805/raw/master/snashots/09-%E5%85%AC%E5%91%8A%E7%AE%A1%E7%90%86.png)

# 编辑公告
![](https://github.com/fkjava/oa_1805/raw/master/snashots/10-%E7%BC%96%E8%BE%91%E5%85%AC%E5%91%8A.png)

