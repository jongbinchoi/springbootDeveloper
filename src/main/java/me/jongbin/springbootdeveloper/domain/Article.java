package me.jongbin.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//롬복 : 코드 반복해 입력할 필요 없음
@Entity //엔티티로지정
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED) //별도 코드없이 protected 기본생성자 생성
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키를 자동으로 1씩증가
    @Column(name="id", updatable=false)
    private Long id;

    @Column(name = "title", nullable = false) //'title'이라는 not null 컬럼과 매핑
    private  String title;

    @Column(name = "content", nullable=false)
    private String content;

    @Builder //빌더 패턴으로 객체 생성
    public Article(String title, String content){
        this.title=title;
        this.content=content;
    }

    public void update(String title, String content){
        this.title=title;
        this.content=content;
    }

}

