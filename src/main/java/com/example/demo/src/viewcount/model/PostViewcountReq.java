package com.example.demo.src.viewcount.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostViewcountReq {
    private int userIdx;
    private int productIdx;
}
