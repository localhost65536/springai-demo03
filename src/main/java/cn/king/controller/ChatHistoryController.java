package cn.king.controller;

import cn.king.entity.vo.MsgVO;
import cn.king.repository.ChatHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: wjl@king.cn
 * @time: 2026/3/19
 * @version: 1.0.0
 * @description: 会话历史
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/ai/history")
public class ChatHistoryController {

    private final ChatHistoryRepository chatHistoryRepository;

    private final ChatMemory chatMemory;

    // 查询会话 id 列表
    @RequestMapping("/{type}")
    public List<String> listChatId(@PathVariable String type) {
        return chatHistoryRepository.listChatId(type);
    }

    // 查询会话详情
    @GetMapping("/{type}/{chatId}")
    public List<MsgVO> getChatHistory(@PathVariable("type") String type, @PathVariable("chatId") String chatId) {
        List<Message> messages = chatMemory.get(chatId);
        return messages.stream().map(MsgVO::new).toList();
    }

}
