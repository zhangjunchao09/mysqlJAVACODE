# mysqlJavaCode
mysql表自动生成java代码

mainClass: com.zhangjc.mysql.MysqlToJava 

mysql.properties 项目配置信息：
url name
# mysql 连接url 驱动类
dbName
user
password
# mysql 库名 用户名 密码
tableName=t_person_crowd_subscribe
# mysql 需要生成代码的表名 t_开头

pakage=com.zhangjc.person
# 生成java代码包名
basePath=D:\\autoCode\\
# 生成java代码文件存放路径
