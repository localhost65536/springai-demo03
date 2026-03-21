package cn.king.controller;

import cn.king.repository.ChatHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author: wjl@king.cn
 * @time: 2026/3/19
 * @version: 1.0.0
 * @description:
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/ai/service")
public class CustomerServiceController {

    private final ChatClient chatClient;

    private final ChatHistoryRepository chatHistoryRepository;

    @RequestMapping(value = "/chat", produces = "text/html;charset=utf-8")
    public Flux<String> chat(String prompt, String chatId) {
        // 同一轮会话,前端传相同的会话 id
        String conversationId = StringUtils.hasText(chatId) ? chatId : ChatMemory.DEFAULT_CONVERSATION_ID;
        // 保存会话 id. 业务类型 agent 表示 "智能客服", 后续做 rag 使用不同的业务类型. 此处简化书写,生产环境搞枚举
        chatHistoryRepository.save("agent", conversationId);
        return chatClient.prompt()
                .user(prompt)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, conversationId))
                .stream()
                .content();
    }

}
