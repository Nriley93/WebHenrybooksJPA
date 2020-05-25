package business;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author n.riley
 */
@Entity
@Table(name="users")
public class User implements Serializable {
    @Id
    @Column(name="userID")
    private String userid;
    
    @Column(name="userName")
    private String username;
    
    @Column(name="userPassword")
    private int password;
    
    @Column(name="storeID")
    private int storeid;
    
    @Column(name="adminLevel")
    private String adminlevel;
    
    @Transient
    private int pwdattempt;

    public User() {
        this.userid = "";
        this.storeid = 0;
        this.password = 0;
        this.pwdattempt = -1;
        this.username = "";
        this.adminlevel = "";
    }

    public void setUserid(String userid) {this.userid = userid;}
    public void setStoreid(int storeid) {this.storeid = storeid;}
    public void setPassword(int password) {this.password = password;}
    public void setPwdattempt(int pwdattempt) {this.pwdattempt = pwdattempt;}
    public void setUsername(String username) {this.username = username;}
    public void setAdminlevel(String adminlevel) {this.adminlevel = adminlevel;}
    
    public String getUserid() {return userid;}
    public int getStoreid() {return storeid;}
    public int getPassword() {return password;}
    public int getPwdattempt() {return pwdattempt;}
    public String getUsername() {return username;}
    public String getAdminlevel() {return adminlevel;}
    
    public boolean isAuthenticated() {
        if(this.password > 0) {return this.password == this.pwdattempt;}
        return false;
    }
}
