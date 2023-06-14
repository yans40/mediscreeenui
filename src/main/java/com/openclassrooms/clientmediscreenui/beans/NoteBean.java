package com.openclassrooms.clientmediscreenui.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoteBean {
    private String id;
    private Long patientId;
    private String patient;
    private String observation;
    private Date date;
}
