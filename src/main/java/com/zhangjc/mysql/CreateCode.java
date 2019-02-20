package com.zhangjc.mysql;

import com.zhangjc.mysql.utils.FreeMarkeUtil;
import com.zhangjc.mysql.utils.SqlToPoUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class CreateCode {

    static void createMapper(Map<String, String> fieldMap,
                             Map<String, String> jdbcTypeMap,
                             String basePath,
                             String pakage,
                             String className,
                             String tableName) {
        String path = basePath + "mapper";//所创建文件的路径
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();//创建目录
        }
        String fileName = className + "Mapper.xml";//文件名及类型
        Map<String, Object> root = new HashMap<>();
        root.put("className", className);
        root.put("tableName", tableName);
        root.put("pakage", pakage);
        Map<String, String> typeMap = new HashMap<>();
        Set<String> keys = jdbcTypeMap.keySet();
        for (Object key : keys) {
            String k = (String) key;
            String javaType = jdbcTypeMap.get(k);
            typeMap.put(k, javaType);
        }
        root.put("fieldMap", fieldMap);
        root.put("typeMap", typeMap);
        FreeMarkeUtil.fprint(path, "mapper.ftl", root, fileName);

    }

    static void createPo(Map<String, String> javaTypeMap, String basePath, String pakage, String className) {
        String path = basePath + "po";//所创建文件的路径
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();//创建目录
        }
        String fileName = className + "Po.java";//文件名及类型
        Map<String, Object> root = new HashMap<>();
        root.put("className", className);
        root.put("lowclassName", SqlToPoUtil.toLowerCaseFirstOne(className));
        root.put("pakage", pakage);
        Map<String, String> typeMap = new HashMap<>();
        Set<String> keys = javaTypeMap.keySet();
        for (Object key : keys) {
            String k = (String) key;
            String field = SqlToPoUtil.replaceUnderlineAndfirstToUpper(k);
            String javaType = javaTypeMap.get(k);
            typeMap.put(field, javaType);
        }
        root.put("typeMap", typeMap);
        FreeMarkeUtil.fprint(path, "po.ftl", root, fileName);
    }

    public static void createModel(Map<String, String> javaTypeMap, String basePath, String pakage, String className) {
        String path = basePath + "model";//所创建文件的路径
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();//创建目录
        }
        String fileName = className + "Model.java";//文件名及类型
        Map<String, Object> root = new HashMap<>();
        root.put("className", className);
        root.put("lowclassName", SqlToPoUtil.toLowerCaseFirstOne(className));
        root.put("pakage", pakage);
        Map<String, String> typeMap = new HashMap<>();
        Set<String> keys = javaTypeMap.keySet();
        for (Object key : keys) {
            String k = (String) key;
            String field = SqlToPoUtil.replaceUnderlineAndfirstToUpper(k);
            String javaType = javaTypeMap.get(k);
            if (javaType.equals("Date")) {
                javaType = "String";
            }
            if (javaType.equals("Long")) {
                javaType = "String";
            }
            typeMap.put(field, javaType);
        }
        root.put("typeMap", typeMap);
        FreeMarkeUtil.fprint(path, "model.ftl", root, fileName);
    }

    public static void createDao(String basePath, String pakage, String className) {
        String path = basePath + "dao";//所创建文件的路径
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();//创建目录
        }
        String fileName = className + "Dao.java";//文件名及类型
        Map<String, Object> root = new HashMap<>();
        root.put("className", className);
        root.put("lowclassName", SqlToPoUtil.toLowerCaseFirstOne(className));
        root.put("pakage", pakage);
        FreeMarkeUtil.fprint(path, "dao.ftl", root, fileName);
    }

    public static void createService(String basePath, String pakage, String className) {
        String path = basePath + "service";//所创建文件的路径
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();//创建目录
        }
        String fileName = "I" + className + "Service.java";//文件名及类型
        Map<String, Object> root = new HashMap<>();
        root.put("className", className);
        root.put("lowclassName", SqlToPoUtil.toLowerCaseFirstOne(className));
        root.put("pakage", pakage);
        FreeMarkeUtil.fprint(path, "service.ftl", root, fileName);
    }

    public static void createServiceIml(String basePath, String pakage, String className) {
        String path = basePath + "serviceiml";//所创建文件的路径
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();//创建目录
        }
        String fileName = className + "Service.java";//文件名及类型
        Map<String, Object> root = new HashMap<>();
        root.put("className", className);
        root.put("lowclassName", SqlToPoUtil.toLowerCaseFirstOne(className));
        root.put("pakage", pakage);
        FreeMarkeUtil.fprint(path, "serviceiml.ftl", root, fileName);

    }
}
