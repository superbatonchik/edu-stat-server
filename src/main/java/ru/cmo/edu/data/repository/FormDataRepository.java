package ru.cmo.edu.data.repository;

import org.springframework.data.repository.NoRepositoryBean;
import ru.cmo.edu.data.entity.BaseFormData;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface FormDataRepository<T extends BaseFormData, ID extends Serializable> extends BaseRepository<T, ID> {
    List<T> findAll(int orgId, int formTypeId, boolean isArchived);
    List<T> findExact(int orgId, int formId, int year);
}
