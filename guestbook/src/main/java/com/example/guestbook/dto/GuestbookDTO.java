package com.example.guestbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder // 빌더 패턴을 사용하여 객체를 생성할 수 있도록 함
@NoArgsConstructor // 인자가 없는 생성자를 자동으로 생성함
@AllArgsConstructor // 모든 필드를 인자로 받는 생성자를 자동으로 생성함
@Data // Getter, Setter, toString, equals, hashCode 등의 메소드를 자동으로 생성함
public class GuestbookDTO {

    private Long gno; //게시글 번호
    private String title; //제목
    private String content; //내용
    private String writer; //작성자
    private LocalDateTime regDate, modDate; //작성일, 수정일
}
