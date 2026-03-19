package cn.king.entity.query;

import lombok.Data;
import org.springframework.ai.tool.annotation.ToolParam;

import java.util.List;

@Data
public class CourseQuery {

    @ToolParam(description = "课程类型：编程、设计、自媒体、其它", required = false)
    private String type;

    @ToolParam(description = "学历要求：0-无、1-初中、2-高中、3-大专、4-本科及本科以上", required = false)
    private Integer edu;

    @ToolParam(description = "排序方式", required = false)
    private List<Sort> sorts;

    @Data
    public static class Sort {
        @ToolParam(description = "排序字段：price或duration", required = false)
        private String field;

        @ToolParam(description = "是否是升序：true/false", required = false)
        private Boolean asc;
    }

}
