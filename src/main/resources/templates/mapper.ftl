<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<#macro mapperEl field type>${r"#{"}${field}${r",jdbcType="}${type}}</#macro>

<mapper namespace="${pakage}.dao.${className}Dao">

    <insert id="insert${className}">
        insert into ${tableName} (
        <#list fieldMap?keys as key>
            <#if key != "pk_id">
            ${key},
            </#if>
        </#list>
        )
        values (
        <#list fieldMap?keys as key>
            <#if key != "pk_id">
            <@mapperEl fieldMap[key] typeMap[key]/>,
            </#if>
        </#list>
        )
    </insert>

    <select id="get${className}" resultType="${pakage}.po.${className}Po">
        SELECT
        sms_receiver as smsReceiver,
        ding_receiver as dingReceiver,
        pk_id as pkId,
        user_id as userId,
        app_id as appId,
        app_name as appName
        FROM ${tableName}
        where pk_id = ${r"#{"}pkId}
    </select>

    <select id="get${className}s" resultType="${pakage}.po.${className}Po">
        SELECT
        sms_receiver as smsReceiver,
        ding_receiver as dingReceiver,
        pk_id as pkId,
        user_id as userId,
        app_id as appId,
        app_name as appName
        FROM ${tableName}
    </select>

    <update id="update${className}" parameterType="${pakage}.${className}Po">
        update ${tableName}
        <set>
        <#list fieldMap?keys as key>
            <#if key != "pk_id">
            <if test="${fieldMap[key]} != null" >
                <@mapperEl fieldMap[key] typeMap[key]/>,
            </if>
            </#if>
        </#list>
        </set>
        where pk_id = ${r"#{"}pkId}
    </update>

    <delete id="delete${className}" >
        delete from ${tableName} where pk_id = ${r"#{"}pkId}
    </delete>
</mapper>
