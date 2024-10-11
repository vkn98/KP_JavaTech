package com.example.Kursova.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.Kursova.entity.SolidWorksModel;
import com.example.Kursova.service.SolidWorksModelService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class SolidWorksModelController {

    private final SolidWorksModelService service;

    public SolidWorksModelController(SolidWorksModelService service) {
        this.service = service;
    }

    @GetMapping("/models")
    public String showModels(Model model) {
        List<SolidWorksModel> models = service.getAllModels();
        model.addAttribute("models", models);
        return "index"; 
    }

    @GetMapping("/upload")
    public String showUploadPage() {
        return "upload"; 
    }

    @PostMapping("/upload")
    public String uploadModel(@RequestParam("file") MultipartFile file,
                              @RequestParam(value = "description", required = false) String description,
                              Model model) throws IOException {
        if (file.isEmpty()) {
            model.addAttribute("errorMessage", "Please select a file to upload.");
            return "upload";
        }

        String fileName = file.getOriginalFilename();

        if (service.getModelByName(fileName).isPresent()) {
            model.addAttribute("errorMessage", "A model with this name already exists.");
            return "upload";
        }

        SolidWorksModel solidWorksModel = new SolidWorksModel(fileName, file.getBytes(), description);
        service.saveModel(solidWorksModel);
        model.addAttribute("successMessage", "Model uploaded successfully!");

        return "redirect:/models"; 
    }

    @PostMapping("/delete/{id}")
    public String deleteModel(@PathVariable Long id, Model model) {
        service.deleteModel(id);
        model.addAttribute("successMessage", "Model deleted successfully!");
        return "redirect:/models"; 
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadModel(@PathVariable Long id) {
        Optional<SolidWorksModel> optionalModel = service.getModelById(id);

        if (optionalModel.isPresent()) {
            SolidWorksModel model = optionalModel.get();

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + model.getModelName() + "\"");

            return new ResponseEntity<>(model.getModelData(), headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}