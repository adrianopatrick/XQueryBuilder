package br.unifor.mia.xquerybuilder.dao;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQSequence;

import br.unifor.mia.xquerybuilder.utils.ArquivoUtils;
import net.sf.saxon.xqj.SaxonXQDataSource;

/**
 * Classe que tem a responsabilidade de executar a consulta criada pelo usuario
 * 
 * @author patrick cunha
 * @since 15/08/2014 08:30
 */
public class XQExpressionSample extends XQJBase {

	private XQDataSource dataSource = null;
	private XQConnection connection = null;
	private XQSequence resultSequence = null;
	private XQExpression expression = null;

	public List<String> consultar(String consultaXQ) throws Exception {
		List<String> retorno = new ArrayList<String>();
		try {
			dataSource = new SaxonXQDataSource();
			connection = dataSource.getConnection();
			expression = connection.createExpression();
			resultSequence = expression.executeQuery(consultaXQ.replace(
					"fn:doc('",
					"fn:doc('" + ArquivoUtils.getPathXquerybuilder()));

			while (resultSequence.next()) {
				retorno.add(resultSequence.getItemAsString(null));
			}
		} finally {
			close(resultSequence);
			close(expression);
			close(connection);
		}

		return retorno;
	}

}
