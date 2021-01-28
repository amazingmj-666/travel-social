package com.wmj.design.travelsocial.common.exception.file;

import com.wmj.design.travelsocial.common.exception.base.BaseException;

/**
 * 文件信息异常类
 *
 * @author liumd
 */
public class FileException extends BaseException {


    public FileException(String code, Object[] args) {
        super("file", code, args, null);
    }

}
