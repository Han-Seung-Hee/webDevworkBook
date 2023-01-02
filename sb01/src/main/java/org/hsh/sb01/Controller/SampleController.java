package org.hsh.sb01.Controller;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@Log4j2
public class SampleController {
    @GetMapping("/hello")
    public void hello(Model model){
        log.info("helooo1oooooo");

        model.addAttribute("msg","Hello, World!");
    }

    @GetMapping("/helloarr")
    public String[] helloArr(){
        log.info("Hello example using json");

        return new String[]{"ARRR","ORRRR"};
    }

    @GetMapping("/ex/ex1")
    public void ex1(Model model){
        List<String> ls = Arrays.asList("AAA","BBB","CCC","DDD");

        model.addAttribute("ls",ls);
    }

    class SampleDTO{
        private String p1,p2,p3;


        public String getP1() {
            return p1;
        }

        public String getP2() {
            return p2;
        }

        public String getP3() {
            return p3;
        }
    }

    @GetMapping("/ex/ex2")
    public void ex2(Model model){
        log.info("ThymeLeaf TEST..............");

        List<String> strls = IntStream.range(1,10)
                .mapToObj(i->"data"+i)
                .collect(Collectors.toList());

        model.addAttribute("ls" , strls);

        Map<String, String> map = new HashMap<>();
        map.put("A","AAA");
        map.put("B","BBB");

        model.addAttribute("map",map);

        SampleDTO sampleDTO = new SampleDTO();
        sampleDTO.p1 ="Value -- p1";
        sampleDTO.p2 ="Value -- p2";
        sampleDTO.p3 ="Value -- p3";

        model.addAttribute("dto",sampleDTO);
    }

    @GetMapping("/ex/ex3")
    public void ex3(Model model){
        model.addAttribute("arr", new String[]{"AAA","BBB","CCC"});
    }
}