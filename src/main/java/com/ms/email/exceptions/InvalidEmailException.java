package com.ms.email.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
public class InvalidEmailException extends Exception{

    private String email;

}
