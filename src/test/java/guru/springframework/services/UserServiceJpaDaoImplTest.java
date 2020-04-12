package guru.springframework.services;

import guru.springframework.config.JpaIntegrationConfig;
import guru.springframework.domain.*;
import org.hibernate.mapping.IdentifierBag;
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
public class UserServiceJpaDaoImplTest {
    private UserService userService;
    private ProductService productService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setProductService(ProductService productService){
        this.productService = productService;
    }

    @Test
    public void testSaveOfUser() throws Exception {
        User user = new User();

        user.setUsername("someusername");
        user.setPassword("myPassword");

        User savedUser = userService.saveOrUpdate(user);

        assertNotEquals(null, savedUser.getId());
        assertNotEquals(null, savedUser.getEncryptedPassword());

        System.out.println("Encrypted Password");
        System.out.println(savedUser.getEncryptedPassword());
    }

    @Test
    public void testSaveOfUserWithCustomer() throws Exception {
        User user = new User();

        user.setUsername("someusername");
        user.setPassword("myPassword");

        Customer customer = new Customer();
        customer.setFirstName("Tom");
        customer.setLastName("Petty");
        user.setCustomer(customer); // this will also set user into customer record

        User newUser = userService.saveOrUpdate(user);

        System.out.println("user " + user.getId());
        System.out.println("newUser " + newUser.getId());

        assertNotEquals(null, newUser.getId());
        assertEquals("Tom", customer.getFirstName());
        assertNotEquals(null, newUser.getVersion());
        assertNotEquals(null, newUser.getCustomer());
        assertNotEquals(null, customer.getUser());
        assertEquals(user.getCustomer(), customer);
//        assertEquals(customer.getUser(), newUser); // problem - customer has original user from before saveOrUpdate
    }

    @Test
    public void testAddCartToUser() throws Exception {
        User user = new User();

        user.setUsername("someusername");
        user.setPassword("myPassword");

        user.setCart(new Cart());

        User savedUser = userService.saveOrUpdate(user);

        assertNotEquals(null, savedUser.getId());
        assertNotEquals(null, savedUser.getVersion());
        assertNotEquals(null, savedUser.getCart());
        assertNotEquals(null, savedUser.getCart().getId());
    }

    @Test
    public void testAddCartToUserWithDetails() throws Exception {
        User user = new User();

        user.setUsername("someusername");
        user.setPassword("myPassword");

        user.setCart(new Cart());

        List<Product> storedProducts = (List<Product>) productService.listAll();

        CartDetail item = new CartDetail();
        item.setProduct(storedProducts.get(0));
        user.getCart().addCartDetail(item);

        item = new CartDetail();
        item.setProduct(storedProducts.get(1));
        user.getCart().addCartDetail(item);

        User savedUser = userService.saveOrUpdate(user);

        assert savedUser.getId() != null;
        assert savedUser.getVersion() != null;
        assert savedUser.getCart() != null;
        assert savedUser.getCart().getId() != null;
        assertEquals(2, savedUser.getCart().getCartDetails().size());
    }

    @Test
    public void testAddAndRemoveCartToUserWithCartDetails() throws Exception {
        User user = new User();

        user.setUsername("someusername");
        user.setPassword("myPassword");

        user.setCart(new Cart());

        List<Product> storedProducts = (List<Product>) productService.listAll();

        CartDetail item = new CartDetail();
        item.setProduct(storedProducts.get(0));
        user.getCart().addCartDetail(item);

        item = new CartDetail();
        item.setProduct(storedProducts.get(1));
        user.getCart().addCartDetail(item);

        User savedUser = userService.saveOrUpdate(user);

        assertEquals(2, savedUser.getCart().getCartDetails().size());

        savedUser.getCart().removeCartDetail(savedUser.getCart().getCartDetails().get(1));
        userService.saveOrUpdate(savedUser);

        assertEquals(1, savedUser.getCart().getCartDetails().size());
    }
}
