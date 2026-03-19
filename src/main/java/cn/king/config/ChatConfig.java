package cn.king.config;

import cn.king.constant.SysConstant;
import cn.king.tool.CourseTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatConfig {

    @Bean
    public ChatClient chatClient(OpenAiChatModel model, ChatMemory chatMemory, CourseTool courseTool) {
        OpenAiChatOptions options = OpenAiChatOptions.builder()
                .model("qwen3.5-plus")
                .temperature(0.8)
                .build();

        return ChatClient.builder(model)
                .defaultOptions(options)
                // 系统提示词
                .defaultSystem(SysConstant.SYSTEM_PROMPT)
                .defaultAdvisors(
                        // 日志
                        new SimpleLoggerAdvisor(),
                        // 记忆
                        MessageChatMemoryAdvisor.builder(chatMemory).build()
                )
                // function/tool
                .defaultTools(courseTool)
                .build();
    }

    // 记忆如何存储. 测试时使用内存存储
    @Bean
    public ChatMemoryRepository chatMemoryRepository() {
        return new InMemoryChatMemoryRepository();
    }

    // 记忆如何管理.
    @Bean
    public ChatMemory chatMemory(ChatMemoryRepository chatMemoryRepository) {
        return MessageWindowChatMemory.builder()
                .chatMemoryRepository(chatMemoryRepository)
                // 仅保留最近 20 条消息，避免上下文无限增长
                .maxMessages(20)
                .build();
    }

}
