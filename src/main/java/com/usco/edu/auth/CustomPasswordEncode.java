package com.usco.edu.auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncode implements PasswordEncoder {

	// IMPLEMENTACIÓN DEL MÉTODO PARA CODIFICAR CONTRASEÑAS
	@Override
	public String encode(CharSequence password) {
		MessageDigest mdSha = null;
		MessageDigest mdMd5 = null;
		String result = "";
		try {
			// OBTENER INSTANCIAS DE ALGORITMOS DE HASH SHA1 Y MD5
			mdSha = MessageDigest.getInstance("SHA1");
			mdMd5 = MessageDigest.getInstance("md5");

			// REALIZAR EL HASH MD5 SOBRE EL RESULTADO DEL HASH SHA1 DE LA CONTRASEÑA
			byte[] byteshashed = mdMd5.digest(mdSha.digest(password.toString().getBytes()));

			// CONVERTIR EL ARRAY DE BYTES A UNA REPRESENTACIÓN HEXADECIMAL Y ALMACENAR EN RESULT
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteshashed.length; i++) {
				sb.append(Integer.toString((byteshashed[i] & 0xff) + 0x100, 16).substring(1));
			}
			result = sb.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		// RETORNAR LA CONTRASEÑA CODIFICADA
		return result;
	}

	// IMPLEMENTACIÓN DEL MÉTODO PARA COMPARAR CONTRASEÑA ORIGINAL CON LA CODIFICADA
	@Override
	public boolean matches(CharSequence password, String encodedPassword) {
		// COMPARAR CONTRASEÑA CODIFICADA CON LA NUEVA CODIFICACIÓN DE LA CONTRASEÑA ORIGINAL
		return encode(password).equals(encodedPassword) ? true : false;
	}
}
