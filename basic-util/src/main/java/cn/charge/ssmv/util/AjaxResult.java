package cn.charge.ssmv.util;

/**
 * 这个类用来返回ajax对象，并且私有化字段，只能通过构造方法赋值
 *
 */
public class AjaxResult {
    private Boolean success = true;
    private String msg;

    private AjaxResult() {
    }

    /**
     * 返回ajax
     * @return 没有错误就返回这个
     */
    public static AjaxResult success(){
        return new AjaxResult();
    }

    /**
     *  返回ajax
     * @param msg 错误信息
     * @return 有错误就返回这个
     */
    public static AjaxResult error(String msg){
        AjaxResult ajaxResult = success();;
        ajaxResult.setSuccess(false);
        ajaxResult.setMsg(msg);
        return ajaxResult;
    }

    public Boolean getSuccess() {
        return success;
    }

    private void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    private void setMsg(String msg) {
        this.msg = msg;
    }
}
