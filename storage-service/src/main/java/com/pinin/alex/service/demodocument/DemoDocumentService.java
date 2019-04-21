package com.pinin.alex.service.demodocument;

import com.pinin.alex.dto.demodocument.DemoDocumentAddDto;
import com.pinin.alex.dto.demodocument.DemoDocumentGetDto;
import com.pinin.alex.dto.demodocument.DemoDocumentUpdateDto;
import com.pinin.alex.service.common.CrudService;

public interface DemoDocumentService extends CrudService
        <DemoDocumentGetDto, DemoDocumentAddDto, DemoDocumentUpdateDto> {
}
