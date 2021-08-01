package ai.treeleaf.blog.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "blog_id", nullable = false)
    private Blog blog;

    private String comment;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @Column(name = "created_at")
    private Date createdAt;

    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @Column(name = "updated_at")
    private Date updatedAt;


    @PrePersist
    protected void prePersist(){
        if (this.createdAt == null) this.createdAt = new Date();
        if (this.updatedAt ==null) this.updatedAt = new Date();
    }

    @PreUpdate
    protected void preUpdate(){
        this.updatedAt = new Date();
    }
}
