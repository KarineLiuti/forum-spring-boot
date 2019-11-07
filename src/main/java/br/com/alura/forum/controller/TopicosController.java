package br.com.alura.forum.controller;

import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;


@Controller
public class TopicosController {

    @ResponseBody
    @RequestMapping("/topicos")
    public List<TopicosDto> lista() {
        Topico topicos = new Topico("Dúvida","Dúvida com spring DTO", new Curso("Spring Boot", "Programação"));

        return TopicosDto.converter(Arrays.asList(topicos));
    }
}
