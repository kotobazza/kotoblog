package com.kotobazza.kotoblog.posts;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface PostsRepository extends JpaRepository<Post, Long> {


    boolean existsByTitle(String title);

    //Page<Post> findAllByCreatedAt(LocalDateTime timeCreatedAt, Pageable pageable);

    @Override
    Page<Post> findAll(@NonNull Pageable pageable);

    Page<Post> findByCategoriesContaining(String category, Pageable pageable);

    Page<Post> findByCategoriesIn(Collection<List<String>> categories, Pageable pageable);

    @Query(
            value = """
                SELECT * FROM posts WHERE LOWER(inner_text) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR 
                            LOWER(title) LIKE LOWER(CONCAT('%', :searchTerm, '%'))
            """,
            nativeQuery = true
    )
    Page<Post> findPostsBySearchText(@Param("searchTerm") String searchTerm, Pageable pageable);


    Page<Post> findPostsByCreatedAtBetween(LocalDateTime createdAtAfter, LocalDateTime createdAtBefore, Pageable pageable);
}


