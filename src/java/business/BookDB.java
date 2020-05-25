package business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author n.riley
 */
public class BookDB {
    public static List<BookInv> getBooksList(double storeid) {
        EntityManager em =
                    DBUtil.getEmFactory().createEntityManager();
        String qs = "SELECT bk FROM BookInv bk " +
                    "WHERE bk.storeid = :storeid";
        TypedQuery<BookInv> inv = em.createQuery(qs, BookInv.class);
        inv.setParameter("storeid", storeid);
        
        List<BookInv> bks = null;
        try {
            bks = inv.getResultList();
            Map<String,Integer> map = new HashMap<>();
            for(BookInv bk : bks) {
                int count = 0;
                if(map.get(bk.getBookid()) != null) {
                    delete(bk);
                } else {
                    map.put(bk.getBookid(), count + 1);
                }   
            }
            if(bks==null || bks.isEmpty()) {
                bks = null;
            }
        } catch(NoResultException e) {
            bks = null;
        } finally {
            em.close();
        }
        return bks;
    }
    
    public static BookInv getBook(String bookid, double storeid) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qs = "SELECT bk FROM BookInv bk " +
                    "WHERE bk.storeid = :storeid " +
                    "AND bk.bookid = :bookid";
        TypedQuery<BookInv> q = em.createQuery(qs, BookInv.class);
        q.setParameter("storeid", storeid);
        q.setParameter("bookid", bookid);
        BookInv bk = null;
        try {
            bk = q.getSingleResult();
        } catch(NoResultException e) {
            return null;
        } finally {
            em.close();
        }
        return bk;
    }
    
    public static boolean bookFound(String bookid, double storeid) {
        BookInv bk = getBook(bookid,storeid);
        return bk != null;
    }
    
    public static void updtOnHand(BookInv book) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try {
            em.merge(book);
            trans.commit();
        } catch(Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public static void delete(BookInv bk) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        try{
            em.remove(em.merge(bk));
            trans.commit();
        } catch(Exception e) {
            System.out.println(e);
            trans.rollback();
        } finally {
            em.close();
        }
    } 
}