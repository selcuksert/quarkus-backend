package com.openshift.fordevelopers;

import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.Panache;
import org.jboss.logging.Logger;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/posts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostResource {
    private static final Logger LOGGER = Logger.getLogger(PostResource.class);
    private static final Gson gson = new Gson();

    public PostResource() {
        // No code
    }

    @GET
    public Response list() {
        return Response.ok(Post.listAll()).build();
    }

    @POST
    @Transactional
    public Response add(Post post) {
        LOGGER.infof("Persisting data: %s", gson.toJson(post));
        post.persist();
        return Response.ok(Post.listAll()).build();
    }

    @DELETE
    @Transactional
    public Response delete(Post post) {
        EntityManager em = Panache.getEntityManager();
        em.remove(em.contains(post) ? post : em.merge(post));
        return Response.ok().build();
    }
}
