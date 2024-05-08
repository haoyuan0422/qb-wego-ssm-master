package com.wego.common.tk.plus.provider;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

import java.util.Set;

/**
 *
 * @author: hc
 * @date: 2023/7/8
 */
public class BatchUpdateByIdProvider extends MapperTemplate {
    public BatchUpdateByIdProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    //  <update id="updateBatch" parameterType="java.util.List">
    //  <!--@mbg.generated-->
    //  update tb_pay_method
    //  <trim prefix="set" suffixOverrides=",">
    //    <trim prefix="`name` = case" suffix="end,">
    //      <foreach collection="list" index="index" item="item">
    //  when id = #{item.id,jdbcType=TINYINT} then #{item.name,jdbcType=VARCHAR}
    //      </foreach>
    //    </trim>
    //    <trim prefix="priority = case" suffix="end,">
    //      <foreach collection="list" index="index" item="item">
    //  when id = #{item.id,jdbcType=TINYINT} then #{item.priority,jdbcType=INTEGER}
    //      </foreach>
    //    </trim>
    //    <trim prefix="`state` = case" suffix="end,">
    //      <foreach collection="list" index="index" item="item">
    //  when id = #{item.id,jdbcType=TINYINT} then #{item.state,jdbcType=TINYINT}
    //      </foreach>
    //    </trim>
    //    <trim prefix="create_time = case" suffix="end,">
    //      <foreach collection="list" index="index" item="item">
    //  when id = #{item.id,jdbcType=TINYINT} then #{item.createTime,jdbcType=TIMESTAMP}
    //      </foreach>
    //    </trim>
    //    <trim prefix="update_time = case" suffix="end,">
    //      <foreach collection="list" index="index" item="item">
    //  when id = #{item.id,jdbcType=TINYINT} then #{item.updateTime,jdbcType=TIMESTAMP}
    //      </foreach>
    //    </trim>
    //  </trim>
    //  where id in
    //          <foreach close=")" collection="list" item="item" open="(" separator=", ">
    //          #{item.id,jdbcType=TINYINT}
    //  </foreach>
    //</update>

    public String batchUpdate(MappedStatement statement) {
        //1.获取实体类对应的Class对象
        Class<?> entityClass = super.getEntityClass(statement);

        //2.获取实体类在数据库中对应的表名
        String tableName = super.tableName(entityClass);

        //3.生成update子句
        String update = SqlHelper.updateTable(entityClass, tableName);

        //4.创建StringBuilder用于拼接SQL语句的各个组成部分
        StringBuilder sb = new StringBuilder(update);

        sb.append("<trim prefix=\"set\" suffixOverrides=\",\">");

        //5.获取所有字段信息
        Set<EntityColumn> columns = EntityHelper.getColumns(entityClass);

        //获取主键
        EntityColumn pkEntityColumn = null;
        for (EntityColumn entityColumn : columns) {
            boolean isPrimaryKey = entityColumn.isId();
            if (isPrimaryKey) {
                pkEntityColumn = entityColumn;
                break;
            }
        }

        for (EntityColumn entityColumn : columns) {
            boolean isPrimaryKey = entityColumn.isId();
            //6.判断当前字段是否为主键
            if (!isPrimaryKey) {
                //7.使用非主键字段拼接SET子句
                String columnHolder = entityColumn.getColumnHolder("item");

                sb.append("<trim prefix=\"").append(entityColumn.getColumn()).append("= case\" suffix=\"end, \">");

                sb.append("<foreach collection=\"list\" index=\"index\" item=\"item\">");

                sb.append(" when ")
                        .append(pkEntityColumn.getColumn()).append(" = ").append(pkEntityColumn.getColumnHolder("item"))
                        .append(" then ").append(columnHolder);

                sb.append("</foreach>");

                sb.append("</trim>");
            }
        }
        sb.append("</trim>");

        //10.使用前面缓存的主键名、主键值拼接where子句
        sb.append("where ").append(pkEntityColumn.getColumn()).append(" in ");
        sb.append("<foreach close=\")\" collection=\"list\" item=\"item\" open=\"(\" separator=\", \">");
        sb.append(" #{item.").append(pkEntityColumn.getProperty()).append("}");
        sb.append("</foreach>");

        //11.将拼接好的字符串返回
        return sb.toString();
    }

}
