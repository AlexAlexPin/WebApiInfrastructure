package com.pinin.alex.service.common;

import com.pinin.alex.dto.common.RequestDto;
import com.pinin.alex.dto.common.ResultDto;

import java.util.List;

public interface CrudService<TGet, TAdd, TUpdate> {

    ResultDto<TGet> add(RequestDto<TAdd> addDto, String login);

    ResultDto<List<TGet>> findAll(String login);  // TODO add size limitations

    ResultDto<TGet> findById(String id, String login);

    ResultDto<TGet> update(String id, RequestDto<TUpdate> updateDto, String login);

    ResultDto<String> delete(String id, String login);
}
