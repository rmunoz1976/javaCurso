package com.proyecto.cts.zgeneral;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GeneralResponse {
    private String estado = "";
    private String mensaje1 ="";
    private String mensaje2 = "";
}
