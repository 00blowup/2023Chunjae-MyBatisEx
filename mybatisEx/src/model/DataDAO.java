package model;

import java.util.List;

public interface DataDAO {
    public List<DataVO> dataList(); // 전체 목록 조회
    public int dataInsert(DataVO vo); // 주소록 데이터 추가
    public DataVO dataContent(int id); // 주소록 데이터 하나 조회
    public int dataDelete(int id); // 주소록 데이터 삭제
    public int dataUpdate(DataVO vo); // 주소록 데이터 수정
}