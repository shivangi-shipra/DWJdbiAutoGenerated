package shivangi.shipra.resource;

import shivangi.shipra.core.User;
import shivangi.shipra.dao.UserDao;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

    @Path("/user")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public class UserResource {

        UserDao dao;

        public UserResource(UserDao UserDao)
        {
            this.dao = UserDao;
        }

        @GET
        @Path("/hello")
        public Response sayHello(){
            return  Response.ok().build();
        }

        @GET
        public List<User> getAll(){
            return dao.getAll();
        }

        @GET
        @Path("/{id}")
        public User get(@PathParam("id") Integer id){
            return dao.getUserById(id);
        }

        @POST
        public User add(@Valid User user) {
            int i = dao.insert(user);
            user.setId(i);
            return user;
        }

        @PUT
        @Path("/{id}")
        public User update(@PathParam("id") Integer id, @Valid User user) {
            User updateUser = new User(id, user.getFirstName(),user.getLastName(),user.getLogin());
            int i = dao.update(updateUser);
            System.out.println(i);
            return updateUser;
        }

    }
