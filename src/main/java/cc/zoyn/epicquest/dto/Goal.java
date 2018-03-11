package cc.zoyn.epicquest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * 代表一个任务目标
 *
 * @author Zoyn
 * @since 2018-03-10
 */
@Data
@AllArgsConstructor
public class Goal {

    private GoalType type;
    private Map<String, String> content;

}
