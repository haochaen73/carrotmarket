package com.example.demo.src.viewcount;

import com.example.demo.src.viewcount.model.PostViewcountReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class ViewcountDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired //readme 참고
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int createViewcount(PostViewcountReq postViewcountReq) {
        String createViewcountQuery = "insert into Viewcount (userIdx, productIdx) VALUES (?,?)"; // 실행될 동적 쿼리문
        Object[] creatViewcountParams = new Object[]{postViewcountReq.getUserIdx(),postViewcountReq.getProductIdx()}; // 동적 쿼리의 ?부분에 주입될 값
        this.jdbcTemplate.update(createViewcountQuery, creatViewcountParams);
        // email -> postUserReq.getEmail(), password -> postUserReq.getPassword(), nickname -> postUserReq.getNickname() 로 매핑(대응)시킨다음 쿼리문을 실행한다.
        // 즉 DB의 User Table에 (email, password, nickname)값을 가지는 유저 데이터를 삽입(생성)한다.

        String lastInserIdQuery = "select last_insert_id()"; // 가장 마지막에 삽입된(생성된) id값은 가져온다.
        return this.jdbcTemplate.queryForObject(lastInserIdQuery, int.class); // 해당 쿼리문의 결과 마지막으로 삽인된 유저의 userIdx번호를 반환한다.
    }

    public int checkUserIdx(int userIdx) {
        String checkUserIdxQuery = "select exists(select userIdx from Viewcount where userIdx = ?)"; // User Table에 해당 phoneNumber 값을 갖는 유저 정보가 존재하는가?
        int checkUserIdxParams = userIdx; // 해당(확인할) 이메일 값
        return this.jdbcTemplate.queryForObject(checkUserIdxQuery,
                int.class,
                checkUserIdxParams); // checkEmailQuery, checkEmailParams를 통해 가져온 값(intgud)을 반환한다. -> 쿼리문의 결과(존재하지 않음(False,0),존재함(True, 1))를 int형(0,1)으로 반환됩니다.
    }
}
