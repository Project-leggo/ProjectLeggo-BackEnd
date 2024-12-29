package leggo.feed.backend.domain.member.oauth2;

import leggo.feed.backend.domain.member.Member;
import leggo.feed.backend.domain.member.MemberRepository;
import leggo.feed.backend.domain.member.response.GoogleResponse;
import leggo.feed.backend.domain.member.response.OAuth2Response;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;

        if (registrationId.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        } else {
            return null;
        }

        String provider = oAuth2Response.getProvider();
        String providerId = oAuth2Response.getProviderId();
        String username = provider + "_" + providerId;

        Member existData = memberRepository.findByUsername(username);

        if (existData == null) {
            Member newOAuth2Member = Member.builder()
                    .username(username)
                    .email(oAuth2Response.getEmail())
                    .name(oAuth2Response.getName())
                    .role("ROLE_USER")
                    .build();
            memberRepository.save(newOAuth2Member);

            OAuth2MemberCreateRequest request = OAuth2MemberCreateRequest.builder()
                    .name(oAuth2Response.getName())
                    .username(username)
                    .role("ROLE_USER")
                    .build();

            return new CustomOAuth2User(request);
        } else {
            OAuth2MemberCreateRequest request = OAuth2MemberCreateRequest.builder()
                    .name(existData.getName())
                    .username(existData.getUsername())
                    .role(existData.getRole())
                    .build();

            return new CustomOAuth2User(request);
        }
    }
}
