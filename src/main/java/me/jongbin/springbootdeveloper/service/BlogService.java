package me.jongbin.springbootdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.jongbin.springbootdeveloper.domain.Article;
import me.jongbin.springbootdeveloper.dto.AddArticleRequest;
import me.jongbin.springbootdeveloper.dto.UpdateArticleRequest;
import me.jongbin.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor //final이 붙거나 @NotNull이 붙은 필드의 생성저 추가
@Service //빈으로 등록
public class BlogService {
    private final BlogRepository blogRepository;

    //블로그 글 추가 메서드
    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll(){
        return blogRepository.findAll();//findall 테이블에 저장된 모든데이터조회\
    }

    public Article findById(long id){
        return blogRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found: "+id));
    }

    public void delete(long id){
        blogRepository.deleteById(id);
    }

    @Transactional //트랜잭션 메서드
    public Article update(long id, UpdateArticleRequest request){
        Article article =blogRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found: "+id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
