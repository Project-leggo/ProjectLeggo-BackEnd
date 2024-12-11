package leggo.feed.backend.domain.member.response;

import leggo.feed.backend.domain.member.Member;

import java.time.LocalDateTime;

public record MemberInfoResponse(
        Long id,
        String email,
        String nickname,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static MemberInfoResponse of(Member member) {
        return new MemberInfoResponse(member.getId(), member.getEmail(), member.getNickname(),
                member.getCreatedAt(), member.getUpdatedAt());
    }
}

