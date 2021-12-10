package com.example.demo.src.viewcount;

import com.example.demo.config.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class ViewcountProvider {
    // *********************** 동작에 있어 필요한 요소들을 불러옵니다. *************************
    private final ViewcountDao viewcountDao;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired //readme 참고
    public ViewcountProvider(ViewcountDao viewcountDao) {
        this.viewcountDao = viewcountDao;
    }
    public int checkUserIdx(int userIdx) throws BaseException {
        try {
            return viewcountDao.checkUserIdx(userIdx);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
