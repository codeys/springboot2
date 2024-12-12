package com.springboot2.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 * @version 1.0
 * @title PageController
 * @description 页面跳转
 * @create 2024/12/4 16:28
 */
@Controller
public class PageController {

    /**
     * 首页
     * @return
     */
    @RequestMapping("/index.html")
    public String index() {
        return "index";
    }

    /**
     * 菜单-欢迎页
     * @return
     */
    @RequestMapping("/welcome.html")
    public String welcome() {
        return "welcome";
    }

    /**
     * 菜单-颜色
     * @return
     */
    @RequestMapping("/color.html")
    public String colorPage() {
        return "menu/color";
    }

    /**
     * 菜单-文本
     * @return
     */
    @RequestMapping("/text.html")
    public String textPage() {
        return "menu/text";
    }

    /**
     * 菜单-边框
     * @return
     */
    @RequestMapping("/border.html")
    public String borderPage() {
        return "menu/border";
    }

    /**
     * 菜单-分割线
     * @return
     */
    @RequestMapping("/divider.html")
    public String dividerPage() {
        return "menu/divider";
    }

    /**
     * 菜单-组件-按钮
     * @return
     */
    @RequestMapping("/button.html")
    public String buttonPage() {
        return "menu/button";
    }

    /**
     * 菜单-组件-面包屑
     * @return
     */
    @RequestMapping("/breadcrumbs.html")
    public String breadcrumbsPage() {
        return "menu/breadcrumbs";
    }

    /**
     * 菜单-组件-分页
     * @return
     */
    @RequestMapping("/pages.html")
    public String pagesPage() {
        return "menu/pages";
    }

    /**
     * 菜单-组件-步骤条
     * @return
     */
    @RequestMapping("/step.html")
    public String stepPage() {
        return "menu/step";
    }

    /**
     * 菜单-组件-时间轴
     * @return
     */
    @RequestMapping("/timeline.html")
    public String timelinePage() {
        return "menu/timeline";
    }

    /**
     * 菜单-组件-表单控件-输入框
     * @return
     */
    @RequestMapping("/input.html")
    public String inputPage() {
        return "menu/input";
    }
    /**
     * 菜单-组件-表单控件-多选框
     * @return
     */
    @RequestMapping("/checkbox.html")
    public String checkboxPage() {
        return "menu/checkbox";
    }
    /**
     * 菜单-组件-表单控件-下拉框
     * @return
     */
    @RequestMapping("/select.html")
    public String selectPage() {
        return "menu/select";
    }
    /**
     * 菜单-组件-表单控件-时间选择框
     * @return
     */
    @RequestMapping("/laydate.html")
    public String laydatePage() {
        return "menu/laydate";
    }

    /**
     * 菜单-组件-树
     * @return
     */
    @RequestMapping("/tree.html")
    public String treePage() {
        return "menu/tree";
    }

    /**
     * 菜单-组件-穿梭框
     * @return
     */
    @RequestMapping("/shuttle.html")
    public String shuttlePage() {
        return "menu/shuttle";
    }

    /**
     * 菜单-组件-上传
     * @return
     */
    @RequestMapping("/upload.html")
    public String uploadPage() {
        return "menu/upload";
    }

    /**
     * 菜单-组件-图片
     * @return
     */
    @RequestMapping("/picture.html")
    public String picturePage() {
        return "menu/picture";
    }

    /**
     * 菜单-栅格
     * @return
     */
    @RequestMapping("/grid.html")
    public String gridPage() {
        return "menu/grid";
    }

    /**
     * 菜单-布局
     * @return
     */
    @RequestMapping("/layout.html")
    public String layoutPage() {
        return "menu/layout";
    }

    /**
     * 菜单-组件-表单示例
     * @return
     */
    @RequestMapping("/form.html")
    public String formPage() {
        return "menu/form";
    }

    /**
     * 菜单-组件-表格示例
     * @return
     */
    @RequestMapping("/table.html")
    public String tablePage() {
        return "menu/table";
    }

    /**
     * 菜单-数据表格示例
     * @return
     */
    @RequestMapping("/tables.html")
    public String tablesPage() {
        return "menu/tables";
    }

    /**
     * 菜单-弹出层示例
     * @return
     */
    @RequestMapping("/dialog.html")
    public String dialogPage() {
        return "menu/dialog";
    }

    /**
     * 菜单-缺省页示例-404
     * @return
     */
    @RequestMapping("/default_404.html")
    public String default404Page() {
        return "menu/default/default404";
    }
    /**
     * 菜单-缺省页示例-505
     * @return
     */
    @RequestMapping("/default_505.html")
    public String default505Page() {
        return "menu/default/default505";
    }
    /**
     * 菜单-缺省页示例-页面错误
     * @return
     */
    @RequestMapping("/default_error.html")
    public String defaultErrorPage() {
        return "menu/default/defaultError";
    }
    /**
     * 菜单-缺省页示例-没有权限
     * @return
     */
    @RequestMapping("/default_permissions.html")
    public String defaultPermissionsPage() {
        return "menu/default/defaultPermissions";
    }
    /**
     * 菜单-缺省页示例-页面超时
     * @return
     */
    @RequestMapping("/default_overtime.html")
    public String default_overtimePage() {
        return "menu/default/defaultOvertime";
    }
    /**
     * 菜单-缺省页示例-暂无内容
     * @return
     */
    @RequestMapping("/default_empty.html")
    public String defaultEmptyPage() {
        return "menu/default/defaultEmpty";
    }
}
