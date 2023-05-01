package kim.jerok.practice_spring_15.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import javax.persistence.EntityManager;

/**
 * Transactional이 붙어 있으면 rollback은 하지만, auto_increment가 초기화되지 않는다.
 */
@Import(CustomerRepository.class)
@DataJpaTest  // Hibernate(db연결 객체들, EntityManager)
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EntityManager em;
    
    @BeforeEach  // 테스트 메서드 실행 직전 마다 발동
    public void setUp() {
        em.createNativeQuery("ALTER TABLE customer_tb ALTER COLUMN id RESTART WITH 1").executeUpdate();
        Customer customer = Customer.builder()
                .name("홍길동")
                .tel("0102222")
                .build();
        customerRepository.save(customer);  // insert 실행됨
        // auto_increment 초기화를 해줘야한다.
        em.clear();
    }

    @Test
    @DisplayName("save() test")
    public void save_test() throws Exception {
        // given
        Customer customer = Customer.builder()
                .name("홍길동")
                .tel("0102222")
                .build();

        // when
        Customer customerPS = customerRepository.save(customer);  // insert 실행됨
        System.out.println(customerPS.getId());
        System.out.println(customerPS.getName());
        System.out.println(customerPS.getTel());
        System.out.println("====================");
        em.clear();

        // PC가 초기화되어서!!
        Customer cs = customerRepository.findById(1L);  // 조회 쿼리 발동 -> DB레코드 -> persistence context 에 영속화 시킴(디폴트 생성자 호출됨)
        System.out.println(cs.getId());
        System.out.println("====================");

        // PC를 캐싱 (조회 쿼리 발동 안함)
        Customer cs2 = customerRepository.findById(1L);

        // then
        Assertions.assertThat(customerPS.getId()).isEqualTo(2);
    }  // rollback (auto_increment 2)

    @Test
    @DisplayName("findById() test")
    public void findById_test() throws Exception {
        // given 1
        Customer customer = Customer.builder()
                .name("홍길동")
                .tel("0102222")
                .build();
        customerRepository.save(customer);  // insert 실행됨
        em.clear();

        // given 2
        Long id = 1L;

        // when
        Customer customerPS = customerRepository.findById(1L);
        System.out.println(customerPS.getId());

        // then
        Assertions.assertThat(customerPS.getId()).isEqualTo(1);

    }

    @Test
    @DisplayName("update() test")
    public void update_test() throws Exception {
        // given
        // 순수한 객체(PK가 없는 것)
        // 비영속 객체(PK가 있는 것)
        // 준영속 객체(PK가 있고, 영속화 되었다가 detach 된 것)
        // 영속 객체(PK가 있고, 영속화 된 것)
        Customer customerPS = customerRepository.findById(1L);
        // em.merge(customerPS);
        
        customerPS.update("Jerok", "0102345");
        em.flush();  // 트랜잭션 종료

        // given 2
        // Customer customerPS = customerRepository.findById(1L);
        // customerPS.update("임꺽정", "0103333");

        // when
        // em.flush();
        // customerRepository.update(customerPS);

        // then


    }  // rollback
    
    @Test
    @DisplayName("delete() test")
    public void delete_test() throws Exception {
        // given
        Customer customerPS = customerRepository.findById(1L);
                
        // when
        customerRepository.delete(customerPS);
        System.out.println(customerPS.getId());
        
        // then
        Assertions.assertThat(customerPS.getId()).isEqualTo(1);
        
    }
    
    @Test
    @DisplayName("findAll() test")
    public void findAll_test() throws Exception {
        // given
        
                
        // when
        
        
        // then
        
        
    }

}
