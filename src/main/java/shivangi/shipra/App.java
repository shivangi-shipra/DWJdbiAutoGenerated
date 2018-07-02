package shivangi.shipra;

import com.fasterxml.jackson.databind.SerializationFeature;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;
import shivangi.shipra.dao.UserDao;
import shivangi.shipra.resource.UserResource;

import java.util.TimeZone;

/**
 * Hello world!
 *
 */
public class App extends Application<Config>
{
    public static void main( String[] args ) throws Exception
    {
        System.out.println( "Hello World!" );
        new App().run(args);
    }

    @Override
    public void initialize(Bootstrap<Config> bootstrap){
        super.initialize(bootstrap);
        bootstrap.getObjectMapper().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        //bootstrap.getObjectMapper().setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    @Override
    public void run(Config config, Environment environment) throws Exception {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment,config.getFactory(),"mysql");
        final UserDao userDao = jdbi.onDemand(UserDao.class);
        final UserResource resource = new UserResource(userDao);
        environment.jersey().register(resource);
    }
}
