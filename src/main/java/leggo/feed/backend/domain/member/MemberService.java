package leggo.feed.backend.domain.member;

import leggo.feed.backend.domain.member.request.MemberServiceCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member createUser(MemberServiceCreateRequest request) {
        String encodePassword = passwordEncoder.encode(request.password()); // 해싱하는 부분
        Member user = Member.of(request, encodePassword);
        return memberRepository.save(user);
    }
}
