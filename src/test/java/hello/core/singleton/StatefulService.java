package hello.core.singleton;

//스프링 빈은 항상 무상태(Stateless)로 설정해야 한다
public class StatefulService {

    //공유값 설정한 경우
    /*
    private int price;      //상태를 유지하는 필드 10000->20000
    
    public void order(String name, int price){
        System.out.println("name = " + name + " price = " + price);
        this.price = price;
    }

    public int getPrice(){
        return price;
    }
    */

    //공유값 설정하지 않은 경우
    public int order(String name, int price){
        System.out.println("name = " + name + " price = " + price);
        return price;
    }

}
