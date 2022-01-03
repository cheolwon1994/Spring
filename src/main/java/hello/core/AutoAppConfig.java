package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration      //설정정보
@ComponentScan(
        //basePackages = "hello.core.member",     //패키지 탐색 시작 위치 지정. 안붙일 시, AutoAppConfig가 속한 hello.core 패키지부터 탐색 시작
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION,classes = Configuration.class)       //@Configuration이 붙은 설정 정보는 자동적으로 등록되는데, 막기 위해 사용
)      //스프링빈을 자동적으로 끌어올린다
public class AutoAppConfig {

    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
