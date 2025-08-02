package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Options;
import org.example.model.Assistant;

import java.util.List;

/**
 * 智能助手 Mapper 接口
 */
@Mapper
public interface AssistantMapper {
    
    //增加助手
    @Insert("INSERT INTO assistants (name, description, created_at, updated_at) VALUES (#{name}, #{description}, #{createdAt}, #{updatedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Assistant assistant);
    
    //根据ID查询助手
    @Select("SELECT * FROM assistants WHERE id = #{id}")
    Assistant selectById(@Param("id") Integer id);
    
    //查询所有助手
    @Select("SELECT * FROM assistants ORDER BY id")
    List<Assistant> selectAll();
    
    //更新助手
    @Update("UPDATE assistants SET name = #{name}, description = #{description}, updated_at = #{updatedAt} WHERE id = #{id}")
    int update(Assistant assistant);
    
    //根据ID删除助手
    @Delete("DELETE FROM assistants WHERE id = #{id}")
    int deleteById(@Param("id") Integer id);
    
    //检查ID是否存在
    @Select("SELECT COUNT(*) FROM assistants WHERE id = #{id}")
    int countById(@Param("id") Integer id);
} 