package com.unifasservice.dto.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataMailResponse {

        private String to;

        private String subject;

        private Map<String, Object> props;
}