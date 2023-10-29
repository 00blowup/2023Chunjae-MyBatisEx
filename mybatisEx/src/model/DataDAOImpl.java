package model;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DataDAOImpl implements DataDAO{

    /* My Batis Start
     * 객체가 한번만 실행되는 것
     * 초기화 블럭 (객체를 생성할 때)
     * My Batis
     */
    private static SqlSessionFactory sqlSessionFactory;

    static {
        String resource = "mybatisEx/config.xml";
        InputStream inputStream;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            //전체 세션을 관리하는 것이 sqlSessionFactory
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DataVO> dataList() {
        //세션정보 가져오기
        SqlSession session = sqlSessionFactory.openSession();
        //SQL문장 ( select ~ ) + MemberVO + ArrayList() 
        //List<MemberVO> list = session.selectList("kr.smhrd.mybatis.memberList"); //동일한 이름이 없을 경우 패키지는 적지 않아도 된다.
        List<DataVO> list = session.selectList("dataList");
        //사용후 사용한 세션에 대한 반납을 실시한다.
        session.close();
        return list;
    }

    @Override
    public int dataInsert(DataVO vo) {
        SqlSession session = sqlSessionFactory.openSession();
        int cnt = session.insert("dataInsert", vo);
        session.commit();
        session.close();
        return cnt;
    }

    @Override
    public int dataDelete(int id) {
        SqlSession session = sqlSessionFactory.openSession();
        int cnt = session.delete("dataDelete", id);
        session.commit();
        session.close();
        return cnt;
    }

    @Override
    public DataVO dataContent(int id) {
        SqlSession session = sqlSessionFactory.openSession();
        DataVO vo = session.selectOne("dataContent", id);
        session.close();
        return vo;
    }

    @Override
    public int dataUpdate(DataVO vo) {
        SqlSession session = sqlSessionFactory.openSession();
        int cnt = session.update("dataUpdate", vo);
        session.commit();
        session.close();
        return cnt;
    }

}