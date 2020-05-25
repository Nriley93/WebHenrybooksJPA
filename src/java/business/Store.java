package business;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author n.riley
 */
@Entity
@Table(name="stores")
public class Store implements Serializable {
    @Id
    @Column(name="storeID")
    private int storeid;
    
    @Column(name="storeName")
    private String storename;
    
    @Column(name="storeAddr")
    private String storeaddr;
    
    @Column(name="storeEmp")
    private int storeemp;

    public Store() {
        this.storeid = 0;
        this.storeemp = 0;
        this.storename = "";
        this.storeaddr = "";
    }

    public void setStoreid(int storeid) {this.storeid = storeid;}
    public void setStoreemp(int storeemp) {this.storeemp = storeemp;}
    public void setStorename(String storename) {this.storename = storename;}
    public void setStoreaddr(String storeaddr) {this.storeaddr = storeaddr;}
    
    public int getStoreid() {return storeid;}
    public int getStoreemp() {return storeemp;}
    public String getStorename() {return storename;}
    public String getStoreaddr() {return storeaddr;}
}