package com.example.demo.src.Chat.model;
import lombok.*;

@Getter
@Setter

@AllArgsConstructor
public class Chat {
    private int chatIdx;
    private int userIdx;
    private String message;
}
