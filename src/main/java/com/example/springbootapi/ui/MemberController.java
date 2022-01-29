package com.example.springbootapi.ui;

import com.example.springbootapi.model.Member;
import com.example.springbootapi.model.MemberCreateRequest;
import com.example.springbootapi.model.MemberUpdateREquest;
import com.example.springbootapi.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor /*필요한 생성자 자동 생성*/



public class MemberController {

    private MemberService memberservice;
    /**
     * 회원 생성 API
     * [POST] /api/members
     * @return
     */
    @PostMapping("/api/members")
    public ResponseEntity<Member> creatMember(@RequestBody MemberCreateRequest request){
        Member creatMember = memberservice.creatMember(request);
        return  new ResponseEntity<>(creatMember, HttpStatus.OK); // 200

    }




    /**
     * 특정 회원 조회 API
     * [GET] /api/members/{memberId}
     * @return
     */
         @GetMapping("/api/members/{memberId}")
    public  ResponseEntity<Member> findMember(@PathVariable Long memberId){
             Member findMember = memberservice.findMember(memberId);
             return  new ResponseEntity<>(findMember, HttpStatus.OK);
         }



    /**
     * 전체 회원 조회 API
     * [GET] /api/members
     * @return
     */
    @GetMapping("/api/members")
    public  ResponseEntity<List<Member>> findAllMember(){
        List<Member> allMember = memberservice.findAllMember();
        return new ResponseEntity<>(allMember, HttpStatus.OK);
    }



    /**
     * 회원 수정 API //put(덮어쓰기), patch(특정부분)
     * [PATCH] /api/members/{memberId}
     * @param memberId
     * @return
     */
    @PatchMapping("/api/members/{memberId}")
    public ResponseEntity<String> updateMember(
            @PathVariable Long memberId, @RequestBody MemberUpdateREquest request){
        memberservice.updateMember( memberId , request);
        return  new ResponseEntity<>( memberId +"번 회원의 이름이 정상적으로 수정되었습니다.", HttpStatus.OK);
    }



    /**
     * 특정 회원 삭제 API
     * [DELETE] /api/members/{memberId}
     * @param memberId
     * @return
     */
    @DeleteMapping("api/members/{memberId}")
    public  ResponseEntity<String> deletMember(@PathVariable Long memberId)
    {
        memberservice.deleteMember(memberId);
        return new ResponseEntity<>(memberId +"삭제되었습니다.", HttpStatus.OK);
    }



    /**
     * 전체 회원 삭제 API
     * [DELETE] /api/members
     * @param memberId
     * @return
     */
    @DeleteMapping("api/members/{memberId}")
    public  ResponseEntity<String> deleteAllMember(@PathVariable Long memberId)
    {
     memberservice.deleteAllMember(memberId);
     return new ResponseEntity<>( memberId +"모든회원이 삭제되었습니다.", HttpStatus.OK);
    }
}
