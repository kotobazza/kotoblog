package com.kotobazza.kotoblog.posts;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/posts")
public class PostsController {
    private PostsService service;

    public PostsController(PostsService service){
        this.service = service;

        service.addNewPost(new PostTitleAndTextDTO("What is it?", "Here it is"));
        service.addNewPost(new PostTitleAndTextDTO("What is it12?", "Here it is33"));
        service.addNewPost(new PostTitleAndTextDTO("What is it323?", "Here it is2332"));
    }


    @PostMapping("/posts")
    public Post addNewPost(@RequestBody PostTitleAndTextDTO dto){
        return service.addNewPost(dto);
    }


    @GetMapping("/posts/{id}")
    public Post getPost(
            @PathVariable Long id
    ){
        return service.getPostById(id);
    }

    @PostMapping("/posts/{id}")
    public Post updatePost(
            @PathVariable Long id,
            @RequestBody PostTitleAndTextDTO dto
    ){
        return service.updatePost(id, dto);
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(
            @PathVariable Long id
    ){
        service.deletePost(id);
    }
















}
