package com.ricky.domain.batch;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
 
/**
 * 批量更新方法实现，条件为主键，选择性更新
 */
@Slf4j
public class UpdateBatchMethod extends AbstractMethod {

    /**
     * update user set name = "a", age = 17 where id = 1;
     * update user set name = "b", age = 18 where id = 2;
     <script>
     <foreach collection="list" item="item" separator=";">
     update user
     <set>
     <if test="item.name != null and item.name != ''">
     name = #{item.name,jdbcType=VARCHAR},
     </if>
     <if test="item.age != null">
     age = #{item.age,jdbcType=INTEGER},
     </if>
     </set>
     where id = #{item.id,jdbcType=INTEGER}
     </foreach>
     </script>
     */
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sql = "<script>\n<foreach collection=\"list\" item=\"item\" separator=\";\">\nupdate %s %s where %s=#{%s} %s\n</foreach>\n</script>";
        String additional = tableInfo.isWithVersion() ? tableInfo.getVersionFieldInfo().getVersionOli("item", "item.") : "" + tableInfo.getLogicDeleteSql(true, true);
        String setSql = sqlSet(tableInfo.isWithLogicDelete(), false, tableInfo, false, "item", "item.");
        String sqlResult = String.format(sql, tableInfo.getTableName(), setSql, tableInfo.getKeyColumn(), "item." + tableInfo.getKeyProperty(), additional);
        log.debug("sqlResult----->{}", sqlResult);
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sqlResult, modelClass);
        // 第三个参数必须和RootMapper的自定义方法名一致
        return this.addUpdateMappedStatement(mapperClass, modelClass, "updateBatch", sqlSource);
    }
 
}