package com.example.student;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentMethods {

    public static Map<String, Double> getAverageGrade(List<Student> students) {
        return students.parallelStream()
                .flatMap(s -> s.getGrades().entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.averagingDouble(Map.Entry::getValue)));
    }
}
