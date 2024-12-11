package leggo.feed.backend.domain.member.request;

import leggo.feed.backend.domain.member.Member;
import lombok.Builder;

/**
 * DTO for creating a new {@link Member}
 */
@Builder
public record MemberCreateRequest(
        String email,
        String password,
        String nickname
        // String image
) {

    public MemberServiceCreateRequest toServiceRequest() {
        return MemberServiceCreateRequest.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .build();
    }
}