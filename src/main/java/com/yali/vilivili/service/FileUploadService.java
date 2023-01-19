package com.yali.vilivili.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author fuqianlin
 * @date 2023-01-18 21:58
 */
public interface FileUploadService {

    /**
     * 图片上传
     * @param file 文件
     * @return url
     */
    String imageUpload(MultipartFile file);

    /**
     * 根据路径返回图片在线预览地址
     * @param path 路径
     * @return url
     */
    String getImageUrl(String path);

}