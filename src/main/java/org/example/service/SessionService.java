package org.example.service;

import org.example.model.Session;
import org.example.model.Message;
import java.util.List;
import java.util.Optional;

/**
 * 会话服务接口
 */
public interface SessionService {
    
    //创建新的会话
    Session createSession(Session session);
    
    //删除会话
    boolean deleteSession(Integer id);

    //根据ID获取会话
    Optional<Session> getSessionById(Integer id);
    
    //获取助手会话
    Optional<Session> getSessionByAssistantId(Integer assistantId);
    
    //向会话添加消息
    Message addMessageToSession(Integer sessionId, Message message);
    
    //获取会话的所有消息
    List<Message> getSessionMessages(Integer sessionId);
} 