package gmx.fwd.mapper.user;

import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import gmx.fwd.vo.uservo.UserVo;

@Mapper
public interface UserMapper {

	int register(UserVo user);

	UserVo login(Map<String, String> params);
	
	UserVo getByUsername(String username);

	int changePassword(UserVo user);

	int unregisterUser(String username);

	UserVo securityLogin(String username);

}
