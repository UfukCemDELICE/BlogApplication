package com.ufukcemdelice.data.repository;
import com.ufukcemdelice.data.entity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IBlogRepository extends JpaRepository<BlogEntity,Long> {

    //Kendi Sorgumuzu yazdık
    BlogEntity findByEmail(String email);
}