package cn.king.tool;

import cn.king.entity.po.Course;
import cn.king.entity.po.School;
import cn.king.entity.query.CourseQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/* ai 要调用的所有工具 */
@Slf4j
@Component
public class CourseTool {

    @Tool(description = "根据条件查询课程")
    public List<Course> queryCourse(@ToolParam(description = "查询条件", required = false) CourseQuery courseQuery) {
        List<Course> list = new ArrayList<>();
        log.info("执行函数 queryCourse, courseQuery: {}", courseQuery);
        list.add(new Course(1, "java"));
        list.add(new Course(2, "python"));
        list.add(new Course(3, "javascript"));
        return list;
    }

    @Tool(description = "查询所有学校")
    public List<School> querySchool() {
        log.info("执行函数 querySchool");
        List<School> list = new ArrayList<>();
        list.add(new School(1, "北京校区"));
        list.add(new School(2, "上海校区"));
        list.add(new School(3, "深圳校区"));
        return list;
    }

    @Tool(description = "生成预约单, 返回预约单号")
    public Integer createCourseReservation(
            @ToolParam(description = "预约课程") String course,
            @ToolParam(description = "预约校区") String school,
            @ToolParam(description = "学生姓名") String studentName,
            @ToolParam(description = "联系电话") String contactInfo,
            @ToolParam(description = "备注", required = false) String remark) {
        log.info("执行函数 createCourseReservation, course: {}, school: {}, studentName: {}, contactInfo: {}",
                course, school, studentName, contactInfo);
        return ThreadLocalRandom.current().nextInt(1, 1001); // [1, 1000] 随机整数
    }

}
