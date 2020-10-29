package com.sweetsixteen.cv.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CVUpdateForm extends CVBasicForm {
    private String id;

}
