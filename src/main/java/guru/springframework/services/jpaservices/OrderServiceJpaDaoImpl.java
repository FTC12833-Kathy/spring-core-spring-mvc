package guru.springframework.services.jpaservices;

import guru.springframework.domain.OrderInfo;
import guru.springframework.services.OrderService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Profile("jpadao")
public class OrderServiceJpaDaoImpl extends AbstractJpaDaoService implements OrderService {

    @Override
    public List<OrderInfo> listAll() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from OrderInfo", OrderInfo.class).getResultList();
    }

    @Override
    public OrderInfo getById(Integer id) {
        EntityManager em = emf.createEntityManager();

        return em.find(OrderInfo.class, id);
    }

    @Override
    public OrderInfo saveOrUpdate(OrderInfo domainObject) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        OrderInfo newOrderInfo = em.merge(domainObject);
        em.getTransaction().commit();

        return newOrderInfo;
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(OrderInfo.class, id));
        em.getTransaction().commit();
    }
}
