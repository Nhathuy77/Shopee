package com.vti.myshopee.config.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

 @Data
    @JsonIgnoreProperties({"stackTrace", "cause", "suppressed", "localizedMessage"})
    @NoArgsConstructor
    public class CustomException extends RuntimeException{
        private Instant timestamp; // Thời gian xảy ra lỗi
        private int status; // Trạng thái, mã lỗi
        private String message; // Nguyên nhân xảy ra lỗi
        private String path; // API xả ra lỗi

     public CustomException(int status, String error) {
         this.timestamp = Instant.now();
         this.status = status;
         this.message = error;
     }

        public CustomException(ErrorResponseEnum responseEnum) {
            this.timestamp = Instant.now();
            this.status = responseEnum.status;
            this.message = responseEnum.message;
        }
    }


