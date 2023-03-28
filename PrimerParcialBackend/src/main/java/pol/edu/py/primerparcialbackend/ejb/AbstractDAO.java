package pol.edu.py.primerparcialbackend.ejb;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author heokim
 */
public abstract class AbstractDAO<T> {

    private final Class<T> entityClass;

    public AbstractDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public T create(T entity) {
        getEntityManager().persist(entity);
        getEntityManager().flush();
        getEntityManager().refresh(entity);
        return entity;
    }

    public T edit(T entity) {
        getEntityManager().merge(entity);
        return entity;
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }
    
    
    public void removeById(Object id) {
        T entityToRemove = getEntityManager().find(entityClass, id);
        getEntityManager().remove(getEntityManager().merge(entityToRemove));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public List<T> findPageWithSize(int page, int pageSize) {
        int Offset = page * pageSize;
        String sql = "SELECT s FROM " + entityClass.getName() + " s ORDER BY s.id DESC";
        Query query = getEntityManager().createQuery(sql);
        query.setFirstResult(Offset).setMaxResults(pageSize);
        List<T> resultList = query.getResultList();
        return resultList;
    }
    
    public List<T> findByParametro(String parameter, Object value) {
        try {
            return getEntityManager()
                    .createQuery("SELECT a FROM " + this.entityClass.getSimpleName() + " a WHERE a." + parameter + " = :value")
                    .setParameter("value", value)
                    .getResultList();
        } catch (Exception e) {
            System.out.println("Exception in  findByParametro() -> " + e.getLocalizedMessage());
            return null;
        }

    }
    
    public List<T> findByParametrosMap(Map<String, Object> map) {
        try {
            String parametros = "";
            int count = 0;
            for (String key : map.keySet()) {
                parametros += (parametros.equals("") ? "" : " AND ") + "a." + key + " = :p" + (++count);

            }
            Query q = getEntityManager().createQuery("SELECT a FROM " + this.entityClass.getSimpleName() + " a WHERE " + parametros);
            count = 0;
            for (String key : map.keySet()) {
                q.setParameter("p" + (++count), map.get(key));
            }
            return q.getResultList();
        } catch (Exception e) {
            System.out.println("Exception in  findByParametrosMap() -> " + e.getLocalizedMessage());
            return null;
        }

    }
    
}
