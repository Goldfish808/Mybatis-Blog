package site.metacoding.red.web.dto.request.boards;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.users.Users;

@Getter
@Setter
public class WriteDto {
	private String title;
	private String content;
	//private Integer id; //2022-09-05 2교시 안써야함
	
	
	public Boards toEntity(Integer usersId) { //Dto 가  엔티티로 변하는 메서드
		Boards boards = new Boards(this.title, this.content, usersId);
		return boards;
	}
}