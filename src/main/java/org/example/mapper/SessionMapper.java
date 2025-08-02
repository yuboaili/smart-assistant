package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Options;
import org.example.model.Session;

/**
 * 会话 Mapper 接口
 */
@Mapper
public interface SessionMapper {
    
    //创建会话
    @Insert("INSERT INTO sessions (assistant_id, created_at, updated_at) VALUES (#{assistantId}, #{createdAt}, #{updatedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Session session);
    
    //根据ID查询会话
    @Select("SELECT * FROM sessions WHERE id = #{id}")
    Session selectById(@Param("id") Integer id);
    
    //根据助手ID查询会话
    @Select("SELECT * FROM sessions WHERE assistant_id = #{assistantId}")
    Session selectByAssistantId(@Param("assistantId") Integer assistantId);
    
    //更新会话
    @Update("UPDATE sessions SET assistant_id = #{assistantId}, updated_at = #{updatedAt} WHERE id = #{id}")
    int update(Session session);
    
    //根据ID删除会话
    @Delete("DELETE FROM sessions WHERE id = #{id}")
    int deleteById(@Param("id") Integer id);
    
    //根据助手ID删除会话
    @Delete("DELETE FROM sessions WHERE assistant_id = #{assistantId}")
    int deleteByAssistantId(@Param("assistantId") Integer assistantId);
    
    //检查ID是否存在
    @Select("SELECT COUNT(*) FROM sessions WHERE id = #{id}")
    int countById(@Param("id") Integer id);
} 