package com.sweetsixteen.cv.models.embedded;

import com.sweetsixteen.cv.enums.Level;
import lombok.*;


@Data
@RequiredArgsConstructor
public class Language {
    private String title;
    private Level level;
}
