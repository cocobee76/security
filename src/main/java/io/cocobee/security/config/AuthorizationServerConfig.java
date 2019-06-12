package io.cocobee.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

//토큰 발급 처리
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final TokenStore tokenStore;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    public AuthorizationServerConfig(TokenStore tokenStore, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.tokenStore = tokenStore;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
            .inMemory()
            .withClient("client")
            .secret(passwordEncoder.encode("pass"))
            .authorizedGrantTypes("password",
                "authorization_code",
                "refresh_token",
                "implicit")
            .scopes("read", "write", "trust")
            .accessTokenValiditySeconds(0)
            .refreshTokenValiditySeconds(0);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore)
            .authenticationManager(authenticationManager);
    }
}
