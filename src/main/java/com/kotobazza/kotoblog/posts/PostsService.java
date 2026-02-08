package com.kotobazza.kotoblog.posts;

import com.kotobazza.kotoblog.posts.exceptions.PostAlreadyExistsException;
import com.kotobazza.kotoblog.posts.exceptions.UnsafePostOrTextException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class PostsService {
    private PostsRepository repo;



    public Post addNewPost(PostTitleAndTextDTO postToAdd) throws PostAlreadyExistsException, UnsafePostOrTextException {
        if(postToAdd.title() == null){
            throw new UnsafePostOrTextException("New post doesn't contain the post title");
        }
        
        if(repo.existsByTitle(postToAdd.title())){
            throw new PostAlreadyExistsException("Post with this title already exists :"+postToAdd.title());
        }
        //TODO: sql validation

        Post post = new Post(
                postToAdd.title(),
                postToAdd.text()
        );
        return repo.save(post);
    }

    public Post updatePost(Long postId, PostTitleAndTextDTO updateData) throws PostAlreadyExistsException, UnsafePostOrTextException {
        if(updateData.title() != null && repo.existsByTitle(updateData.title())){
            throw new PostAlreadyExistsException("Post with this title already exists :" + updateData.title());
        }

        //TODO: sql validation
        
        Post existing = repo.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post with this identifier doesn't exist: " + postId.toString()));
        
        if(updateData.title() != null)
            existing.setTitle(updateData.title());

        if(updateData.text() != null)
            existing.setInnerText(updateData.text());

        return repo.save(existing);
    }

    public void deletePost(Long postId){
        repo.deleteById(postId);
    }

    public Post getPostById(Long postId){
        return repo.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post with this identifier doesn't exist: " + postId.toString()));
    }












}
