package guru.springframework.services.mapservices;

import guru.springframework.domain.AbstractDomain;

import java.util.*;

public abstract class AbstractMapService  {
    protected Map<Integer, AbstractDomain> domainMap;

    public AbstractMapService() {
        domainMap = new HashMap<>();
    }

    public List<AbstractDomain> listAll() {
        return new ArrayList<>(domainMap.values());
    }

    public AbstractDomain getById(Integer id) {
        return domainMap.get(id);
    }

    public AbstractDomain saveOrUpdate(AbstractDomain abstractDomain) {
        if (abstractDomain != null){

            if (abstractDomain.getId() == null){
                abstractDomain.setId(getNextKey());
            }
            domainMap.put(abstractDomain.getId(), abstractDomain);

            return abstractDomain;
        } else {
            throw new RuntimeException("Object Can't be null");
        }
    }

    public void delete(Integer id) {
        domainMap.remove(id);
    }

    private Integer getNextKey(){
        if (domainMap.isEmpty())
            return 1;

        return Collections.max(domainMap.keySet()) + 1;
    }
}
