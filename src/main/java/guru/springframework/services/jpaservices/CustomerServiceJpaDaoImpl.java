package guru.springframework.services.jpaservices;

import guru.springframework.domain.Customer;
import guru.springframework.services.CustomerService;
import guru.springframework.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Profile("jpadao")
public class CustomerServiceJpaDaoImpl extends AbstractJpaDaoService implements CustomerService {

    private EncryptionService encryptionService;

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @Override
    public List<Customer> listAll() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from Customer", Customer.class).getResultList();
    }

    @Override
    public Customer getById(Integer id) {
        EntityManager em = emf.createEntityManager();

        return em.find(Customer.class, id);
    }

    @Override
    public Customer saveOrUpdate(Customer inCustomer) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        // encrypt password from inCustomer before merging to savedCustomer
        if (inCustomer.getUser() != null && inCustomer.getUser().getPassword() != null) {
            inCustomer.getUser().setEncryptedPassword(
                    encryptionService.encryptString(inCustomer.getUser().getPassword()));
        }
        Customer savedCustomer = em.merge(inCustomer);
        em.getTransaction().commit();

        return savedCustomer;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Customer.class, id));
        em.getTransaction().commit();
    }
}
