package business;

import javax.persistence.EntityManager;

/**
 *
 * @author n.riley
 */
public class UserDB {
    public static User getUserByID(String userid) {
        EntityManager em =
                DBUtil.getEmFactory().createEntityManager();
        try{
            User u = em.find(User.class, userid);
            return u;
        } catch(Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}