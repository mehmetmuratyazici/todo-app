package io.mmy.todoapp.config;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomBeanFactory {

    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }
}
