package pl.oskarpolak.ormtest.models.services;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class UploadService {
    private final static String LOGIN = "akademiakodu1";
    private final static String PASSWORD = "akademia";
    private final static String IP = "5.135.218.27";
    private final static int PORT = 21;

    private FTPClient ftpClient;

    public UploadService() {
        ftpClient = new FTPClient();
    }

    @Async
    public void upload(byte[] data, String name){
        try {
            ftpClient.connect(IP, PORT);
            ftpClient.login(LOGIN, PASSWORD);
            ftpClient.enterLocalPassiveMode();

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            ftpClient.storeFile(name, new ByteArrayInputStream(data));
            ftpClient.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
