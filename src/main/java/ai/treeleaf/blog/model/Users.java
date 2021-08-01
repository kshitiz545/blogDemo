package ai.treeleaf.blog.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class Users {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;

    @Column(name = "username",unique = true,nullable = false)
    private String username;

    @ManyToMany(cascade=CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(
            name="user_role",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="id")})
    private List<Roles> roles;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private String address;
    private String mobile;
    @Column(nullable = false)
    private boolean status;

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
