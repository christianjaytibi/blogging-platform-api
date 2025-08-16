package com.example.blogapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.blogapi.model.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    @Query("""
            SELECT b
            FROM Blog b
            JOIN b.tags t
            WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :term, '%'))
                OR LOWER(b.category) LIKE LOWER(CONCAT('%', :term, '%'))
                OR LOWER(t.name) LIKE LOWER(CONCAT('%', :term, '%'))
            """)
    List<Blog> findByTitleOrCategoryOrTagsContaining(@Param("term") String term);
}
