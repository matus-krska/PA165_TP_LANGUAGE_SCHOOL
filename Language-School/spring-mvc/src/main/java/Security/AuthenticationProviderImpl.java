package Security;

import ConfigMapper.BeanMapper;
import DTO.UserDTO;
import FacadeImp.UserFacadeImpl;
import ServiceImp.UserServiceImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Simon Hyben, 421112
 *
 */
@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

	private UserServiceImpl userService;
	private BeanMapper beanMapper;

	@Inject
	private UserFacadeImpl userFacade = new UserFacadeImpl(userService, beanMapper);

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String email = auth.getName();

		UserDTO user = userFacade.getUserByEmail(email).orElseThrow(() ->
				new UsernameNotFoundException("Provide valid email: " + email));

		String pwd = (String) auth.getCredentials();

		UserDTO userForAuth = new UserDTO();
		userForAuth.setId(user.getId());
		userForAuth.setEmail(email);
		userForAuth.setPasswordHash(pwd);

		if (!userFacade.authenticate(userForAuth)) {
			throw new BadCredentialsException("Provide valid email or password");
		}
		List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(user.getUserRole());
		return new UsernamePasswordAuthenticationToken(email, pwd, authorities);
	}
	
	@Override
	public boolean supports(Class<?> auth) {
		return true;
	}

}
