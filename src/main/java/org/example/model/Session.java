package org.example.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * 会话模型类
 */
public class Session {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    static {
        objectMapper.registerModule(new JavaTimeModule());
    }
    
    private Integer id;
    private Integer assistantId;
    private List<Message> messages;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Session() {
        this.messages = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Session(Integer assistantId) {
        this();
        this.assistantId = assistantId;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAssistantId() {
        return assistantId;
    }

    public void setAssistantId(Integer assistantId) {
        this.assistantId = assistantId;
        this.updatedAt = LocalDateTime.now();
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // 业务方法
    public void addMessage(Message message) {
        this.messages.add(message);
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        try {
            return objectMapper.writeValueAsString(this);
        } catch (Exception e) {
            return "{\"error\":\"Failed to serialize Session object\"}";
        }
    }
} 