package de.unikoblenz.west.lkastler;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.thinkaurelius.titan.core.TitanFactory;
import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.Vertex;

/**
 * simple test
 * 
 * @author lkastler
 *
 */
public class MainTest {

	static Logger log = LogManager.getLogger();
	
	@Test
	public void simpleTitanTest() throws Throwable {
		log.info("START");
		
		Configuration conf = new PropertiesConfiguration(this.getClass().getClassLoader().getResource("config.properties"));
		TitanGraph g = TitanFactory.open(conf);
		
		Vertex juno = g.addVertex(null);
		juno.setProperty("name", "juno");
		Vertex jupiter = g.addVertex(null);
		jupiter.setProperty("name", "jupiter");
		g.addEdge(null, juno, jupiter, "married");
		
		for(Vertex v : juno.query().labels("married").vertices()) {
			log.info(v.getProperty("name"));
		}
		log.info("END");
	}
}
