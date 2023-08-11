package com.example.demo.firstinstance.controller.firstinstanceurl.domain.categorystr;
import lombok.RequiredArgsConstructor;
// import Service, Entity, ApiDtoForm.
import com.example.demo.domain.categorystr.CategoryStr;
import com.example.demo.domain.categorystr.CategoryStrApiDto;
import com.example.demo.domain.categorystr.CategoryStrSearchCondition;
import com.example.demo.domain.categorystr.CategoryStrService;
import com.example.demo.firstinstance.controller.firstinstanceurl.form.CategoryStrApiDtoForm;

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
public class InstanceUrlCategoryStrController {

    private final CategoryStrService categoryStrService;

    @GetMapping("/administer/instanceurl/categoryStr")
    public String index(Model model, CategoryStrSearchCondition condition,
                        @RequestParam(value="page", required=false) Integer page,
                        @PageableDefault(size= 10)Pageable pageable) throws Exception {

        Page<CategoryStrApiDto> boards = categoryStrService.searchAllV2(condition, pageable);


        model.addAttribute("boards", boards);
        model.addAttribute("condition", condition);
        model.addAttribute("page", pageable.getPageNumber()+1); // 0부터 시작, +1이 필요.

        return "firstinstance/categoryStr/index";
    }

    @GetMapping("/administer/instanceurl/categoryStr/insert")
    public String insert(Model model, CategoryStrSearchCondition condition,
                         @RequestParam(value="page", required=false) Integer page,
                         @PageableDefault(size= 10)Pageable pageable) throws Exception{

        Page<CategoryStrApiDto> boards = categoryStrService.searchAllV2(condition, pageable);


        model.addAttribute("boards", boards);
        model.addAttribute("condition", condition);
        model.addAttribute("page", pageable.getPageNumber()+1); // 0부터 시작, +1이 필요.

        CategoryStrApiDtoForm userForm = new CategoryStrApiDtoForm();
        model.addAttribute("userForm",userForm);

        return "firstinstance/categoryStr/insert";
    }

    @PostMapping("/administer/instanceurl/categoryStr/insert_2")
    public String insert_2(Model model, CategoryStrApiDtoForm userForm){

        CategoryStr categoryStr = null;

        try {
            categoryStr = new CategoryStr();
        DateTimeFormatter stdFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        categoryStr.setName(userForm.getName());
        categoryStr.setRefId(userForm.getRefId());
        categoryStr.setIsDel(userForm.getIsDel());
            categoryStr.setModifiedDate(LocalDateTime.now());
            categoryStr.setCreatedDate(LocalDateTime.now());
            categoryStr.setIsDel("N");

            categoryStrService.save(categoryStr);

        } catch (Exception e) {
        return "redirect:/administer/instanceurl/categoryStr/insert";
        }
        return "redirect:/administer/instanceurl/categoryStr/insert";}

    @GetMapping("/administer/instanceurl/categoryStr/delete")
    public String delete(@RequestParam(value="id")Long id, Model model) {

        CategoryStr categoryStr = null;
        try {
             categoryStr = categoryStrService.findById(id);
        } catch (Exception e) {
            return "redirect:/administer/instanceurl/categoryStr/";
        }

        categoryStr.setIsDel("Y");
        categoryStrService.save(categoryStr);


        return "redirect:/administer/instanceurl/categoryStr/";
    }

    @GetMapping("/administer/instanceurl/categoryStr/update")
    public String update(Model model, @RequestParam(value="id")Long id, CategoryStrSearchCondition condition,
                         @RequestParam(value="page", required=false) Integer page,
                         @PageableDefault(size= 10)Pageable pageable) throws Exception{
        Page<CategoryStrApiDto> boards = categoryStrService.searchAllV2(condition, pageable);


        model.addAttribute("boards", boards);
        model.addAttribute("condition", condition);
        model.addAttribute("page", pageable.getPageNumber()+1); // 0부터 시작, +1이 필요.

        CategoryStrApiDtoForm userForm = new CategoryStrApiDtoForm();
        CategoryStr categoryStr = null;

        try {
            categoryStr = categoryStrService.findById(id);
        }catch(Exception e){
            return "redirect:/administer/instanceurl/categoryStr/insert";
        }

        userForm.setId(categoryStr.getId());
              DateTimeFormatter stdFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        userForm.setId(categoryStr.getId());
        userForm.setName(categoryStr.getName());
        userForm.setRefId(categoryStr.getRefId());
        userForm.setIsDel(categoryStr.getIsDel());

        userForm.setCreatedDate(categoryStr.getCreatedDate());
        userForm.setModifiedDate(categoryStr.getModifiedDate());

        model.addAttribute("userForm",userForm);
        return "firstinstance/categoryStr/update";
    }

    @PostMapping("/administer/instanceurl/categoryStr/update_2")
    public String update_2(Model model, @RequestParam(value="id")Long id,CategoryStrApiDtoForm userForm, CategoryStrSearchCondition condition,
                           @RequestParam(value="page", required=false) Integer page,
                           Pageable pageable) throws Exception {


        CategoryStr categoryStr = null;
        try{
            categoryStr = categoryStrService.findById(id);
        }catch(Exception e){
            return "redirect:/administer/instanceurl/categoryStr/insert";
        }

        try{
        DateTimeFormatter stdFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        categoryStr.setName(userForm.getName());
        categoryStr.setRefId(userForm.getRefId());
        categoryStr.setIsDel(userForm.getIsDel());
        categoryStr.setModifiedDate(LocalDateTime.now());

        categoryStrService.save(categoryStr);
        }catch(Exception e){
            return "redirect:/administer/instanceurl/categoryStr/insert";
        }





        return "redirect:/administer/instanceurl/categoryStr/insert";
    }


}
