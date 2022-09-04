package site.metacoding.red.domain.boards.mapper;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.users.Users;

@Getter
@Setter
public class BoardsOrm {
	private Integer id;
	private String title;
	private String content;
	private Integer userId;
	private String username;
	private String password;
	private String email;
	
}
