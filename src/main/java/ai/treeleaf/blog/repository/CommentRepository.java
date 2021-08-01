package ai.treeleaf.blog.repository;

import ai.treeleaf.blog.model.Blog;
import ai.treeleaf.blog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Query("SELECT u FROM Comment u WHERE u.blog = ?1")
    List<Comment> findByBlog(Blog blog);
}
