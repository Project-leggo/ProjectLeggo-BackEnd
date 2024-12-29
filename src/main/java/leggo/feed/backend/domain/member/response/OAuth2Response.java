package leggo.feed.backend.domain.member.response;

public interface OAuth2Response {

    String getProvider();
    String getProviderId();
    String getEmail();
    String getName();

}
