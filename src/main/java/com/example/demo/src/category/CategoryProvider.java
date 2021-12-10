package com.example.demo.src.category;

import com.example.demo.config.BaseException;
import com.example.demo.src.category.model.GetCategoryRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
public class CategoryProvider {
    private final CategoryDao categoryDao;
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired //readme 참고
    public CategoryProvider(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public int checkCategory(String category) throws BaseException {
        try {
            return categoryDao.checkCategory(category);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    // User들의 정보를 조회
    public List<GetCategoryRes> getCategories() throws BaseException {
        try {
            List<GetCategoryRes> getCategoryRes = categoryDao.getCategories();
            return getCategoryRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //전체 categories 조회
    public List<GetCategoryRes> getCategoriesByCategoryIdx(int categoryIdx) throws BaseException {
        try {
            List<GetCategoryRes> getCategoriesRes = categoryDao.getCategoriesByCategoryIdx(categoryIdx);
            return getCategoriesRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
