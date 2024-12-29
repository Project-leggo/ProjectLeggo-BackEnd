package leggo.feed.backend.domain.member.oauth2;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@RequiredArgsConstructor
public class CustomOAuth2User implements OAuth2User {

    private final OAuth2MemberCreateRequest oAuth2MemberCreateRequest;

    @Override
    public Map<String, Object> getAttributes() {
        return Map.of();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {

                return oAuth2MemberCreateRequest.role();

            }
        });

        return collection;
    }

    @Override
    public String getName() {
        return oAuth2MemberCreateRequest.name();
    }

    public String getUsername() {

        return oAuth2MemberCreateRequest.username();

    }

}
