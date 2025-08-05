package org.example.service;

import org.example.model.ChatRequest;
import org.example.model.ChatResponse;

/**
 * LLM客户端接口
 */
public interface LlmService {
    
    /**
     * 发送聊天请求
     * @param request 聊天请求
     * @return 聊天响应
     */
    ChatResponse chat(ChatRequest request);
} 