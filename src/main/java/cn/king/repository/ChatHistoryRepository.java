package cn.king.repository;

import java.util.List;

/**
 * @author: wjl@king.cn
 * @time: 2026/3/19
 * @version: 1.0.0
 * @description:
 */
public interface ChatHistoryRepository {

    /**
     * @param: type 业务类型,例如 chat\service\pdf 对话机器人\智能客服\知识库
     * @param: chatId
     * @return: void
     * @description: 保存会话记录
     */
    void save(String type, String chatId);

    /**
     * @param: type 业务类型
     * @return: java.util.List<java.lang.String>
     * @description: 获取会话 id 列表
     */
    List<String> listChatId(String type);

}

