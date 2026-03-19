package cn.king.entity.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/* 课程 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Course {

    private Integer id;
    private String name;

}
