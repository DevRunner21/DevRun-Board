package com.devrun.backend.tag.service;

import com.devrun.backend.common.exception.ErrorInfo;
import com.devrun.backend.common.exception.BusinessException;
import com.devrun.backend.tag.dto.request.CreateTagRequest;
import com.devrun.backend.tag.domain.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public void createNewTag(CreateTagRequest request) {
        if(isDuplicatedTagName(request.getName())){
            throw new BusinessException(ErrorInfo.DUPLICATE_TAG_NAME);
        }
        tagRepository.save(request.convertToTag());
    }

    private boolean isDuplicatedTagName(String name) {
        return tagRepository.findTagByName(name).isPresent();
    }

}
