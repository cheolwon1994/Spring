package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

        //성공
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();

        //없기 때문에 실패
        /*BeanB beanB = ac.getBean("beanB", BeanB.class);
        assertThat(beanB).isNotNull();*/

        //성공하고 싶으면 throw error
        assertThrows(NoSuchBeanDefinitionException.class, ()->ac.getBean("beanB",BeanB.class));
    }

    @Configuration
    @ComponentScan(
            includeFilters = @Filter(type= FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @Filter(type=FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig{

    }

}
