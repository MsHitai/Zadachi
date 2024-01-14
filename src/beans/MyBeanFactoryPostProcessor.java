package beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.util.Map;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Map<String, Object> map = beanFactory.getBeansWithAnnotation(Qualifier.class);

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            createInstances(beanFactory, entry.getKey(), entry.getValue());
        }
    }

    private void createInstances(ConfigurableListableBeanFactory beanFactory, String beanName, Object bean) {
        Qualifier qualifier = bean.getClass().getAnnotation(Qualifier.class);
        for (String name : extractNames(qualifier)) {
            Object newBean = beanFactory.getBean(beanName);
            beanFactory.registerSingleton(name.trim(), newBean);
        }
    }

    private String[] extractNames(Qualifier qualifier) {
        return qualifier.value().split(",");
    }
}
