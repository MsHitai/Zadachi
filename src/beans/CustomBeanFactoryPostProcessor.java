package beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String targetPropertyName = "sameProperty";
        String targetValue = "newValue";

        String[] beanNames = beanFactory.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            if (beanFactory.getBeanDefinition(beanName).getPropertyValues().contains(targetPropertyName)) {
                beanFactory.getBeanDefinition(beanName).getPropertyValues().add(targetPropertyName, targetValue);
            }
        }
    }
}

