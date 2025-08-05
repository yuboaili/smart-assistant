package org.example.controller;

import org.example.model.ChatRequest;
import org.example.model.ChatResponse;
import org.example.model.Message;
import org.example.service.LlmClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 聊天控制器
 */
@RestController
@RequestMapping("/api/chat")
public class ChatController {
    
    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);
    
    @Autowired
    private LlmClient llmClient;
    
    /**
     * 聊天接口
     */
    @PostMapping
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest request) {
        try {
            // 验证请求
            if (request.getMessages() == null) {
                request.setMessages(new ArrayList<>());
            }
            
            // 如果 Prompt 字段存在内容，将其插入到 Messages 数组头部
            if (request.getPrompt() != null && !request.getPrompt().isEmpty()) {
                Message newMessage = new Message();
                newMessage.setRole("user");
                newMessage.setContent(request.getPrompt());
                request.getMessages().add(0, newMessage);
            }
            
            // 设置时间戳
            String now = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            
            // 调用LLM
            ChatResponse response = llmClient.chat(request);
            
            // 补充响应信息
            response.setTimestamp(now);
            response.setSessionId(request.getSessionId());
            response.setLang(request.getLang());
            response.setMetadata(request.getMetadata());
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            logger.error("Chat request failed", e);
            return ResponseEntity.internalServerError().build();
        }
    }
    
    /**
     * 获取支持的模型列表
     */
    @GetMapping("/models")
    public ResponseEntity<List<String>> getSupportedModels() {
        return ResponseEntity.ok(Arrays.asList("qwen-turbo"));
    }
} 