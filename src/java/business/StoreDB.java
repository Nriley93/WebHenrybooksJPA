package business;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author n.riley
 */
public class StoreDB {
    public static List<Store> getStoresList() {
        EntityManager em =
                DBUtil.getEmFactory().createEntityManager();
        String qs = "SELECT st FROM Store st ORDER BY st.storename";
        TypedQuery<Store> q = em.createQuery(qs, Store.class);
        
        List<Store> st = null;
        try{
            st = q.getResultList();
            if(st==null || st.isEmpty()) {
                st = null;
            }
        } catch(NoResultException e) {
            st = null;
        } finally {
            em.close();
        }
        return st;
    }
    
    public static Store getStore(double storeid) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qs = "SELECT st FROM Store st " +
                    " WHERE st.storeid = :storeid";
        TypedQuery<Store> q = em.createQuery(qs, Store.class);
        q.setParameter("storeid", storeid);
        try {
            Store st = q.getSingleResult();
            return st;
        } catch(NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public static boolean storeFound(double storeid) {
        Store st = getStore(storeid);
        return st != null;
    }
}