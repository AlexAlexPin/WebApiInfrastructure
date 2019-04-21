package com.pinin.alex.service.common;

import com.pinin.alex.document.Account;
import com.pinin.alex.document.AccountDocument;
import com.pinin.alex.dto.common.RequestDto;
import com.pinin.alex.dto.common.ResultDto;
import com.pinin.alex.dto.common.ResultMetadata;
import com.pinin.alex.repository.AccountRepository;
import com.pinin.alex.repository.CustomCrudRepository;
import com.pinin.alex.service.exceptions.AccountNotFoundException;
import com.pinin.alex.utils.DateUtils;

import java.time.LocalDateTime;
import java.util.List;

public abstract class AbstractCrudServiceImpl<TGet, TAdd, TUpdate, TDoc extends AccountDocument>
        implements CrudService <TGet, TAdd, TUpdate>  {

    private final CustomCrudRepository<TDoc> repository;
    private final AccountRepository accountRepository;
    private final MappingService mappingService;

    public AbstractCrudServiceImpl(
            CustomCrudRepository<TDoc> repository,
            AccountRepository accountRepository,
            MappingService mappingService) {
        this.repository = repository;
        this.accountRepository = accountRepository;
        this.mappingService = mappingService;
    }

    protected abstract Class<TGet> getGetClass();

    protected abstract Class<TDoc> getDocumentClass();

    protected abstract boolean update(TDoc document, RequestDto<TUpdate> update);

    @Override
    public ResultDto<TGet> add(RequestDto<TAdd> addDto, String login) {
        Account account = findAccount(login);
        if (account.getTimestamp() > addDto.getMetadata().getTimestamp()) {
//            throw new OutdatedTimestampException("Outdated timestamp"); TODO service to get timestamp
        }

        TDoc document = convertToDocument(addDto, account);
        document = repository.save(document);

        account.setTimestamp(DateUtils.getDateAsLong(document.getCreated()));
        accountRepository.save(account);

        return convertToGetDto(document, account.getTimestamp());
    }

    @Override
    public ResultDto<TGet> findById(String id, String login) {
        Account account = findAccount(login);
        TDoc document = repository.findByIdAndAccount(id, account).orElse(null);
        return convertToGetDto(document, account.getTimestamp());
    }

    @Override
    public ResultDto<List<TGet>> findAll(String login) {
        Account account = findAccount(login);
        List<TDoc> words = repository.findAllByAccount(account);
        List<TGet> wordsGetDto = mappingService.mapAll(words, getGetClass());
        return new ResultDto<>(new ResultMetadata(account.getTimestamp()), wordsGetDto);
    }

    @Override
    public ResultDto<TGet> update(String id, RequestDto<TUpdate> updateDto, String login) {
        Account account = findAccount(login);
        if (account.getTimestamp() > updateDto.getMetadata().getTimestamp()) {
            // throw new OutdatedTimestampException("Outdated timestamp"); TODO service to get timestamp
        }
        TDoc document = repository.findByIdAndAccount(id, account)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Document id='%s' does not exist", id)));

        boolean updated = update(document, updateDto);

        if (updated) {
            document.setUpdated(LocalDateTime.now());
            document = repository.save(document);
            account.setTimestamp(DateUtils.getDateAsLong(document.getUpdated()));
            accountRepository.save(account);
        }

        return convertToGetDto(document, account.getTimestamp());
    }

    @Override
    public ResultDto<String> delete(String id, String login) {
        Account account = findAccount(login);
        int deleted = repository.deleteByIdAndAccount(id, account);
        if (deleted > 0) {
            account.setTimestamp(DateUtils.getDateAsLong(LocalDateTime.now()));
            return new ResultDto<>(new ResultMetadata(account.getTimestamp()), id);
        }
        return new ResultDto<>(new ResultMetadata(account.getTimestamp()), null);
    }

    private TDoc convertToDocument(RequestDto<TAdd> addDto, Account account) {
        if (addDto == null) {
            return null;
        }
        TDoc document = mappingService.map(addDto.getData(), getDocumentClass());
        document.setAccount(account);
        document.setCreated(LocalDateTime.now());
        return document;
    }

    private ResultDto<TGet> convertToGetDto(TDoc document, long timestamp) {
        if (document == null) {
            return null;
        }
        TGet getDto = mappingService.map(document, getGetClass());
        return new ResultDto<>(new ResultMetadata(timestamp), getDto);
    }

    private Account findAccount(String login) {
        return accountRepository.findByLogin(login)
                .orElseThrow(() -> new AccountNotFoundException(login));
    }
}
