package com.nrsc.security.browser.session;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nrsc.security.enums.ResultEnum;
import com.nrsc.security.utils.ResultVOUtil;
import com.nrsc.security.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author : Sun Chuan
 * @date : 2019/9/21 17:14
 * Description：
 * 如果设置的session并发策略为一个账户第二次登陆会将第一次给踢下来
 * 则第一次登陆的用户再访问我们的项目时会进入到该类
 * event里封装了request、response信息
 */
@Slf4j
public class NRSCExpiredSessionStrategy implements SessionInformationExpiredStrategy {

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        String header = event.getRequest().getHeader("user-agent");
        log.info("浏览器信息为：{}", header);

        //告诉前端并发登陆异常
        event.getResponse().setContentType("application/json;charset=UTF-8");
        event.getResponse().getWriter().write("并发登陆！！！");
    }
}
