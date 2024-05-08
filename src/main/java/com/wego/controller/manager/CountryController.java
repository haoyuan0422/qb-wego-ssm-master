package com.wego.controller.manager;

import com.wego.bean.Notification;
import com.wego.common.bean.EntryBean;
import com.wego.common.bean.PageBean;
import com.wego.common.utils.QueryUtil;
import com.wego.converter.CountryConverter;
import com.wego.converter.ProvinceConverter;
import com.wego.converter.trans.CountryTrans;
import com.wego.entity.domain.City;
import com.wego.entity.domain.Country;
import com.wego.entity.domain.Province;
import com.wego.entity.query.CountryQuery;
import com.wego.entity.vo.CountryVO;
import com.wego.ex.GlobalException;
import com.wego.service.CityService;
import com.wego.service.CountryService;
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
 * 县区控制器
 *
 * @author hc
 */
@Controller
@RequestMapping("/manager/country")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private CityService cityService;

    @Autowired
    private ProvinceConverter provinceConverter;

    @Autowired
    private CountryConverter countryConverter;

    @Autowired
    private CountryTrans countryTrans;

    /**
     * 省份列表
     *
     * @param countryQuery
     * @param model
     * @param notification
     * @return
     */
    @GetMapping("/list")
    public String list(CountryQuery countryQuery, Model model, @ModelAttribute("notification") Notification notification) {
        //构造动态查询条件
        String keywords = countryQuery.getKeywords();
        if (keywords != null) {
            if (countryQuery.getName() == null) {
                countryQuery.setName(keywords);
            }
        }

        PageBean<Country> countryPageBean = countryService.selectPage(countryQuery);
        //转换
        PageBean<CountryVO> pageBean = countryTrans.getInstance().tPageBean2VPageBean(countryPageBean);

        //分页时携带上查询条件
        String params = QueryUtil.genQueryCondition(countryQuery);
        pageBean.setUrl("manager/country/list?" + params);
        model.addAttribute("pageBean", pageBean);

        //查询条件数据回显时使用
        model.addAttribute("countryQuery", countryQuery);

        //增删改操作完返回到列表页面的提示信息
        if (notification != null) {
            model.addAttribute("notification", notification);
        }

        return "manager/country";
    }

    /**
     * 省份详情
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/details")
    public String details(Long id, Model model) {
        Country country = null;
        country = countryService.selectByPrimaryKey(id);
        model.addAttribute("country", country);

        return "manager/country_details";
    }

    /**
     * 打开添加修改页面
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/openAddUpdate")
    public String openAddUpdate(Long id, Model model) {
        final List<Province> provinceList = provinceService.selectAll();
        final List<EntryBean<String>> entryBeanList = provinceConverter.provinceList2EntryList(provinceList);
        model.addAttribute("provinceList", entryBeanList);

        if (id != null) {
            Country country = countryService.selectByPrimaryKey(id);
            model.addAttribute("country", country);

            final City city = cityService.selectByPrimaryKey(country.getCityId());
            final Province province = provinceService.selectByPrimaryKey(city.getProvinceId());
            model.addAttribute("city", city);
            model.addAttribute("province", province);
            model.addAttribute("title", "修改区县");
        } else {
            model.addAttribute("title", "添加区县");
        }

        return "manager/country_add_update";
    }

    /**
     * 具体执行添加修改操作
     *
     * @param country
     * @return
     */
    @PostMapping("/doAddUpdate")
    public String doAddUpdate(Country country, Long provinceId, RedirectAttributes redirectAttributes, Model model) {
        country.setUpdateTime(LocalDateTime.now());
        Notification notification;
        Long id = country.getId();

        if (id != null) {
            //修改
            boolean res = countryService.updateByPrimaryKeySelective(country);
            String msg = res ? "修改成功！" : "修改失败";
            notification = new Notification(res, msg);
        } else {
            //添加
            country.setCreateTime(LocalDateTime.now());
            boolean res = countryService.insertSelective(country);
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
     *
     * @param country
     * @return
     */
    @GetMapping("/changeState")
    public String changeState(Country country, RedirectAttributes redirectAttributes) {
        Integer state = country.getState();
        Long id = country.getId();
        if (state == null && id == null) {
            throw new GlobalException(400, "执行修改状态时提交的参数不正确", "list");
        }

        //修改状态的值
        country.setState(state == 1 ? 0 : 1);
        country.setUpdateTime(LocalDateTime.now());

        boolean res = countryService.updateByPrimaryKeySelective(country);
        //返回列表页面时，对话框中显示的提示信息
        String msg = res ? "更新成功！" : "更新失败";
        Notification notification = new Notification(res, msg);
        redirectAttributes.addFlashAttribute("notification", notification);

        return "redirect:list";
    }

    /**
     * 根据主键删除
     *
     * @param id
     * @return
     */
    @GetMapping("/deleteById")
    public String deleteById(Long id, RedirectAttributes redirectAttributes) {
        boolean res = countryService.deleteByPrimaryKey(id);

        //返回列表页面时，对话框中显示的提示信息
        String msg = res ? "删除成功！" : "删除失败";
        Notification notification = new Notification(res, msg);
        redirectAttributes.addFlashAttribute("notification", notification);

        return "redirect:list";
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @GetMapping("/batchDelete")
    public String batchDelete(String ids, RedirectAttributes redirectAttributes) {
        boolean res;
        res = countryService.batchDelete(ids);

        //返回列表页面时，对话框中显示的提示信息
        String msg = res ? "批量删除成功！" : "批量删除失败";
        Notification notification = new Notification(res, msg);
        redirectAttributes.addFlashAttribute("notification", notification);

        return "redirect:list";
    }

    @ResponseBody
    @GetMapping("/selectCountryByCityId")
    List<EntryBean<String>> selectCountryByCityId(Long cityId) {
        final List<Country> countryList = countryService.selectList(Country.builder()
                .cityId(cityId)
                .build());
        return countryConverter.countryList2EntryList(countryList);
    }
}
