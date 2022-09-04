package site.metacoding.red.domain.users;

import java.util.List;

import site.metacoding.red.web.dto.request.users.JoinDto;
import site.metacoding.red.web.dto.request.users.LoginDto;

public interface UsersDao {
	//로그인
	public Users login(LoginDto loginDto);
	public void insert(JoinDto joinDto); // DTO 생각해보기 회원가입
	public Users findById(Integer id);
	public List<Users> findAll();
	public void update(Users users); // DTO 생각해보기
	public void delete(Integer id);
	

}
