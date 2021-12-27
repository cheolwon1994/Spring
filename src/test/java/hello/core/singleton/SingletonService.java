package hello.core.singleton;

public class SingletonService {

    /**
     * 싱글톤 패턴의 단점
     * 1. 아래 1,2,3번 함수를 모두 구현해야 한다 -> 구현해야 하는 함수가 많다
     * 2. getInstance() 함수를 호출을 해야한다 -> 클라이언트가 구체 클래스에 의존 -> DIP 위반 + OCP 위반 가능성 높음
     * 3. 유연적으로 테스트하기 어렵다
     * 4. 내부 속성 변경 or 초기화하기 어렵다
     * 5. private 생성자로 인해 자식 클래스를 만들기 어렵다
     * 6. 안티패턴으로 불리기도 한다
     */

    //1. Static 영역에 객체를 딱 1개만 생성해둔다
    private static final SingletonService instance = new SingletonService();

    //2. Public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다
    public static SingletonService getInstance(){
        return instance;
    }

    //3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다(컴파일 에러)
    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출출");
   }
}
