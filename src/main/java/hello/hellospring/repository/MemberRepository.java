package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

/*저장소 역할*/
public interface MemberRepository {
    Member save(Member member); //회원이 저장소에 저장 됌
    Optional<Member> findById(Long id); //찾아오기
    Optional<Member> findByName(String name); //찾아오기
    List<Member> findAll(); //찾아온 모든 회원 반환

}
