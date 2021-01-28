package com.wmj.design.travelsocial.common.exception.file;

/**
 * 文件名大小限制异常类
 *
 * @author weimj
 */
public class FileSizeLimitExceededException extends FileException {


    public FileSizeLimitExceededException(long defaultMaxSize) {
        super("upload.exceed.maxSize", new Object[]{defaultMaxSize});
    }
}
