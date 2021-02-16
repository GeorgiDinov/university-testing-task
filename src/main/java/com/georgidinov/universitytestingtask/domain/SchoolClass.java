package com.georgidinov.universitytestingtask.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchoolClass {

    private String className;
    private Teacher teacher;
    private List<Student> students;
    private List<Subject> subjects;

}