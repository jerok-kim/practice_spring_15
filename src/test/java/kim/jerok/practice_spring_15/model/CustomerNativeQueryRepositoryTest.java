package kim.jerok.practice_spring_15.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import javax.persistence.EntityManager;
import java.util.List;

@Import(CustomerNativeQueryRepository.class)
@DataJpaTest
public class CustomerNativeQueryRepositoryTest {

    @Autowired
    private CustomerNativeQueryRepository customerRepository;

    @Autowired
    private EntityManager em;

    @BeforeEach
    public void setUp() {
        em.createNativeQuery("ALTER TABLE customer ALTER COLUMN `id` RESTART WITH 1").executeUpdate();
        customerRepository.save(new Customer(1L, "고객1", "0101111"));
        customerRepository.save(new Customer(2L, "고객2", "0102222"));
        customerRepository.save(new Customer(3L, "고객3", "0103333"));
    }

    @Test
    @DisplayName("save() test")
    public void save_test() throws Exception {
        // given
        Customer customer = new Customer(null, "고객4", "0104444");

        // when
        customerRepository.save(customer);

        // then
        Customer customerPS = customerRepository.findById(4L);
        Assertions.assertThat(customerPS.getId()).isEqualTo(4L);
        Assertions.assertThat(customerPS.getName()).isEqualTo(customer.getName());
        Assertions.assertThat(customerPS.getTel()).isEqualTo(customer.getTel());
    }

    @Test
    @DisplayName("update() test")
    public void update_test() throws Exception {
        // given
        Customer customer = new Customer(3L, "고객3", "0109999");

        // when
        customerRepository.update(customer);

        // then
        Customer customerPS = customerRepository.findById(3L);
        Assertions.assertThat(customerPS.getTel()).isEqualTo(customer.getTel());
    }

    @Test
    @DisplayName("delete() test")
    public void delete_test() throws Exception {
        // given
        Customer customer = new Customer(3L, "고객3", "0103333");

        // when
        customerRepository.delete(customer);

        // then
        Assertions.assertThatThrownBy(() -> {
            customerRepository.findById(3L);
        });
    }

    @Test
    @DisplayName("findById() test")
    public void findById_test() throws Exception {
        // given
        Long id = 1L;

        // when
        Customer customerPS = customerRepository.findById(id);

        // then
        Assertions.assertThat(customerPS.getTel()).isEqualTo("0101111");
    }

    @Test
    @DisplayName("findAll() test")
    public void findAll_test() throws Exception {
        // given
        int page = 0;

        // when
        List<Customer> customerListPS = customerRepository.findAll(page);

        // then
        Assertions.assertThat(customerListPS.size()).isEqualTo(2);
    }

}
