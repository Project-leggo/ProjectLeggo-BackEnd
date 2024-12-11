package leggo.feed.backend.domain.member;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import leggo.feed.backend.domain.member.constant.MemberService;
import leggo.feed.backend.domain.member.request.MemberCreateRequest;
import leggo.feed.backend.domain.member.response.MemberInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "인증 관련 API")
public class MemberController {

    private final MemberService userService;

    @Operation(summary = "회원가입", description = "회원 가입을 진행합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "회원 가입 성공",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = MemberInfoResponse.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "타입 오류",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            example = "{\"code\":\"VALIDATION_ERROR\", \"parameter\":\"email\", \"message\":\"이메일 양식을 지켜주세요.\"}"
                                    )
                            )
                    ),
            }
    )

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signup(
            @RequestBody MemberCreateRequest userCreateRequest) {
        userService.createUser(userCreateRequest.toServiceRequest());
        return new ResponseEntity<>(Map.of("message", "사용자 생성 성공"), HttpStatus.CREATED);
    }
}
