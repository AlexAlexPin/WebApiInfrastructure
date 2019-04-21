package com.pinin.alex.controller;

import com.pinin.alex.dto.common.RequestDto;
import com.pinin.alex.dto.common.ResultDto;
import com.pinin.alex.dto.demodocument.DemoDocumentAddDto;
import com.pinin.alex.dto.demodocument.DemoDocumentGetDto;
import com.pinin.alex.dto.demodocument.DemoDocumentUpdateDto;
import com.pinin.alex.service.demodocument.DemoDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoDocumentController extends AbstractCrudController
        <DemoDocumentGetDto, DemoDocumentAddDto, DemoDocumentUpdateDto> {

    @Autowired
    public DemoDocumentController(DemoDocumentService demoDocumentService) {
        super(demoDocumentService);
    }

    @PostMapping("")
    @Override
    public ResultDto<DemoDocumentGetDto> add(
            @RequestBody RequestDto<DemoDocumentAddDto> addDto,
            Authentication auth) {
        return super.add(addDto, auth);
    }

    @GetMapping("")
    @Override
    public ResultDto<List<DemoDocumentGetDto>> findAll(Authentication auth) {
        return super.findAll(auth);
    }

    @GetMapping("{id}")
    @Override
    public ResultDto<DemoDocumentGetDto> findById(
            @PathVariable String id,
            Authentication auth) {
        return super.findById(id, auth);
    }

    @PutMapping("{id}")
    @Override
    public ResultDto<DemoDocumentGetDto> update(
            @PathVariable String id,
            @RequestBody RequestDto<DemoDocumentUpdateDto> update,
            Authentication auth) {
        return super.update(id, update, auth);
    }

    @DeleteMapping("{id}")
    @Override
    public ResultDto<String> delete(
            @PathVariable String id,
            Authentication auth) {
        return super.delete(id, auth);
    }
}
