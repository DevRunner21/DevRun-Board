package com.devrun.backend.oauth2;

import com.devrun.backend.domain.member.Member;
import com.devrun.backend.jwt.Jwt;
import com.devrun.backend.member.service.MemberService;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class OAuth2AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final Jwt jwt;

    private final MemberService memberService;

    public OAuth2AuthenticationSuccessHandler(Jwt jwt, MemberService memberService) {
        this.jwt = jwt;
        this.memberService = memberService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        /**
         * JWT
         */
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauth2Token = (OAuth2AuthenticationToken) authentication;
            OAuth2User principal = oauth2Token.getPrincipal();
            String registrationId = oauth2Token.getAuthorizedClientRegistrationId();

            Member member = processUserOAuth2UserJoin(principal, registrationId);
            String loginSuccessJson = generateLoginSuccessJson(member);
            response.setContentType("application/json;charset=UTF-8");
            response.setContentLength(loginSuccessJson.getBytes(StandardCharsets.UTF_8).length);
            response.getWriter().write(loginSuccessJson);
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }

    private Member processUserOAuth2UserJoin(OAuth2User oAuth2User, String registrationId) {
        return memberService.join(oAuth2User, registrationId);
    }

    private String generateLoginSuccessJson(Member member) {
        String token = generateToken(member);
        log.debug("Jwt({}) created for oauth2 login user {}", token, member.getUsername());
        return "{\"token\":\"" + token + "\", \"username\":\"" + member.getUsername() + "\", \"permission\":\"" + member.getPermission().getName() + "\"}";
    }

    private String generateToken(Member member) {
        return jwt.sign(Jwt.Claims.from(member.getUsername(), new String[]{"ROLE_USER"}));
    }

}
