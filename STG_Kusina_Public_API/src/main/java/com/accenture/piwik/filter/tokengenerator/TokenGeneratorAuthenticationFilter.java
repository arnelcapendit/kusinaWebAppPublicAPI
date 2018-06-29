//package com.accenture.piwik.filter.tokengenerator;
//
//
//import com.accenture.piwik.model.User;
//import com.accenture.piwik.service.PiwikService;
//import com.accenture.piwik.service.UserService;
//import com.accenture.piwik.utils.KusinaDateUtils;
//import com.accenture.piwik.utils.KusinaStringUtils;
//import java.io.IOException;
//import java.net.URLEncoder;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Enumeration;
//import java.util.TimeZone;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.json.simple.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.support.SpringBeanAutowiringSupport;
//
///**
// *
// * @author arnel.m.capendit
// */
//@Component
//public class TokenGeneratorAuthenticationFilter implements Filter {
//
//    @Autowired
//    private KusinaStringUtils kusinaStringUtils;
//
//    @Autowired
//    private PiwikService piwikService;
//    
//    @Autowired 
//    private UserService userService;
//    
//    @Autowired
//    private KusinaDateUtils kusinaDateUtils;
//
//    @Override
//    public void init(FilterConfig fc) throws ServletException {
//        // Enable Annotation(s) in Filter
//        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
//                fc.getServletContext());
//    }
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc) throws IOException, ServletException {
//        HttpServletRequest httprequest = (HttpServletRequest) req;
//        HttpServletResponse httpresponse = (HttpServletResponse) res;
//
//        httpresponse.setContentType("application/json");
//        httprequest.setCharacterEncoding("UTF-8");
//
//        Enumeration<String> params = req.getParameterNames();
//        
//        JSONObject paramMap = new JSONObject();
//
//        JSONObject ar = new JSONObject();
//
//        try {
//            while (params.hasMoreElements()) {
//                String name = params.nextElement(); 
//                String value = req.getParameter(name);
//                paramMap.put(name, kusinaStringUtils.sanitizeString(value));
//            }
//            User user = userService.retrieveUserData(paramMap.get("eid").toString());
//            if (user != null) {
//                if (kusinaDateUtils.convertStrDateToEpochMilli(user.getExpiryDate()) > new Date().getTime()) {
//                        req.setAttribute("user", user);
//                        req.setAttribute("params", paramMap);
//                        fc.doFilter(req,res);
//                } else {
//                    ar.put("status", "user already expired");
//                    httpresponse.getWriter().write(ar.toJSONString());
//                }
//            } else {
//                ar.put("status", "unauthorized user.");
//                httpresponse.getWriter().write(ar.toJSONString());
//            }
//        } catch (IOException | ServletException | NullPointerException e) {
//            ar.put("status", e.getMessage());
//            httpresponse.getWriter().write(ar.toJSONString());
//        }
//
//    }
//
//    @Override
//    public void destroy() {
//    }
//}
//
