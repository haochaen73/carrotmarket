package com.example.demo.src.category;

import com.example.demo.config.BaseException;
import com.example.demo.src.category.model.*;
import com.example.demo.src.category.model.PostCategoryRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;
import static com.example.demo.config.BaseResponseStatus.POST_CATEGORYS_EXISTS_CATEGORY;


@Service
public class CategoryService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final CategoryDao categoryDao;
    private final CategoryProvider categoryProvider;

    public CategoryService(CategoryDao categoryDao, CategoryProvider categoryProvider){
        this.categoryDao = categoryDao;
        this.categoryProvider = categoryProvider;
    }

    //카테고리 추가
    public PostCategoryRes createCategory(PostCategoryReq postCategoryReq) throws BaseException {
        if (categoryProvider.checkCategory(postCategoryReq.getCategory()) == 1) {
            throw new BaseException(POST_CATEGORYS_EXISTS_CATEGORY);
        }
        try {
            int categoryIdx = categoryDao.createCategory(postCategoryReq);
            return new PostCategoryRes(categoryIdx);
        } catch (Exception exception) { // DB에 이상이 있는 경우 에러 메시지를 보냅니다.
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
