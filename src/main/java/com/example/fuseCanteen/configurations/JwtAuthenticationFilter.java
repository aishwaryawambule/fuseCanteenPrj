//package com.example.fuseCanteen.configurations;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.SignatureException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.example.fuseCanteen.Service.*;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * Created by aishwarya on 9/8/20.
// */
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private userDetailsServiceImp userDetailsService;
//
//    @Autowired
//    private JwtTokenProvider jwtTokenUtil;
//
//    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
//
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        try {
//
//            String jwt = getJwtFromRequest(request);
//            if (StringUtils.hasText(jwt) && jwtTokenUtil.validateToken(jwt)) {
//                Integer userId = jwtTokenUtil.getUserIdFromJWT(jwt);
////                UserPrincipal userPrincipal = customUserDetailsService.loadUserPrincipleById(userId);
//                UserDetails userDetails = userDetailsService.loadUserById(userId);
//                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        } catch (Exception ex) {
//            logger.error("Could not set user authentication in authentication context", ex);
//        }
//        response.addHeader("CREATED BY", "@aishwarya");
//        filterChain.doFilter(request, response);
//    }
//
//    private String getJwtFromRequest(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
//            return bearerToken.substring(7, bearerToken.length());
//        }
//        return null;
//    }
//
//}
