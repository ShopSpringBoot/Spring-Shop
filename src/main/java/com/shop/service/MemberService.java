package com.shop.service;

import com.shop.entity.Member;
import com.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
// import javax.transaction.Transactional; 자동완성 되었는데 뭐 사용할 지 고민 중

@Service
// 로직 처리 중 에러 발생 시 로직을 수행하기 이전 상태로 콜백
@Transactional
// final이나 @NonNull이 붙은 필드에 생성자 생성
@RequiredArgsConstructor
public class MemberService {
    // 빈에 생성자가 1개이고, 파라미터 타입이 빈으로 등록 가능 시 @Autowired 없이 의존성 주입 가능
    private final MemberRepository memberRepository;

    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    // 이미 가입된 회원일 경우 예외 발생
    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}