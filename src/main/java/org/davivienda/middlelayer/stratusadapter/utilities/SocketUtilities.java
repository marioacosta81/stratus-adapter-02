package org.davivienda.middlelayer.stratusadapter.utilities;

import jakarta.ws.rs.core.Response;
import org.apache.http.HttpStatus;
import org.davivienda.middlelayer.stratusadapter.model.dtos.DataAttributeDto;
import org.davivienda.middlelayer.stratusadapter.model.exceptions.StratusAdapterException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketUtilities {

    public static final String EMPTY = "";
    public static final String DOC = ".";

    public static String requestSocket(String host, Integer port, String request) throws StratusAdapterException {
        try{
            Socket sock = new Socket(host,port);
            InputStream input = sock.getInputStream();
            OutputStream output = sock.getOutputStream();

            output.write(   request.getBytes(StandardCharsets.ISO_8859_1)  );

            byte[] response = new byte [2048];

            for(int i=0;i<3;i++){
                input.read(response);
                Thread.sleep(3000);
            }



            sock.close();

            return  (new String(response)).trim();

        }catch ( IOException | InterruptedException e){
            throw new StratusAdapterException(Response.Status.INTERNAL_SERVER_ERROR,e.getMessage() );
        }
    }
}
