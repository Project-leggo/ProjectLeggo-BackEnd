package leggo.feed.backend.domain.member.oauth2;

import lombok.Builder;

/**
 * DTO for representing a user with role, name, and username.
 */
@Builder
public record UserDTO(
        String role,
        String name,
        String username
) {
}
