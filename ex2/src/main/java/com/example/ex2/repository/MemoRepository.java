package com.example.ex2.repository;

import com.example.ex2.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
    Spring Data JPA는 JpaRepository 인터페이스를 제공
    이 인터페이스는 CRUD(Create, Read, Update, Delete) 기능을 지원하는 메소드들을 제공
    여기에서 제네릭으로 엔티티 클래스와 ID 타입을 받음
    MemoRepository는 Memo 엔티티 클래스를 데이터베이스에서 가져오고, 저장하고, 삭제하는 등의 작업을 수행할 수 있다
 */
public interface MemoRepository extends JpaRepository<Memo, Long> {

    List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);
    /*
    findBy: 조회 대상 필드를 지정, 여기서는 mno 필드를 조회 대상 필드로 지정.
    MnoBetween: 조회할 mno 필드의 범위를 지정, from과 to 사이의 mno 필드를 조회 대상으로 지정.
    OrderByMnoDesc: 조회 결과를 mno 필드값의 역순으로 정렬, Desc는 내림차순을 의미.
    Memo 엔티티의 mno 필드값이 from과 to 사이에 해당하는 Memo 엔티티 리스트를
    mno 필드값의 역순으로 정렬하여 반환하는 메서드.
     */

    Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageable);
    /*
    Memo 엔티티의 mno 필드값이 from과 to 사이에 해당하는 Memo 엔티티 리스트를
    pageable에 설정된 페이징 정보에 따라 나누어 반환하는 메서드
     */

    void deleteMemoByMnoLessThan(Long num);
    /*
    Memo 엔티티의 mno 필드값이 num보다 작은 Memo 엔티티를 삭제하는 메서드
    delete: 조회 대상 필드를 지정, 여기서는 Memo 엔티티를 삭제하는 메서드를 선언.
    MemoBy: 삭제 대상 필드를 지정, 여기서는 mno 필드를 삭제 대상 필드로 지정.
    MnoLessThan: 삭제할 mno 필드의 범위를 지정, num보다 작은 mno 필드를 삭제 대상으로 지정.
    */
}
