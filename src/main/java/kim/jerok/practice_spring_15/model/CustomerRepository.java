package kim.jerok.practice_spring_15.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@Repository
public class CustomerRepository {

    private EntityManager em;

    public CustomerRepository(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public Customer save(Customer customer) {
        // customer -> 비영속
        em.persist(customer);  // -> 영속화 -> flush
        // customer -> 영속화 (id = 1)
        return customer;
    }
    
    @Transactional
    public void update(Customer customer) {
        // 트랜잭션 시작
        em.merge(customer);
        // 트랜잭션 종료 (영속 객체의 변경이 감지되면 flush)
    }

    @Transactional
    public void delete(Customer customer) {
        em.remove(customer);
    }

    public Customer findById(Long id) {
        return em.find(Customer.class, id);
    }

    public List<Customer> findAll(int page) {
        final int row = 2;
        return em.createQuery("select ct from Customer ct", Customer.class)
                .setFirstResult(page * row)
                .setMaxResults(row)
                .getResultList();
    }

}
