package com.kotobazza.kotoblog.posts;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    private String title;

    public Post(String title, String text){
        this.title = title;
        this.innerText = text;
        this.createdAt = LocalDateTime.now();
    }


    @Getter
    @ElementCollection
    @CollectionTable(name = "categories", joinColumns = @JoinColumn(name = "id"))
    private List<String> categories = new ArrayList<>();
    @Column(name="inner_text")
    @Getter
    private String innerText;

    @Column(name="created_at")
    @Getter
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    @Getter
    private LocalDateTime updatedAt;

    private void updatePost(){
        updatedAt = LocalDateTime.now();
    }

    public void setTitle(String title) {
        this.title = title;
        updatePost();
    }

    public void addCategory(String category){
        if(!categories.contains(category.toLowerCase())){
            categories.add(category.toLowerCase());
        }
        updatePost();
    }

    public boolean deleteCategory(String category){
        boolean isRemoved = categories.remove(category.toLowerCase());
        if(isRemoved){
            updatePost();
        }
        return isRemoved;
    }

    public void setCategories(List<String> categories){
        this.categories = categories.stream().map(String::toLowerCase).toList();
        updatePost();
    }


    public void setInnerText(String innerText) {
        this.innerText = innerText;
        updatePost();
    }
}
