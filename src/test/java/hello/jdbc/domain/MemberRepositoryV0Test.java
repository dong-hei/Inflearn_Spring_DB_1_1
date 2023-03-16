package hello.jdbc.domain;

import hello.jdbc.repository.MemberRepositoryV0;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
class MemberRepositoryV0Test {

    MemberRepositoryV0 repositoryV0 = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException {
        //save
        Member member = new Member("memberV1", 2500000);
        repositoryV0.save(member);

        //findById
        Member findMember = repositoryV0.findById(member.getMemberId());
        log.info("findMember={}", findMember);
        log.info("member equals findMember {}", member.equals(findMember));
        assertThat(findMember).isEqualTo(member);

        //update: money : 2500000- 5000000
        repositoryV0.update(member.getMemberId(), 5000000);
        Member updateMember = repositoryV0.findById(member.getMemberId());
        assertThat(updateMember.getMoney()).isEqualTo(5000000);

        //delete
        repositoryV0.delete(member.getMemberId());
        assertThatThrownBy(() -> repositoryV0.findById(member.getMemberId())).
                isInstanceOf(NoSuchElementException.class);
    }

}