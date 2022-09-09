package org.dawan.formations.controllers.neutralized;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.dawan.formations.dtos.BilanDto;
import org.dawan.formations.services.BilanService;
import org.dawan.formations.tools.FreeMarkerTool;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("/api/Bilan")
public class BilanController{

    private final BilanService randomGeneratorService;

    @Value("${app.storagefolder}")
    private String storageFolder;

    @Autowired
    protected BilanController(BilanService randomGeneratorService, Configuration freemarkerConfig) {

        this.randomGeneratorService = randomGeneratorService;
    }

    @GetMapping("/Generate/{id}")
    public ResponseEntity<String> inscription(@PathVariable("id") long id)
    {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(randomGeneratorService.generate3To5EvalPerStudientPerBlocForPromo(id) + " Evalutations générées");
        } catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Something went wrong");
        }
    }

    @GetMapping(produces = "text/html")
    public String Bilan(
            @RequestParam long etudiantId,
            @RequestParam long promotionId) throws IOException, TemplateException {
        return FreeMarkerTool.getResult(this.getClass(),"Bilan.ftl", randomGeneratorService.GETgenerate(etudiantId,promotionId));
    }
}
