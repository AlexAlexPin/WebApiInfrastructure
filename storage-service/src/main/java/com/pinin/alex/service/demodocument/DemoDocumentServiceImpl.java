package com.pinin.alex.service.demodocument;

import com.pinin.alex.document.DemoDocument;
import com.pinin.alex.dto.common.RequestDto;
import com.pinin.alex.dto.demodocument.DemoDocumentAddDto;
import com.pinin.alex.dto.demodocument.DemoDocumentGetDto;
import com.pinin.alex.dto.demodocument.DemoDocumentUpdateDto;
import com.pinin.alex.repository.AccountRepository;
import com.pinin.alex.repository.DemoDocumentRepository;
import com.pinin.alex.service.common.AbstractCrudServiceImpl;
import com.pinin.alex.service.common.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;

@Service
class DemoDocumentServiceImpl extends AbstractCrudServiceImpl<DemoDocumentGetDto, DemoDocumentAddDto, DemoDocumentUpdateDto, DemoDocument>
        implements DemoDocumentService {

    @Autowired
    public DemoDocumentServiceImpl(
            DemoDocumentRepository repository,
            AccountRepository accountRepository,
            MappingService mappingService) {
        super(repository, accountRepository, mappingService);
    }

    @Override
    protected Class<DemoDocumentGetDto> getGetClass() {
        return DemoDocumentGetDto.class;
    }

    @Override
    protected Class<DemoDocument> getDocumentClass() {
        return DemoDocument.class;
    }

    @Override
    protected boolean update(DemoDocument document, RequestDto<DemoDocumentUpdateDto> update) {
        @NotBlank String newValue = update.getData().getValue();
        if (document.getValue().equals(newValue)) {
            return false;
        }
        document.setValue(newValue);
        return true;
    }
}
