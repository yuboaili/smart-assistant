package org.example.service.impl;

import org.example.mapper.SessionMapper;
import org.example.mapper.MessageMapper;
import org.example.model.Session;
import org.example.model.Message;
import org.example.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 会话服务实现类
 */
@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionMapper sessionMapper;
    
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public Session createSession(Session session) {
        sessionMapper.insert(session);
        return session;
    }

    @Override
    public boolean deleteSession(Integer id) {
        if (sessionMapper.countById(id) > 0) {
            sessionMapper.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Session> getSessionById(Integer id) {
        Session session = sessionMapper.selectById(id);
        if (session != null) {
            // 手动加载messages
            List<Message> messages = messageMapper.selectBySessionId(id);
            session.setMessages(messages);
        }
        return Optional.ofNullable(session);
    }

    @Override
    public Optional<Session> getSessionByAssistantId(Integer assistantId) {
        Session session = sessionMapper.selectByAssistantId(assistantId);
        if (session != null) {
            // 手动加载messages
            List<Message> messages = messageMapper.selectBySessionId(session.getId());
            session.setMessages(messages);
        }
        return Optional.ofNullable(session);
    }

    @Override
    public Message addMessageToSession(Integer sessionId, Message message) {
        if (sessionMapper.countById(sessionId) > 0) {
            message.setSessionId(sessionId);
            messageMapper.insert(message);
            return message;
        }
        return null;
    }

    @Override
    public List<Message> getSessionMessages(Integer sessionId) {
        return messageMapper.selectBySessionId(sessionId);
    }
} 