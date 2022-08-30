package org.springblade.auth.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
@Data
public class YmlConfig {

    @Value("${ymlConfig.theDefaultPassword}")
    private  String theDefaultPassword;
}
