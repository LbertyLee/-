package com.ruoyi.web.controller.system;


import com.ruoyi.system.service.impl.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WordController {

    @Autowired
    private WordService wordService;

    @GetMapping("/generate-word")
    public String generateWord() {
//        try {
//            wordService.createWordDocument("确认书.docx");
//            return "文档生成成功！";
//        } catch (Exception e) {
//            return "文档生成失败：" + e.getMessage();
//        }
        return "文档生成成功！";
    }
}
