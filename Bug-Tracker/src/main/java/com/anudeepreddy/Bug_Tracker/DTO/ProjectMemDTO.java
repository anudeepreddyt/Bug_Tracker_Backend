package com.anudeepreddy.Bug_Tracker.DTO;

import com.anudeepreddy.Bug_Tracker.Common.ProjectRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMemDTO {

    private Integer projectid;
    private Integer userid;

}
