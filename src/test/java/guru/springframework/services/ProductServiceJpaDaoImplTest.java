package guru.springframework.services;

import guru.springframework.config.JpaIntegrationConfig;
import guru.springframework.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(JpaIntegrationConfig.class)
@ActiveProfiles({"jpadao"})
public class ProductServiceJpaDaoImplTest {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Test
    public void testList() throws Exception {
        List<Product> products = (List<Product>) productService.listAll();

        assert products.size() == 5;
    }

    @Test
    public void testShow() throws Exception {
        Integer id = 3;
        Product product = productService.getById(id);

        assert product.getDescription() == "Product 3";
    }

    @Test
    public void testCreate() throws Exception {
        Product product = new Product();
        product.setDescription("Product 6");
        product.setPrice(new BigDecimal("108.99"));
        product.setImageUrl("http://example.com/product6");
        Product newProduct = productService.saveOrUpdate(product);

        assertEquals("Product 6", newProduct.getDescription());
        assertEquals((Integer) 6, newProduct.getId());
    }

    @Test
    public void testUpdate() throws Exception {
        Integer Id = 4;
        Product product = productService.getById(4);
        product.setDescription("Test description change");
        Product newProduct = productService.saveOrUpdate(product);

        assertEquals("Test description change", newProduct.getDescription());
    }

    @Test
    public void testDelete() throws Exception {
        Integer id = 2;

        productService.delete(id);

        assertEquals(null, productService.getById(2));
    }
}
