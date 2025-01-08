package leggo.feed.backend.domain.member.response;

import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class KakaoResponse implements OAuth2Response {

    private final String nickname;
    private final String email;
    private final Map<String, Object> attributes;

    public KakaoResponse(Map<String, Object> attributes) {
        this.attributes = attributes;

        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");

        this.nickname = Optional.ofNullable((Map<String, Object>) kakaoAccount.get("profile"))
                .map(profile -> (String) profile.get("nickname"))
                .orElse(null);
        this.email = Optional.ofNullable((String) kakaoAccount.get("email"))
                .orElse(null);
    }
    // TODO TEST CODE 작성 후, null에 대한 exception 발생 추가

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderId() {
        Long id = (Long) attributes.get("id");
        return id != null ? String.valueOf(id) : null;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getName() {
        return nickname;
    }

}
