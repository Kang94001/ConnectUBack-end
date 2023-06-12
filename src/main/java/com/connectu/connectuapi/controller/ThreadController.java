package com.connectu.connectuapi.controller;

import com.connectu.connectuapi.controller.util.Code;
import com.connectu.connectuapi.controller.util.Result;
import com.connectu.connectuapi.domain.Thread;
import com.connectu.connectuapi.domain.User;
import com.connectu.connectuapi.exception.file.*;
import com.connectu.connectuapi.service.IReplyService;
import com.connectu.connectuapi.service.IThreadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Api(tags ="論壇")
@RestController
@RequestMapping("/threads")
public class ThreadController extends BaseController{
    @Autowired
    private IThreadService threadService;

    //假資料
    @ApiIgnore    // 忽略这个api
   // @PostMapping("/addFakeThread")
    public String addFakeThread() {
        threadService.addFakeThread(50);
        return "Fake Thread added successfully!";
    }
    //新增論壇文章
    @PostMapping
    @ApiOperation("新增論壇文章")
    public Result save(Thread thread , @Nullable @RequestParam("file") MultipartFile file, HttpSession session) {

        Integer getThreadId = threadService.getLastThreadById();
        //session.setAttribute("threadId", getThreadId);
        Integer userId = (Integer) session.getAttribute("userId");
        String fileUrl = null;;
        if(file != null && !file.isEmpty()) {
            fileUrl = upload(file,session);
        }
        boolean flag = threadService.saveThread(thread,userId,fileUrl);
        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR, flag, flag ?"論壇文章新增成功":"論壇文章新增失敗");
    }
    //查詢所有文章
    @GetMapping
    @ApiOperation("查詢所有論壇文章")
    public Result getAllThread() {
        List<Thread> thread = threadService.list(null);
        System.out.println(thread);
        Integer code = thread != null ? Code.GET_OK : Code.GET_ERR;
        String msg = thread != null ? "所有論壇文章資料成功" : "查無論壇文章資料";
        return new Result(code, thread, msg);
    }
    //修改文章
    @PutMapping
    @ApiOperation("修改論壇文章")
    public Result updateById(@RequestBody Thread thread) {
//        Integer getUserId = (Integer) session.getAttribute("userId");
//        Integer getThreadId = (Integer) session.getAttribute("threadId");
//        String fileUrl = null;;
//        if(file != null && !file.isEmpty()) {
//            fileUrl = upload(file,session);
//        }
        //boolean flag = threadService.putThreadById(thread, getUserId, getThreadId,fileUrl);
        boolean flag = threadService.updateById(thread);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR, flag, flag ? "論壇文章更新成功" : "論壇文章更新失敗");
    }
    //刪除文章
    @ApiImplicitParam(name = "Threadid", value = "論壇文章id")
    @DeleteMapping("/{Threadid}")
    @ApiOperation("刪除論壇文章")
    public Result deleteById(@PathVariable Integer Threadid) {
        boolean flag = threadService.removeById(Threadid);
        return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERR, flag, flag ?"論壇文章刪除成功":"論壇文章刪除失敗");
    }
    //查詢單筆論壇
    @GetMapping("/{id}")
    @ApiOperation("查詢單筆論壇文章")
    public Result getUserById(@PathVariable Integer id, HttpSession session) {
        Thread thread = threadService.getById(id);
        session.setAttribute("threadId", thread.getThreadId()); // 修改這一行
        Integer code = thread != null ? Code.GET_OK : Code.GET_ERR;
        String msg = thread != null ? "論壇文章資料取得成功" : "查無資料";
        return new Result(code, thread, msg);
    }
    //查詢單筆論壇
    @GetMapping("last")
    @ApiOperation("查詢最後一筆論壇文章")
    public Result getUserlastById() {
        Integer getThreadId = threadService.getLastThreadById();
        //System.out.println(getThreadId);
        Thread thread = threadService.getById(getThreadId-1);
        Integer code = thread != null ? Code.GET_OK : Code.GET_ERR;
        String msg = thread != null ? "最後一筆資料取得成功" : "查無資料";
        return new Result(code, thread, msg);
    }
}
