package com.mail.sender.dto;

import lombok.Data;

import java.util.List;

@Data
public class EmailDTO {

    private String rementete;
    private String destinatario;
    private String conteudo;

}
