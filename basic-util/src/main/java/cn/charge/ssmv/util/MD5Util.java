package cn.charge.ssmv.util;

import org.apache.shiro.crypto.hash.SimpleHash;

/*加密类
* */
public class MD5Util {
    //加密方式
    public static final String ALGORITHMNAME = "MD5";
    //盐值
    public static final String SALT = "fm";
    //加密次数
    public static final int HASHITERATIONS = 10;

    /**
     * MD5加密
     * @param source  要加密的内容
     * @return
     */
    public static String createMd5(String source){
        SimpleHash hash = new SimpleHash(ALGORITHMNAME,source,SALT,HASHITERATIONS);
        return hash.toString();
    }
}
