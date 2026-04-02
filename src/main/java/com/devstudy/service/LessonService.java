package com.devstudy.service;

import com.devstudy.domain.Lesson;
import com.devstudy.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LessonService {

    private final LessonRepository lessonRepository;

    public List<Lesson> getAllLessons() {
        return lessonRepository.findAllByOrderByOrderNumAsc();
    }

    public Optional<Lesson> getLessonById(Long id) {
        return lessonRepository.findById(id);
    }

    public List<Lesson> getLessonsByCategory(String category) {
        return lessonRepository.findByCategoryOrderByOrderNumAsc(category);
    }

    public Optional<Lesson> getNextLesson(Long currentId) {
        List<Lesson> all = getAllLessons();
        for (int i = 0; i < all.size() - 1; i++) {
            if (all.get(i).getId().equals(currentId)) {
                return Optional.of(all.get(i + 1));
            }
        }
        return Optional.empty();
    }

    public Optional<Lesson> getPrevLesson(Long currentId) {
        List<Lesson> all = getAllLessons();
        for (int i = 1; i < all.size(); i++) {
            if (all.get(i).getId().equals(currentId)) {
                return Optional.of(all.get(i - 1));
            }
        }
        return Optional.empty();
    }
}
