# 영단어 RPG

테이블 만드는 규칙 -> 각각 id명 규칙으로 해당 테이블명+만든시간초단위(
ex:UTC로 14400005224 -> 2021년 몇월 몇일 몇시 몇분 몇초)+랜덤숫자4자리
 
 * BattleNotification *
 notice_category 구별 
 
 const val BATTLE_RESULT_WIN = 2001 //승리
 const val BATTLE_RESULT_LOSE = 2002 //패배
 const val BATTLE_RESULT_TIE = 2003   // 무승부
 const val BATTLE_RESULT_REQUEST = 2004 // 리퀘스트
 
 -주요내용 
 # 배틀쪽은 거의다 주석처리를 해놨습니다. 데이터 형식을 고친게 많아서 나중에 다시 손써야할듯 합니다.
 # 더미로 만든 테이블은 mockdata 폴더에 모아놓았고 , 실제 사용할 테이블은 model 폴더에 모아놓았습니다.
 

