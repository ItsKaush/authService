package dev.kaushar.authservice.authservice;

import dev.kaushar.authservice.authservice.security.repositories.ClientRepository;
import dev.kaushar.authservice.authservice.security.services.JpaRegisteredClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.util.UUID;

//@SpringBootTest
public class InsertRegisteredClient {
//    @Autowired
//    private ClientRepository clientRepository;
//    @Autowired
//    private JpaRegisteredClientRepository jpaRegisteredClientRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Test
//    public void insertNewClient(){
//        RegisteredClient postmalClient = RegisteredClient.withId(UUID.randomUUID().toString())
//                .clientId("Postman-client")
//                .clientSecret(passwordEncoder.encode("postmanPassword"))
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//                .redirectUri("https://oauth.io/vl/callback")
//                .postLogoutRedirectUri("http://127.0.0.1:8080/")
//                .scope(OidcScopes.OPENID)
//                .scope(OidcScopes.PROFILE)
//                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
//                .build();
//
//        jpaRegisteredClientRepository.save(postmalClient);
//    }
}
