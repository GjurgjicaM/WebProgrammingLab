//package mk.finki.ukim.mk.lab.config;
//
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CustomUsernamePasswordAuthenticationProvider implements AuthenticationProvider {
//
//    private final UserService userService;
//    private final PasswordEncoder passwordEncoder;
//
//    public CustomUsernamePasswordAuthenticationProvider(UserService userService, PasswordEncoder passwordEncoder) {
//        this.userService = userService;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        if (username.isEmpty() || password.isEmpty()) {
//            throw new BadCredentialsException("Empty credentials!");
//        }
//
//        UserDetails userDetails = this.userService.loadUserByUsername(username);
//
//        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
//            throw new BadCredentialsException("Password is incorrect!");
//        }
//        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return aClass.equals(UsernamePasswordAuthenticationToken.class);
//    }
//
//}