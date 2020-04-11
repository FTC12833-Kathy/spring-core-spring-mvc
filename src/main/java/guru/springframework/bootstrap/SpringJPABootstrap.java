package guru.springframework.bootstrap;

import guru.springframework.domain.Customer;
import guru.springframework.domain.Product;
import guru.springframework.services.CustomerService;
import guru.springframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent> {
    
    private ProductService productService;
    private CustomerService customerService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadProducts();
        loadCustomers();
    }

    private void loadCustomers() {
        Customer cust = new Customer();
        cust.setFirstName("Fred");
        cust.setLastName("Flintstone");
        cust.setEmail("none");
        cust.setPhoneNumber("Just holler");
        cust.setAddressLine1("111 Stoney Way");
        cust.setAddressLine2("");
        cust.setCity("Bedrock");
        cust.setState("NY");
        cust.setZipCode("12345");
        customerService.saveOrUpdate(cust);

        cust = new Customer();
        cust.setFirstName("Barney");
        cust.setLastName("Rubble");
        cust.setEmail("haha");
        cust.setPhoneNumber("Just yell");
        cust.setAddressLine1("114 Stoney Way");
        cust.setAddressLine2("");
        cust.setCity("Bedrock");
        cust.setState("NY");
        cust.setZipCode("12345");
        customerService.saveOrUpdate(cust);

        cust = new Customer();
        cust.setFirstName("Herman");
        cust.setLastName("Munster");
        cust.setEmail("franknstein@gmail.com");
        cust.setPhoneNumber("555-5555");
        cust.setAddressLine1("1313 Mockingbird Lane");
        cust.setAddressLine2("");
        cust.setCity("Mockingbird Heights");
        cust.setState("CA");
        cust.setZipCode("90120");
        customerService.saveOrUpdate(cust);

        cust = new Customer();
        cust.setFirstName("Mike");
        cust.setLastName("Brady");
        cust.setEmail("franknstein@gmail.com");
        cust.setPhoneNumber("762-0799");
        cust.setAddressLine1("4222 Clinton Way");
        cust.setAddressLine2("");
        cust.setCity("Studio City");
        cust.setState("CA");
        cust.setZipCode("90120");
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
}
