package com.spring.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {

    private String subject;
    private String recipient;
    private String sender ="Hospital Support <support@hospital.com>";
    private String body;
    private String URL="";

}
