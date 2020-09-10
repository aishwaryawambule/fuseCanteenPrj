//package com.example.fuseCanteen.Controller;
//
//import com.example.fuseCanteen.configurations.JwtAuthenticationFilter;
//import com.example.fuseCanteen.configurations.JwtTokenProvider;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//import com.example.fuseCanteen.Model.user.LoginUser;
//import javax.naming.AuthenticationException;
//
///**
// * Created by aishwarya on 9/8/20.
// */
//    @CrossOrigin(origins = "*", maxAge = 3600)
//    @RestController
//    @RequestMapping("/token")
//    public class authenticationController {
//
//        public static final Logger LOGGER = LoggerFactory.getLogger(authenticationController.class);
//
//        @Autowired
//        private AuthenticationManager authenticationManager;
//
//        @Autowired
//        private JwtTokenProvider jwtTokenUtil;
//
//        @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
//        public ResponseEntity register(@RequestBody LoginUser loginUser) throws AuthenticationException {
//        try{
//            final Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            loginUser.getUserName(),
//                            loginUser.getPassword()
//                    )
//            );
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            final String jwt = jwtTokenUtil.generateToken(authentication);
//            HttpHeaders httpHeaders = new HttpHeaders();
//            httpHeaders.add("Authorization", "Bearer " + jwt);
//            return ResponseEntity.status(200).body("AUTHORIZED " + jwt);
//
//        }
//            catch (Exception ex) {
//                LOGGER.info("NOT AUTHORIZED " + ex.toString());
//                return ResponseEntity.status(401).body("NOT AUTHORIZED");
//            }
//
//        }
//
//
//
//}
