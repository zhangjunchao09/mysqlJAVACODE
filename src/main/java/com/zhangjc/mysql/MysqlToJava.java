package com.zhangjc.mysql;

import com.zhangjc.mysql.jdbc.DBHelper;
import com.zhangjc.mysql.utils.SqlToPoUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class MysqlToJava {

    public static void main(String[] args) {
        String tableName = "t_person_crowd_subscribe";
        String dbName = "db_personcenter";
        String pakage = "com.dtdream.personservice";
        String basePath = "D:\\autoCode\\";
        String className = SqlToPoUtil.replaceUnderlineAndfirstToUpper(tableName).substring(1);
        String sql = "select column_name,data_type,character_maximum_length,column_comment " +
                "from information_schema.columns where table_schema ='" + dbName + "'  and table_name = '" + tableName + "' order by data_type";//SQL语句
        DBHelper db1 = new DBHelper(sql);//创建DBHelper对象
        ResultSet ret = null;
        try {
            ret = db1.pst.executeQuery();//执行语句，得到结果集
            Map<String, String> fieldMap = new LinkedHashMap<>();
            Map<String, String> javaTypeMap = new LinkedHashMap<>();
            Map<String, String> jdbcTypeMap = new LinkedHashMap<>();
            while (ret.next()) {
                String column_name = ret.getString(1);
                String data_type = ret.getString(2);
                if (null != column_name && !column_name.equals("")) {
                    fieldMap.put(column_name, SqlToPoUtil.replaceUnderlineAndfirstToUpper(column_name));
                    if ("bigint".equals(data_type)) {
                        javaTypeMap.put(column_name, "Long");
                        jdbcTypeMap.put(column_name, "BIGINT");
                    } else if ("int".equals(data_type)) {
                        javaTypeMap.put(column_name, "Integer");
                        jdbcTypeMap.put(column_name, "INTEGER");
                    } else if ("datetime".equals(data_type)) {
                        javaTypeMap.put(column_name, "Date");
                        jdbcTypeMap.put(column_name, "TIMESTAMP");
                    } else {
                        javaTypeMap.put(column_name, "String");
                        jdbcTypeMap.put(column_name, "VARCHAR");
                    }
                }
            }
            CreateCode.createDao(basePath, pakage, className);
            CreateCode.createService(basePath, pakage, className);
            CreateCode.createServiceIml(basePath, pakage, className);
            CreateCode.createModel(javaTypeMap, basePath, pakage, className);
            CreateCode.createPo(javaTypeMap, basePath, pakage, className);
            CreateCode.createMapper(fieldMap, jdbcTypeMap, basePath, pakage, className, tableName);
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
            if (null != db1) {
                db1.close();
            }
        }
    }
}
