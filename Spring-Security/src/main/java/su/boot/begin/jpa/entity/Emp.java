package su.boot.begin.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.sql.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// 기본 생성자를 자동으로 생성한다.
@NoArgsConstructor
@Getter
@Setter
// 클래스가 JPA 엔티티임을 나타낸다. 
@Entity
// 클래스명을 테이블명으로 선언한다.
public class Emp {

// 필드가 엔티티의 기본키임을 나타낸다.
	@Id
	private Long empno;
	private String ename;
	private String job;
	private Long mgr;
	private Date hiredate;
	private Double sal;
	private Double comm;
	private Long deptno;
}
