package org.example.controller;

import org.example.model.Session;
import org.example.model.Message;
import org.example.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 会话控制器
 */
@RestController
@RequestMapping("/api/session")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    //创建会话
    @PostMapping("/create")
    public ResponseEntity<Session> createSession(@RequestBody Session session) {
        Session createdSession = sessionService.createSession(session);
        return ResponseEntity.ok(createdSession);
    }

    //根据ID获取会话
    @GetMapping("/getbyid/{id}")
    public ResponseEntity<Session> getSessionById(@PathVariable Integer id) {
        Optional<Session> session = sessionService.getSessionById(id);
        return session.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //根据助手id获取会话
    @GetMapping("/getbyassistantid/{assistantId}")
    public ResponseEntity<Session> getSessionByAssistantId(@PathVariable Integer assistantId) {
        Optional<Session> session = sessionService.getSessionByAssistantId(assistantId);
        return session.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //删除会话
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Integer id) {
        boolean deleted = sessionService.deleteSession(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    //向会话添加消息
    @PostMapping("/addmessage/{sessionId}")
    public ResponseEntity<Message> addMessageToSession(@PathVariable Integer sessionId, @RequestBody Message message) {
        Message addedMessage = sessionService.addMessageToSession(sessionId, message);
        return ResponseEntity.ok(addedMessage);
    }

    //获取会话的所有消息
    @GetMapping("/getallmessages/{sessionId}")
    public ResponseEntity<List<Message>> getSessionMessages(@PathVariable Integer sessionId) {
        List<Message> messages = sessionService.getSessionMessages(sessionId);
        return ResponseEntity.ok(messages);
    }
} 