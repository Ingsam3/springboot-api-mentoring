package com.example.springbootapi.model;

import lombok.*;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "member") /*테이블 생성*/
@Builder

public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private  String name;

    private  String email;

    private  String number;

    //MembercreateRequest => Member로 변환해주는 과정이 필요
    public static Member toEntity(MemberCreateRequest request) {
        return  Member.builder()
                .name(request.getName())
                .email(request.getEmail())
                .number(request.getNumber())
                .build();
    }

    public  void  updateName(String name){
        this.name = name;
    }
}
