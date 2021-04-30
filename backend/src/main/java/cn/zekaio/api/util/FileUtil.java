package cn.zekaio.api.util;

import cn.zekaio.api.exception.BusinessException;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypes;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileUtil {
    // 保存文件
    public String saveMultipartFile(MultipartFile multipartFile, String path) {
        if (multipartFile == null) {
            return "";
        }
        String extensionName;
        try {
            MimeTypes mimeTypes = MimeTypes.getDefaultMimeTypes();
            MimeType mimeType = mimeTypes.forName(multipartFile.getContentType());
            extensionName = mimeType.getExtension();
        } catch (Exception e) {
            extensionName = "";
        }
        String fileName = UUID.randomUUID() + extensionName;
        File file = new File(path, fileName);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            throw new BusinessException(500, "保存图片失败" + e);
        }

        return fileName;
    }

    // 批量保存文件
    public List<String> getFileNames(MultipartFile[] multipartFiles, String path) {
        List<String> fileNames = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            String fileName = saveMultipartFile(multipartFile, path);
            fileNames.add(fileName);
        }
        return fileNames;
    }
}
