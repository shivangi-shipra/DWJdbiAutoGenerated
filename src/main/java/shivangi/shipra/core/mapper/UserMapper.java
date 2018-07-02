package shivangi.shipra.core.mapper;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import shivangi.shipra.core.User;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserMapper implements ResultSetMapper<User> {
    @Override
    public User map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        //DateTimeFormatter df = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss").withZone(ZoneId.of("UTC"));
        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-mm-dd HH:mm:ss.S").withZone(DateTimeZone.forID("UTC"));
        //DateTime dt = dtf.f new DateTime(resultSet.getTimestamp("login"));

        DateTime d = dtf.parseDateTime(resultSet.getTimestamp("login").toString());
        String s = "";
        return new User(resultSet.getInt("ID"), resultSet.getString("FIRSTNAME"),
                resultSet.getString("LASTNAME"), d);
    }
}