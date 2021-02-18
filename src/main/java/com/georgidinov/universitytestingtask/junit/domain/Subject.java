package com.georgidinov.universitytestingtask.junit.domain;


import com.georgidinov.universitytestingtask.junit.baseperson.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subject implements BaseEntity {

    private Long id;

    private String subjectName;

}