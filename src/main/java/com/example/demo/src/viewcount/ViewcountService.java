package com.example.demo.src.viewcount;

import com.example.demo.config.BaseException;
import com.example.demo.src.viewcount.ViewcountProvider;
import com.example.demo.src.viewcount.ViewcountDao;
import com.example.demo.src.viewcount.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;
import static com.example.demo.config.BaseResponseStatus.POST_VIEWCOUNTS_EXISTS_USERIDX;

@Service
public class ViewcountService {
    final Logger logger = LoggerFactory.getLogger(this.getClass()); // Log 처리부분: Log를 기록하기 위해 필요한 함수입니다.

    // *********************** 동작에 있어 필요한 요소들을 불러옵니다. *************************
    private final ViewcountDao viewcountDao;
    private final ViewcountProvider viewcountProvider;

    @Autowired //readme 참고
    public ViewcountService(ViewcountDao viewcountDao, ViewcountProvider viewcountProvider) {
        this.viewcountDao = viewcountDao;
        this.viewcountProvider = viewcountProvider;
    }

    public PostViewcountRes createViewcount(PostViewcountReq postViewcountReq) throws BaseException {
        if (viewcountProvider.checkUserIdx(postViewcountReq.getUserIdx()) == 1) {
            throw new BaseException(POST_VIEWCOUNTS_EXISTS_USERIDX);
        }
        try {
            int viewcountIdx = viewcountDao.createViewcount(postViewcountReq);
            return new PostViewcountRes(viewcountIdx);
        } catch (Exception exception) { // DB에 이상이 있는 경우 에러 메시지를 보냅니다.
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
