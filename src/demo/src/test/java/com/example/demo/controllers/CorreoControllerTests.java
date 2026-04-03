package com.example.demo.controllers;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;

import java.time.Instant;
import java.util.function.Consumer;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.Jwt;

import com.example.demo.configuration.SecurityConfiguration;
import com.example.demo.domain.Usuario;
import com.example.demo.domain.exceptions.ValidacionException;
import com.example.demo.services.interfaces.CorreoService;
import com.example.demo.services.interfaces.JwtService;
import com.example.demo.services.interfaces.VerificacionEmailService;

@WebMvcTest(CorreoController.class)
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @AutoConfigureRestTestClient
@Import(SecurityConfiguration.class) // Import your security configuration
public class CorreoControllerTests {

    // @Autowired
    // private RestTestClient restTestClient;

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private VerificacionEmailService verificacionEmailService;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private UserDetailsService userDetailsService;

    @MockitoBean
    private CorreoService correoService;

    @Test
    void sendTestEmail_Ok() throws ValidacionException, Exception {
        var usuario = new Usuario();
        usuario.setUsuarioId(1L);
        usuario.setNombre("Omar");
        usuario.setApellidoPaterno("ricardo");
        usuario.setApellidoMaterno("Cier");

        when(userDetailsService.loadUserByUsername(any())).thenReturn(usuario);
        
        doNothing().when(verificacionEmailService).enviarCorreoVerificacionToken(any());

        mockMvc.perform(
                get("/correos/confirmation-email")
                .header("Authorization", "Bearer token")
                        .with(
                                getToken()))
                                

                .andExpect(status().isOk());
    }

    private RequestPostProcessor getToken() {


        Jwt jwt = Jwt.withTokenValue("token")
        .header("alg", "HS512")
        .subject("fer")
                .claim("sub", "fer")
                .claim("jti", "3")
                .issuedAt(Instant.now())
                .expiresAt(Instant.MAX)
                .claim("roles", new String[] {})
                .build();


        Consumer<Jwt.Builder> builder = (b) -> jwt.getClaims().forEach(b::claim);

        RequestPostProcessor jwtRequestPostProcessor = jwt().jwt(jwt);
        return jwtRequestPostProcessor;
        // return
        // "eyJhbGciOiJIUzUxMiJ9.eyJyb2xlcyI6W10sImp0aSI6IjMiLCJzdWIiOiJmZXIiLCJpYXQiOjE3NzUxODg3MzMsImV4cCI6MTc3NTE5MjMzM30.JqTrfn0e_TJYFqbKMEk6AfRwANoMsdBxUUnsfUPztuNcLXGLoBbzM_R-M3WYIanvAJFkQmleEU-iKzdlw-YURw";
    }

}
