package dasturlash.uz.services;

import dasturlash.uz.dto.LangResponseDTO;
import dasturlash.uz.dto.SectionDTO;
import dasturlash.uz.entities.SectionEntity;
import dasturlash.uz.enums.AppLanguageEnum;
import dasturlash.uz.exceptions.AppBadException;
import dasturlash.uz.exceptions.NotFoundException;
import dasturlash.uz.repositories.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class SectionService {

    @Autowired
    private SectionRepository repository;

    public SectionDTO create(SectionDTO dto){
        Optional<SectionEntity> optional = repository.findByOrderNumber(dto.getOrderNumber());
        if (optional.isPresent()) {
            throw new AppBadException("OrderNumber " + dto.getOrderNumber() + " already exist");
        }
        SectionEntity entity = new SectionEntity();
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        entity.setSectionKey(dto.getSectionKey());
        entity.setCreatedDate(LocalDateTime.now());
        repository.save(entity);
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public SectionDTO update(Integer id, SectionDTO newDto){
        Optional<SectionEntity> optional = repository.findById(id);
        if (optional.isEmpty() || optional.get().getVisible() == Boolean.FALSE){
            throw new NotFoundException("Category not found");
        }
        SectionEntity entity = optional.get();
        entity.setOrderNumber(newDto.getOrderNumber());
        entity.setNameUz(newDto.getNameUz());
        entity.setNameRu(newDto.getNameRu());
        entity.setNameEn(newDto.getNameEn());
        entity.setSectionKey(String.valueOf(newDto.getSectionKey()));
        newDto.setId(entity.getId());
        newDto.setCreatedDate(entity.getCreatedDate());
        repository.save(entity);
        return newDto;
    }

    public Boolean delete(Integer id) {
        var entity = repository.findByIdAndVisibleIsTrue(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));
        int i = repository.updateVisibleById(entity.getId());
        return i == 1;
    }

    public List<SectionDTO> getAllByOrder() {
        Iterable<SectionEntity> iterable = repository.getAllByOrderSorted();
        List<SectionDTO> dtos = new LinkedList<>();
        iterable.forEach(entity -> dtos.add(toDto(entity)));
        return dtos;
    }

    public List<LangResponseDTO> getAllByLang(AppLanguageEnum lang){
        Iterable<SectionEntity> iterable = repository.getAllByOrderSorted();
        List<LangResponseDTO> dtos = new LinkedList<>();
        iterable.forEach(entity -> dtos.add(toLangResponseDto(lang, entity)));
        return dtos;
    }

    private SectionDTO toDto(SectionEntity entity) {
        SectionDTO dto = new SectionDTO();
        dto.setId(entity.getId());
        dto.setOrderNumber(entity.getOrderNumber());
        dto.setNameUz(entity.getNameUz());
        dto.setNameRu(entity.getNameRu());
        dto.setNameEn(entity.getNameEn());
        dto.setSectionKey(entity.getSectionKey());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    private LangResponseDTO toLangResponseDto(AppLanguageEnum lang, SectionEntity entity){
        LangResponseDTO dto = new LangResponseDTO();
        dto.setId(entity.getId());
        dto.setKey(entity.getSectionKey());
        switch (lang){
            case UZ:
                dto.setName(entity.getNameUz());
                break;
            case RU:
                dto.setName(entity.getNameRu());
                break;
            case EN:
                dto.setName(entity.getNameEn());
                break;
        }
        return dto;
    }

}
