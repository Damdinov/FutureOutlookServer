package com.damdinov.server;
import java.io.File;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/")
public class Service {
    private static final String DIR_PATH = "C:\\test\\";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/models/")
    public Response getModel(@QueryParam("latitude") double latitude, @QueryParam("longitude") double longitude){
        Factory factory = Factory.getInstance();
        ModelDaoImpl modelDao = factory.getModelDao();
        ModelsEntity modelEntity = null;
        List list;

        try {
            list = modelDao.getModel(latitude, longitude );

            //TODO: check for several elements in list

            modelEntity = (ModelsEntity) list.get(0);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Response.ok(modelEntity).build();
    }

    @GET
    @Path("/models/obj/{filename}")
    @Produces("text/plain")
    public Response getObjFile(@PathParam("filename") String filename) {
        //TODO check
        File file = new File(DIR_PATH + filename + ".obj");
        Response.ResponseBuilder response = Response.ok(file);
        //response.header("Content-Disposition","attachment; filename=\"file_from_server.obj\"");
        return response.build();
    }

    @GET
    @Path("/models/mtl/{filename}")
    @Produces("text/plain")
    public Response getMltFile(@PathParam("filename") String filename) {
        //TODO check

        File file = new File(DIR_PATH + filename + ".mtl");

        Response.ResponseBuilder response = Response.ok(file);
        //response.header("Content-Disposition","attachment; filename=newfile.mlt");
        return response.build();
    }


}
