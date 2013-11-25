package models;
 
import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;
 
@Entity
public class Task extends Model {
 
    public String title;
    public Date createdOn = new Date();
    public Date updatedOn;
    
    @Lob
    public String content;
    
    @ManyToOne
    public User createdBy;
    @ManyToOne
    public User updatedBy;
    
    @OneToMany(mappedBy="task", cascade=CascadeType.ALL)
    public List<Comment> comments;
     
    /**
     * Default Task constructor
     * @param author
     * @param title
     * @param content
     */
    public Task(User author, String title, String content) { 
        this.comments = new ArrayList<Comment>();
        this.createdBy = author;
        this.title = title;
        this.content = content;
    }
    
    /**
     * Add comment to task
     * @param author
     * @param content
     * @return
     */
    public Task addComment(String author, String content) {
        Comment newComment = new Comment(this, author, content).save();
        
        this.comments.add(newComment);
        this.save();
        
        return this;
    }
 
}