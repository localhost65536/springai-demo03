package cn.king.repository;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: wjl@king.cn
 * @time: 2026/3/19
 * @version: 1.0.0
 * @description:
 */
@Component
public class ChatHistoryRepositoryImpl implements ChatHistoryRepository {

    // 保存到内存中 生产环境存库
    // k 业务类型, v 会话 id 列表
    private final Map<String, List<String>> chatHistory = new HashMap<>();

    // 保存会话 id
    @Override
    public void save(String type, String chatId) {
        // if (!chatHistory.containsKey(type)) {
        //     chatHistory.put(type, new ArrayList<>());
        // }
        // List<String> chatIdList = chatHistory.get(type);

        List<String> chatIdList = chatHistory.computeIfAbsent(type, k -> new ArrayList<>());
        if (!chatIdList.contains(chatId)) {
            chatIdList.add(chatId);
        }
    }

    // 查询会话 id 列表
    @Override
    public List<String> listChatId(String type) {
        // List<String> chatIdList = chatHistory.get(type);
        // return CollectionUtils.isEmpty(chatIdList) ? new ArrayList<>() : chatIdList;

        return chatHistory.getOrDefault(type, List.of());
    }
}
