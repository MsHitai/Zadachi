package beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBeanConfig {

    @Bean
    public MyBeanFactoryPostProcessor MyBeanFactoryPostProcessor() {
        return new MyBeanFactoryPostProcessor();
    }

    @Bean
    public MyBean myBean() {
        return new MyBean();
    }

    @Bean
    public MyBeanInitializing myBeanInitializing() {
        return new MyBeanInitializing();
    }
}
