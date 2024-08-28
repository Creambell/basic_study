package com.kh.spring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Board;

@Controller
public class HomeController {

    @Autowired
    private BoardService bService;

    @GetMapping("home.do")
    public String home(Model model) {
        Map<String, Integer> categoryMap = new HashMap<>();
        categoryMap.put("USER", 100);
        categoryMap.put("IMAGE", 101);
        categoryMap.put("SPRING", 102);
        categoryMap.put("SPRINGBOOT", 103);
        categoryMap.put("JAVA", 104);
        categoryMap.put("JAVASCRIPT", 105);
        categoryMap.put("ERROR", 106);
        categoryMap.put("BLOGGER", 107);

        for (Map.Entry<String, Integer> entry : categoryMap.entrySet()) {
            String category = entry.getKey();
            int cateNo = entry.getValue();
            List<Board> boardList = bService.selectBoardListByCategory(cateNo);
            model.addAttribute(category.toLowerCase() + "BoardList", boardList);
        }

        return "home";
    }
}
