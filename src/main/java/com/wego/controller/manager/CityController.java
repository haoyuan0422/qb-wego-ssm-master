package com.wego.controller.manager;

import com.wego.bean.Notification;
import com.wego.common.bean.EntryBean;
import com.wego.common.bean.PageBean;
import com.wego.common.utils.QueryUtil;
import com.wego.converter.CityConverter;
import com.wego.converter.ProvinceConverter;
import com.wego.converter.trans.CityTrans;
import com.wego.entity.domain.City;
import com.wego.entity.domain.Province;
import com.wego.entity.query.CityQuery;
import com.wego.entity.vo.CityVO;
import com.wego.ex.GlobalException;
import com.wego.service.CityService;
import com.wego.service.ProvinceService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 城市控制器
 *
 * @author hc
 */
@Controller
@RequestMapping("/manager/city")
public class CityController {
    @Autowired
    private CityService cityService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private ProvinceConverter provinceConverter;

    @Autowired
    private CityConverter cityConverter;

    @Autowired
    private CityTrans cityTrans;

    /**
     * 省份列表
     * @param cityQuery
     * @param model
     * @param notification
     * @return
     */
    @GetMapping("/list")
    public String list(CityQuery cityQuery, Model model, @ModelAttribute("notification") Notification notification) {
        //构造动态查询条件
        String keywords = cityQuery.getKeywords();
        if (keywords != null) {
            if (cityQuery.getName() == null) {
                cityQuery.setName(keywords);
            }
        }

        final PageBean<City> cityPageBean = cityService.selectPage(cityQuery);
        //转换
        PageBean<CityVO> pageBean = cityTrans.getInstance().tPageBean2VPageBean(cityPageBean);

        //分页时携带上查询条件
        String params = QueryUtil.genQueryCondition(cityQuery);
        pageBean.setUrl("manager/city/list?" + params);
        model.addAttribute("pageBean", pageBean);

        //查询条件数据回显时使用
        model.addAttribute("cityQuery", cityQuery);

        //查询到所有的省份信息
        final List<Province> provinces = provinceService.selectAll();
        final List<EntryBean<String>> provinceList = provinceConverter.provinceList2EntryList(provinces);
        model.addAttribute("provinceList", provinceList);

        //增删改操作完返回到列表页面的提示信息
        if (notification != null) {
            model.addAttribute("notification", notification);
        }

        return "manager/city";
    }

    /**
     * 省份详情
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/details")
    public String details(Long id, Model model) {
        City city = cityService.selectByPrimaryKey(id);

        model.addAttribute("city", city);

        return "manager/city_details";
    }

    /**
     * 打开添加修改页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/openAddUpdate")
    public String openAddUpdate(Long id, Model model) {
        //查询到所有的省份信息
        final List<Province> provinces = provinceService.selectAll();
        final List<EntryBean<String>> provinceList = provinceConverter.provinceList2EntryList(provinces);
        model.addAttribute("provinceList", provinceList);
        if (id != null) {
            City city = cityService.selectByPrimaryKey(id);
            model.addAttribute("city", city);
            model.addAttribute("title", "修改省份");
        } else {
            model.addAttribute("title", "添加省份");
        }

        return "manager/city_add_update";
    }

    /**
     * 具体执行添加修改操作
     * @param city
     * @return
     */
    @PostMapping("/doAddUpdate")
    public String doAddUpdate(City city, RedirectAttributes redirectAttributes) {
        city.setUpdateTime(LocalDateTime.now());

        Notification notification;
        Long id = city.getId();

        if (id != null) {
            //修改
            boolean res = cityService.updateByPrimaryKeySelective(city);
            String msg = res ? "修改成功！" : "修改失败";
            notification = new Notification(res, msg);
        } else {
            //添加
            city.setCreateTime(LocalDateTime.now());
            boolean res = cityService.insertSelective(city);
            String msg = res ? "添加成功！" : "添加失败";
            notification = new Notification(res, msg);
        }

        //返回列表页面时，对话框中显示的提示信息
        redirectAttributes.addFlashAttribute("notification", notification);

        //重定向
        return "redirect:list";
    }

    /**
     * 修改状态
     * @param city
     * @return
     */
    @GetMapping("/changeState")
    public String changeState(City city, RedirectAttributes redirectAttributes) {
        Integer state = city.getState();
        Long id = city.getId();
        if (state == null && id == null) {
            throw new GlobalException(400, "执行修改状态时提交的参数不正确", "list");
        }

        //修改状态的值
        city.setState(state == 1 ? 0 : 1);
        city.setUpdateTime(LocalDateTime.now());

        boolean res = cityService.updateByPrimaryKeySelective(city);

        //返回列表页面时，对话框中显示的提示信息
        String msg = res ? "更新成功！" : "更新失败";
        Notification notification = new Notification(res, msg);
        redirectAttributes.addFlashAttribute("notification", notification);

        return "redirect:list";
    }

    /**
     * 根据主键删除
     * @param id
     * @return
     */
    @GetMapping("/deleteById")
    public String deleteById(Long id, RedirectAttributes redirectAttributes) {
        boolean res = cityService.deleteByPrimaryKey(id);

        //返回列表页面时，对话框中显示的提示信息
        String msg = res ? "删除成功！" : "删除失败";
        Notification notification = new Notification(res, msg);
        redirectAttributes.addFlashAttribute("notification", notification);

        return "redirect:list";
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @GetMapping("/batchDelete")
    public String batchDelete(String ids, RedirectAttributes redirectAttributes) {
        boolean res = cityService.batchDelete(ids);

        //返回列表页面时，对话框中显示的提示信息
        String msg = res ? "批量删除成功！" : "批量删除失败";
        Notification notification = new Notification(res, msg);
        redirectAttributes.addFlashAttribute("notification", notification);

        return "redirect:list";
    }

    @ResponseBody
    @GetMapping("/selectCityByProvinceId")
    List<EntryBean<String>> selectCityByProvinceId(Long provinceId) {
        final List<City> cityList = cityService.selectList(City.builder()
                .provinceId(provinceId)
                .build());
        final List<EntryBean<String>> entryBeanList = cityConverter.cityList2EntryList(cityList);
        return entryBeanList;
    }
}
