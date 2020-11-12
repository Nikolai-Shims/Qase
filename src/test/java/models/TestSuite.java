package models;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class TestSuite {

    String title;
    String description;
    String postCondition;


//            "parent_id": null,

}
