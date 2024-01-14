package beans;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class MyBeanInitializing implements InitializingBean {

    private MyBean beanOne;
    private MyBean beanTwo;

    @Override
    public void afterPropertiesSet() {
        Assert.notNull(beanOne, "One");
        Assert.notNull(beanTwo, "Two");
    }

    @Autowired
    public void setBeanOne(MyBean beanOne) {
        this.beanOne = beanOne;
        this.beanOne.setName("One");
        this.beanOne.setQuality("Test One");
    }

    @Autowired
    public void setBeanTwo(MyBean beanTwo) {
        this.beanTwo = beanTwo;
        this.beanTwo.setName("Two");
        this.beanTwo.setQuality("Test Two");
    }

}
