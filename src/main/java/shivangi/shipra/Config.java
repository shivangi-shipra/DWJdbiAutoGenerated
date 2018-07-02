package shivangi.shipra;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Config extends Configuration {

    @NotNull
    @Valid
    private DataSourceFactory factory = new DataSourceFactory();

    @JsonProperty("database")
    public DataSourceFactory getFactory() {
        return factory;
    }

    @JsonProperty("database")
    public void setFactory(DataSourceFactory factory) {
        this.factory = factory;
    }
}
