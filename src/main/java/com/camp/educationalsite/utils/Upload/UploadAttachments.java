package com.camp.educationalsite.utils.Upload;

import java.util.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadAttachments {

    private final RestTemplate restTemplate;

    public UploadAttachments(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public ObjectNode addHallTicket(String fileName, MultipartFile file) {
        try {
            String url = "https://upload.imagekit.io/api/v1/files/upload";
        
        String originalInput = "private_vT8t/BnF0nY1DKAEyHdlh1GmTd8=:";
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("Authorization","Basic "+encodedString);

        byte[] fileContents = file.getBytes();

        ByteArrayResource contentsAsResource = new ByteArrayResource(fileContents) {
            @Override
            public String getFilename() {
                return fileName; 
            }
        };

        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("file", contentsAsResource);
        form.add("fileName",fileName);
        form.add("folder", "/project/");

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(form, headers);
        
        return new ObjectMapper().readValue(this.restTemplate.postForObject(url, entity, String.class), ObjectNode.class);
        } catch (Exception e) {
            return new ObjectMapper().createObjectNode().put("error",e.getMessage());
        }
    }
    
}
