package com.chen.web.rest;

import com.chen.service.WeChatTokenService;
import com.chen.service.WechatAccountService;
import com.chen.service.dto.WechatAccountDTO;
import com.chen.utils.CommonResult;
import com.chen.utils.RetrofitFactory;
import com.chen.utils.WechatUtil;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retrofit2.Call;
import retrofit2.Response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

/**
 * @author chen
 */
@RestController
@RequestMapping("/api/global")
public class WechatResource {
    private static final Logger log = LoggerFactory.getLogger(WechatResource.class);
    private static final String VALIDATE_TOKEN = "testweixin";
    private static final char[] HEX = {'0', '1', '2', '3', '4', '5',
        '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final String APP_ID = "wx7d7d4163b5995846";
    private static final String APP_SECRET = "2a714158b7719ae7974e90f33ed493a6";
    private final WechatAccountService wechatAccountService;

    public WechatResource(WechatAccountService wechatAccountService, Environment env) {
        this.wechatAccountService = wechatAccountService;
    }

    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (byte aByte : bytes) {
            buf.append(HEX[(aByte >> 4) & 0x0f]);
            buf.append(HEX[aByte & 0x0f]);
        }
        return buf.toString();
    }

    @RequestMapping(value = "/validate", method = {RequestMethod.POST, RequestMethod.GET})
    public void validateWxServer(HttpServletRequest request, HttpServletResponse response) {
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        String signature = request.getParameter("signature");
        // 1）将token、timestamp、nonce三个参数进行字典序排序 2）将三个参数字符串拼接成一个字符串进行sha1加密 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        String[] strs = {VALIDATE_TOKEN, timestamp, nonce};
        Arrays.sort(strs);
        String str = strs[0] + strs[1] + strs[2];
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(str.getBytes());
            String formattedText = getFormattedText(messageDigest.digest());
            if (formattedText.equals(signature)) {
                response.getWriter().print(echostr);
            }
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/login/app", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<Map<String, Object>> wechatAppLogin(@RequestParam("encryptedData") String encryptedData,
                                                              @RequestParam("iv") String iv,
                                                              @RequestParam("code") String code) throws IOException {
        WeChatTokenService tokenApi = RetrofitFactory.getInstance().create(WeChatTokenService.class);
        Call<Map<String, Object>> callResult = tokenApi.getAppToken(APP_ID, APP_SECRET, code, "authorization_code");
        Response<Map<String, Object>> response = callResult.execute();

        log.info("small app token: {}, {}, {}", response.errorBody(), response.body(), response.message());

        String sessionKey = response.body().get("session_key") != null ? String.valueOf(response.body().get("session_key")) : null;
        log.info("small app info: {}", response.body());

        String decryptData = WechatUtil.decryptData(encryptedData, sessionKey, iv);
        log.info("decryptData: {}", decryptData);
        Gson gson = new Gson();
        @SuppressWarnings("all") Type jsonType = new TypeToken<Map<String, Object>>() {
        }.getType();
        Map<String, Object> map = gson.fromJson(decryptData, jsonType);
        log.info("json format: {}", map);

        return ResponseEntity.ok(map);
    }

    @PostMapping("/account")
    public CommonResult<Boolean> updateOrCreate(@RequestBody WechatAccountDTO payload) {
        log.info("更新或新建微信小程序用户; payload => {}", payload);
        try {
            wechatAccountService.createOrUpdate(payload);
            return CommonResult.success(true);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }
    }
}
