package com.devstudy.service;

import com.devstudy.domain.StudyCategory;
import com.devstudy.dto.StudyCategoryForm;
import com.devstudy.repository.StudyCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudyCategoryService {

    private final StudyCategoryRepository studyCategoryRepository;

    @Transactional(readOnly = true)
    public List<StudyCategory> getAllCategories() {
        return studyCategoryRepository.findAllByOrderByOrderNumAsc();
    }

    @Transactional(readOnly = true)
    public StudyCategory getById(Long id) {
        return studyCategoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("스터디 카테고리를 찾을 수 없습니다."));
    }

    @Transactional
    public StudyCategory create(StudyCategoryForm form) {
        int nextOrder = (int) studyCategoryRepository.count() + 1;
        return studyCategoryRepository.save(StudyCategory.builder()
                .name(form.getName())
                .description(form.getDescription())
                .icon(form.getIcon())
                .colorHex(form.getColorHex())
                .orderNum(nextOrder)
                .builtIn(false)
                .build());
    }
}
