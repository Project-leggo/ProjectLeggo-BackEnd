package leggo.feed.backend.domain.member;

import jakarta.persistence.*;
import leggo.feed.backend.domain.member.oauth2.OAuth2MemberCreateRequest;
import leggo.feed.backend.domain.member.request.MemberServiceCreateRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access =AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    private String role;

    @Column(nullable = false, length = 100)
    private String name;

    private String nickname;

    private String tel;

    private String birth;

    private String description;

    private String username;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    private LocalDateTime deleteAt;

    @Builder
    private Member(String email, String password, String role, String name, String nickname,
                   String tel, String birth, String description, String username,
                   LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deleteAt) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.name = name;
        this.nickname = nickname;
        this.tel = tel;
        this.birth = birth;
        this.description = description;
        this.username = username;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleteAt = deleteAt;
    }

    public static Member of(MemberServiceCreateRequest request, String password) {
        return Member.builder()
                .email(request.email())
                .password(password)
                .nickname(request.nickname())
                .build();
    }

}
