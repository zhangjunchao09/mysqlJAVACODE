package com.zhangjc.mysql;

import com.zhangjc.mysql.jdbc.DBHelper;
import com.zhangjc.mysql.utils.PropertiesUtil;
import com.zhangjc.mysql.utils.SqlToPoUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;


public class MysqlToJava {

    public static void main(String[] args) {
        String tableName = PropertiesUtil.getPropertiesByName("tableName");
        String dbName = PropertiesUtil.getPropertiesByName("dbName");
        String pakage = PropertiesUtil.getPropertiesByName("pakage");
        String basePath = PropertiesUtil.getPropertiesByName("basePath");
        String className = SqlToPoUtil.toUpperCaseFirstOne(SqlToPoUtil.replaceUnderlineAndfirstToUpper(tableName));
        String url = PropertiesUtil.getPropertiesByName("url");
        String name = PropertiesUtil.getPropertiesByName("name");
        String user = PropertiesUtil.getPropertiesByName("user");
        String password = PropertiesUtil.getPropertiesByName("password");
        String primaryKeyField = PropertiesUtil.getPropertiesByName("primaryKey");
        String primaryKey = SqlToPoUtil.replaceUnderlineAndfirstToUpper(primaryKeyField);
        String sql = "select column_name,data_type,character_maximum_length,column_comment " +
                "from information_schema.columns where table_schema ='" + dbName + "'  and table_name = '" + tableName + "' order by data_type";//SQL语句
        DBHelper db = new DBHelper(url, name, user, password, sql);//创建DBHelper对象
        ResultSet ret = null;
        try {
            ret = db.pst.executeQuery();//执行语句，得到结果集
            DataGenerate dataGenerate = new DataGenerate();
            dataGenerate.dataGenerate(ret);
            Map<String, String> fieldMap = dataGenerate.getFieldMap();
            Map<String, String> javaTypeMap = dataGenerate.getJavaTypeMap();
            Map<String, String> jdbcTypeMap = dataGenerate.getJdbcTypeMap();
            CreateCode.createDao(basePath, pakage, className, primaryKey);
            CreateCode.createService(basePath, pakage, className, primaryKey);
            CreateCode.createServiceIml(basePath, pakage, className, primaryKey);
            CreateCode.createDto(javaTypeMap, basePath, pakage, className);
            CreateCode.createModel(javaTypeMap, basePath, pakage, className);
            CreateCode.createMapper(fieldMap, jdbcTypeMap, basePath, pakage, className, tableName, primaryKey, primaryKeyField);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (null != ret) {
                try {
                    ret.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (null != db) {
                db.close();
            }
        }
    }
}
