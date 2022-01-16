package com.clone.buckethouse.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    //사용자 = User DTO
    private String id;          //ex)나중에 Review에서 표시되는 userId가 UserDTO의 id
    private String username;    //User의 이름
    private String email;       //User의 email  -> 실제적인 사용자 Id
    private String password;    //email의 password

}
