package com.example.ex2.entity;

// lombok 라이브러리의 ToString 아노테이션을 사용하기 위해 선언
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
//JPA를 사용하기 위해 필요한 어노테이션을 선언
import javax.persistence.*;


@Entity // Memo 클래스를 JPA의 Entity로 등록
@Table(name = "tbl_memo") // Table 어노테이션을 사용하여 Memo 클래스가 DB에서 사용될 테이블을 지정
@ToString // lombok 라이브러리의 ToString 애노테이션을 사용하여 객체를 문자열로 표현
@Getter
@Builder
@AllArgsConstructor
public class Memo {

    @Id // Id 어노테이션을 사용하여 mno 필드를 Primary Key로 지정합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // GeneratedValue 어노테이션을 사용하여 mno 필드를 DB에서 자동 생성되는 값으로 지정
    private Long mno;

    // Column 어노테이션을 사용하여 memoText 필드가 DB에서 사용될 컬럼임을 지정
    // length 속성을 사용하여 memoText 필드의 최대 길이를 200으로 지정
    // nullable 속성을 사용하여 memoText 필드에 null 값을 허용하지 않도록 지정합
    @Column(length = 200, nullable = false)
    private String memoText;

    // 기본 생성자
    public Memo() {

    }

}
