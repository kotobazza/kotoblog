package com.kotobazza.kotoblog.posts;

import com.kotobazza.kotoblog.posts.exceptions.PostAlreadyExistsException;
import com.kotobazza.kotoblog.posts.exceptions.UnsafePostOrTextException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


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

    public Page<Post> getAllByPaging(int page, int pageSize){
        Pageable pageable = PageRequest.of(page, pageSize);

        return repo.findAll(pageable);
    }


    public Page<Post> getAllByCategoryPaging(String category, int page, int pageSize){
        Pageable pageable = PageRequest.of(page, pageSize);

        return repo.findByCategoriesContaining(category, pageable);

    }

    public Page<Post> getAllByCategoriesPaging(List<String> categories, int page, int pageSize){
        Pageable pageable = PageRequest.of(page, pageSize);

        return repo.findByCategoriesIn(List.of(categories), pageable);

    }


    public Post setCategoriesToPost(Long id, List<String> newCategories) {
        Post existing = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Post with this identifier doesn't exist: " + id));

        existing.setCategories(newCategories);

        return repo.save(existing);
    }

    public Post addCategoryToPost(Long id, String category) {
        Post existing = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Post with this identifier doesn't exist: " + id));

        List<String> existingCategories = existing.getCategories();

        if(!existingCategories.contains(category))
            existingCategories.add(category);

        existing.setCategories(existingCategories);
        return repo.save(existing);

    }

    public Post removeCategoryFromPost(Long id, String category) {
        Post existing = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Post with this identifier doesn't exist: " + id));

        List<String> existingCategories = existing.getCategories();

        existingCategories.remove(category);

        existing.setCategories(existingCategories);
        return repo.save(existing);

    }
}
