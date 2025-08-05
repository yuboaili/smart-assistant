package org.example.service.impl;

import org.example.service.LlmClient;
import org.example.model.ChatRequest;
import org.example.model.ChatResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Qwen LLM客户端实现
 */
@Service
public class LlmServiceImpl implements LlmService {
    
    private static final Logger logger = LoggerFactory.getLogger(QwenLlmClient.class);
    private static final String MODEL_NAME = "qwen-turbo";
    
    @Value("${qwen.api.key}")
    private String apiKey;
    
    @Value("${qwen.api.url}")
    private String apiUrl;
    
    private final RestTemplate restTemplate = new RestTemplate();
    
    @Override
    public ChatResponse chat(ChatRequest request) {
        try {
            // 构建请求头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + apiKey);
            
            // 直接使用ChatRequest，设置模型名称
            request.setModel(MODEL_NAME);
            
            HttpEntity<ChatRequest> entity = new HttpEntity<>(request, headers);
            
            // 发送请求
            ResponseEntity<ChatResponse> response = restTemplate.postForEntity(apiUrl, entity, ChatResponse.class);
            
            return response.getBody();
            
        } catch (Exception e) {
            logger.error("Qwen API调用失败", e);
            throw new RuntimeException("LLM调用失败: " + e.getMessage());
        }
    }
    

} 