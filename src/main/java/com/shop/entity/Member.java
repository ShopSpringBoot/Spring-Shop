package com.shop.entity;

import com.shop.constant.Role;
import com.shop.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name="member")
@Getter @Setter
@ToString
public class Member extends BaseEntity{

    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    // 이메일로 구분할 수 있도록 unique 속성 지정
    @Column(unique = true)
    private String email;

    private String password;

    private String address;

    // enum의 순서가 바뀔 경우 문제가 발생할 수 있으므로 String으로 저장 권장
    @Enumerated(EnumType.STRING)
    private Role role;

    // Member 엔티티 생성
    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getAddress());
        // BCryptPasswordEncoder Bean을 파라미터로 넘겨 비밀번호 암호화
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        // Member 엔터티 생성 시 권한 User Role -> ADMIN Role 수정
        member.setRole(Role.ADMIN);
        return member;
    }
}
