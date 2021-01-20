package test;

import io.quarkus.security.identity.SecurityIdentity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * More of a RPC type REST controller for admin maintenance.
 */
@Path("/admin")
@RolesAllowed("admin")
public class AdminController {
    private static Logger log = LoggerFactory.getLogger(AdminController.class);
    @Inject
    SecurityIdentity identity;

    @GET
    public String getTeams() {
        log.info(identity.getPrincipal().getName());
        log.info(String.join(";", identity.getRoles()));
        return "yea";
    }
}
