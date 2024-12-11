package leggo.feed.backend.domain.member.request;

import lombok.Builder;

@Builder
public record MemberServiceCreateRequest(
        String email,
        String password,
        String nickname
) {
}
