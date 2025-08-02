package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Options;
import org.example.model.Message;

import java.util.List;

/**
 * 消息 Mapper 接口
 */
@Mapper
public interface MessageMapper {
    
    //插入消息
    @Insert("INSERT INTO messages (session_id, content, role, created_at, updated_at) VALUES (#{sessionId}, #{content}, #{role}, #{createdAt}, #{updatedAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Message message);
    
    //根据ID查询消息
    @Select("SELECT * FROM messages WHERE id = #{id}")
    Message selectById(@Param("id") Integer id);
    
    //根据会话ID查询所有消息
    @Select("SELECT * FROM messages WHERE session_id = #{sessionId} ORDER BY created_at")
    List<Message> selectBySessionId(@Param("sessionId") Integer sessionId);
    
    //查询所有消息
    @Select("SELECT * FROM messages ORDER BY created_at")
    List<Message> selectAll();
    
    //更新消息
    @Update("UPDATE messages SET content = #{content}, role = #{role}, updated_at = #{updatedAt} WHERE id = #{id}")
    int update(Message message);
    
    //根据ID删除消息
    @Delete("DELETE FROM messages WHERE id = #{id}")
    int deleteById(@Param("id") Integer id);
    
    //根据会话ID删除消息
    @Delete("DELETE FROM messages WHERE session_id = #{sessionId}")
    int deleteBySessionId(@Param("sessionId") Integer sessionId);
} 