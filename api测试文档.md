# 智能助手API接口测试数据文档

## 概述
本文档提供了智能助手系统中所有API接口的测试JSON数据，用于接口测试和调试。

## 基础信息
- **基础URL**: `http://localhost:8080`
- **Content-Type**: `application/json`

---

## 1. 智能助手管理接口

### 1.1 创建智能助手
**接口**: `POST /api/assistant/create`

**请求数据**:
```json
{
  "name": "智能客服助手",
  "description": "一个专业的客服智能助手，能够回答用户常见问题"
}
```

**响应示例**:
```json
{
  "id": 1,
  "name": "智能客服助手",
  "description": "一个专业的客服智能助手，能够回答用户常见问题",
  "createdAt": "2024-01-15T10:30:00",
  "updatedAt": "2024-01-15T10:30:00"
}
```

### 1.2 获取智能助手详情
**接口**: `GET /api/assistant/get/{id}`

**请求参数**: `id = 1`

**响应示例**:
```json
{
  "id": 1,
  "name": "智能客服助手",
  "description": "一个专业的客服智能助手，能够回答用户常见问题",
  "createdAt": "2024-01-15T10:30:00",
  "updatedAt": "2024-01-15T10:30:00"
}
```

### 1.3 获取所有智能助手
**接口**: `GET /api/assistant/getall`

**响应示例**:
```json
[
  {
    "id": 1,
    "name": "智能客服助手",
    "description": "一个专业的客服智能助手，能够回答用户常见问题",
    "createdAt": "2024-01-15T10:30:00",
    "updatedAt": "2024-01-15T10:30:00"
  },
  {
    "id": 2,
    "name": "技术支持助手",
    "description": "专门处理技术问题的智能助手",
    "createdAt": "2024-01-15T11:00:00",
    "updatedAt": "2024-01-15T11:00:00"
  }
]
```

### 1.4 更新智能助手
**接口**: `PUT /api/assistant/update/{id}`

**请求数据**:
```json
{
  "name": "升级版智能客服助手",
  "description": "升级后的智能客服助手，具备更强大的问题解决能力"
}
```

**响应示例**:
```json
{
  "id": 1,
  "name": "升级版智能客服助手",
  "description": "升级后的智能客服助手，具备更强大的问题解决能力",
  "createdAt": "2024-01-15T10:30:00",
  "updatedAt": "2024-01-15T14:20:00"
}
```

### 1.5 删除智能助手
**接口**: `DELETE /api/assistant/delete/{id}`

**请求参数**: `id = 1`

**响应**: `200 OK` (无响应体)

---

## 2. 会话管理接口

### 2.1 创建会话
**接口**: `POST /api/session/create`

**请求数据**:
```json
{
  "assistantId": 1
}
```

**响应示例**:
```json
{
  "id": 1,
  "assistantId": 1,
  "messages": [],
  "createdAt": "2024-01-15T10:30:00",
  "updatedAt": "2024-01-15T10:30:00"
}
```

### 2.2 根据ID获取会话
**接口**: `GET /api/session/getbyid/{id}`

**请求参数**: `id = 1`

**响应示例**:
```json
{
  "id": 1,
  "assistantId": 1,
  "messages": [
    {
      "id": 1,
      "sessionId": 1,
      "content": "你好，我需要帮助",
      "role": "USER",
      "createdAt": "2024-01-15T10:35:00",
      "updatedAt": "2024-01-15T10:35:00"
    },
    {
      "id": 2,
      "sessionId": 1,
      "content": "您好！我是智能客服助手，很高兴为您服务。请问有什么可以帮助您的吗？",
      "role": "ASSISTANT",
      "createdAt": "2024-01-15T10:35:30",
      "updatedAt": "2024-01-15T10:35:30"
    }
  ],
  "createdAt": "2024-01-15T10:30:00",
  "updatedAt": "2024-01-15T10:35:30"
}
```

### 2.3 根据助手ID获取会话
**接口**: `GET /api/session/getbyassistantid/{assistantId}`

**请求参数**: `assistantId = 1`

**响应示例**:
```json
{
  "id": 1,
  "assistantId": 1,
  "messages": [
    {
      "id": 1,
      "sessionId": 1,
      "content": "你好，我需要帮助",
      "role": "USER",
      "createdAt": "2024-01-15T10:35:00",
      "updatedAt": "2024-01-15T10:35:00"
    },
    {
      "id": 2,
      "sessionId": 1,
      "content": "您好！我是智能客服助手，很高兴为您服务。请问有什么可以帮助您的吗？",
      "role": "ASSISTANT",
      "createdAt": "2024-01-15T10:35:30",
      "updatedAt": "2024-01-15T10:35:30"
    }
  ],
  "createdAt": "2024-01-15T10:30:00",
  "updatedAt": "2024-01-15T10:35:30"
}
```

### 2.4 删除会话
**接口**: `DELETE /api/session/delete/{id}`

**请求参数**: `id = 1`

**响应**: `200 OK` (无响应体)

---

## 3. 消息管理接口

### 3.1 向会话添加消息
**接口**: `POST /api/session/addmessage/{sessionId}`

**请求数据** (用户消息):
```json
{
  "content": "我想了解产品的退货政策",
  "role": "USER"
}
```

**响应示例**:
```json
{
  "id": 3,
  "sessionId": 1,
  "content": "我想了解产品的退货政策",
  "role": "USER",
  "createdAt": "2024-01-15T10:40:00",
  "updatedAt": "2024-01-15T10:40:00"
}
```

**请求数据** (助手回复):
```json
{
  "content": "根据我们的退货政策，在购买后30天内，如果产品存在质量问题，您可以申请退货。退货时需要提供购买凭证和产品原包装。",
  "role": "ASSISTANT"
}
```

**响应示例**:
```json
{
  "id": 4,
  "sessionId": 1,
  "content": "根据我们的退货政策，在购买后30天内，如果产品存在质量问题，您可以申请退货。退货时需要提供购买凭证和产品原包装。",
  "role": "ASSISTANT",
  "createdAt": "2024-01-15T10:40:30",
  "updatedAt": "2024-01-15T10:40:30"
}
```

### 3.2 获取会话的所有消息
**接口**: `GET /api/session/getallmessages/{sessionId}`

**请求参数**: `sessionId = 1`

**响应示例**:
```json
[
  {
    "id": 1,
    "sessionId": 1,
    "content": "你好，我需要帮助",
    "role": "USER",
    "createdAt": "2024-01-15T10:35:00",
    "updatedAt": "2024-01-15T10:35:00"
  },
  {
    "id": 2,
    "sessionId": 1,
    "content": "您好！我是智能客服助手，很高兴为您服务。请问有什么可以帮助您的吗？",
    "role": "ASSISTANT",
    "createdAt": "2024-01-15T10:35:30",
    "updatedAt": "2024-01-15T10:35:30"
  },
  {
    "id": 3,
    "sessionId": 1,
    "content": "我想了解产品的退货政策",
    "role": "USER",
    "createdAt": "2024-01-15T10:40:00",
    "updatedAt": "2024-01-15T10:40:00"
  },
  {
    "id": 4,
    "sessionId": 1,
    "content": "根据我们的退货政策，在购买后30天内，如果产品存在质量问题，您可以申请退货。退货时需要提供购买凭证和产品原包装。",
    "role": "ASSISTANT",
    "createdAt": "2024-01-15T10:40:30",
    "updatedAt": "2024-01-15T10:40:30"
  }
]
```
