package com.example.guestbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder // 빌더 패턴을 사용하여 객체를 생성할 수 있도록 함
@AllArgsConstructor // 모든 필드를 인자로 받는 생성자를 자동으로 생성함
@Data // Getter, Setter, toString, equals, hashCode 등의 메소드를 자동으로 생성함
public class PageRequestDTO {

    private int page;
    private int size;

    public PageRequestDTO() {
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page -1, size, sort);
    }
}
