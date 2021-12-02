package com.example.SpringBootPlaygound.honux_class;

import com.example.SpringBootPlaygound.honux_class.Food;
import com.example.SpringBootPlaygound.honux_class.Github;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;

import java.util.HashSet;
import java.util.Set;

public class User {
    @Id
    private Long id;

    private String email;
    private String name;

    @Embedded.Nullable // 임베디드고 널을 허용해주겠다
    private Github github;// 1:1 관계일 때에는 이렇게 끼워넣고 끝

    private Set<Food> foods = new HashSet<>();

    public void addFood(Food food) {
        foods.add(food);
    }

    public void addMultiFood(Food... foods) {
        for (Food f : foods) {
            this.foods.add(f);
        }//위대한 호루스 호눅스께서는 가변인자를 자랑하셨고 포문으로 넣으셨음
    }// Collection.addAll 로 대체 가능하다고 함. 메뉴얼로 푸드를 카피해넣는게 싫으시다는거지

    public Set<Food> getFoods() {
        return foods;
    }

    public User(String email, String name, Github github) {
        this.email = email;
        this.name = name;
        this.github = github;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Github getGithub() {
        return github;
    }

    public void setGithub(Github github) {
        this.github = github;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", github=" + github +
                '}';
    }
}
