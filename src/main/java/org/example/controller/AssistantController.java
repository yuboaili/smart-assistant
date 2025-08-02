package org.example.controller;

import org.example.model.Assistant;
import org.example.service.AssistantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 智能助手控制器
 */
@RestController
@RequestMapping("/api/assistant")
public class AssistantController {

    @Autowired
    private AssistantService assistantService;

    //创建新的智能助手
    @PostMapping("/create")
    public ResponseEntity<Assistant> createAssistant(@RequestBody Assistant assistant) {
        Assistant createdAssistant = assistantService.createAssistant(assistant);
        return ResponseEntity.ok(createdAssistant);
    }

    //根据ID获取智能助手
    @GetMapping("/get/{id}")
    public ResponseEntity<Assistant> getAssistantById(@PathVariable Integer id) {
        Optional<Assistant> assistant = assistantService.getAssistantById(id);
        return assistant.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //获取所有智能助手
    @GetMapping("/getall")
    public ResponseEntity<List<Assistant>> getAllAssistants() {
        List<Assistant> assistants = assistantService.getAllAssistants();
        return ResponseEntity.ok(assistants);
    }

    //更新智能助手信息
    @PutMapping("/update/{id}")
    public ResponseEntity<Assistant> updateAssistant(@PathVariable Integer id, @RequestBody Assistant assistant) {
        assistant.setId(id);
        Assistant updatedAssistant = assistantService.updateAssistant(assistant);
        return ResponseEntity.ok(updatedAssistant);
    }

    //删除智能助手
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAssistant(@PathVariable Integer id) {
        boolean deleted = assistantService.deleteAssistant(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
} 