package com.usco.edu.auth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    // CODIFICADOR DE CONTRASEÑAS BCRYPT
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // ADMINISTRADOR DE AUTENTICACIÓN
    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;

    // CLASE PARA AÑADIR INFORMACIÓN ADICIONAL AL TOKEN JWT
    @Autowired
    private InfoAdicionalToken infoAdicionalToken;

    // CONFIGURACIÓN DE LA SEGURIDAD DEL SERVIDOR DE AUTORIZACIÓN
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()") // PERMITE EL ACCESO PÚBLICO A LA CLAVE DEL TOKEN
                .checkTokenAccess("isAuthenticated()"); // REQUIERE AUTENTICACIÓN PARA VALIDAR EL TOKEN
    }

    // CONFIGURACIÓN DE CLIENTES AUTORIZADOS Y SUS DETALLES
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("angularapp")
                .secret(passwordEncoder.encode("12345")) // CONTRASEÑA ENCRIPTADA
                .scopes("read", "write") // ÁMBITOS DE ACCESO
                .authorizedGrantTypes("password", "refresh_token") // TIPOS DE CONCESIÓN AUTORIZADOS
                .accessTokenValiditySeconds(4800) // TIEMPO DE VALIDEZ DEL TOKEN DE ACCESO EN SEGUNDOS
                .refreshTokenValiditySeconds(4800); // TIEMPO DE VALIDEZ DEL TOKEN DE ACTUALIZACIÓN EN SEGUNDOS
    }

    // CONFIGURACIÓN DE LOS ENDPOINTS DEL SERVIDOR DE AUTORIZACIÓN
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // CADENA DE MEJORADORES DE TOKEN
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAdicionalToken, accessTokenConverter()));

        endpoints.authenticationManager(authenticationManager) // CONFIGURACIÓN DEL ADMINISTRADOR DE AUTENTICACIÓN
                .tokenStore(tokenStore()) // ALMACENAMIENTO DEL TOKEN
                .accessTokenConverter(accessTokenConverter()) // CONVERTIDOR DEL TOKEN DE ACCESO
                .tokenEnhancer(tokenEnhancerChain); // CADENA DE MEJORADORES DE TOKEN
    }

    // BEAN PARA EL ALMACENAMIENTO DE TOKENS JWT
    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    // BEAN PARA EL CONVERTIDOR DE TOKENS JWT
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(JwtConfig.RSA_PRIVADA); // CLAVE PRIVADA PARA FIRMAR EL TOKEN
        jwtAccessTokenConverter.setVerifierKey(JwtConfig.RSA_PUBLICA); // CLAVE PÚBLICA PARA VERIFICAR LA FIRMA DEL TOKEN
        return jwtAccessTokenConverter;
    }

}
