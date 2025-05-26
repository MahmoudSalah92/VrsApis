package sa.gov.alriyadh.amana.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class ResourceServerEndpointConfig extends ResourceServerConfigurerAdapter {

    @Value("${token.server.url}")
    private String authURL;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/vrs/api/services/*").hasAuthority("ADMIN_ROLE");
        //http.authorizeRequests().antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll().anyRequest().authenticated();
    }

    @Bean
    public ResourceServerTokenServices tokenService() {
        RemoteTokenServices tokenServices = new RemoteTokenServices();
        tokenServices.setClientId("INTERNAL_API_CLIENT");
        tokenServices.setClientSecret("$2a$08$fL7u5xcvsZl78su29x1ti");
        tokenServices.setCheckTokenEndpointUrl(authURL);
        return tokenServices;
    }
}
