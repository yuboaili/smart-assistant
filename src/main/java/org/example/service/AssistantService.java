package org.example.service;

import org.example.model.Assistant;
import java.util.List;
import java.util.Optional;

/**
 * 智能助手服务接口
 */
public interface AssistantService {
      
     //创建新的智能助手  
    Assistant createAssistant(Assistant assistant);
    
    //根据ID获取智能助手
    Optional<Assistant> getAssistantById(Integer id);
    
    //获取所有智能助手
    List<Assistant> getAllAssistants();
    
    //更新智能助手信息
    Assistant updateAssistant(Assistant assistant);  
    
    //删除智能助手
    boolean deleteAssistant(Integer id);
} 