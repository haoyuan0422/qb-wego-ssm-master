package com.wego.controller.manager;

import com.wego.bean.Notification;
import com.wego.common.bean.EntryBean;
import com.wego.common.bean.PageBean;
import com.wego.common.utils.QueryUtil;
import com.wego.converter.trans.AddressTrans;
import com.wego.entity.domain.Address;
import com.wego.entity.domain.City;
import com.wego.entity.domain.Country;
import com.wego.entity.domain.Province;
import com.wego.entity.query.AddressQuery;
import com.wego.entity.vo.AddressVO;
import com.wego.ex.GlobalException;
import com.wego.service.AddressService;
import com.wego.service.CityService;
import com.wego.service.CountryService;
import com.wego.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;


/**
 * 地址控制器
 *
 * @author hc
 */
@Controller
@RequestMapping("/manager/address")
public class AddressController {
    /**
     * 服务对象
     */
    @Autowired
    private AddressService addressService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private CityService cityService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private AddressTrans addressTrans;

    /**
     * 省份列表
     *
     * @param addressQuery
     * @param model
     * @param notification
     * @return
     */
    @GetMapping("/list")
    public String list(AddressQuery addressQuery, Model model, @ModelAttribute("notification") Notification notification) {
        //构造动态查询条件
        String keywords = addressQuery.getKeywords();
        if (keywords != null) {
            if (addressQuery.getReceiver() == null) {
                addressQuery.setReceiver(keywords);
            }
            if (addressQuery.getAddr() == null) {
                addressQuery.setAddr(keywords);
            }
            if (addressQuery.getPhone() == null) {
                addressQuery.setPhone(keywords);
            }
        }

        final PageBean<Address> addressPageBean = addressService.selectPage(addressQuery);
        //转换
        PageBean<AddressVO> pageBean = addressTrans.getInstance().tPageBean2VPageBean(addressPageBean);

        //分页时携带上查询条件
        String params = QueryUtil.genQueryCondition(addressQuery);
        pageBean.setUrl("manager/address/list?" + params);
        model.addAttribute("pageBean", pageBean);

        //查询条件数据回显时使用
        model.addAttribute("addressQuery", addressQuery);

        //增删改操作完返回到列表页面的提示信息
        if (notification != null) {
            model.addAttribute("notification", notification);
        }

        return "manager/address";
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
        Address address = addressService.selectByPrimaryKey(id);
        model.addAttribute("address", address);
        //省市县
        final List<EntryBean<String>> entryBeanList = countryService.selectProvinceCityCountryByCountryId(address.getCountryId());
        model.addAttribute("pccList", entryBeanList);
        return "manager/address_details";
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
        Address address = addressService.selectByPrimaryKey(id);
        model.addAttribute("address", address);

        //获取顶级商品类别
        final List<Province> provinceList = provinceService.selectAll();
        model.addAttribute("provinceList", provinceList);

        if (id != null) {
            Country country = countryService.selectByPrimaryKey(address.getCountryId());
            City city = cityService.selectByPrimaryKey(country.getCityId());
            Province province = provinceService.selectByPrimaryKey(city.getProvinceId());
            model.addAttribute("province", province);
            model.addAttribute("city", city);
            model.addAttribute("country", country);
            model.addAttribute("title", "修改地址");
        } else {
            model.addAttribute("title", "添加地址");
        }

        return "manager/address_add_update";
    }

    /**
     * 具体执行添加修改操作
     *
     * @param address
     * @return
     */
    @PostMapping("/doAddUpdate")
    public String doAddUpdate(Address address, RedirectAttributes redirectAttributes) {
        address.setUpdateTime(LocalDateTime.now());

        Notification notification;
        Long id = address.getId();

        if (id != null) {
            //修改
            boolean res = addressService.updateByPrimaryKeySelective(address);
            String msg = res ? "修改成功！" : "修改失败";
            notification = new Notification(res, msg);
        } else {
            //添加
            address.setCreateTime(LocalDateTime.now());
            System.out.println(address);
            boolean res = addressService.insertSelective(address);
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
     * @param address
     * @return
     */
    @GetMapping("/changeState")
    public String changeState(Address address, RedirectAttributes redirectAttributes) {
        boolean res = false;

        Integer state = address.getState();
        Long id = address.getId();
        if (state == null && id == null) {
            throw new GlobalException(400, "执行修改状态时提交的参数不正确", "list");
        }

        //修改状态的值
        address.setState(state == 1 ? 0 : 1);
        address.setUpdateTime(LocalDateTime.now());
        res = addressService.updateByPrimaryKeySelective(address);
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
        boolean res = addressService.deleteByPrimaryKey(id);

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
        boolean res = addressService.batchDelete(ids);

        //返回列表页面时，对话框中显示的提示信息
        String msg = res ? "批量删除成功！" : "批量删除失败";
        Notification notification = new Notification(res, msg);
        redirectAttributes.addFlashAttribute("notification", notification);

        return "redirect:list";
    }

}
