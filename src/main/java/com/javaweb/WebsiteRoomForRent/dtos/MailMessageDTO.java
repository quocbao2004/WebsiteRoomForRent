package com.javaweb.WebsiteRoomForRent.dtos;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MailMessageDTO {

    private String from;
    private String to;
    private String subject;
    private String body;
}
