package leggo.feed.backend.domain.member.oauth2;

import lombok.Builder;

/**
 * DTO for representing a member with role, name, and username.
 */
@Builder
public record OAuth2MemberCreateRequest(
        String name,
        String username,
        String role
) {
}
