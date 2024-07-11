package project.emergency.kakao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.emergency.kakao.repository.KakaoUserRepository;
import project.emergency.member.dto.MemberDTO;
import project.emergency.member.entitiy.Member;
import project.emergency.naver.repository.NaverUserRepository;
import project.emergency.naver.service.NaverUserService;

@Service
public class KakaoUserServiceImp implements KakaoUserService {

    @Autowired
    private KakaoUserRepository repository;

    public void KakaoUserService(KakaoUserRepository kakaoUserRepository) {
        this.repository = kakaoUserRepository;
    }

    @Override
    public boolean register(MemberDTO dto) {
        return false;
    }

    @Transactional
    public Member saveKakaoUser(Member member) {
        return repository.save(member);
    }
}
