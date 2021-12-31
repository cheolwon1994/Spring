package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration      //설정정보
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION,classes = Configuration.class)       //@Configuration이 붙은 설정 정보는 자동적으로 등록되는데, 막기 위해 사용
)      //스프링빈을 자동적으로 끌어올린다
public class AutoAppConfig {

}
