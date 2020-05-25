package business;

import java.io.Serializable;
import java.text.NumberFormat;
import javax.persistence.*;

/**
 *
 * @author n.riley
 */
@Entity
@Table(name="bookinv")
public class BookInv implements Serializable {
    @Id
    @Column(name="ID")
    private long id = 0;
    
    @Column(name="bookID")
    private String bookid = "";
    
    @Column(name="storeID")
    private int storeid = 0;
    
    @Column(name="OnHand")
    private int onhand = 0;
    
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="bookID", insertable=false, updatable=false)
    private BookList booklist;
    
    public BookInv() {
        this.storeid = 0;
        this.bookid = "";
        this.onhand = 0;
        this.id = 0;
    }
    private void setid(long id) {this.id = id;}
    public void setStoreid(int storeid) {this.storeid = storeid;}
    public void setBookid(String bookid) {this.bookid = bookid;}
    public void setOnhand(int onhand) {this.onhand = onhand;}
    public void setAuthor(String author){booklist.setAuthor(author);}
    public void setTitle(String title){booklist.setTitle(title);}
    public void setPrice(Double price){booklist.setPrice(price);}
    
    public long getid() {return this.id;}
    public int getStoreid() {return this.storeid;}
    public String getBookid() {return this.bookid;}
    public int getOnhand() {return this.onhand;}
    public String getAuthor() {return booklist.getAuthor();}
    public String getTitle() {return booklist.getTitle();}
    public double getPrice() {return booklist.getPrice();}
    public String getPricefmt() {
        NumberFormat curr = NumberFormat.getCurrencyInstance();
        return curr.format(booklist.getPrice());
    }
}
