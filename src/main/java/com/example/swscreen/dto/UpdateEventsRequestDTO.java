package com.example.swscreen.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEventsRequestDTO {

    private String title;
    private Date event_date;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime time;
}
