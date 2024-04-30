package com.cremedia.cremedia.models;

import lombok.*;
import lombok.extern.slf4j.Slf4j;


//@RequiredArgsConstructor
//@Getter
//@Setter
//@ToString
//@EqualsAndHashCode
//@NoArgsConstructor(force = true)
//@AllArgsConstructor
@Data
@Slf4j
public class Teacher {


    @ToString.Exclude
    private final int id;
    private final String name;
    private final String surname;
    private final String email;

    public void getLog() {
        log.info("Teacher: " + this);
    }
}
