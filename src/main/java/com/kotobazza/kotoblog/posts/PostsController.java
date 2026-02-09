package com.kotobazza.kotoblog.posts;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/posts")
public class PostsController {
    private final PostsService service;

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


    @GetMapping("/posts")
    public Page<Post> getPosts(
            @RequestParam(defaultValue="0") int page,
            @RequestParam(defaultValue="10") int size,
            @RequestParam(required = false) List<String> categories
    ){
        if(categories!= null && !categories.isEmpty()){
            if(categories.size() == 1){
                return service.getAllByCategoryPaging(categories.get(0), page, size);
            }
            return service.getAllByCategoriesPaging(categories, page, size);
        }
        return service.getAllByPaging(page, size);
    }

    @PatchMapping("/posts/{id}/categories")
    public Post massUpdateCategories(
            @PathVariable Long id,
            @RequestBody List<String> newCategories
    ){
        return service.setCategoriesToPost(id, newCategories);
    }


    @PostMapping("/posts/{id}/categories")
    public Post addCategoryToPost(
            @PathVariable Long id,
            @RequestBody String category
    ){
        return service.addCategoryToPost(id, category);
    }


    @DeleteMapping("/posts/{id}/categories")
    public Post deleteCategoryFromPost(
            @PathVariable Long id,
            @RequestParam(required=true) String category
    ){
        return service.removeCategoryFromPost(id, category);
    }














}
