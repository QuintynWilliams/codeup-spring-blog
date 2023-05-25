package com.codeup.codeupspringblog.models;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "spring_ads")
public class Ad {
//           ^ By default JPA will call table by class name .˙. @table(name = "some_name")
//  Entities are the objects we use to represent the data we store in our database.
//  Entity requires annotations to tell JPA how to map the object to the database.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 350)
    private String description;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Ad() {}
    public Ad(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

}