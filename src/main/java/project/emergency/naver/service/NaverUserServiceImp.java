package project.emergency.naver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.emergency.member.dto.MemberDTO;
import project.emergency.member.entitiy.Member;
import project.emergency.naver.repository.NaverUserRepository;

@Service
public class NaverUserServiceImp implements NaverUserService {

    @Autowired
    private NaverUserRepository repository;

    public void NaverUserService(NaverUserRepository naverUserRepository) {
        this.repository = naverUserRepository;
    }

    @Override
    public boolean register(MemberDTO dto) {
        return false;
    }

    @Transactional
    public Member saveNaverUser(Member member) {
        return repository.save(member);
    }
}
