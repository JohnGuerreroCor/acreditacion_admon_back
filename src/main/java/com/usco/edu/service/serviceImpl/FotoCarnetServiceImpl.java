package com.usco.edu.service.serviceImpl;

import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usco.edu.dao.IFotoCarnetDao;
import com.usco.edu.dto.FotoAntigua;
import com.usco.edu.dto.RespuestaSubirFoto;
import com.usco.edu.dto.RespuestaVerFoto;
import com.usco.edu.entities.Documento;
import com.usco.edu.feing.EnviarFotoClient;
import com.usco.edu.service.IFotoCarnetService;

@Service
public class FotoCarnetServiceImpl implements IFotoCarnetService {

    @Autowired
    private EnviarFotoClient enviarFotoClient;

    @Autowired
    private IFotoCarnetDao fotoCarnetDao;

    // MÉTODO PARA SUBIR UNA FOTO AL SISTEMA
    @Override
    public String subirFoto(MultipartFile file, String perCodigo, int uaa, String user, HttpServletRequest request) {

        String err = "";

        if (!file.isEmpty()) {

            // VERIFICAR QUE EL NOMBRE DEL ARCHIVO ES VÁLIDO
            if (isValido(file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf(".")))) {

                // CREAR UN OBJETO DOCUMENTO CON LA INFORMACIÓN DE LA FOTO
                Documento foto = new Documento();
                foto.setDocNombreArchivo(file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf(".")));
                foto.setPerCodigo(perCodigo);
                foto.setDocCliente("Carnetizacion_Administrador_VERSION:1.0");
                foto.setDocContenido("Fotos Carnetizacion");
                foto.setDocExtension("png");
                foto.setDocIp(request.getRemoteAddr());
                foto.setDocSesion(request.getSession().getId());
                foto.setModCodigo(74);
                foto.setTdocCodigo(78);
                foto.setUaaCodigo(uaa);

                // OBTENER UNA CLAVE/TOKEN PARA LA FOTO
                String Key = fotoCarnetDao.obtenerTokenFoto(
                        foto.getModCodigo().toString() + foto.getUaaCodigo() + foto.getPerCodigo(),
                        user);

                System.out.println("Datos para el token: " + foto.getModCodigo().toString() + foto.getUaaCodigo()
                        + foto.getPerCodigo());

                ObjectMapper objectMapper = new ObjectMapper();
                RespuestaSubirFoto respuesta = new RespuestaSubirFoto();

                try {
                    // ENVIAR LA FOTO AL SISTEMA EXTERNO
                    respuesta = enviarFotoClient.subirFoto(file, Key, objectMapper.writeValueAsString(foto));
                    System.out.println(respuesta.getMensaje());
                    System.out.println(respuesta.getPerCodigo());
                    err = respuesta.getMensaje();

                    if (!respuesta.isEstado()) {
                        System.out.println("Ocurrió un error: " + respuesta.getMensaje());
                    }

                } catch (Exception e) {
                    System.out.println("Ocurrió un error: " + e);
                    return null;
                }

                System.out.println("Foto subida con éxito: " + respuesta.getMensaje());
                return respuesta.getPerCodigo() + "";

            } else {
                System.out.println("Ocurrió un error: " + err);
                return null;
            }

        } else {
            System.out.println("Ocurrió un error: " + err);
            return null;
        }
    }

    // MÉTODO PARA VALIDAR SI UN NOMBRE DE ARCHIVO ES VÁLIDO
    public boolean isValido(String nombre) {
        String expresion = "^([[a-zA-Z][0-9]_]{2,150})$";
        try {
            Pattern p = Pattern.compile(expresion);
            Matcher matcher = p.matcher(nombre);
            return matcher.matches();
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    // MÉTODO PARA OBTENER UNA FOTO Y CONVERTIRLA EN UN FLUJO DE ENTRADA
    @Override
    public ByteArrayInputStream mirarFoto(String perCodigo, String user, HttpServletResponse response) {

        byte[] array = { 1, 2, 3, 4 };

        // OBTENER UNA CLAVE/TOKEN PARA LA FOTO
        String Key = fotoCarnetDao.obtenerTokenFoto(perCodigo + "", user);

        RespuestaVerFoto respuesta = new RespuestaVerFoto();

        try {
            // OBTENER LA FOTO DEL SISTEMA EXTERNO
            respuesta = enviarFotoClient.mostrarFoto(perCodigo, Key);

            byte[] fotoBytes = Base64.getDecoder().decode(respuesta.getBase64().split(",")[1]);
            return new ByteArrayInputStream(fotoBytes);

        } catch (Exception e) {
            System.out.println(e);
        }

        return new ByteArrayInputStream(array);
    }

    // MÉTODO PARA OBTENER UNA FOTO ANTIGUA Y DEVOLVERLA COMO UN OBJETO FOTOANTIGUA
    @Override
    public FotoAntigua mirarFotoAntigua(String perCodigo, String user) {
        String aux = "";
        String Key = fotoCarnetDao.obtenerTokenFoto(perCodigo, user);
        aux = enviarFotoClient.mostrarFotoAntigua(perCodigo, Key).toString();
        FotoAntigua foto = new FotoAntigua();
        foto.setUrl(aux);
        return foto;
    }
}
