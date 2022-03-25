package com.freshmall.ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RibbonRandomRuleConfig {
    //方法名一定要中 iRule
    @Bean
    public IRule iRule(){
        return new RandomRule();
    }
}