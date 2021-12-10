package com.example.demo.src.category;

import com.example.demo.src.category.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository

public class CategoryDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired //readme 참고
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int createCategory(PostCategoryReq postCategoryReq) {
        String createCategoryQuery = "insert into Category (category) VALUES (?)"; // 실행될 동적 쿼리문
        Object[] createCategoryParams = new Object[]{postCategoryReq.getCategory()}; // 동적 쿼리의 ?부분에 주입될 값
        this.jdbcTemplate.update(createCategoryQuery, createCategoryParams);
        // email -> postUserReq.getEmail(), password -> postUserReq.getPassword(), nickname -> postUserReq.getNickname() 로 매핑(대응)시킨다음 쿼리문을 실행한다.
        // 즉 DB의 User Table에 (email, password, nickname)값을 가지는 유저 데이터를 삽입(생성)한다.

        String lastInserIdQuery = "select last_insert_id()"; // 가장 마지막에 삽입된(생성된) id값은 가져온다.
        return this.jdbcTemplate.queryForObject(lastInserIdQuery, int.class); // 해당 쿼리문의 결과 마지막으로 삽인된 유저의 userIdx번호를 반환한다.
    }
    public int checkCategory(String category) {
        String checkCategoryQuery = "select exists(select category from Category where category = ?)"; // User Table에 해당 phoneNumber 값을 갖는 유저 정보가 존재하는가?
        String checkCategoryParams = category; // 해당(확인할) 이메일 값
        return this.jdbcTemplate.queryForObject(checkCategoryQuery,
                int.class,
                checkCategoryParams); // checkEmailQuery, checkEmailParams를 통해 가져온 값(intgud)을 반환한다. -> 쿼리문의 결과(존재하지 않음(False,0),존재함(True, 1))를 int형(0,1)으로 반환됩니다.
    }

    // User 테이블에 존재하는 전체 유저들의 정보 조회
    public List<GetCategoryRes> getCategories() {
        String getCategoriesQuery = "select * from Category"; //User 테이블에 존재하는 모든 회원들의 정보를 조회하는 쿼리
        return this.jdbcTemplate.query(getCategoriesQuery,
                (rs, rowNum) -> new GetCategoryRes(
                        rs.getInt("categoryIdx"),
                        rs.getString("category")
                ) // RowMapper(위의 링크 참조): 원하는 결과값 형태로 받기
        ); // 복수개의 회원정보들을 얻기 위해 jdbcTemplate 함수(Query, 객체 매핑 정보)의 결과 반환(동적쿼리가 아니므로 Parmas부분이 없음)
    }

    //categories 전체 조회
    public List<GetCategoryRes> getCategoriesByCategoryIdx(int categoryIdx) {
        String getCategoriesByCategoryIdxQuery = "select * from Category where categoryIdx =?"; // 해당 이메일을 만족하는 유저를 조회하는 쿼리문
        int getCategoriesByCategoryIdxParams = categoryIdx;
        return this.jdbcTemplate.query(getCategoriesByCategoryIdxQuery,
                (rs, rowNum) -> new GetCategoryRes(
                        rs.getInt("categoryIdx"),
                        rs.getString("category")
                        ), // RowMapper(위의 링크 참조): 원하는 결과값 형태로 받기
                getCategoriesByCategoryIdxParams); // 해당 닉네임을 갖는 모든 User 정보를 얻기 위해 jdbcTemplate 함수(Query, 객체 매핑 정보, Params)의 결과 반환
    }
}
