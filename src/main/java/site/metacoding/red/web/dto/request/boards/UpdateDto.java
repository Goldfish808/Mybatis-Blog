package site.metacoding.red.web.dto.request.boards;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.users.Users;

@Getter
@Setter
public class UpdateDto {
	private String title;
	private String content;
	//private Integer id; //2022-09-05 2교시 안써야함
}