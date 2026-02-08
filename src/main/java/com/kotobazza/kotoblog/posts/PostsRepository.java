package com.kotobazza.kotoblog.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Post, Long> {


    boolean existsByTitle(String title);



}


