package com.example.demo.src.Chat.model;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostRoomReq {
    private int userIdx;
    private String message;
}
