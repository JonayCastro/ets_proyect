package com.zeven.ets_proyect.Ets_Proyect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String name;
    private String password;
    private String contact;
    private Long chatId;
    private String urlChatBot;
}
