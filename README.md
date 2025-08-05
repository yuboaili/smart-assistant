# 智能助手系统 (Smart Assistant)

基于 Spring Boot 的智能助手系统，提供智能助手管理、会话管理和LLM对话功能。采用 MyBatis + MySQL 进行数据持久化。

## 项目结构

```
smart-assistant/
├── src/main/java/org/example/
│   ├── application/
│   │   └── SmartAssistantApplication.java
│   ├── controller/
│   │   ├── AssistantController.java
│   │   ├── ChatController.java
│   │   └── SessionController.java
│   ├── mapper/
│   │   ├── AssistantMapper.java
│   │   ├── MessageMapper.java
│   │   └── SessionMapper.java
│   ├── model/
│   │   ├── Assistant.java
│   │   ├── ChatRequest.java
│   │   ├── ChatResponse.java
│   │   ├── Message.java
│   │   └── Session.java
│   └── service/
│       ├── AssistantService.java
│       ├── LlmService.java
│       ├── SessionService.java
│       └── impl/
│           ├── AssistantServiceImpl.java
│           ├── LlmServiceImpl.java
│           └── SessionServiceImpl.java
├── src/main/resources/
│   └── application.yml
├── pom.xml
└── README.md
```

## 核心功能

### 1. 智能助手管理
- 创建、查询、更新、删除智能助手
- 支持助手的基本信息管理

### 2. 会话管理
- 创建和管理用户会话
- 支持按助手ID查询会话
- 会话消息管理

### 3. LLM对话功能
- 集成通义千问API
- 支持多轮对话
- 可配置模型参数

## 技术栈

- **框架**: Spring Boot 2.7.0
- **数据库**: MySQL 8.0
- **ORM**: MyBatis 3.5
- **LLM**: 通义千问API
- **构建工具**: Maven
- **Java版本**: 11

## 快速开始

### 1. 环境要求
- JDK 11+
- Maven 3.6+
- MySQL 8.0+

### 2. 数据库设置
```sql
CREATE DATABASE smart_assistant CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE assistants (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE sessions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    assistant_id INT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (assistant_id) REFERENCES assistants(id) ON DELETE CASCADE
);

CREATE TABLE messages (
    id INT AUTO_INCREMENT PRIMARY KEY,
    session_id INT NOT NULL,
    content TEXT NOT NULL,
    role ENUM('USER', 'ASSISTANT') NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (session_id) REFERENCES sessions(id) ON DELETE CASCADE
);
```

### 3. 配置
修改 `src/main/resources/application.yml`：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/smart_assistant?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf8
    username: root
    password: 123456

qwen:
  api:
    key: your-api-key
    url: https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation
```

### 4. 运行项目
```bash
mvn clean compile
mvn spring-boot:run
```

访问: http://localhost:8080

## API 接口

### 智能助手管理

#### 创建智能助手
**POST** `/api/assistant/create`

```json
{
  "name": "智能客服助手",
  "description": "一个专业的客服智能助手，能够回答用户常见问题"
}
```

#### 获取所有助手
**GET** `/api/assistant/getall`

#### 根据ID获取助手
**GET** `/api/assistant/get/{id}`

#### 更新助手信息
**PUT** `/api/assistant/update/{id}`

```json
{
  "name": "升级版智能客服助手",
  "description": "升级后的智能客服助手，具备更强大的问题解决能力"
}
```

#### 删除助手
**DELETE** `/api/assistant/delete/{id}`

### 会话管理

#### 创建会话
**POST** `/api/session/create`

```json
{
  "assistantId": 1
}
```

#### 根据ID获取会话
**GET** `/api/session/getbyid/{id}`

#### 根据助手ID获取会话
**GET** `/api/session/getbyassistantid/{assistantId}`

#### 删除会话
**DELETE** `/api/session/delete/{id}`

#### 向会话添加消息
**POST** `/api/session/addmessage/{sessionId}`

```json
{
  "content": "我想了解产品的退货政策",
  "role": "USER"
}
```

#### 获取会话的所有消息
**GET** `/api/session/getallmessages/{sessionId}`

### LLM对话接口

#### 获取大模型回复
**POST** `/api/chat`

```json
{
  "model": "qwen-turbo",
  "messages": [
    {
      "role": "user",
      "content": "你好，请介绍一下你自己"
    }
  ],
  "user_id": "test_user",
  "session_id": "test_session",
  "lang": "zh-CN",
  "stream": false,
  "temperature": 0.7,
  "max_tokens": 1000
}
```

## 数据模型

### Assistant
- `id`: 唯一标识符
- `name`: 助手名称
- `description`: 助手描述
- `createdAt`: 创建时间
- `updatedAt`: 更新时间

### Session
- `id`: 唯一标识符
- `assistantId`: 关联的助手ID
- `messages`: 消息列表
- `createdAt`: 创建时间
- `updatedAt`: 更新时间

### Message
- `id`: 唯一标识符
- `sessionId`: 所属会话ID
- `content`: 消息内容
- `role`: 消息角色 (USER, ASSISTANT)
- `createdAt`: 创建时间
- `updatedAt`: 更新时间

### ChatRequest
- `model`: 模型名称
- `messages`: 消息列表
- `userId`: 用户ID
- `sessionId`: 会话ID
- `lang`: 语言
- `stream`: 是否流式输出
- `temperature`: 温度参数
- `maxTokens`: 最大token数
- `metadata`: 元数据

## 开发说明

### 添加新功能
1. 在 `model` 包中创建数据模型
2. 在 `mapper` 包中创建 MyBatis Mapper 接口
3. 在 `service` 包中创建业务逻辑接口和实现
4. 在 `controller` 包中创建 REST 接口

