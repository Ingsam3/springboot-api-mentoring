package com.example.springbootapi.service;

import com.example.springbootapi.model.Member;
import com.example.springbootapi.model.MemberCreateRequest;
import com.example.springbootapi.model.MemberUpdateREquest;
import com.example.springbootapi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service

public class MemberService {
    private  final MemberRepository memberRepository; //레포지토리에 생성하라고 명령함 (받음)
    //MembercreateRequest => Member로 변환해주는 과정이 필요

    public Member creatMember(MemberCreateRequest request) {
        Member member = Member.toEntity(request);
        return  memberRepository.save(member);


    }

    public Member findMember(Long memberId) {
        return  memberRepository.findById(memberId).get();
    }

    public List<Member> findAllMember() {
        List<Member> allMember = memberRepository.findAll();
        return  allMember;
    }

    public void updateMember(Long memberId, MemberUpdateREquest request) {
        //1. 회원을 찾는다

        Member member = memberRepository.findById(memberId).get();


        //2. 찾은 회원의 이름변경
        member.updateName(request.getName());

        //3. 변경된 내역을 데이터 베이스에 저장한다.
        //JPA에서 SAVE() 함수는 데이터가 존재하면 업데이트, 아니면 삽입
        memberRepository.save(member);
    }


    public void deleteMember(Long memberId) {
        //1. 회원을 찾는다.
        Member member = memberRepository.findById(memberId).get();
        //2. 찾은 회원을 삭제한다.
        memberRepository.delete(member);
    }

    public void deleteAllMember(Long memberId) {
        memberRepository.deleteAll();
    }
}
