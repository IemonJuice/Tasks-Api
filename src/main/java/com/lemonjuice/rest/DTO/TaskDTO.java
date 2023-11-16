package com.lemonjuice.rest.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class TaskDTO {
    @Null
    private Integer id;
    @NotNull
    private String name;
    @Length(min = 10,max=1000)
    private String description;
}
