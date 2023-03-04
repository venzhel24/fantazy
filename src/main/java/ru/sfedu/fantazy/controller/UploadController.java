package ru.sfedu.fantazy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.sfedu.fantazy.service.UploadService;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin")
public class UploadController {
    private final UploadService uploadService;

    @GetMapping("/upload")
    public String index() {
        return "uploadForm";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload");
            return "uploadForm";
        }

        try {
            if(uploadService.uploadRace(file.getBytes()))
                model.addAttribute("message", "Successfully uploaded '" + file.getOriginalFilename() + "'");
            else {
                model.addAttribute("message","File upload '" + file.getOriginalFilename() + "' FAILED");
            }
        } catch (IOException e) {
            model.addAttribute("message","bad file");
            log.error("FileUpload Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
        }

        return "uploadForm";
    }
}
