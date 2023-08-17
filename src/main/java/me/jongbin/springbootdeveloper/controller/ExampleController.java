package me.jongbin.springbootdeveloper.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ExampleController {

    @GetMapping("/thymeleaf/example")
    public String thymeleafExample(Model model) {
        Person examplePerson = new Person();
        examplePerson.setId(1L);
        examplePerson.setName("홍동");
        examplePerson.setAge(11);
        examplePerson.setHobbies(List.of("운동", "독서"));

        model.addAttribute("person", examplePerson);
        model.addAttribute("today", LocalDate.now());

        return "example"; // example.html라는 뷰 조회 하라는 것
    }

    @Setter
    @Getter
    class Person {
        private Long id;
        private String name;
        private int age;
        private List<String> hobbies;
    }
}
//model 객체는 뷰, 따로 객체생성 할 필요없음 코드 처럼 인자로 선언만하면됨.
//addAttribute 메서드로 모델에 값을 저장
//@Controller 보고, 반환하는 값의 이름을 가진 뷰의 파일을 찾아라는 뜻 : return "example"