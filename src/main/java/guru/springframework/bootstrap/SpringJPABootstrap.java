package guru.springframework.bootstrap;

import guru.springframework.domain.Address;
import guru.springframework.domain.Customer;
import guru.springframework.domain.Product;
import guru.springframework.domain.User;
import guru.springframework.services.CustomerService;
import guru.springframework.services.ProductService;
import guru.springframework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent> {
    
    private ProductService productService;
    private CustomerService customerService;
    private UserService userService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadProducts();
        loadCustomers();
        loadUsers();
    }

    private void loadCustomers() {
        Customer cust = new Customer();
        cust.setFirstName("Fred");
        cust.setLastName("Flintstone");
        cust.setEmail("none");
        cust.setPhoneNumber("Just holler");
        cust.setBillingAddress(new Address());
        cust.getBillingAddress().setAddressLine1("111 Stoney Way");
        cust.getBillingAddress().setAddressLine2("");
        cust.getBillingAddress().setCity("Bedrock");
        cust.getBillingAddress().setState("NY");
        cust.getBillingAddress().setZipCode("12345");
        customerService.saveOrUpdate(cust);

        cust = new Customer();
        cust.setFirstName("Barney");
        cust.setLastName("Rubble");
        cust.setEmail("haha");
        cust.setPhoneNumber("Just yell");
        cust.setBillingAddress(new Address());
        cust.getBillingAddress().setAddressLine1("114 Stoney Way");
        cust.getBillingAddress().setAddressLine2("");
        cust.getBillingAddress().setCity("Bedrock");
        cust.getBillingAddress().setState("NY");
        cust.getBillingAddress().setZipCode("12345");
        customerService.saveOrUpdate(cust);

        cust = new Customer();
        cust.setFirstName("Herman");
        cust.setLastName("Munster");
        cust.setEmail("franknstein@gmail.com");
        cust.setPhoneNumber("555-5555");
        cust.setBillingAddress(new Address());
        cust.getBillingAddress().setAddressLine1("1313 Mockingbird Lane");
        cust.getBillingAddress().setAddressLine2("");
        cust.getBillingAddress().setCity("Mockingbird Heights");
        cust.getBillingAddress().setState("CA");
        cust.getBillingAddress().setZipCode("90120");
        customerService.saveOrUpdate(cust);

        cust = new Customer();
        cust.setFirstName("Mike");
        cust.setLastName("Brady");
        cust.setEmail("mbrady@gmail.com");
        cust.setPhoneNumber("762-0799");
        cust.setBillingAddress(new Address());
        cust.getBillingAddress().setAddressLine1("4222 Clinton Way");
        cust.getBillingAddress().setAddressLine2("");
        cust.getBillingAddress().setCity("Studio City");
        cust.getBillingAddress().setState("CA");
        cust.getBillingAddress().setZipCode("90120");
        customerService.saveOrUpdate(cust);
    }

    public void loadProducts(){
        Product product = new Product();
        product.setDescription("Product 1");
        product.setPrice(new BigDecimal("12.99"));
        product.setImageUrl("http://example.com/product1");
        productService.saveOrUpdate(product);
        
        product = new Product();
        product.setDescription("Product 2");
        product.setPrice(new BigDecimal("14.99"));
        product.setImageUrl("http://example.com/product2");
        productService.saveOrUpdate(product);

        product = new Product();
        product.setDescription("Product 3");
        product.setPrice(new BigDecimal("34.99"));
        product.setImageUrl("http://example.com/product3");
        productService.saveOrUpdate(product);

        product = new Product();
        product.setDescription("Product 4");
        product.setPrice(new BigDecimal("44.99"));
        product.setImageUrl("http://example.com/product4");
        productService.saveOrUpdate(product);

        product = new Product();
        product.setDescription("Product 5");
        product.setPrice(new BigDecimal("25.99"));
        product.setImageUrl("http://example.com/product5");
        productService.saveOrUpdate(product);
    }

    private void loadUsers() {
        User user = new User();
        user.setUsername("FrankNStein");
        user.setPassword("Lilly");
        user.setCustomer(customerService.getById(3));
        userService.saveOrUpdate(user);

        user = new User();
        user.setUsername("FreddieF");
        user.setPassword("Wilma");
        user.setCustomer(customerService.getById(1));
        userService.saveOrUpdate(user);
    }
}
