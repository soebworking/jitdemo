package com.jitdemo.jitdemo.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class UserNotFoundException extends RuntimeException{
    private String message;
}
