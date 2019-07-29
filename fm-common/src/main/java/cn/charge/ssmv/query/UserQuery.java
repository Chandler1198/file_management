package cn.charge.ssmv.query;

import org.springframework.util.StringUtils;

public class UserQuery extends BaseQuery{

    //关键字查询
    private String keyword;

    public String getKeyword() {
        if(StringUtils.hasLength(keyword)){
            return keyword.trim();
        }
        return null;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}