package models;
 
import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;
 
@Entity
public class Comment extends Model {
 
    public String createdBy;
    public Date createdOn = new Date();
     
    @Lob
    public String content;
    
    @ManyToOne
    public Task task;
    
    /**
     * Create comment for task
     * @param task
     * @param author
     * @param content
     */
    public Comment(Task task, String author, String content) {
        this.task = task;
        this.createdBy = author;
        this.content = content;
    }
 
}