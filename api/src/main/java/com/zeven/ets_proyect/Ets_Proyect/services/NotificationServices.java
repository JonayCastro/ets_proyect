package com.zeven.ets_proyect.Ets_Proyect.services;

import java.util.List;

public interface NotificationServices <ResponseDto> {

    void sendNotification();
    void sendNotification(List<ResponseDto> responseDto);
    String buildMessage(ResponseDto responseDto);
    String getUserContact();
}
