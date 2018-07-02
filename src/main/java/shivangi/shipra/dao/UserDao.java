package shivangi.shipra.dao;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.unstable.BindIn;
import shivangi.shipra.core.User;
import shivangi.shipra.core.mapper.UserMapper;

import java.util.List;
//id firstName and lastName are in like that in POJO.
@RegisterMapper(UserMapper.class)
public interface UserDao  {

    @SqlQuery("SELECT * FROM User")
    List<User> getAll();

    @SqlQuery("Select * from User where ID = :id")
    User getUserById( @Bind("id") int id);

    @SqlUpdate("Insert into User (FIRSTNAME, LASTNAME, login) values (:firstName, :lastName, :login)")
    @GetGeneratedKeys
    int insert(@BindBean User user);


    //@SqlUpdate("Update User set FIRSTNAME = :firstName," +
      //      "LASTNAME = :lastName where ID = :id and last_insert_id(id) limit 1")
    @SqlUpdate("Update User set FIRSTNAME = :firstName," +
            "LASTNAME = :lastName, login := login where ID = :id and last_insert_id(ID)")
    @GetGeneratedKeys
    int update(@BindBean User User);
}
