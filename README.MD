# 1일차
- hibername.dialect 설정은 DB에 맞는 방언을 설정하는 부분이다. h2, mysql, oracle등 DB에 맞춰서 사용한다.
- EntityManager는 한 쓰레드에서만 사용해야한다.(공유 X)
- __JPA의 모든 데이터 변경은 트랜잭션 안에서__ 실행해야함.