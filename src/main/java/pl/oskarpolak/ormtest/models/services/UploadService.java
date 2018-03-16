package pl.oskarpolak.ormtest.models.services;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
public class UploadService {
    private final static String LOGIN = "akademiakodu";
    private final static String PASSWORD = "akademia";
    private final static String IP = "5.135.218.27";
    private final static int PORT = 21;

    private FTPClient ftpClient;

    public UploadService() {
        ftpClient = new FTPClient();
    }

    public boolean upload(byte[] data, String name){
        boolean uploaded = false;
        try {
            ftpClient.connect(IP, PORT);
            ftpClient.login(LOGIN, PASSWORD);
            ftpClient.enterLocalPassiveMode();

            uploaded = ftpClient.storeFile(name, new ByteArrayInputStream(data));
            ftpClient.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return uploaded;
    }
}
