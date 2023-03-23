package com.example.guestbook.service;

import com.example.guestbook.dto.GuestbookDTO;
import com.example.guestbook.dto.PageRequestDTO;
import com.example.guestbook.dto.PageResultDTO;
import com.example.guestbook.entity.Guestbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GuestbookServiceTests {

    @Autowired
    private GuestbookService service;

    @Test
    public void testRegister() { // 실행시 새로운 멤버를 추가합니다, 301번째 멤버를 추가했습니다
        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                .title("Sample Title...")
                .content("Sample Content...")
                .writer("user0")
                .build();

        System.out.println(service.register(guestbookDTO));
    }

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();

        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);

        for (GuestbookDTO guestbookDTO : resultDTO.getDtoList()) {
            System.out.println(guestbookDTO);

            System.out.println("PREV :" +resultDTO.isPrev());
            System.out.println("NEXT :" +resultDTO.isNext());
            System.out.println("TOTAL :" +resultDTO.getTotalPage());
            System.out.println("-------------------------------------");
            for (GuestbookDTO guestbookDTO1 : resultDTO.getDtoList()) {
                System.out.println(guestbookDTO);
            }

            System.out.println("======================================");
            resultDTO.getPageList().forEach(i -> System.out.println(i));
        }
    }
}
