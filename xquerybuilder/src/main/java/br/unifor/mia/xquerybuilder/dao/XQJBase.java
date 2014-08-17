package br.unifor.mia.xquerybuilder.dao;

import java.io.Reader;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQSequence;

/**
 * Classe que implementa o fechamento de recursos
 * 
 * @author patrick cunha
 * @since 15/08/2014 08:30
 */
public class XQJBase {

	protected static void close(XQConnection conn) {
		try {
			if(conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected static void close(XQExpression conn) {
		try {
			if(conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected static void close(XQPreparedExpression conn) {
		try {
			if(conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected static void close(XQSequence conn) {

		try {
			if(conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected static void close(Reader conn) {

		try {
			if(conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
