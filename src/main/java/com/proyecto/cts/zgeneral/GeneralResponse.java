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

    public GeneralResponse mensaje(GeneralResponse response){

        String resultado = "No encontrado";
        String subcadena1 = "could not execute statement";
        String subcadena2 = "Cannot add or update a child row: a foreign key constraint fails";
        String subcadena3 = "Data truncation: Data too long for column '";
        String subcadena4 = "Cannot delete or update a parent row: a foreign key constraint fails";
        String textoBuscado = "";

        switch (response.getEstado()) {
            case "saveError":
            case "updateError":
                if (response.getMensaje1().contains(subcadena1)) {
                    response.setMensaje1(EnumMsgstatus.ERR7900.getErrorDescripcion());

                    if (response.getMensaje2().contains(subcadena2)) {
                        textoBuscado = "REFERENCES `";
                        if (response.getMensaje2().contains(textoBuscado)) {
                            response.setMensaje2( response.getMensaje2().substring(response.getMensaje2().indexOf( textoBuscado)+textoBuscado.length(),response.getMensaje2().length()));
                            textoBuscado = "`";
                            response.setMensaje2( EnumMsgstatus.ERR7901.getErrorDescripcion() + " " + response.getMensaje2().substring(0,response.getMensaje2().indexOf( textoBuscado)));
                            resultado = "Encontrado";
                        }
                    }
                    if (response.getMensaje2().contains(subcadena3)) {
                        textoBuscado = "column '";
                        if (response.getMensaje2().contains(textoBuscado)) {
                            response.setMensaje2( response.getMensaje2().substring(response.getMensaje2().indexOf( textoBuscado)+textoBuscado.length(),response.getMensaje2().length()));
                            textoBuscado = "'";
                            response.setMensaje2( EnumMsgstatus.ERR7902.getErrorDescripcion() + " " + response.getMensaje2().substring(0,response.getMensaje2().indexOf( textoBuscado)));
                            resultado = "Encontrado";
                        }
                    }
                    System.out.println(resultado);
                } else {
                    System.out.println(resultado);
                }
                break;
            case "deleteError":
                response.setEstado(EnumResult.mensajeError.getDeleteError());
                if (response.getMensaje1().contains(subcadena1)) {
                    response.setMensaje1(EnumMsgstatus.ERR7900.getErrorDescripcion());

                    if (response.getMensaje2().contains(subcadena4)) {
                        textoBuscado = "`, CONSTRAINT";
                        if (response.getMensaje2().contains(textoBuscado)) {
                            response.setMensaje1(response.getMensaje2().substring(0,response.getMensaje2().indexOf( textoBuscado)));
                            textoBuscado = "`.`t";
                            response.setMensaje1(EnumMsgstatus.ERR7903.getErrorDescripcion() + " " + response.getMensaje2().substring(response.getMensaje2().indexOf( textoBuscado)+textoBuscado.length()-1,response.getMensaje2().length()));
                            resultado = "Encontrado";
                        }
                    }
                    System.out.println(resultado);
                } else {
                    System.out.println(resultado);
                }
                break;
            default:
                // Default secuencia de sentencias.
        }
        return response;
    }
}
