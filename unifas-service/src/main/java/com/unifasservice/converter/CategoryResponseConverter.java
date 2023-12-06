package com.unifasservice.converter;

import com.unifasservice.dto.payload.response.CategoryResponse;
import com.unifasservice.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
@RequiredArgsConstructor
public class CategoryResponseConverter implements GeneralConverter<Category, CategoryResponse>{
    @Override
    public CategoryResponse convert(Category source) {
        CategoryResponse target = new CategoryResponse();
        BeanUtils.copyProperties(source, target);
        target.setId(source.getId());
        target.setName(source.getName());
        target.setGender(source.getGender());
        return target;
    }

    @Override
    public Category revert(CategoryResponse target) {
        Category source = new Category();
        BeanUtils.copyProperties(source,target);
        return source;
    }

    @Override
    public List<CategoryResponse> convert(List<Category> sources) {
        return sources.stream().map(this::convert).toList();
    }

    @Override
    public List<Category> revert(List<CategoryResponse> targets) {
        return null;
    }

    public Page<CategoryResponse> converter(Page<Category> sources) {
        List<CategoryResponse> convertedList = sources.getContent().stream()
                .map(this::convert)
                .collect(Collectors.toList());

        return new PageImpl<>(convertedList, sources.getPageable(), sources.getTotalElements());
    }
}
