package org.example.model;

import java.time.LocalDateTime;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * 智能助手模型类
 */
public class Assistant {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    static {
        objectMapper.registerModule(new JavaTimeModule());
    }
    
    private Integer id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Assistant() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Assistant(String name, String description) {
        this();
        this.name = name;
        this.description = description;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.updatedAt = LocalDateTime.now();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        try {
            return objectMapper.writeValueAsString(this);
        } catch (Exception e) {
            return "{\"error\":\"Failed to serialize Assistant object\"}";
        }
    }
} 