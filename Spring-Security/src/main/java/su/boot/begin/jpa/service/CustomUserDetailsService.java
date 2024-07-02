package su.boot.begin.jpa.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import jakarta.inject.Inject;
import su.boot.begin.jpa.entity.Emp;
import su.boot.begin.jpa.repository.EmpRepository;
import java.util.Collections;

// spring의 서비스 컴포넌트임을 나타낸다.
@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Inject
	private EmpRepository empRepository;

//사용자 이름인 ename을 기반으로 사용자 세부 정보를 로드한다.
	@Override
	public UserDetails loadUserByUsername(String ename) throws UsernameNotFoundException { // 사용자 이름이 admin일 경우에 하드코딩된
																							// 관리자 계정을 반환한다.
		if ("admin".equals(ename)) {
			return User.withUsername("admin").password("{noop}1234").roles("ADMIN").build();
		}
// empRepository를 사용하여 데이터베이스에서 사용자 정보를 조회한다.
		Emp emp = empRepository.findByEname(ename);
		if (emp == null) {
// 사용자를 찾지 못한 경우 예외를 발생시킨다.
			throw new UsernameNotFoundException("직원을 찾을 수 없습니다.");
		}
//사원 번호를 비밀번호로 사용하며 비밀번호는 인코딩되지 않았음을 나타내는 {noop} 접두사를 사용한다.
		String password = "{noop}" + emp.getEmpno().toString(); // UserDetails 객체를 생성하여 반환한다.
		return new User(emp.getEname(), password,
				Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + emp.getJob())));
	}
}
