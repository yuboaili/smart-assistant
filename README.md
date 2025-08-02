# 智能助手系统 (Smart Assistant)

这是一个基于 Spring Boot 的智能助手系统，提供了智能助手管理和会话管理的功能。系统采用 MyBatis + MySQL 进行数据持久化。

## 项目结构

```
smart-assistant/
├── src/
│   ├── main/
│   │   ├── java/org/example/
│   │   │   ├── application/
│   │   │   │   └── SmartAssistantApplication.java  # 主应用程序类
│   │   │   ├── model/           # 数据模型
│   │   │   │   ├── Assistant.java    # 智能助手模型
│   │   │   │   ├── Session.java      # 会话模型
│   │   │   │   └── Message.java      # 消息模型
│   │   │   ├── mapper/          # MyBatis Mapper 接口
│   │   │   │   ├── AssistantMapper.java
│   │   │   │   ├── SessionMapper.java
│   │   │   │   └── MessageMapper.java
│   │   │   ├── service/         # 业务逻辑层
│   │   │   │   ├── AssistantService.java
│   │   │   │   ├── SessionService.java
│   │   │   │   └── impl/
│   │   │   │       ├── AssistantServiceImpl.java
│   │   │   │       └── SessionServiceImpl.java
│   │   │   └── controller/      # 控制器层
│   │   │       ├── AssistantController.java
│   │   │       └── SessionController.java
│   │   └── resources/
│   │       └── application.yml  # 配置文件
│   └── test/                    # 测试代码
├── pom.xml                      # Maven配置文件
├── README.md                    # 项目说明文档
└── api测试文档.md               # API测试文档
```

## 核心功能

### 1. 智能助手管理 (Assistant)
- 创建、查询、更新、删除智能助手
- 支持助手的基本信息管理（名称、描述等）
- RESTful API 接口

### 2. 会话管理 (Session)
- 创建和管理用户会话
- 支持按助手ID查询会话
- 会话消息管理

### 3. 消息管理 (Message)
- 在会话中添加和管理消息
- 支持不同角色的消息（用户、助手）
- 消息时间戳和状态跟踪

## 技术栈

- **框架**: Spring Boot 2.7.0
- **数据库**: MySQL 8.0
- **ORM**: MyBatis 3.5
- **构建工具**: Maven
- **Java版本**: 11
- **JSON处理**: Jackson

## 快速开始

### 1. 环境要求
- JDK 11 或更高版本
- Maven 3.6 或更高版本
- MySQL 8.0 或更高版本

### 2. 数据库设置
```sql
-- 创建数据库
CREATE DATABASE smart_assistant CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 创建表结构
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

### 3. 配置数据库连接
修改 `src/main/resources/application.yml` 中的数据库连接信息：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/smart_assistant?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=utf8
    username: root
    password: 123456
```

### 4. 运行项目
```bash
# 克隆项目
git clone <repository-url>
cd smart-assistant

# 编译项目
mvn clean compile

# 运行项目
mvn spring-boot:run
```

### 5. 访问应用
- 应用程序: http://localhost:8080
- API 文档: 查看下面的 API 接口列表

## API 接口

### 智能助手接口
- `POST /api/assistant/create` - 创建智能助手
- `GET /api/assistant/getall` - 获取所有助手
- `GET /api/assistant/get/{id}` - 根据ID获取助手
- `PUT /api/assistant/update/{id}` - 更新助手信息
- `DELETE /api/assistant/delete/{id}` - 删除助手

### 会话接口
- `POST /api/session/create` - 创建会话
- `GET /api/session/getbyid/{id}` - 根据ID获取会话
- `GET /api/session/getbyassistantid/{assistantId}` - 根据助手ID获取会话
- `DELETE /api/session/delete/{id}` - 删除会话
- `POST /api/session/addmessage/{sessionId}` - 向会话添加消息
- `GET /api/session/getallmessages/{sessionId}` - 获取会话的所有消息

## 数据模型

### Assistant (智能助手)
- `id`: 唯一标识符
- `name`: 助手名称
- `description`: 助手描述
- `createdAt`: 创建时间
- `updatedAt`: 更新时间

### Session (会话)
- `id`: 唯一标识符
- `assistantId`: 关联的助手ID
- `messages`: 消息列表
- `createdAt`: 创建时间
- `updatedAt`: 更新时间

### Message (消息)
- `id`: 唯一标识符
- `sessionId`: 所属会话ID
- `content`: 消息内容
- `role`: 消息角色 (USER, ASSISTANT)
- `createdAt`: 创建时间
- `updatedAt`: 更新时间

## 技术特点

### MyBatis 优势
- **完全控制 SQL**: 可以写复杂的查询和优化
- **性能优化**: 支持批量操作和存储过程
- **灵活性**: 支持动态 SQL 和复杂关联查询
- **学习曲线**: 对 SQL 熟悉的开发者更容易上手

### 项目特色
- **三层架构**: Controller -> Service -> Mapper
- **RESTful API**: 标准的 REST 接口设计
- **数据持久化**: MySQL 数据库存储
- **JSON 序列化**: Jackson 处理 JSON 数据
- **时间戳管理**: 自动管理创建和更新时间
- **注解驱动**: 使用 MyBatis 注解简化配置

## 开发说明

### 添加新功能
1. 在 `model` 包中创建数据模型
2. 在 `mapper` 包中创建 MyBatis Mapper 接口
3. 在 `service` 包中创建业务逻辑接口和实现
4. 在 `controller` 包中创建 REST 接口

### 数据库配置
当前使用 MySQL 数据库。如需使用其他数据库，请修改 `application.yml` 中的数据库配置。

### 日志配置
项目配置了详细的 SQL 日志，可以在控制台查看执行的 SQL 语句：
```yaml
logging:
  level:
    org.example.mapper: DEBUG

```## 许可证
本项目采用 MIT 许可证。 