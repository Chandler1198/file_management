package cn.charge.ssmv.web.controller;



import cn.charge.ssmv.domain.User;
import cn.charge.ssmv.query.UserQuery;
import cn.charge.ssmv.service.UserService;
import cn.charge.ssmv.util.AjaxResult;
import cn.charge.ssmv.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据id查询用户
     *
     */
    @RequestMapping("/getById")
    @ResponseBody
    public User getById(Long id) {
       return userService.selectByPrimaryKey(id);
    }
    /**
     * 用户主界面
     */
    @RequestMapping("/index")
    public String index(Model model){
        return "user";
    }

    /**
     * 用户列表数据
     *
     */
    @RequestMapping("/list")
    @ResponseBody
    public PageResult list(UserQuery qo){
        return userService.selectAllForList(qo);
    }


//    /**
//     * 用户添加/修改
//     */
//    @RequestMapping("/saveOrUpdate")
//    @ResponseBody
//    public AjaxResult saveOrUpdate(User user){
//        try {
//            if(user.getId() == null){
//                userService.insert(user);
//            }else{
//                userService.updateByPrimaryKey(user);
//            }
//            return AjaxResult.success();
//        }catch (Exception e){
//            e.printStackTrace();
//            return AjaxResult.error("啊,系统异常啦,我们正在殴打程序员O(∩_∩)O~");
//        }
//    }
    /**
     * 用户添加/修改
     */
    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult add(User user){
        System.out.println(user);
        try {
            if(user.getId() == null){
//                userService.insert(user);
                userService.updateByPrimaryKey(user);
            }else{
//                userService.updateByPrimaryKey(user);
                userService.insert(user);
            }
            return AjaxResult.success();
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error("啊,系统异常啦,我们正在殴打程序员O(∩_∩)O~");
        }

    }

    /**
     *
     *批量删除
     */
    @RequestMapping("/batchDelete")
    @ResponseBody
    public AjaxResult batchDelete(Long[] ids){
        try {
            for (Long id : ids) {
                userService.deleteByPrimaryKey(id);
            }
            return AjaxResult.success();
        } catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 用户删除
     */
    @RequestMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Long id){
        try {
            userService.deleteByPrimaryKey(id);
            return AjaxResult.success();
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error("啊,系统异常啦,我们正在殴打程序员O(∩_∩)O~");
        }
    }
}
