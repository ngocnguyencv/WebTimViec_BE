package com.casemd6_be.model.dto;

import lombok.Data;

@Data
public class UpImage {
    private String url;

    public UpImage(String url) {
        this.url = url;
    }
}
