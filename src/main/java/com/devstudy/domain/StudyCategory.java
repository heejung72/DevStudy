package com.devstudy.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "study_categories")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudyCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private String icon;

    @Column(name = "color_hex", length = 10)
    private String colorHex;

    @Column(name = "order_num")
    private int orderNum;

    @Column(name = "built_in")
    private boolean builtIn;

    @OneToMany(mappedBy = "studyCategory", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Lesson> lessons = new ArrayList<>();

    public int getLessonCount() {
        return lessons.size();
    }
}
