package com.connectu.connectuapi.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.connectu.connectuapi.dao.ReplyDao;
import com.connectu.connectuapi.dao.impl.Reply;
import com.connectu.connectuapi.service.IReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import static com.connectu.connectuapi.service.utils.faker.generateFakeArticle;
@Service
public class ReplyServiceImpl extends ServiceImpl<ReplyDao, Reply> implements IReplyService {
    @Autowired
    private ReplyDao replyDao;
    @Override
    public void addFakeReply(int count) {
        for (int i = 0; i < count; i++) {
            Reply fakeReply = ReplyServiceImpl.createFakeReply(count);
            replyDao.insert(fakeReply);
        }
    }
    public static Reply createFakeReply(int count) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
        String formattedDate = dateFormat.format(currentDate);
        Reply Reply = new Reply();
        Reply.setThreadId((int) (Math.random() * count) + 1);
        Reply.setUserId((int) (Math.random() * count) + 1);
        Reply.setContent(generateFakeArticle(count));
        Reply.setCreatedAt(formattedDate);
        return Reply;
    }
}
