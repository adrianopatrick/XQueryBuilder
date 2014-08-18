package br.unifor.mia.xquerybuilder.utils;

import java.io.File;

/**
 * Classe tem por responsabilidade configurar a pasta padrão do XQueryBuilder
 * 
 * @author patrick cunha
 * @since 15/08/2014 08:30
 */
public class ArquivoUtils {

	private static final String PATH_XQUERYBUILDER = System
			.getProperty("user.home")
			+ "/"
			+ ".xquerybuilder"
			+ "/";

	public static void configuraPastas() {
		File file = new File(PATH_XQUERYBUILDER);
		if (!file.exists()) {
				file.mkdir();
		}
	}

	public static String getPathXquerybuilder() {
		return PATH_XQUERYBUILDER;
	}

}
