package com.kotobazza.kotoblog.posts;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface PostsRepository extends JpaRepository<Post, Long> {


    boolean existsByTitle(String title);

    //Page<Post> findAllByCreatedAt(LocalDateTime timeCreatedAt, Pageable pageable);

    @Override
    Page<Post> findAll(@NonNull Pageable pageable);

    Page<Post> findByCategoriesContaining(String category, Pageable pageable);

    Page<Post> findByCategoriesIn(Collection<List<String>> categories, Pageable pageable);

}


