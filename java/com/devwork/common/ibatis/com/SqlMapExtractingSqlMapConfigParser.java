package com.devwork.common.ibatis.com;

import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.w3c.dom.Node;

import com.ibatis.common.xml.Nodelet;
import com.ibatis.common.xml.NodeletParser;
import com.ibatis.common.xml.NodeletUtils;
import com.ibatis.sqlmap.client.SqlMapException;
import com.ibatis.sqlmap.engine.builder.xml.SqlMapClasspathEntityResolver;
//import com.ibatis.sqlmap.engine.builder.xml.XmlParserState;

/**
 * com.devwork.common.ibatis.com 
 *    |_ SqlMapExtractingSqlMapConfigParser.java
 * 
 * @comment: 
 * @date   : 2022. 02. 12. 오전 12:00:00
 * @version: 1.0 
 * @author : j2dev 
 */
@SuppressWarnings("unchecked")
public class SqlMapExtractingSqlMapConfigParser {
	
	protected final NodeletParser parser = new NodeletParser();
//	private XmlParserState state = new XmlParserState();
	
	@SuppressWarnings("rawtypes")
	private List sqlMapList = new ArrayList();
	
	private ResourceLoader resourceLoader = new DefaultResourceLoader();
	
	public SqlMapExtractingSqlMapConfigParser() {
		parser.setValidation(true);
		parser.setEntityResolver(new SqlMapClasspathEntityResolver());
		
		addSqlMapNodelets();
		
	}
	
	@SuppressWarnings("rawtypes")
	public List parse(Reader reader) {
		try {
			parser.parse(reader);
			return sqlMapList;
		}
		catch (Exception e) {
			throw new RuntimeException("Error occurred.  Cause: " + e, e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public List parse(InputStream inputStream) {
		try {
			parser.parse(inputStream);
			return sqlMapList;
		}
		catch (Exception e) {
			throw new RuntimeException("Error occurred.  Cause: " + e, e);
		}
	}
	
	protected void addSqlMapNodelets() {
		parser.addNodelet("/sqlMapConfig/sqlMap", new Nodelet() {
			public void process(Node node) throws Exception {
//				state.getConfig().getErrorContext().setActivity(
//						"loading the SQL Map resource");
				
				Properties attributes = NodeletUtils.parseAttributes(node,
//						state.getGlobalProps());
						null);
				
				String resource = attributes.getProperty("resource");
				String url = attributes.getProperty("url");
				
				if (resource != null) {
					sqlMapList.add(resourceLoader.getResource(resource));
				}
				else if (url != null) {
					sqlMapList.add(resourceLoader.getResource(url));
				}
				else {
					throw new SqlMapException(
							"The <sqlMap> element requires either a resource or a url attribute.");
				}
			}
		});
	}
	
}
