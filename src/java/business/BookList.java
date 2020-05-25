package business;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author n.riley
 */
@Entity
@Table(name="booklist")
public class BookList implements Serializable {
    @Id
    @Column(name="bookID")
    private String bookid;
    
    @Column(name="title")
    private String title;
    
    @Column(name="author")
    private String author;
    
    @Column(name="price")
    private double price;
    
    public BookList() {
        this.bookid = "";
        this.title = "";
        this.author = "";
        this.price = 0;
    }

    public void setBookid(String bookid) {this.bookid = bookid;}
    public void setTitle(String title) {this.title = title;}
    public void setAuthor(String author) {this.author = author;}
    public void setPrice(double price) {this.price = price;}
    
    public String getBookid() {return this.bookid;}
    public String getTitle() {return this.title;}
    public String getAuthor() {return this.author;}
    public double getPrice() {return this.price;}
}
