package su.boot.begin.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import su.boot.begin.jpa.entity.Emp;

public interface EmpRepository extends JpaRepository<Emp, Long> {
//ename 필드를 기반으로 Emp를 찾기 위한 쿼리 메서드를 선언한다.
	Emp findByEname(String ename);
}