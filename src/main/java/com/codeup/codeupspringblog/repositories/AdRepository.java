package com.codeup.codeupspringblog.repositories;

import com.codeup.codeupspringblog.models.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRepository extends JpaRepository<Ad, Long> {

//    AdRepository is empty because we don't need to write any code to make it work.
//    JpaRepository provides all the functionality we need to work with the ads table.

}