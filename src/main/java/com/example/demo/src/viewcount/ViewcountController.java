package com.example.demo.src.viewcount;
import com.example.demo.src.viewcount.ViewcountProvider;
import com.example.demo.src.viewcount.ViewcountService;
import com.example.demo.src.viewcount.model.PostViewcountReq;
import com.example.demo.src.viewcount.model.PostViewcountRes;
import com.example.demo.src.viewcount.model.Viewcount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import static com.example.demo.config.BaseResponseStatus.POST_VIEWCOUNTS_EMPTY_USDERIDX;

@RestController
@RequestMapping("app/viewcounts")

public class ViewcountController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private final ViewcountProvider viewcountProvider;
    @Autowired
    private final ViewcountService viewcountService;
    @Autowired

    public ViewcountController(ViewcountProvider viewcountProvider, ViewcountService viewcountService) {
        this.viewcountProvider = viewcountProvider;
        this.viewcountService = viewcountService;
    }

    @ResponseBody
    @PostMapping("/view")
    public BaseResponse<PostViewcountRes> creatViewcount(@RequestBody PostViewcountReq postViewcountReq) {
        //  @RequestBody란, 클라이언트가 전송하는 HTTP Request Body(우리는 JSON으로 통신하니, 이 경우 body는 JSON)를 자바 객체로 매핑시켜주는 어노테이션
        // TODO: email 관련한 짧은 validation 예시입니다. 그 외 더 부가적으로 추가해주세요!
        // email에 값이 존재하는지, 빈 값으로 요청하지는 않았는지 검사합니다. 빈값으로 요청했다면 에러 메시지를 보냅니다.
        if (postViewcountReq.getUserIdx() == 0) {
            return new BaseResponse<>(POST_VIEWCOUNTS_EMPTY_USDERIDX);
        }
        try {
            PostViewcountRes postViewcountRes = viewcountService.createViewcount(postViewcountReq);
            return new BaseResponse<>(postViewcountRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
