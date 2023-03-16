package hello.jdbc.repository;

import hello.jdbc.domain.Member;

import java.sql.SQLException;

public interface MemberRepositoryEx {
    Member save(Member member) throws SQLException;
    Member findById(String memberId) throws SQLException;
    void update(String memberId, int money) throws SQLException;
    void delete(String memberId) throws SQLException;

    //기존의 문제: 인터페이스에 SQLException을 던져줘야 Repository에 사용가능한데
    //이렇게 인터페이스에 SQLException을 던져주면 interface가 SQLException에 의존하게 된다.
    //이런 문제를 해결하기위해 런타임 예외를 적용시킨다.
}
