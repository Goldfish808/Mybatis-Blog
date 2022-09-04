package site.metacoding.red.web.dto.request.boards;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.red.domain.users.Users;

@Getter
@Setter
public class WriteDto {
	private String title;
	private String content;
	private Integer id;
}