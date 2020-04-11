package guru.springframework.services;

import guru.springframework.config.JpaIntegrationConfig;
import guru.springframework.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

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
        cust.setAddressLine1("607 Milky Way");
        cust.setAddressLine2("");
        cust.setCity("Spaceville");
        cust.setState("ZZ");
        cust.setZipCode("99999");

        Customer newCust = customerService.saveOrUpdate(cust);

        assertEquals((Integer) 5, newCust.getId());
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
}
