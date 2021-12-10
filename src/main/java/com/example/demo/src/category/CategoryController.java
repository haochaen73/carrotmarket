package com.example.demo.src.category;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.category.model.PostCategoryReq;
import com.example.demo.src.category.model.PostCategoryRes;
import com.example.demo.src.category.model.GetCategoryRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/app/categories")
public class CategoryController {
    @Autowired
    private final CategoryProvider categoryProvider;
    @Autowired
    private final CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryProvider categoryProvider, CategoryService categoryService) {
        this.categoryProvider = categoryProvider;
        this.categoryService = categoryService;
    }

    @ResponseBody
    @PostMapping("/add")
    public BaseResponse<PostCategoryRes> createCategory(@RequestBody PostCategoryReq postCategoryReq) {
        //  @RequestBody란, 클라이언트가 전송하는 HTTP Request Body(우리는 JSON으로 통신하니, 이 경우 body는 JSON)를 자바 객체로 매핑시켜주는 어노테이션
        // TODO: email 관련한 짧은 validation 예시입니다. 그 외 더 부가적으로 추가해주세요!
        // email에 값이 존재하는지, 빈 값으로 요청하지는 않았는지 검사합니다. 빈값으로 요청했다면 에러 메시지를 보냅니다.
        if (postCategoryReq.getCategory() == null){
            return new BaseResponse<>(POST_CATEGORYS_EMPTY_CATEGORY);
        }
        try {
            PostCategoryRes postCategoryRes = categoryService.createCategory(postCategoryReq);
            return new BaseResponse<>(postCategoryRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 모든 회원들의  조회 API
     * [GET] /users
     *
     * 또는
     *
     * 해당 닉네임을 같는 유저들의 정보 조회 API
     * [GET] /users? NickName=
     */
    //Query String
    @ResponseBody   // return되는 자바 객체를 JSON으로 바꿔서 HTTP body에 담는 어노테이션.
    //  JSON은 HTTP 통신 시, 데이터를 주고받을 때 많이 쓰이는 데이터 포맷.
    @GetMapping("") // (GET) 127.0.0.1:9000/app/users
    // GET 방식의 요청을 매핑하기 위한 어노테이션
    public BaseResponse<List<GetCategoryRes>> getCategories(@RequestParam(required = false) int categoryIdx) {
        //  @RequestParam은, 1개의 HTTP Request 파라미터를 받을 수 있는 어노테이션(?뒤의 값). default로 RequestParam은 반드시 값이 존재해야 하도록 설정되어 있지만, (전송 안되면 400 Error 유발)
        //  지금 예시와 같이 required 설정으로 필수 값에서 제외 시킬 수 있음
        //  defaultValue를 통해, 기본값(파라미터가 없는 경우, 해당 파라미터의 기본값 설정)을 지정할 수 있음
        try {
            if (categoryIdx == 0) { // query strig인 nickname이 n없을 경우, 그냥 전체 유저정보를 불러온다.
                List<GetCategoryRes> getCategoriesRes = categoryProvider.getCategories();
                return new BaseResponse<>(getCategoriesRes);
            }
            // query string인 nickname이 있을 경우, 조건을 만족하는 유저정보들을 불러온다.
            List<GetCategoryRes> getCategoriesRes = categoryProvider.getCategoriesByCategoryIdx(categoryIdx);
            return new BaseResponse<>(getCategoriesRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
