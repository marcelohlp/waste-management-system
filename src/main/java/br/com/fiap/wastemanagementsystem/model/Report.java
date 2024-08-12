package br.com.fiap.wastemanagementsystem.model;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Report {
    private District district;
    private LocalDate initialDate;
    private LocalDate finalDate;
    private Integer totalScheduled;
    private Integer totalFinished;
    private Integer totalDelayed;
}
