package com.example.demo.src.Chat.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor

public class GetRoomRes {
    private int chatIdx;
    private int userIdx;
    private String message;
}
