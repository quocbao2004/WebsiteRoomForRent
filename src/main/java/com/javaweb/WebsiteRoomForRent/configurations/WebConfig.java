//package com.javaweb.WebsiteRoomForRent.configurations;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/api/**")  // Cấu hình CORS cho tất cả các đường dẫn bắt đầu với /api
//                .allowedOrigins("http://localhost:3000")  // Cho phép địa chỉ frontend của bạn
//                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Các phương thức HTTP được phép
//                .allowCredentials(true);  // Nếu bạn dùng cookies hoặc token để xác thực
//    }
//}
