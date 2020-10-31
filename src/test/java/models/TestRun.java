package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TestRun {

    String title;
    String description;
    String defaultAssignee;
}
