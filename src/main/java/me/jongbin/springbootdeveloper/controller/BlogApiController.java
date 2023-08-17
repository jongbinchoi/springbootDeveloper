package me.jongbin.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.jongbin.springbootdeveloper.domain.Article;
import me.jongbin.springbootdeveloper.dto.AddArticleRequest;
import me.jongbin.springbootdeveloper.dto.ArticleResponse;
import me.jongbin.springbootdeveloper.dto.UpdateArticleRequest;
import me.jongbin.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController //HTTP Response Boby에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
public class BlogApiController {

    private final BlogService blogService;

    //HTTP 메서드가 POST일 때 전달받은 URL과 동일하면 메서드로 매핑
    @PostMapping("/api/articles")
    // @RequestBody로 요청 본문 값 매핑
    public  ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
        Article savedArticle =blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllarticles(){
        List<ArticleResponse> artcles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(artcles);
    }

    @GetMapping("/api/articles/{id}")
    //URL 경로에서 값 추출
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id){
        Article artile =blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(artile));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleterArticle(@PathVariable long id){
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id,
                                                 @RequestBody UpdateArticleRequest request){
        Article updatedArticle =blogService.update(id,request);

        return ResponseEntity.ok().body(updatedArticle);

    }

}