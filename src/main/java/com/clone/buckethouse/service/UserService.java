package com.clone.buckethouse.service;

import com.clone.buckethouse.model.UserEntity;
import com.clone.buckethouse.persistence.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Service
public class UserService {
    //Controller에서 Service를 이용하여 User를 만들게 됨.

    @Autowired
    private UserRepository repository;

    public UserEntity create(final UserEntity userEntity){
        if(userEntity==null || userEntity.getEmail()==null){
            //Controller에서 넘어온 Entity객체가 null 혹은 그 안의 필드가 null
            throw new RuntimeException("Please fill in the blanks");
        }

        final String email = userEntity.getEmail();

        if(repository.existsByEmail(email)){
            log.warn("Email already exists {}", email);
            throw new RuntimeException("Email already exists");
        }


        return repository.save(userEntity);
    }

    //email과 password 를 입력시 해당 데이터를 찾는 메서드
    public UserEntity getByCredentials(final String email, final String password){
        return repository.findByEmailAndPassword(email,password);
    }

    /*
    나중에 User 삭제(탈퇴) 구현 해야 함.
     */
}
