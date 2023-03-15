package kr.co.e1ct.mappers;

import kr.co.e1ct.vo.WBoardVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class WBoardRepositoryTest {

    @Autowired
    WBoardRepository wBoardRepository;

    @DisplayName("query  test")
    @Test
    public void _테스트() throws Exception{

//        WBoardVo wBoardVo = new WBoardVo();
//        wBoardVo.setMType( "NT" );
//
//        List<WBoardVo> pageByMType = wBoardRepository.findPageByMType(wBoardVo);
//        for (WBoardVo boardVo : pageByMType) {
//            System.out.println("boardVo = " + boardVo);
//        }




    }


}