package org.example.service.impl;

import org.example.mapper.AssistantMapper;
import org.example.model.Assistant;
import org.example.service.AssistantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 智能助手服务实现类
 */
@Service
public class AssistantServiceImpl implements AssistantService {

    @Autowired
    private AssistantMapper assistantMapper;

    @Override
    public Assistant createAssistant(Assistant assistant) {
        assistantMapper.insert(assistant);
        return assistant;
    }

    @Override
    public Optional<Assistant> getAssistantById(Integer id) {
        return Optional.ofNullable(assistantMapper.selectById(id));
    }

    @Override
    public List<Assistant> getAllAssistants() {
        return assistantMapper.selectAll();
    }

    @Override
    public Assistant updateAssistant(Assistant assistant) {
        if (assistantMapper.countById(assistant.getId()) > 0) {
            assistantMapper.update(assistant);
            return assistant;
        }
        return null;
    }

    @Override
    public boolean deleteAssistant(Integer id) {
        if (assistantMapper.countById(id) > 0) {
            assistantMapper.deleteById(id);
            return true;
        }
        return false;
    }
} 