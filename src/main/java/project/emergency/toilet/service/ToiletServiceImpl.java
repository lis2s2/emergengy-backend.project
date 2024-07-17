package project.emergency.toilet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.emergency.member.entitiy.Member;
import project.emergency.member.repository.MemberRepository;
import project.emergency.toilet.dto.ToiletDTO;
import project.emergency.toilet.entity.Toilet;
import project.emergency.toilet.repository.ToiletRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ToiletServiceImpl implements ToiletService {

    @Autowired
    ToiletRepository ToiletRepository;

    @Autowired
    MemberRepository memberRepository;

    @Override
    public String register(ToiletDTO dto) {
        Toilet entity = dtoToEntity(dto);
        Optional<Member> optionalMember = memberRepository.findById(dto.getWriter());
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            member.setMemPoint(member.getMemPoint()+1000);
            if ("FAMILY".equals(member.getMemGrade()) && member.getMemPoint() >= 3000) {
                member.setMemGrade("VIP");
                ToiletRepository.save(entity);
                return "VIP";
            }
        }
        ToiletRepository.save(entity);
        return null;
    }

    @Override
    public String registerInfo(ToiletDTO dto) {
        Toilet entity = dtoToEntity(dto);
        Optional<Member> optionalMember = memberRepository.findById(dto.getWriter());
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            member.setMemPoint(member.getMemPoint()+500);
            if ("FAMILY".equals(member.getMemGrade()) && member.getMemPoint() >= 3000) {
                member.setMemGrade("VIP");
                ToiletRepository.save(entity);
                return "VIP";
            }
        }
        ToiletRepository.save(entity);
        return null;

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
        if (entityList.isEmpty()) {
            return Collections.emptyList();
        }
        return entityList.stream().map(this::entityToDto).toList();
    }
}
