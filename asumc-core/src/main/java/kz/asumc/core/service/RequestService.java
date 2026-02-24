package kz.asumc.core.service;

import kz.asumc.dto.RequestDto;
import kz.asumc.storage.entity.RequestEntity;
import kz.asumc.storage.repository.RequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RequestService {

    private final RequestRepository repository;

    public RequestService(RequestRepository repository) {
        this.repository = repository;
    }

    public String hello() {
        return "Hello from core";
    }

    @Transactional
    public RequestDto createRequest(RequestDto dto) {
        RequestEntity entity = new RequestEntity();
        entity.setRequestNumber(dto.getRequestNumber());
        entity.setClientName(dto.getClientName());
        entity.setDescription(dto.getDescription());
        entity.setStatus(RequestEntity.RequestStatus.NEW);
        entity.setClientId(1L);

        RequestEntity saved = repository.save(entity);

        return new RequestDto(
            saved.getRequestNumber(),
            saved.getClientName(),
            saved.getDescription()
        );
    }
}
