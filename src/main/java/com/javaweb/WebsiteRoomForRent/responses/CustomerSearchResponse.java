package com.javaweb.WebsiteRoomForRent.responses;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerSearchResponse {
    private Long id;
    private String fullname;
    private String phone;
    private String email;
}