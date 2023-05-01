package kim.jerok.practice_spring_15.model;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Table(name = "customer_tb")
@Entity  // hibernate가 관리해준다.
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String tel;

    public Customer() {
        // setter가 없지만, 리플렉션을 통해서 private 변수에 직접 접근해서 값을 할당함.
        System.out.println("디폴트 생성자 호출 = hibernate가 DB의 레코드를 영속화(자바 객체로) 시킬 때");
    }

    @Builder
    public Customer(Long id, String name, String tel) {
        System.out.println("조회시에 mapper 동작할 때 풀 생성자 실행");
        this.id = id;
        this.name = name;
        this.tel = tel;
    }

    public void update(String name, String tel) {
        this.name = name;
        this.tel = tel;
    }

}
