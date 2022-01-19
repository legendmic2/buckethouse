package com.clone.buckethouse.persistence;


import com.clone.buckethouse.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity,String> {

        UserEntity findByEmail(String email);
        UserEntity findByEmailAndPassword(String email, String password);
        boolean existsByEmail(String email);

}
