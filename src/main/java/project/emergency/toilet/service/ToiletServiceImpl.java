package project.emergency.toilet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.emergency.member.entitiy.Member;
import project.emergency.member.repository.MemberRepository;
import project.emergency.toilet.dto.ToiletDTO;
import project.emergency.toilet.entity.Toilet;
import project.emergency.toilet.repository.ToiletRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ToiletServiceImpl implements ToiletService {

    @Autowired
    ToiletRepository ToiletRepository;

    @Autowired
    MemberRepository memberRepository;

    @Override
    public void register(ToiletDTO dto) {
        Toilet entity = dtoToEntity(dto);
        Optional<Member> optionalMember = memberRepository.findById(dto.getWriter());
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            member.setMemPoint(member.getMemPoint()+1000);
        }
        ToiletRepository.save(entity);
    }

    @Override
    public void registerInfo(ToiletDTO dto) {
        Toilet entity = dtoToEntity(dto);
        Optional<Member> optionalMember = memberRepository.findById(dto.getWriter());
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            member.setMemPoint(member.getMemPoint()+500);
        }
        ToiletRepository.save(entity);
    }

    @Override
    public ToiletDTO getByToiletNo(String toiletNo) {
        Toilet entity = ToiletRepository.findByToiletNo(toiletNo);
        return entityToDto(entity);
    }

    @Override
    public List<ToiletDTO> getListByMemRegister() {
        Boolean memRegister = true;
        List<Toilet> entityList = ToiletRepository.findByMemRegister(memRegister);
        return entityList.stream().map(this::entityToDto).toList();
    }

    @Override
    public List<ToiletDTO> getListByDisabled() {
        Boolean disabled = true;
        List<Toilet> entityList = ToiletRepository.findByDisabled(disabled);
        return entityList.stream().map(this::entityToDto).toList();
    }

    @Override
    public List<ToiletDTO> getListByDiaper() {
        Boolean diaper = true;
        List<Toilet> entityList = ToiletRepository.findByDiaper(diaper);
        return entityList.stream().map(this::entityToDto).toList();
    }

    @Override
    public List<ToiletDTO> getListBySeparated() {
        Boolean separated = true;
        List<Toilet> entityList = ToiletRepository.findBySeparated(separated);
        return entityList.stream().map(this::entityToDto).toList();
    }

    @Override
    public List<ToiletDTO> getListByPaper() {
        Boolean paper = true;
        List<Toilet> entityList = ToiletRepository.findByPaper(paper);
        return entityList.stream().map(this::entityToDto).toList();
    }

    @Override
    public List<ToiletDTO> getListAll() {
        List<Toilet> entityList = ToiletRepository.findAll();
        return entityList.stream().map(this::entityToDto).toList();
    }
}
