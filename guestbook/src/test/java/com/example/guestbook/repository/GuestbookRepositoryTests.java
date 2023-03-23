package com.example.guestbook.repository;

import com.example.guestbook.entity.Guestbook;
import com.example.guestbook.entity.QGuestbook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class GuestbookRepositoryTests {

    @Autowired
    private GuestbookRepository guestbookRepository;

    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1, 300).forEach(i -> {

            Guestbook guestbook = Guestbook.builder()
                    .title("Title...." + i)
                    .content("Content..." +i)
                    .writer("user" + (i % 10))
                    .build();
            System.out.println(guestbookRepository.save(guestbook));
        });
    }

    @Test
    public void updateTest() {
        // guestbookRepository 객체에서 id 값이 300인 Guestbook 엔티티를 찾는다
        Optional<Guestbook> result = guestbookRepository.findById(300L);
        // Optional 클래스는 값이 있을 수도 있고 없을 수도 있는 객체를 다룰 때 사용
        // findById 메소드는 해당 id 값의 엔티티를 찾을 경우, Optional에 해당 엔티티를 감싸서 반환하고,
        // 그렇지 않을 경우 Optional 객체 자체를 반환한다.

        //result 객체에 값이 존재하는지 체크
        if (result.isPresent()) {
            // Optional 객체에서 값을 추출하고, Guestbook 객체 guestbook 변수에 할당
            Guestbook guestbook = result.get();

            // Guestbook 객체의 changeTitle 메소드를 호출하여, 제목을 변경
            guestbook.changeTitle("Changed Title....");

            // Guestbook 객체의 changeContent 메소드를 호출하여, 내용을 변경
            guestbook.changeContent("Changed Content...");

            // 변경된 Guestbook 엔티티를 guestbookRepository에 저장
            guestbookRepository.save(guestbook);
        }
    }

    @Test
    public void testQuery1() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        QGuestbook qGuestbook = QGuestbook.guestbook;

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression expression = qGuestbook.title.contains(keyword);

        builder.and(expression);

        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);

        result.stream().forEach(guestbook -> {
            System.out.println(guestbook);
        });
    }

    @Test
    public void testQuery2() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        QGuestbook qGuestbook = QGuestbook.guestbook;
        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression exTitle = qGuestbook.title.contains(keyword);

        BooleanExpression exContent = qGuestbook.content.contains(keyword);

        BooleanExpression exAll = exTitle.or((exContent));

        builder.and(exAll);

        builder.and(qGuestbook.gno.gt(0L));

        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);

        result.stream().forEach(guestbook -> {
            System.out.println(guestbook);
        });
    }
}
