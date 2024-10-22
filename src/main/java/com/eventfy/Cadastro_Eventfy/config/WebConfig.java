package com.eventfy.Cadastro_Eventfy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permite todas as rotas
                .allowedOrigins("https://event-fy.vercel.app/cadastro") // Permite acesso do Angular
                .allowedOrigins("https://cadastroeventfy-production.up.railway.app/cadastro") // Permite acesso do Angular
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
                .allowedHeaders("*") // Permite todos os cabeçalhos
                .allowCredentials(true);
    }

//    @Override
//    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
//        responseContext.getHeaders().add("Access-Control-Allow-Origin", "https://event-fy.vercel.app"); // Permite apenas a origem especificada
//        responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
//        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
//        responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
//    }
}
