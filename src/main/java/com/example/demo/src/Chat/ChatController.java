package com.example.demo.src.Chat;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import com.example.demo.config.BaseException;
 import com.example.demo.config.BaseResponse;
 import com.example.demo.src.user.model.*;
 import com.example.demo.utils.JwtService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.web.bind.annotation.*;

 import javax.transaction.Transactional;
 import java.util.List;
 import static com.example.demo.config.BaseResponseStatus.*;
 import static com.example.demo.utils.ValidationRegex.isRegexPhoneNumber;
 */
@RestController
@RequestMapping("/app/chats")
public class ChatController {
    /**
     @Autowired  // 객체 생성을 스프링에서 자동으로 생성해주는 역할. 주입하려 하는 객체의 타입이 일치하는 객체를 자동으로 주입한다.
     // IoC(Inversion of Control, 제어의 역전) / DI(Dependency Injection, 의존관계 주입)에 대한 공부하시면, 더 깊이 있게 Spring에 대한 공부를 하실 수 있을 겁니다!(일단은 모르고 넘어가셔도 무방합니다.)
     // IoC 간단설명,  메소드나 객체의 호출작업을 개발자가 결정하는 것이 아니라, 외부에서 결정되는 것을 의미
     // DI 간단설명, 객체를 직접 생성하는 게 아니라 외부에서 생성한 후 주입 시켜주는 방식
     private final UserProvider userProvider;
     @Autowired
     private final UserService userService;

     public UserController(UserProvider userProvider, UserService userService, JwtService jwtService) {
     this.userProvider = userProvider;
     this.userService = userService;
     this.jwtService = jwtService; // JWT부분은 7주차에 다루므로 모르셔도 됩니다!
     }

     //chat room 생성 API
     @ResponseBody
     @PostMapping("/room")    // POST 방식의 요청을 매핑하기 위한 어노테이션
     @Transactional
     public BaseResponse<PostChatRes> createChat(@RequestBody PostChatReq postChatReq) {
     //  @RequestBody란, 클라이언트가 전송하는 HTTP Request Body(우리는 JSON으로 통신하니, 이 경우 body는 JSON)를 자바 객체로 매핑시켜주는 어노테이션
     // TODO: email 관련한 짧은 validation 예시입니다. 그 외 더 부가적으로 추가해주세요!
     // email에 값이 존재하는지, 빈 값으로 요청하지는 않았는지 검사합니다. 빈값으로 요청했다면 에러 메시지를 보냅니다.
     try {
     PostRoomRes postRoomRes = userService.createChat(postRoomReq);
     return new BaseResponse<>(postRoomRes);
     } catch (BaseException exception) {
     return new BaseResponse<>((exception.getStatus()));
     }
     }
     */
}
