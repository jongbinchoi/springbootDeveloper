package me.jongbin.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jongbin.springbootdeveloper.domain.Article;

@NoArgsConstructor //기본생성자 추가
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자 추가
@Getter
public class AddArticleRequest {

    private String title;
    private String content;

    public Article toEntity(){ //생성자를 사용해 객체 생성, DTO를 엔티티로 만듦
        return Article.builder() //빌더 패턴사용,가독성 증가
                .title(title)
                .content(content)
                .build();
    }
}
