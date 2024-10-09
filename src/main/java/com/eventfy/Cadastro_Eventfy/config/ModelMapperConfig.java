package com.eventfy.Cadastro_Eventfy.config;

import com.eventfy.Cadastro_Eventfy.mapper.ModelMapperCustom;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapperCustom modelMapper() {
        var mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return new ModelMapperCustom(mapper);
    }

}
