package cn.king.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.ai.chat.messages.Message;

/**
 * @author: wjl@king.cn
 * @time: 2026/3/19
 * @version: 1.0.0
 * @description: 封装会话详情
 */
@NoArgsConstructor
@Data
public class MsgVO {

    private String role;
    private String content;

    public MsgVO(Message message) {
        switch (message.getMessageType()) {
            case USER:
                this.role = "user";
                break;
            case ASSISTANT:
                this.role = "assistant";
                break;
            default:
                this.role = "";
                break;
        }
        this.content = message.getText();
    }

}
