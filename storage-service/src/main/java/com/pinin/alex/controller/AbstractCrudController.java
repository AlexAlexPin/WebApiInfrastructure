package com.pinin.alex.controller;

import com.pinin.alex.dto.common.RequestDto;
import com.pinin.alex.dto.common.ResultDto;
import com.pinin.alex.service.common.CrudService;
import com.pinin.alex.validation.IdConstraint;
import org.springframework.security.core.Authentication;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

abstract class AbstractCrudController<TGet, TAdd, TUpdate> {

    private final CrudService<TGet, TAdd, TUpdate> crudService;

    AbstractCrudController(CrudService<TGet, TAdd, TUpdate> crudService) {
        this.crudService = crudService;
    }

    public ResultDto<TGet> add(
            @Valid @NotEmpty RequestDto<TAdd> addDto,
            Authentication auth) {
        return crudService.add(addDto, auth.getName());
    }

    public ResultDto<List<TGet>> findAll(Authentication auth) {
        return crudService.findAll(auth.getName());
    }

    public ResultDto<TGet> findById(
            @Valid @IdConstraint String id,
            Authentication auth) {
        return crudService.findById(id, auth.getName());
    }

    public ResultDto<TGet> update(
            @Valid @IdConstraint String id,
            @Valid @NotEmpty RequestDto<TUpdate> update,
            Authentication auth) {
        return crudService.update(id, update, auth.getName());
    }

    public ResultDto<String> delete(
            @Valid @IdConstraint String id,
            Authentication auth) {
        return crudService.delete(id, auth.getName());
    }
}
