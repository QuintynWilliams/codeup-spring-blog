package com.codeup.codeupspringblog.models;

import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String body;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    public Post() {}
    public Post(User user, String title, String body) {
        this.user = user;
        this.title = title;
        this.body = body;
    }
}