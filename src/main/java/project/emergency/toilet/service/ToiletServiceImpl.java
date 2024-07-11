package project.emergency.toilet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.emergency.toilet.dto.ToiletDTO;
import project.emergency.toilet.entity.Toilet;
import project.emergency.toilet.repository.ToiletRepository;

import java.util.List;

@Service
public class ToiletServiceImpl implements ToiletService {

    @Autowired
    ToiletRepository repository;

    @Override
    public void register(ToiletDTO dto) {
        Toilet entity = dtoToEntity(dto);
        repository.save(entity);
    }

    @Override
    public ToiletDTO getByToiletNo(String toiletNo) {
        Toilet entity = repository.findByToiletNo(toiletNo);
        return entityToDto(entity);
    }

    @Override
    public List<ToiletDTO> getListByMemRegister() {
        Boolean memRegister = true;
        List<Toilet> entityList = repository.findByMemRegister(memRegister);
        return entityList.stream().map(this::entityToDto).toList();
    }

    @Override
    public List<ToiletDTO> getListByDisabled() {
        Boolean disabled = true;
        List<Toilet> entityList = repository.findByDisabled(disabled);
        return entityList.stream().map(this::entityToDto).toList();
    }

    @Override
    public List<ToiletDTO> getListByDiaper() {
        Boolean diaper = true;
        List<Toilet> entityList = repository.findByDiaper(diaper);
        return entityList.stream().map(this::entityToDto).toList();
    }

    @Override
    public List<ToiletDTO> getListBySeparated() {
        Boolean separated = true;
        List<Toilet> entityList = repository.findBySeparated(separated);
        return entityList.stream().map(this::entityToDto).toList();
    }
}
