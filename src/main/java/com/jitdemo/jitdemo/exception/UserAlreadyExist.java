package com.jitdemo.jitdemo.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class UserAlreadyExist extends RuntimeException{
    private String message;
}
