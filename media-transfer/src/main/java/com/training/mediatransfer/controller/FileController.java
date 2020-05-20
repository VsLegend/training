package com.training.mediatransfer.controller;

import com.training.mediatransfer.utils.FileDownloadUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Wangjunwei
 * @Date 2019/10/16 17:21
 * @Description 文件上传下载接口
 */

@Api(tags = "文件上传下载接口")
@RestController
@RequestMapping("/file")
public class FileController {

  @Value("${mediatransfer.api.url}")
  private String targetUrl;

  @ApiOperation("文件上传")
  @PostMapping("/uploadFile")
  public String uploadFile(@RequestParam("filename") String filename,
      @RequestParam("file") MultipartFile file) throws IOException {
    if (file.isEmpty()) {
      return "上传内容为空";
    }
    byte[] bytes = file.getBytes();
    FileOutputStream out = null;
    File path = new File(targetUrl + "/" + filename);
    try {
      out = new FileOutputStream(path);
      out.write(bytes);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return path.getAbsolutePath();
  }

  @GetMapping("downloadFile")
  @ApiOperation(value = "下载文件", notes = "下载文件")
  public void downloadFile(
      @RequestParam("filename") String filename,
      @RequestParam("url") String url,
      HttpServletRequest request,
      HttpServletResponse response) {
    FileDownloadUtils
        .downloadFiles(response, request, filename, url);
  }

//  @PostMapping("/jz/imgCode")
//  @ApiOperation(value = "卷宗图片base64编码", notes = "卷宗图片base64编码")
//  public APIResult base64Img(@RequestBody ImgCodeRequestDTO dto) {
//    return APIResult.success(FileDownloadUtils.base64Img(dto.getFileName()));
//  }
}
