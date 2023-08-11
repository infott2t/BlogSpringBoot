package com.example.demo.firstinstance.controller.firstinstanceurl.domain.customer;
import lombok.RequiredArgsConstructor;
// import Service, Entity, ApiDtoForm.
import com.example.demo.domain.customer.Customer;
import com.example.demo.domain.customer.CustomerApiDto;
import com.example.demo.domain.customer.CustomerSearchCondition;
import com.example.demo.domain.customer.CustomerService;
import com.example.demo.firstinstance.controller.firstinstanceurl.form.CustomerApiDtoForm;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.format.DateTimeFormatter;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Controller
public class InstanceUrlCustomerController {

    private final CustomerService customerService;

    @GetMapping("/administer/instanceurl/customer")
    public String index(Model model, CustomerSearchCondition condition,
                        @RequestParam(value="page", required=false) Integer page,
                        @PageableDefault(size= 10)Pageable pageable) throws Exception {

        Page<CustomerApiDto> boards = customerService.searchAllV2(condition, pageable);


        model.addAttribute("boards", boards);
        model.addAttribute("condition", condition);
        model.addAttribute("page", pageable.getPageNumber()+1); // 0부터 시작, +1이 필요.

        return "firstinstance/customer/index";
    }

    @GetMapping("/administer/instanceurl/customer/insert")
    public String insert(Model model, CustomerSearchCondition condition,
                         @RequestParam(value="page", required=false) Integer page,
                         @PageableDefault(size= 10)Pageable pageable) throws Exception{

        Page<CustomerApiDto> boards = customerService.searchAllV2(condition, pageable);


        model.addAttribute("boards", boards);
        model.addAttribute("condition", condition);
        model.addAttribute("page", pageable.getPageNumber()+1); // 0부터 시작, +1이 필요.

        CustomerApiDtoForm userForm = new CustomerApiDtoForm();
        model.addAttribute("userForm",userForm);

        return "firstinstance/customer/insert";
    }

    @PostMapping("/administer/instanceurl/customer/insert_2")
    public String insert_2(Model model, CustomerApiDtoForm userForm){

        Customer customer = null;

        try {
            customer = new Customer();
        DateTimeFormatter stdFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        customer.setName(userForm.getName());
        customer.setIsDel(userForm.getIsDel());
            customer.setModifiedDate(LocalDateTime.now());
            customer.setCreatedDate(LocalDateTime.now());
            customer.setIsDel("N");

            customerService.save(customer);

        } catch (Exception e) {
        return "redirect:/administer/instanceurl/customer/insert";
        }
        return "redirect:/administer/instanceurl/customer/insert";}

    @GetMapping("/administer/instanceurl/customer/delete")
    public String delete(@RequestParam(value="id")Long id, Model model) {

        Customer customer = null;
        try {
             customer = customerService.findById(id);
        } catch (Exception e) {
            return "redirect:/administer/instanceurl/customer/";
        }

        customer.setIsDel("Y");
        customerService.save(customer);


        return "redirect:/administer/instanceurl/customer/";
    }

    @GetMapping("/administer/instanceurl/customer/update")
    public String update(Model model, @RequestParam(value="id")Long id, CustomerSearchCondition condition,
                         @RequestParam(value="page", required=false) Integer page,
                         @PageableDefault(size= 10)Pageable pageable) throws Exception{
        Page<CustomerApiDto> boards = customerService.searchAllV2(condition, pageable);


        model.addAttribute("boards", boards);
        model.addAttribute("condition", condition);
        model.addAttribute("page", pageable.getPageNumber()+1); // 0부터 시작, +1이 필요.

        CustomerApiDtoForm userForm = new CustomerApiDtoForm();
        Customer customer = null;

        try {
            customer = customerService.findById(id);
        }catch(Exception e){
            return "redirect:/administer/instanceurl/customer/insert";
        }

        userForm.setId(customer.getId());
              DateTimeFormatter stdFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        userForm.setId(customer.getId());
        userForm.setName(customer.getName());
        userForm.setIsDel(customer.getIsDel());

        userForm.setCreatedDate(customer.getCreatedDate());
        userForm.setModifiedDate(customer.getModifiedDate());

        model.addAttribute("userForm",userForm);
        return "firstinstance/customer/update";
    }

    @PostMapping("/administer/instanceurl/customer/update_2")
    public String update_2(Model model, @RequestParam(value="id")Long id,CustomerApiDtoForm userForm, CustomerSearchCondition condition,
                           @RequestParam(value="page", required=false) Integer page,
                           Pageable pageable) throws Exception {


        Customer customer = null;
        try{
            customer = customerService.findById(id);
        }catch(Exception e){
            return "redirect:/administer/instanceurl/customer/insert";
        }

        try{
        DateTimeFormatter stdFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        customer.setName(userForm.getName());
        customer.setIsDel(userForm.getIsDel());
        customer.setModifiedDate(LocalDateTime.now());

        customerService.save(customer);
        }catch(Exception e){
            return "redirect:/administer/instanceurl/customer/insert";
        }





        return "redirect:/administer/instanceurl/customer/insert";
    }


}
