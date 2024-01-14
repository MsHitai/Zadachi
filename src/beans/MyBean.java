package beans;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier(value = "beanOne, beanTwo")
public class MyBean implements FactoryBean<Object> {

    private String name;
    private String quality;

    public MyBean() {

    }

    @Override
    public Class<?> getObjectType() {
        return MyBean.class;
    }

    @Override
    public Object getObject() {
        return new MyBean();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }
}
