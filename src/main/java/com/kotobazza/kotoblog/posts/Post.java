package com.kotobazza.kotoblog.posts;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
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
        if(!categories.contains(category)){
            categories.add(category);
        }
        updatePost();
    }

    public boolean deleteCategory(String category){
        boolean isRemoved = categories.remove(category);
        if(isRemoved){
            updatePost();
        }
        return isRemoved;
    }

    public void setInnerText(String innerText) {
        this.innerText = innerText;
        updatePost();
    }
}
