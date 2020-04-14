package guru.springframework.services;

import guru.springframework.config.JpaIntegrationConfig;
import guru.springframework.domain.Address;
import guru.springframework.domain.Customer;
import guru.springframework.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(JpaIntegrationConfig.class)
@ActiveProfiles({"jpadao"})
public class CustomerServiceJpaDaoImplTest {
    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Test
    public void testList() throws Exception{
        List<Customer> customers = (List<Customer>) customerService.listAll();

        assert customers.size() == 4;
    }

    @Test
    public void testShow() throws Exception{
        Integer id = 2;

        assertEquals("Rubble", customerService.getById(2).getLastName());
    }

    @Test
    public void testCreate() throws Exception{
        Customer cust = new Customer();
        cust.setFirstName("George");
        cust.setLastName("Jetson");
        cust.setEmail("GeorgeJane@gmail.com");
        cust.setPhoneNumber("123-123-1234");
        cust.setBillingAddress(new Address());
        cust.getBillingAddress().setAddressLine1("607 Milky Way");
        cust.getBillingAddress().setAddressLine2("");
        cust.getBillingAddress().setCity("Spaceville");
        cust.getBillingAddress().setState("ZZ");
        cust.getBillingAddress().setZipCode("99999");

        Customer newCust = customerService.saveOrUpdate(cust);

        assertEquals((Integer) 6, newCust.getId());
    }

    @Test
    public void testUpdate() throws Exception{
        Integer id = 4;

        Customer customer = customerService.getById(id);
        customer.setFirstName("Carol");

        Customer updCust = customerService.saveOrUpdate(customer);
        assertEquals("Brady", updCust.getLastName());
    }

    @Test
    public void testDelete() throws Exception{
        Integer id = 2;

        customerService.delete(id);

        assertEquals(null, customerService.getById(id));
    }

    @Test
    public void testSaveWithUser() throws Exception {
        Customer customer = new Customer();
        User user = new User();
        user.setUsername("user name");
        user.setPassword("awesomePassword");
        customer.setUser(user);

        Customer savedCustomer = customerService.saveOrUpdate(customer);

        assertNotEquals(null, savedCustomer.getUser().getId());
    }
}
