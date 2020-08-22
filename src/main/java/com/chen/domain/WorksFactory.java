package com.chen.domain;

import com.chen.security.SecurityUtils;
import com.chen.security.UserNotActivatedException;
import com.github.wujun234.uid.UidGenerator;
import org.springframework.stereotype.Service;

/**
 * @author chen
 */
@Service
public class WorksFactory {
    private final UidGenerator defaultUidGenerator;

    public WorksFactory(UidGenerator defaultUidGenerator) {
        this.defaultUidGenerator = defaultUidGenerator;
    }

    public Works submitWork(String author, String college, String grade, String phoneNumber, String worksName, String workDesc,
                            String teacher, String teacherCollege, String workCategory, String worksType, Integer submitType,
                            String fileName, String filePath, Integer duration, String durationDesc) {
        String login = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new UserNotActivatedException("没有找到用户信息"));
        long uid = defaultUidGenerator.getUID();
        WorksId worksId = new WorksId(String.valueOf(uid));
        Works works = Works.submitWorks(worksId, login, author, college, grade, phoneNumber, worksName, workDesc, teacher, teacherCollege, workCategory, worksType, submitType);
        if (filePath != null) {
            WorksFile worksFile = new WorksFile(fileName, filePath, duration, durationDesc);
            works.addFile(worksFile);
        }
        return works;
    }
}
