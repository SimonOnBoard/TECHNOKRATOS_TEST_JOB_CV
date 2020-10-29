package com.sweetsixteen.cv.models.embedded;


import lombok.*;

@Data
@RequiredArgsConstructor
public class Work {
    private String name;
    private int years;
    private int months;
    private int position;
}
