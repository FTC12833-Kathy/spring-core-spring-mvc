package guru.springframework.services.mapservices;

import guru.springframework.domain.AbstractDomain;
import guru.springframework.domain.OrderInfo;
import guru.springframework.services.OrderService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("map")
public class OrderServiceMapImpl extends AbstractMapService implements OrderService {

    @Override
    public OrderInfo saveOrUpdate(OrderInfo domainObject) {
        return (OrderInfo) super.saveOrUpdate(domainObject);
    }

    @Override
    public List<AbstractDomain> listAll() {
        return  super.listAll();
    }

    @Override
    public OrderInfo getById(Integer id) {
        return (OrderInfo) super.getById(id);
    }

    @Override
    public AbstractDomain saveOrUpdate(AbstractDomain abstractDomain) {
        return super.saveOrUpdate(abstractDomain);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }
}
